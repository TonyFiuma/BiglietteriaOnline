package com.iscs.BiglietteriaOnlineEnhanced.controllers;

import com.iscs.BiglietteriaOnlineEnhanced.dto.BigliettoJson;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Biglietto;
import com.iscs.BiglietteriaOnlineEnhanced.services.IBigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biglietteria")
public class BigliettoController {

    private final IBigliettoService bigliettoService;

    @Autowired
    public BigliettoController(IBigliettoService bigliettoService) {
        this.bigliettoService = bigliettoService;
    }





    @GetMapping(value = "/biglietto/{codCliente}")
    public ResponseEntity<BigliettoJson> getBigliettoByCodCliente(@PathVariable String codCliente) {
        BigliettoJson bigliettoJson = bigliettoService.getBigliettiByCodCliente(codCliente);
        return ResponseEntity.ok(bigliettoJson);
    }


    @GetMapping(value = "/biglietti/all")
    public ResponseEntity<List<BigliettoJson>> getAllBiglietti(){
        List<BigliettoJson> bigliettoJsonList = bigliettoService.getAllBiglietti();
        return ResponseEntity.ok(bigliettoJsonList);
    }

    //Nota per il front end il codice cliente lo rigirerai tu perchè l'acquisto avverà dopo la registrazione quindi la mancanza
    // Del cliente non è gestita
    @PostMapping(value = "/biglietti/{idVolo}/{codCliente}/{nBiglietti}/{tipoPagamento}")
    ResponseEntity<Biglietto> addBiglietto(@PathVariable Long idVolo, @PathVariable String codCliente,
                                               @PathVariable int nBiglietti, @PathVariable Biglietto.TipoPagamento tipoPagamento) {
        Biglietto biglietto = bigliettoService.addBiglietti(idVolo, codCliente, nBiglietti, tipoPagamento);
        return new ResponseEntity<>(biglietto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/biglietti/update/{codPrenotazione}")
    public ResponseEntity<BigliettoJson> updateBiglietto(@PathVariable int codPrenotazione,@RequestParam int numeroBiglietti){
        BigliettoJson bigliettoJson = bigliettoService.getBigliettoByCodPrenotazione(codPrenotazione);
        bigliettoJson.setQuantitaBiglietti(numeroBiglietti);
        bigliettoService.updateBiglietti(bigliettoJson);
        return ResponseEntity.ok(bigliettoJson);
    }

    @DeleteMapping(value = "/delete/{codPrenotazione}")
    public ResponseEntity<Void> deleteBigliettoById(@PathVariable int codPrenotazione){
        bigliettoService.deleteBiglietto(codPrenotazione);
        return ResponseEntity.noContent().build();
    }
}
