package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.ClienteJson;

public interface IClienteService {
    ClienteJson getClienteByIDePassword(String codCliente, String Password);
}
