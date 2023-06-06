package com.iscs.BiglietteriaOnlineEnhanced.controllers;

import com.iscs.BiglietteriaOnlineEnhanced.dto.VoloJson;
import com.iscs.BiglietteriaOnlineEnhanced.services.IVoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("biglietteria")
public class VoloController {

    private final IVoloService voloService;

    @Autowired
    public VoloController(IVoloService voloService) {
        this.voloService = voloService;
    }

    @GetMapping(value = "/volo/{id}")
    public ResponseEntity<VoloJson> getVoloById(@PathVariable Long id){
        VoloJson voloJson = voloService.getAVolo(id);
        return ResponseEntity.ok(voloJson);
    }

    @GetMapping(value = "allVoli")
    public ResponseEntity<List<VoloJson>> getAllVoli(){
        List<VoloJson> voloList = voloService.getAllVoli();
        return ResponseEntity.ok(voloList);
    }
}
