package com.iscs.BiglietteriaOnlineEnhanced.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoloJson {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idVolo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idAeroportoPartenza;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idAeroportoDestinazione;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate dataVolo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Time oraPartenza;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Time oraArrivo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int postiDisponibili;
}
