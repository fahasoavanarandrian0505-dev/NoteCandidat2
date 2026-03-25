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
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du client: " + e.getMessage());
        }
    }
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }
    
    public Client updateClient(Integer id, Client clientDetails) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));
        client.setNom(clientDetails.getNom());
        client.setEmail(clientDetails.getEmail());
        return clientRepository.save(client);
    }
    
    @Transactional
    public void deleteClient(Integer id) {
        try {
            Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));
            
            clientRepository.delete(client);
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du client: " + e.getMessage());
        }
    }
}