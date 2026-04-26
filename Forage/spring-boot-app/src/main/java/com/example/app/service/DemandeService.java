package com.example.app.service;

import com.example.app.entity.*;
import com.example.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DemandeService {
    
    @Autowired private DemandeRepository demandeRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private DemandeStatusRepository demandeStatusRepository;
    @Autowired private StatusRepository statusRepository;
    
    
    public Demande createDemande(Demande demande, Integer clientId) {
        demande.setClient(clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client non trouvé")));
        
        if (demande.getDateDemande() == null) {
            demande.setDateDemande(LocalDate.now());
        }
        
        Demande saved = demandeRepository.save(demande);
        
        ajouterLigneHistorique(saved.getIdDemande(), "créé", "Demande créée");
        
        return saved;
    }
    
    // ==================== READ ====================
    
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }
    
    public Optional<Demande> getDemandeById(Integer id) {
        return demandeRepository.findById(id);
    }
    
    // ==================== UPDATE ====================
    
    public Demande updateDemande(Integer id, Demande details, Integer clientId) {
        Demande demande = demandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        
        demande.setClient(clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client non trouvé")));
        demande.setLieu(details.getLieu());
        demande.setDistrict(details.getDistrict());
        demande.setDateDemande(details.getDateDemande());
        
        Demande updated = demandeRepository.save(demande);
        
        List<DemandeStatus> historique = demandeStatusRepository.findByDemande_IdDemande(id);
        if (!historique.isEmpty()) {
            DemandeStatus dernierStatut = historique.get(historique.size() - 1);
            dernierStatut.setDateChangement(LocalDateTime.now());
            demandeStatusRepository.save(dernierStatut);
        }
        
        return updated;
    }
    
    
    private void ajouterLigneHistorique(Integer demandeId, String libelleStatut, String observation) {
        Demande demande = demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        
        Status status = statusRepository.findByLibelle(libelleStatut)
            .orElseThrow(() -> new RuntimeException("Statut '" + libelleStatut + "' non trouvé"));
        
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setDateChangement(LocalDateTime.now());
        ds.setObservation(observation);
        demandeStatusRepository.save(ds);
    }
    
    public void changerStatut(Integer demandeId, Integer statusId, String observation) {
        Status status = statusRepository.findById(statusId)
            .orElseThrow(() -> new RuntimeException("Statut non trouvé"));
        
        ajouterLigneHistorique(demandeId, status.getLibelle(), observation);
    }
    
    public void changerStatutPourDevis(Integer demandeId, String typeDevis) {
        String libelleStatut = typeDevis.equalsIgnoreCase("Etude") ? "devis etude créé" : "devis forage créé";
        ajouterLigneHistorique(demandeId, libelleStatut, "Devis " + typeDevis + " créé");
    }

public void updateLastStatutObservation(Integer demandeId, String observation) {
    List<DemandeStatus> historique = demandeStatusRepository.findByDemande_IdDemande(demandeId);
    if (!historique.isEmpty()) {
        DemandeStatus dernierStatut = historique.get(historique.size() - 1);
        dernierStatut.setObservation(observation);
        demandeStatusRepository.save(dernierStatut);
    }
}
    
    
    public void deleteDemande(Integer id) {
        demandeRepository.deleteById(id);
    }

    public List<Demande> getDemandesByStatut(String libelleStatut) {
    List<Demande> toutesDemandes = demandeRepository.findAll();
    List<Demande> demandesFiltrees = new ArrayList<>();
    
    for (Demande demande : toutesDemandes) {
        List<DemandeStatus> historique = demandeStatusRepository.findByDemande_IdDemande(demande.getIdDemande());
        if (!historique.isEmpty()) {
            DemandeStatus dernier = historique.get(historique.size() - 1);
            if (dernier.getStatus().getLibelle().equals(libelleStatut)) {
                demandesFiltrees.add(demande);
            }
        }
    }
    
    return demandesFiltrees;
}
}