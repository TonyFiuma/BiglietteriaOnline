package com.iscs.BiglietteriaOnlineEnhanced.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Biglietto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigliettoJson {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer codPrenotazione;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String codCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int idVolo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int quantitaBiglietti;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Biglietto.TipoPagamento tipoPagamento;




}
