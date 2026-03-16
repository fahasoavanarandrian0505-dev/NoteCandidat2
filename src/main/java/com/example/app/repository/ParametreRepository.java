package com.example.app.repository;

import com.example.app.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Integer> {
    // Requêtes personnalisées peuvent être ajoutées ici
}
