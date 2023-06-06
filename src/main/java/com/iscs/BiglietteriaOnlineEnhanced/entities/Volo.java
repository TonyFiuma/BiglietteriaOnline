package com.iscs.BiglietteriaOnlineEnhanced.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "volo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_volo", nullable = false)
    private Long idVolo;

    @Column(nullable = false)
    private String nome;

    @Column(name = "id_aeroporto_partenza", nullable = false)
    private String idAeroportoPartenza;

    @Column(name = "id_aeroporto_destinazione", nullable = false)
    private String idAeroportoDestinazione;

    @Column(name = "data_volo", nullable = false)
    private LocalDate dataVolo;

    @Column(name = "ora_partenza", nullable = false)
    private Time oraPartenza;

    @Column(name = "ora_arrivo", nullable = false)
    private Time oraArrivo;

    @Column(name = "posti_disponibili", nullable = false)
    private int postiDisponibili;

}
