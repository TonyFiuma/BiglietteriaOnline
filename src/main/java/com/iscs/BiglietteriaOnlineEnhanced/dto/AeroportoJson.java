package com.iscs.BiglietteriaOnlineEnhanced.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AeroportoJson {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String idAeroporto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String citta;
}
