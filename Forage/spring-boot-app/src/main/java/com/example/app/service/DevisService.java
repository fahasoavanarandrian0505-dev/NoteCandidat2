package com.example.app.service;

import com.example.app.entity.Devis;
import com.example.app.entity.Demande;
import com.example.app.entity.TypeDevis;
import com.example.app.repository.DevisRepository;
import com.example.app.repository.DemandeRepository;
import com.example.app.repository.TypeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DevisService {
    
    @Autowired
    private DevisRepository devisRepository;
    
    @Autowired
    private DemandeRepository demandeRepository;
    
    @Autowired
    private TypeDevisRepository typeDevisRepository;
    
    public Devis createDevis(Devis devis, Integer demandeId, Integer typeDevisId) {
        Demande demande = demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        
        TypeDevis typeDevis = typeDevisRepository.findById(typeDevisId)
            .orElseThrow(() -> new RuntimeException("Type de devis non trouvé"));
        
        devis.setDemande(demande);
        devis.setTypeDevis(typeDevis);
        devis.setEstAccepte(false);
        
        if (devis.getDateDevis() == null) {
            devis.setDateDevis(LocalDate.now());
        }
        
        return devisRepository.save(devis);
    }
    
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }
    
    public Optional<Devis> getDevisById(Integer id) {
        return devisRepository.findById(id);
    }
    
    public List<Devis> getDevisByDemande(Integer demandeId) {
        Demande demande = demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        return demande.getDevis();
    }
    
    public Devis acceptDevis(Integer id) {
        Devis devis = devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        
        devis.setEstAccepte(true);
        return devisRepository.save(devis);
    }
    
    public Devis updateDevis(Integer id, Devis devisDetails) {
        Devis devis = devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        
        devis.setDateDevis(devisDetails.getDateDevis());
        devis.setMontantTotal(devisDetails.getMontantTotal());
        
        return devisRepository.save(devis);
    }
    
    public void deleteDevis(Integer id) {
        devisRepository.deleteById(id);
    }
}