package com.iscs.BiglietteriaOnlineEnhanced.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "biglietti")
@Data
@NoArgsConstructor
public class Biglietto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod_prenotazione")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codPrenotazione;

    @Column(name = "cod_cliente")
    private String codCliente;

    @Column(name = "id_volo")
    private Long idVolo;

    @Column(name = "quantita_biglietti")
    private int quantitaBiglietti;

    @Column(name = "tipo_pagamento")
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    public Biglietto(String codCliente, Long idVolo, int quantitaBiglietti, TipoPagamento tipoPagamento) {
        this.codCliente = codCliente;
        this.idVolo = idVolo;
        this.quantitaBiglietti = quantitaBiglietti;
        this.tipoPagamento = tipoPagamento;
    }

    public enum TipoPagamento{
        MASTERCARD,
        VISA,
        PAYPAL
    }

}
