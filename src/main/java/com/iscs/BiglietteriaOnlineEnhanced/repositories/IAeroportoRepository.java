package com.iscs.BiglietteriaOnlineEnhanced.repositories;

import com.iscs.BiglietteriaOnlineEnhanced.entities.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAeroportoRepository extends JpaRepository<Aeroporto,String> {
}
