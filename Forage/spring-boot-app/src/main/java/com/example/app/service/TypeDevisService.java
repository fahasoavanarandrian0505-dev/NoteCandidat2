package com.example.app.service;

import com.example.app.entity.TypeDevis;
import com.example.app.repository.TypeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeDevisService {
    
    @Autowired
    private TypeDevisRepository typeDevisRepository;
    
    public List<TypeDevis> getAllTypeDevis() {
        return typeDevisRepository.findAll();
    }
    
    public Optional<TypeDevis> getTypeDevisById(Integer id) {
        return typeDevisRepository.findById(id);
    }
    
    public TypeDevis createTypeDevis(TypeDevis typeDevis) {
        return typeDevisRepository.save(typeDevis);
    }
}