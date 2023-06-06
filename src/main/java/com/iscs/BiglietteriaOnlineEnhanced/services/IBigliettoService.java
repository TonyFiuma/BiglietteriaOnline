package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.BigliettoJson;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Biglietto;

import java.util.List;

public interface IBigliettoService {

    BigliettoJson getBigliettoByCodPrenotazione(Integer id);

    BigliettoJson getBigliettiByCodCliente(String cod_cliente);
    List<BigliettoJson> getAllBiglietti();


    Biglietto addBiglietti(Long id, String codCliente, int numBiglietti, Biglietto.TipoPagamento tipoPagamento);

    void updateBiglietti(BigliettoJson bigliettoJson);

    void deleteBiglietto(int codPrenotazione);
}
