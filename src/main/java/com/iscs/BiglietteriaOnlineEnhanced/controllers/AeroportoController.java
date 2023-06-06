package com.iscs.BiglietteriaOnlineEnhanced.controllers;

import com.iscs.BiglietteriaOnlineEnhanced.dto.AeroportoJson;
import com.iscs.BiglietteriaOnlineEnhanced.services.IAeroportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biglietteria")
public class AeroportoController {


    private final IAeroportoService aeroportoService;

    @Autowired
    public AeroportoController(IAeroportoService aeroportoService) {
        this.aeroportoService = aeroportoService;
    }


    @GetMapping(value = "/aeroporto/{id}")
    public ResponseEntity<AeroportoJson> getAeroportoById(@PathVariable String id){
        AeroportoJson aeroportoJson = aeroportoService.getAnAeroporto(id);
        return ResponseEntity.ok(aeroportoJson);
    }

    @GetMapping(value = "/aeroporto/all")
    public ResponseEntity<List<AeroportoJson>> getAllAeroporti(){
        List<AeroportoJson> aeroportoJsonList = aeroportoService.getAllAeroporti();
        return ResponseEntity.of(Optional.ofNullable(aeroportoJsonList));
    }
}
