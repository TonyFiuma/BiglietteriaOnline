package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.AeroportoJson;

import java.util.List;

public interface IAeroportoService {

AeroportoJson getAnAeroporto(String id);

List<AeroportoJson> getAllAeroporti();
}
