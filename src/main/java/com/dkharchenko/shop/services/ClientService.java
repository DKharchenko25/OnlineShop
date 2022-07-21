package com.dkharchenko.shop.services;

import com.dkharchenko.shop.dtos.ClientDTO;
import com.dkharchenko.shop.entities.Client;
import com.dkharchenko.shop.exceptions.ClientNotFoundException;
import com.dkharchenko.shop.repositories.ClientRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public Integer registerClient(ClientDTO dto) {
        return clientRepository.save(new Client(dto.getName(), dto.getPhoneNumber())).getId();
    }

    public Integer deleteClientById(Integer id) throws ClientNotFoundException {
        if (clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException("Client with ID #" + id + " is not found");
        }
        return id;
    }

    public Client findById(Integer id) throws ClientNotFoundException {
        Client client;
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            client = optionalClient.get();
        } else {
            throw new ClientNotFoundException("Client with ID #" + id + " is not found");
        }
        return client;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

}
