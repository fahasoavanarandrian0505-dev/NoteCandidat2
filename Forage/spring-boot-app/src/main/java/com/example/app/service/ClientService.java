package com.example.app.service;

import com.example.app.entity.Client;
import com.example.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }
    
    public Client updateClient(Integer id, Client clientDetails) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        client.setNom(clientDetails.getNom());
        client.setEmail(clientDetails.getEmail());
        return clientRepository.save(client);
    }
    
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}