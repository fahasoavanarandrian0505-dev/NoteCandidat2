package com.example.app.service;

import com.example.app.entity.Parametre;
import com.example.app.repository.ParametreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParametreService {

    private final ParametreRepository parametreRepository;

    public List<Parametre> getAllParametres() {
        return parametreRepository.findAll();
    }

    public Optional<Parametre> getParametreById(Integer id) {
        return parametreRepository.findById(id);
    }

    public Parametre saveParametre(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    public void deleteParametreById(Integer id) {
        parametreRepository.deleteById(id);
    }

    public Parametre updateParametre(Parametre parametre) {
        return parametreRepository.save(parametre);
    }
}
