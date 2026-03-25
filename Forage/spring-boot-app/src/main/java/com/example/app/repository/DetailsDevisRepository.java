package com.example.app.repository;

import com.example.app.entity.DetailsDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailsDevisRepository extends JpaRepository<DetailsDevis, Integer> {
    List<DetailsDevis> findByDevis_IdDevis(Integer idDevis);
}