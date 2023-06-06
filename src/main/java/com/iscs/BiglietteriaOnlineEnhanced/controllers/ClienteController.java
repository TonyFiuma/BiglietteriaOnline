package com.iscs.BiglietteriaOnlineEnhanced.controllers;

import com.iscs.BiglietteriaOnlineEnhanced.dto.ClienteJson;
import com.iscs.BiglietteriaOnlineEnhanced.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("biglietteria")
public class ClienteController {

    private final IClienteService clienteService;

    @Autowired
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/cliente/{id}/{password}")
    public ResponseEntity<ClienteJson> getCliente(@PathVariable String id, @PathVariable String password){
    ClienteJson clienteJson = clienteService.getClienteByIDePassword(id,password);
    return ResponseEntity.ok(clienteJson);
    }
}
