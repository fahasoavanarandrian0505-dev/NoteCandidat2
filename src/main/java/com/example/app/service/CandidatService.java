package com.example.app.service;

import com.example.app.entity.Candidat;
import com.example.app.repository.CandidatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatService {

    private final CandidatRepository candidatRepository;

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public Optional<Candidat> getCandidatById(Integer id) {
        return candidatRepository.findById(id);
    }

    public Candidat saveCandidatService(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public void deleteCandidatById(Integer id) {
        candidatRepository.deleteById(id);
    }

    public Candidat updateCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }
}
