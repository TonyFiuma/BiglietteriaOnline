package com.iscs.BiglietteriaOnlineEnhanced.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod_cliente", nullable = false)
    private String codCliente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(unique = true)
    private String email;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Column(nullable = false)
    private String password;
}
