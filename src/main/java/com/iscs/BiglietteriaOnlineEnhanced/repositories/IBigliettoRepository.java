package com.iscs.BiglietteriaOnlineEnhanced.repositories;

import com.iscs.BiglietteriaOnlineEnhanced.entities.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBigliettoRepository extends JpaRepository<Biglietto,Integer> {

    Biglietto getBigliettiByCodCliente(String codCliente);

}
