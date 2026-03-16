package com.example.app.service;

import com.example.app.entity.Correcteur;
import com.example.app.repository.CorrecteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorrecteurService {

    private final CorrecteurRepository correcteurRepository;

    public List<Correcteur> getAllCorrecteurs() {
        return correcteurRepository.findAll();
    }

    public Optional<Correcteur> getCorrecteurById(Integer id) {
        return correcteurRepository.findById(id);
    }

    public Correcteur saveCorrecteur(Correcteur correcteur) {
        return correcteurRepository.save(correcteur);
    }

    public void deleteCorrecteurById(Integer id) {
        correcteurRepository.deleteById(id);
    }

    public Correcteur updateCorrecteur(Correcteur correcteur) {
        return correcteurRepository.save(correcteur);
    }
}
