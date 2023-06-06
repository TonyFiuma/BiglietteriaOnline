package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.VoloJson;

import java.util.List;

public interface IVoloService {

    VoloJson getAVolo(Long id);

    List<VoloJson> getAllVoli();
}
