package com.example.app.service;

import com.example.app.entity.Operateur;
import com.example.app.repository.OperateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperateurService {

    private final OperateurRepository operateurRepository;

    public List<Operateur> getAllOperateurs() {
        return operateurRepository.findAll();
    }

    public Optional<Operateur> getOperateurById(Integer id) {
        return operateurRepository.findById(id);
    }

    public Operateur saveOperateur(Operateur operateur) {
        return operateurRepository.save(operateur);
    }

    public void deleteOperateurById(Integer id) {
        operateurRepository.deleteById(id);
    }

    public Operateur updateOperateur(Operateur operateur) {
        return operateurRepository.save(operateur);
    }
}
