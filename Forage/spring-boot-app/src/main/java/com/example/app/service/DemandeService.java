package com.example.app.service;

import com.example.app.entity.Client;
import com.example.app.entity.Demande;
import com.example.app.repository.DemandeRepository;
import com.example.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DemandeService {
    
    @Autowired
    private DemandeRepository demandeRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    public Demande createDemande(Demande demande, Integer clientId) {
        try {
            System.out.println("=== Création d'une demande ===");
            System.out.println("Client ID: " + clientId);
            
            Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + clientId));
            
            demande.setClient(client);
            
            if (demande.getDateDemande() == null) {
                demande.setDateDemande(LocalDate.now());
            }
            
            Demande savedDemande = demandeRepository.save(demande);
            System.out.println("Demande créée avec succès, ID: " + savedDemande.getIdDemande());
            return savedDemande;
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la demande: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la création de la demande: " + e.getMessage(), e);
        }
    }
    
    @Transactional(readOnly = true)
    public List<Demande> getAllDemandes() {
        List<Demande> demandes = demandeRepository.findAll();
        for (Demande demande : demandes) {
            if (demande.getClient() != null) {
                demande.getClient().getNom();
                demande.getClient().getEmail();
            }
        }
        return demandes;
    }
    
    @Transactional(readOnly = true)
    public Optional<Demande> getDemandeById(Integer id) {
        Optional<Demande> demandeOpt = demandeRepository.findById(id);
        if (demandeOpt.isPresent()) {
            Demande demande = demandeOpt.get();
            if (demande.getClient() != null) {
                demande.getClient().getNom();
            }
        }
        return demandeOpt;
    }
    
    public Demande updateDemande(Integer id, Demande demandeDetails) {
        Demande demande = demandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée avec l'id: " + id));
        demande.setLieu(demandeDetails.getLieu());
        demande.setDistrict(demandeDetails.getDistrict());
        demande.setDateDemande(demandeDetails.getDateDemande());
        return demandeRepository.save(demande);
    }
    
    public Demande updateDemandeWithClient(Integer id, Demande demandeDetails, Integer clientId) {
        Demande demande = demandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée avec l'id: " + id));
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + clientId));
        demande.setClient(client);
        demande.setLieu(demandeDetails.getLieu());
        demande.setDistrict(demandeDetails.getDistrict());
        demande.setDateDemande(demandeDetails.getDateDemande());
        return demandeRepository.save(demande);
    }
    
    public void deleteDemande(Integer id) {
        demandeRepository.deleteById(id);
    }
}