package com.example.app.service;

import com.example.app.entity.DetailsDevis;
import com.example.app.entity.DemandeStatus;
import com.example.app.entity.Status;
import com.example.app.repository.ClientRepository;
import com.example.app.repository.DemandeStatusRepository;
import com.example.app.repository.DevisRepository;
import com.example.app.repository.DetailsDevisRepository;
import com.example.app.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DetailsDevisRepository detailsDevisRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private DemandeStatusRepository demandeStatusRepository;

    public long getNombreClients() {
        return clientRepository.count();
    }

    public long getNombreDevis() {
        return devisRepository.count();
    }

    public BigDecimal getChiffreAffaire() {
        List<DetailsDevis> allDetails = detailsDevisRepository.findAll();
        return allDetails.stream()
            .map(detail -> {
                BigDecimal prix = detail.getPrixUnitaire() != null ? detail.getPrixUnitaire() : BigDecimal.ZERO;
                Integer qte = detail.getQuantite() != null ? detail.getQuantite() : 0;
                return prix.multiply(BigDecimal.valueOf(qte));
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, Long> getStatistiquesStatuts() {
        List<DemandeStatus> allDemandeStatus = demandeStatusRepository.findAll();
        Map<String, Long> stats = new HashMap<>();
        Map<Integer, DemandeStatus> dernierStatutParDemande = new HashMap<>();
        
        for (DemandeStatus ds : allDemandeStatus) {
            Integer demandeId = ds.getDemande().getIdDemande();
            if (!dernierStatutParDemande.containsKey(demandeId) ||
                ds.getDateChangement().isAfter(dernierStatutParDemande.get(demandeId).getDateChangement())) {
                dernierStatutParDemande.put(demandeId, ds);
            }
        }
        
        for (DemandeStatus ds : dernierStatutParDemande.values()) {
            String libelle = ds.getStatus().getLibelle();
            stats.put(libelle, stats.getOrDefault(libelle, 0L) + 1);
        }
        
        List<Status> allStatus = statusRepository.findAll();
        for (Status status : allStatus) {
            if (!stats.containsKey(status.getLibelle())) {
                stats.put(status.getLibelle(), 0L);
            }
        }
        
        return stats;
    }
}