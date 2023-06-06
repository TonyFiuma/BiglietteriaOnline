package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.BigliettoJson;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Biglietto;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Volo;
import com.iscs.BiglietteriaOnlineEnhanced.exceptions.BigliettoNotFoundExeption;
import com.iscs.BiglietteriaOnlineEnhanced.exceptions.TooManyBigliettiException;
import com.iscs.BiglietteriaOnlineEnhanced.exceptions.VoloNotFoundException;
import com.iscs.BiglietteriaOnlineEnhanced.repositories.IBigliettoRepository;
import com.iscs.BiglietteriaOnlineEnhanced.repositories.IVoloRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BigliettoService implements IBigliettoService {

    private final IBigliettoRepository bigliettoRepository;

    private final IVoloRepository voloRepository;

    @Autowired
    public BigliettoService(IBigliettoRepository bigliettoRepository, IVoloRepository voloRepository) {
        this.bigliettoRepository = bigliettoRepository;
        this.voloRepository = voloRepository;
    }


    /**
     * Restituisce un oggetto {@link BigliettoJson} corrispondente al biglietto con il codice di prenotazione specificato.
     *
     * @param id il codice di prenotazione del biglietto da cercare
     * @return l'oggetto {@link BigliettoJson} corrispondente al biglietto trovato
     * @throws BigliettoNotFoundExeption se il biglietto con il codice di prenotazione specificato non viene trovato
     */
    @Override
    public BigliettoJson getBigliettoByCodPrenotazione(Integer id) {
        Optional<Biglietto> optionalBigliettoJson = bigliettoRepository.findById(id);
        return optionalBigliettoJson.map(biglietto -> {
                    BigliettoJson bigliettoJson = new BigliettoJson();
                    BeanUtils.copyProperties(biglietto, bigliettoJson);
                    return bigliettoJson;
                })
                .orElseThrow(() -> new BigliettoNotFoundExeption("Il codice di prenotazione " + id + " non c'è"));
    }

    /**
     * Restituisce un oggetto {@link BigliettoJson} corrispondente al biglietto associato al cliente con il codice cliente specificato.
     *
     * @param codCliente il codice del cliente per cui cercare il biglietto
     * @return l'oggetto {@link BigliettoJson} corrispondente al biglietto trovato
     * @throws BigliettoNotFoundExeption se il cliente con il codice specificato non ha biglietti associati
     */
    @Override
    public BigliettoJson getBigliettiByCodCliente(String codCliente) {
        Optional<Biglietto> optionalBigliettoJson = Optional.ofNullable(bigliettoRepository.getBigliettiByCodCliente(codCliente));
        return optionalBigliettoJson.map(biglietto -> {
                    BigliettoJson bigliettoJson = new BigliettoJson();
                    BeanUtils.copyProperties(biglietto, bigliettoJson);
                    return bigliettoJson;
                })
                .orElseThrow(() -> new BigliettoNotFoundExeption("Il cliente " + codCliente + " non ha biglietti"));
    }

    /**
     * Restituisce una lista di oggetti {@link BigliettoJson} rappresentanti tutti i biglietti disponibili.
     *
     * @return la lista di oggetti {@link BigliettoJson} dei biglietti trovati
     * @throws BigliettoNotFoundExeption se non ci sono biglietti acquistati
     */
    @Override
    public List<BigliettoJson> getAllBiglietti() {
        List<Biglietto> bigliettoJsonList = bigliettoRepository.findAll();
        return Optional.of(bigliettoJsonList)
                .filter(list -> !bigliettoJsonList.isEmpty())
                .map(list -> list.stream()
                        .map(biglietto -> {
                            BigliettoJson bigliettoJson = new BigliettoJson();
                            BeanUtils.copyProperties(biglietto, bigliettoJson);
                            return bigliettoJson;
                        })
                        .toList())
                .orElseThrow(() -> new BigliettoNotFoundExeption("Non ci sono biglietti acquistati"));
    }

    /**
     * Aggiunge un nuovo biglietto associato al volo specificato, al cliente con il codice cliente specificato,
     * con il numero di biglietti e il tipo di pagamento forniti.
     *
     * @param id           l'ID del volo a cui associare il biglietto
     * @param codCliente   il codice del cliente a cui associare il biglietto
     * @param numBiglietti il numero di biglietti da acquistare
     * @param tipoPagamento il tipo di pagamento del biglietto
     * @return il biglietto appena aggiunto
     * @throws VoloNotFoundException        se il volo con l'ID specificato non viene trovato
     * @throws TooManyBigliettiException    se il numero di biglietti richiesto supera i posti disponibili nel volo
     */
    @Override
    public Biglietto addBiglietti(Long id, String codCliente, int numBiglietti, Biglietto.TipoPagamento tipoPagamento) {
        Optional<Volo> optionalVolo = voloRepository.findById(id);
        if (optionalVolo.isEmpty()) {
            throw new VoloNotFoundException("Volo " + id + " non presente");
        }
        Volo volo = optionalVolo.get();
        if (volo.getPostiDisponibili() - numBiglietti < 0) {
            throw new TooManyBigliettiException("I biglietti disponibili sono " + volo.getPostiDisponibili() +
                    " e tu hai richiesto " + numBiglietti);
        }
        Biglietto biglietto = new Biglietto(codCliente, id, numBiglietti, tipoPagamento);
        return bigliettoRepository.save(biglietto);
    }

    /**
     * Aggiorna le informazioni di un biglietto esistente con i dati forniti in un oggetto {@link BigliettoJson}.
     *
     * @param bigliettoJson l'oggetto {@link BigliettoJson} contenente i nuovi dati del biglietto
     * @throws BigliettoNotFoundExeption se il biglietto con il codice di prenotazione specificato non viene trovato
     */
    @Override
    public void updateBiglietti(BigliettoJson bigliettoJson) {
        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(bigliettoJson.getCodPrenotazione());
        optionalBiglietto.ifPresentOrElse(biglietto -> {
            BeanUtils.copyProperties(bigliettoJson, biglietto);
            bigliettoRepository.save(biglietto);
            bigliettoJson.setIdVolo(Math.toIntExact(optionalBiglietto.get().getIdVolo()));
        }, () -> {
            throw new BigliettoNotFoundExeption("Il biglietto con codice di prenotazione " + bigliettoJson.getCodPrenotazione() + " non è presente");
        });
    }

    /**
     * Elimina un biglietto con il codice di prenotazione specificato.
     *
     * @param codPrenotazione il codice di prenotazione del biglietto da eliminare
     * @throws BigliettoNotFoundExeption se il biglietto con il codice specificato non esiste
     */
    @Override
    public void deleteBiglietto(int codPrenotazione) {
        if (bigliettoRepository.existsById(codPrenotazione)) {
            bigliettoRepository.deleteById(codPrenotazione);
        } else {
            throw new BigliettoNotFoundExeption("Il biglietto con codice " + codPrenotazione + " non esiste");
        }
    }


}
