package com.example.app.service;

import com.example.app.dto.DevisRequestDTO;
import com.example.app.entity.Devis;
import com.example.app.entity.Demande;
import com.example.app.entity.TypeDevis;
import com.example.app.entity.DetailsDevis;
import com.example.app.repository.DevisRepository;
import com.example.app.repository.DemandeRepository;
import com.example.app.repository.TypeDevisRepository;
import com.example.app.repository.DetailsDevisRepository;
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
    
    @Autowired
    private DevisRepository devisRepository;
    
    @Autowired
    private DemandeRepository demandeRepository;
    
    @Autowired
    private TypeDevisRepository typeDevisRepository;
    
    @Autowired
    private DetailsDevisRepository detailsDevisRepository;
    
    @Autowired
    private DemandeService demandeService;
    
    public Devis createDevisWithDetails(Devis devis, Integer demandeId, Integer typeDevisId, 
                                         List<DevisRequestDTO.DetailDTO> detailsDTO) {
        Demande demande = demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        TypeDevis typeDevis = typeDevisRepository.findById(typeDevisId)
            .orElseThrow(() -> new RuntimeException("Type de devis non trouvé"));
        
        devis.setDemande(demande);
        devis.setTypeDevis(typeDevis);
        if (devis.getDateDevis() == null) {
            devis.setDateDevis(LocalDate.now());
        }
        
        Devis savedDevis = devisRepository.save(devis);
        
        BigDecimal totalGeneral = BigDecimal.ZERO;
        
        if (detailsDTO != null) {
            for (DevisRequestDTO.DetailDTO detailDTO : detailsDTO) {
                if (detailDTO.getLibelle() != null && !detailDTO.getLibelle().isEmpty()) {
                    DetailsDevis detail = new DetailsDevis();
                    detail.setDevis(savedDevis);
                    detail.setLibelle(detailDTO.getLibelle());
                    detail.setPrixUnitaire(detailDTO.getPrixUnitaire());
                    detail.setQuantite(detailDTO.getQuantite());
                    detailsDevisRepository.save(detail);
                    
                    BigDecimal sousTotal = detailDTO.getPrixUnitaire()
                        .multiply(BigDecimal.valueOf(detailDTO.getQuantite()));
                    totalGeneral = totalGeneral.add(sousTotal);
                }
            }
        }
        
        savedDevis.setMontantTotal(totalGeneral);
        devisRepository.save(savedDevis);
        
        demandeService.ajouterStatut(demandeId, "Devis créé");
        
        return savedDevis;
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
        Devis devis = devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        devis.setEstAccepte(true);
        Devis savedDevis = devisRepository.save(devis);
        demandeService.ajouterStatut(devis.getDemande().getIdDemande(), "Devis accepté");
        return savedDevis;
    }
    
    public Devis refuseDevis(Integer id) {
        Devis devis = devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        devis.setEstAccepte(false);
        Devis savedDevis = devisRepository.save(devis);
        demandeService.ajouterStatut(devis.getDemande().getIdDemande(), "Devis refusé");
        return savedDevis;
    }
    
    public void deleteDevis(Integer id) {
        devisRepository.deleteById(id);
    }
}