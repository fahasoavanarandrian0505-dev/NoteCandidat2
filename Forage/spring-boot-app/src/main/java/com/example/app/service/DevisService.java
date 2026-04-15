package com.example.app.service;

import com.example.app.entity.*;
import com.example.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DevisService {
    
    @Autowired private DevisRepository devisRepository;
    @Autowired private DemandeRepository demandeRepository;
    @Autowired private TypeDevisRepository typeDevisRepository;
    @Autowired private DetailsDevisRepository detailsDevisRepository;
    @Autowired private DemandeService demandeService;
    
    private static final BigDecimal SEUIL_REMISE = new BigDecimal("1000000");
    private static final BigDecimal TAUX_REMISE = new BigDecimal("0.9"); 
    
    
    public Devis createDevisWithDetails(Devis devis, Integer demandeId, Integer typeDevisId, List<DetailsDevis> details) {
        devis.setDemande(demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée")));
        devis.setTypeDevis(typeDevisRepository.findById(typeDevisId)
            .orElseThrow(() -> new RuntimeException("Type de devis non trouvé")));
        devis.setDateDevis(LocalDate.now());
        devis.setEstAccepte(false);
        
        Devis saved = devisRepository.save(devis);
        
        for (DetailsDevis detail : details) {
            BigDecimal prixOriginal = detail.getPrixUnitaire();
            BigDecimal prixFinal = appliquerRemise(prixOriginal);
            detail.setPrixUnitaire(prixFinal);
            detail.setDevis(saved);
            detailsDevisRepository.save(detail);
        }
        
        demandeService.changerStatutPourDevis(demandeId, devis.getTypeDevis().getLibelle());
        
        return saved;
    }
    
    
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }
    
    public Optional<Devis> getDevisById(Integer id) {
        return devisRepository.findById(id);
    }
    
    public List<DetailsDevis> getDetailsByDevis(Integer devisId) {
        return detailsDevisRepository.findByDevis_IdDevis(devisId);
    }
    
    
    public Devis updateDevis(Devis devis, Integer demandeId, Integer typeDevisId, List<DetailsDevis> details) {
        // Mettre à jour les informations du devis
        devis.setDemande(demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée")));
        devis.setTypeDevis(typeDevisRepository.findById(typeDevisId)
            .orElseThrow(() -> new RuntimeException("Type de devis non trouvé")));
        devis.setDateDevis(LocalDate.now());
        
        Devis updated = devisRepository.save(devis);
        
        for (DetailsDevis detail : details) {
            BigDecimal prixOriginal = detail.getPrixUnitaire();
            BigDecimal prixFinal = appliquerRemise(prixOriginal);
            detail.setPrixUnitaire(prixFinal);
            detail.setDevis(updated);
            detailsDevisRepository.save(detail);
        }
        
        return updated;
    }
    
    public void accepterDevis(Integer id) {
        Devis devis = devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        devis.setEstAccepte(true);
        devisRepository.save(devis);
        demandeService.ajouterStatut(devis.getDemande().getIdDemande(), "valide");
    }
    
    public void refuserDevis(Integer id) {
        Devis devis = devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        devis.setEstAccepte(false);
        devisRepository.save(devis);
        demandeService.ajouterStatut(devis.getDemande().getIdDemande(), "rejeter");
    }
    
    
    public void deleteDetailsByDevisId(Integer devisId) {
        List<DetailsDevis> details = detailsDevisRepository.findByDevis_IdDevis(devisId);
        detailsDevisRepository.deleteAll(details);
    }
    
    public void deleteDevis(Integer id) {
        deleteDetailsByDevisId(id);
        devisRepository.deleteById(id);
    }
    
    
    private BigDecimal appliquerRemise(BigDecimal prix) {
        if (prix.compareTo(SEUIL_REMISE) >= 0) {
            
            return prix.multiply(TAUX_REMISE);
        }
        return prix;
    }
    
    public BigDecimal getSommeGlobaleDevis() {
        List<Devis> allDevis = getAllDevis();
        return allDevis.stream()
            .map(Devis::calculerMontantTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}