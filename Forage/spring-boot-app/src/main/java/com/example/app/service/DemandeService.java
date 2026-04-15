package com.example.app.service;

import com.example.app.entity.*;
import com.example.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional; 
import java.util.List;

@Service
@Transactional
public class DemandeService {
    
    @Autowired private DemandeRepository demandeRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private DemandeStatusRepository demandeStatusRepository;
    @Autowired private StatusRepository statusRepository;
    
    public Demande createDemande(Demande demande, Integer clientId) {
        demande.setClient(clientRepository.findById(clientId).orElseThrow());
        if (demande.getDateDemande() == null) demande.setDateDemande(LocalDate.now());
        
        Demande saved = demandeRepository.save(demande);
        
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(saved);
        ds.setStatus(statusRepository.findByLibelle("créé").orElseThrow());
        ds.setDateChangement(LocalDateTime.now());
        ds.setObservation("Demande créée automatiquement");
        demandeStatusRepository.save(ds);
        
        return saved;
    }
    
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }
    
    public Optional<Demande> getDemandeById(Integer id) {
        return demandeRepository.findById(id);
    }
    
    public void ajouterStatut(Integer demandeId, String libelle) {
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demandeRepository.findById(demandeId).orElseThrow());
        ds.setStatus(statusRepository.findByLibelle(libelle).orElseThrow());
        ds.setDateChangement(LocalDateTime.now());
        ds.setObservation("Changement de statut");
        demandeStatusRepository.save(ds);
    }
    
    public void ajouterStatutAvecObservation(Integer demandeId, String libelle, String observation) {
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demandeRepository.findById(demandeId).orElseThrow());
        ds.setStatus(statusRepository.findByLibelle(libelle).orElseThrow());
        ds.setDateChangement(LocalDateTime.now());
        ds.setObservation(observation);
        demandeStatusRepository.save(ds);
    }
    
    public void changerStatutPourDevis(Integer demandeId, String typeDevis) {
        String statutLibelle = typeDevis.equalsIgnoreCase("Etude") ? "devis etude créé" : "devis forage créé";
        ajouterStatutAvecObservation(demandeId, statutLibelle, "Devis " + typeDevis + " créé");
    }
    
    public Demande updateDemandeWithClient(Integer id, Demande details, Integer clientId) {
        Demande demande = demandeRepository.findById(id).orElseThrow();
        demande.setClient(clientRepository.findById(clientId).orElseThrow());
        demande.setLieu(details.getLieu());
        demande.setDistrict(details.getDistrict());
        demande.setDateDemande(details.getDateDemande());
        return demandeRepository.save(demande);
    }
    
    public void deleteDemande(Integer id) {
        demandeRepository.deleteById(id);
    }

    public void ajouterStatutAvecObservation(Integer demandeId, Integer statusId, String observation) {
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demandeRepository.findById(demandeId).orElseThrow());
        ds.setStatus(statusRepository.findById(statusId).orElseThrow());
        ds.setDateChangement(LocalDateTime.now());
        ds.setObservation(observation);
        demandeStatusRepository.save(ds);
    }


}