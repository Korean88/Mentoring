package com.epam.mentoring.repository;

import com.epam.mentoring.model.Client;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Andrey on 04.04.2016.
 */

@Repository(value = "clientRepo") //Repository annotation (task3)
public class ClientRepositoryImpl implements GenericRepository<Client> {

    @Resource(name = "clientResource") //Resource annotation (task4)
    private Map<Integer, Client> clientResource;

    public void printAllClients() {
        clientResource.entrySet().stream().forEach(
                c -> System.out.println(c.getKey() + ": " + c.getValue().toString()));
    }

    @Override
    public Client findById(Integer id) {
        return clientResource.get(id);
    }

    @Override
    public Map<Integer, Client> getAll() {
        return clientResource;
    }
}
