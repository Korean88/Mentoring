package com.epam.mentoring.service;

import com.epam.mentoring.model.Client;
import com.epam.mentoring.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Andrey on 10.04.2016.
 */

@Service(value = "clientService")
public class ClientServiceImpl implements GenericService<Client> {

//    @Resource(name = "clientRepo")
    private GenericRepository<Client> clientRepository;

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public void print(Integer id) {
        Client client = findById(id);
        System.out.println(client != null ? client : "Client with id " + id + " was not found");
    }

    @Override
    public Map<Integer, Client> getAll() {
        return clientRepository.getAll();
    }

    //Method populate (task4)
    @Autowired
    public void populate(GenericRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }
}
