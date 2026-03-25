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
    
    public Devis createDevis(Devis devis, Integer demandeId, Integer typeDevisId) {
        devis.setDemande(demandeRepository.findById(demandeId).orElseThrow());
        devis.setTypeDevis(typeDevisRepository.findById(typeDevisId).orElseThrow());
        devis.setEstAccepte(false);
        if (devis.getDateDevis() == null) devis.setDateDevis(LocalDate.now());
        
        Devis saved = devisRepository.save(devis);
        demandeService.ajouterStatut(demandeId, "Devis créé");
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
    
    public Devis acceptDevis(Integer id) {
        Devis devis = devisRepository.findById(id).orElseThrow();
        devis.setEstAccepte(true);
        demandeService.ajouterStatut(devis.getDemande().getIdDemande(), "Devis accepté");
        return devisRepository.save(devis);
    }
    
    public Devis refuseDevis(Integer id) {
        Devis devis = devisRepository.findById(id).orElseThrow();
        devis.setEstAccepte(false);
        demandeService.ajouterStatut(devis.getDemande().getIdDemande(), "Devis refusé");
        return devisRepository.save(devis);
    }
    
    public void deleteDevis(Integer id) {
        devisRepository.deleteById(id);
    }
    
    public DetailsDevis addDetailToDevis(Integer devisId, DetailsDevis detail) {
        detail.setDevis(devisRepository.findById(devisId).orElseThrow());
        DetailsDevis saved = detailsDevisRepository.save(detail);
        saved.getDevis().recalculerMontantTotal();
        devisRepository.save(saved.getDevis());
        return saved;
    }
    
    public void removeDetailFromDevis(Integer detailId) {
        DetailsDevis detail = detailsDevisRepository.findById(detailId).orElseThrow();
        Integer devisId = detail.getDevis().getIdDevis();
        detailsDevisRepository.delete(detail);
        
        Devis devis = devisRepository.findById(devisId).orElseThrow();
        devis.recalculerMontantTotal();
        devisRepository.save(devis);
    }
    
    public DetailsDevis updateDetail(Integer detailId, DetailsDevis details) {
        DetailsDevis detail = detailsDevisRepository.findById(detailId).orElseThrow();
        detail.setLibelle(details.getLibelle());
        detail.setPrixUnitaire(details.getPrixUnitaire());
        detail.setQuantite(details.getQuantite());
        
        DetailsDevis updated = detailsDevisRepository.save(detail);
        updated.getDevis().recalculerMontantTotal();
        devisRepository.save(updated.getDevis());
        return updated;
    }
    
    public BigDecimal calculerMontantTotalDevis(Integer devisId) {
        return devisRepository.findById(devisId).orElseThrow().calculerMontantTotal();
    }
}