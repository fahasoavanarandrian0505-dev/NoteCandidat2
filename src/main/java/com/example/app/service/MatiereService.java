package com.example.app.service;

import com.example.app.entity.Matiere;
import com.example.app.repository.MatiereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Optional<Matiere> getMatiereById(Integer id) {
        return matiereRepository.findById(id);
    }

    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public void deleteMatiereById(Integer id) {
        matiereRepository.deleteById(id);
    }

    public Matiere updateMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }
}
