package com.iscs.BiglietteriaOnlineEnhanced.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteJson {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String codCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cognome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate dataNascita;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}
