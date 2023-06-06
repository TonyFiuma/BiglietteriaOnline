package com.iscs.BiglietteriaOnlineEnhanced.repositories;

import com.iscs.BiglietteriaOnlineEnhanced.entities.Volo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoloRepository extends JpaRepository<Volo,Long> {


}
