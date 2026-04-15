package com.example.app.service;

import com.example.app.entity.Status;
import com.example.app.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository;
    
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }
    
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }
    
    public Optional<Status> getStatusById(Integer id) {
        return statusRepository.findById(id);
    }
    
    public Optional<Status> findByLibelle(String libelle) {
        return statusRepository.findByLibelle(libelle);
    }
    
    public Status updateStatus(Integer id, Status details) {
        Status status = statusRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Statut non trouvé"));
        status.setLibelle(details.getLibelle());
        return statusRepository.save(status);
    }
    
    public void deleteStatus(Integer id) {
        statusRepository.deleteById(id);
    }
}
