package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.VoloJson;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Volo;
import com.iscs.BiglietteriaOnlineEnhanced.exceptions.VoloNotFoundException;
import com.iscs.BiglietteriaOnlineEnhanced.repositories.IVoloRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoloService implements IVoloService{

    private final IVoloRepository voloRepository;

    public VoloService(IVoloRepository voloRepository) {
        this.voloRepository = voloRepository;
    }

    /**
     * Restituisce un oggetto {@link VoloJson} relativo a un volo specificato dall'ID.
     *
     * @param id l'ID del volo da cercare
     * @return l'oggetto {@link VoloJson} corrispondente al volo trovato
     * @throws VoloNotFoundException se il volo con l'ID specificato non viene trovato
     */
    @Override
    public VoloJson getAVolo(Long id) {
        Optional<Volo> optionalVolo = voloRepository.findById(id);
        return optionalVolo.map(volo -> {
            VoloJson voloJson = new VoloJson();
            BeanUtils.copyProperties(volo, voloJson);
            return voloJson;
        }).orElseThrow(() -> new VoloNotFoundException("Volo " + id + " non trovato"));
    }

    /**
     * Restituisce una lista di oggetti {@link VoloJson} rappresentanti tutti i voli disponibili.
     *
     * @return la lista di oggetti {@link VoloJson} dei voli trovati
     * @throws VoloNotFoundException se non ci sono voli disponibili
     */
    @Override
    public List<VoloJson> getAllVoli() {
        List<Volo> voloList = voloRepository.findAll();
        return Optional.of(voloList)
                .filter(list -> !voloList.isEmpty())
                .map(list -> list.stream()
                        .map(volo -> {
                            VoloJson voloJson = new VoloJson();
                            BeanUtils.copyProperties(volo, voloJson);
                            return voloJson;
                        })
                        .toList())
                .orElseThrow(() -> new VoloNotFoundException("Voli non trovati"));
    }

}
