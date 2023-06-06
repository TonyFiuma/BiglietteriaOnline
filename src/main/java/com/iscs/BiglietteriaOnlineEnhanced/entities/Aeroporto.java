package com.iscs.BiglietteriaOnlineEnhanced.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "aeroporto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aeroporto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_aeroporto", nullable = false)
    private String idAeroporto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String citta;

}
