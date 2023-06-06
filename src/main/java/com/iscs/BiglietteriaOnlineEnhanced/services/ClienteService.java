package com.iscs.BiglietteriaOnlineEnhanced.services;

import com.iscs.BiglietteriaOnlineEnhanced.dto.ClienteJson;
import com.iscs.BiglietteriaOnlineEnhanced.entities.Cliente;
import com.iscs.BiglietteriaOnlineEnhanced.exceptions.ClienteNotFoundException;
import com.iscs.BiglietteriaOnlineEnhanced.repositories.IClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final IClienteRepository clienteRepository;

    @Autowired
    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Restituisce un oggetto {@link ClienteJson} corrispondente al cliente con il codice cliente e la password specificati.
     *
     * @param codCliente il codice del cliente da cercare
     * @param password   la password del cliente da cercare
     * @return l'oggetto {@link ClienteJson} corrispondente al cliente trovato
     * @throws ClienteNotFoundException se il cliente con il codice e la password specificati non viene trovato
     */
    @Override
    public ClienteJson getClienteByIDePassword(String codCliente, String password) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(codCliente);
        return optionalCliente.map(cliente -> {
            ClienteJson clienteJson = new ClienteJson();
            BeanUtils.copyProperties(cliente, clienteJson);
            return clienteJson;
        }).orElseThrow(() -> new ClienteNotFoundException("Cliente " + codCliente + " non trovato"));
    }

}
