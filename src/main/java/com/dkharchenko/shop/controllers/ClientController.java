package com.dkharchenko.shop.controllers;

import com.dkharchenko.shop.dtos.ClientDTO;
import com.dkharchenko.shop.entities.Client;
import com.dkharchenko.shop.exceptions.ClientNotFoundException;
import com.dkharchenko.shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public ResponseEntity<Integer> addNewClient(@RequestBody ClientDTO dto) {
        Integer id = clientService.registerClient(dto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public ResponseEntity<Integer> deleteClient(@RequestParam Integer id) throws ClientNotFoundException {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Client getClientById(@PathVariable(name = "id") Integer id) throws ClientNotFoundException {
        return clientService.findById(id);
    }

}
