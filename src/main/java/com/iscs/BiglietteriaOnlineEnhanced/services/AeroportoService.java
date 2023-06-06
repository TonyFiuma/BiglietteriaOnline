package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.AeroportoJson;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Aeroporto;
import com.iscs.BiglietteriaOnlineEnhanced.exceptions.AeroportoNotFoundException;
import com.iscs.BiglietteriaOnlineEnhanced.repositories.IAeroportoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportoService implements IAeroportoService{

    private final IAeroportoRepository aeroportoRepository;


    @Autowired
    public AeroportoService(IAeroportoRepository aeroportoRepository) {
        this.aeroportoRepository = aeroportoRepository;
    }

    /**
     * Restituisce un oggetto {@link AeroportoJson} corrispondente all'aeroporto con l'ID specificato.
     *
     * @param id l'ID dell'aeroporto da cercare
     * @return l'oggetto {@link AeroportoJson} corrispondente all'aeroporto trovato
     * @throws AeroportoNotFoundException se l'aeroporto con l'ID specificato non viene trovato
     */
    @Override
    public AeroportoJson getAnAeroporto(String id) {
        Optional<Aeroporto> aeroportoJsonOptional = aeroportoRepository.findById(id);
        return aeroportoJsonOptional.map(aeroporto -> {
                    AeroportoJson aeroportoJson = new AeroportoJson();
                    BeanUtils.copyProperties(aeroporto, aeroportoJson);
                    return aeroportoJson;
                })
                .orElseThrow(() -> new AeroportoNotFoundException("Aeroporto " + id + " non trovato"));
    }

    /**
     * Restituisce una lista di oggetti {@link AeroportoJson} rappresentanti tutti gli aeroporti disponibili.
     *
     * @return la lista di oggetti {@link AeroportoJson} degli aeroporti trovati
     * @throws AeroportoNotFoundException se non ci sono aeroporti disponibili
     */
    @Override
    public List<AeroportoJson> getAllAeroporti() {
        List<Aeroporto> aeroportoList = aeroportoRepository.findAll();
        return Optional.of(aeroportoList)
                .filter(list -> !aeroportoList.isEmpty())
                .map(list -> list.parallelStream()
                        .map(aeroporto -> {
                            AeroportoJson aeroportoJson = new AeroportoJson();
                            BeanUtils.copyProperties(aeroporto, aeroportoJson);
                            return aeroportoJson;
                        })
                        .toList())
                .orElseThrow(() -> new AeroportoNotFoundException("Aeroporti non trovati"));
    }

}
