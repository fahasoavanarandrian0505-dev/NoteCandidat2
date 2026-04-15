package com.example.app.service;

import com.example.app.entity.*;
import com.example.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DemandeStatutsService {
    
    @Autowired private DemandeStatusRepository demandeStatusRepository;
    
   
    
    public List<DemandeStatus> getAllDemandeStatus() {
        return demandeStatusRepository.findAll();
    }
    
    public List<DemandeStatus> getDemandeStatusByDemandeId(Integer demandeId) {
        return demandeStatusRepository.findByDemande_IdDemande(demandeId);
    }
    

}