package com.iscs.BiglietteriaOnlineEnhanced.repositories;

import com.iscs.BiglietteriaOnlineEnhanced.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente,String> {

}
