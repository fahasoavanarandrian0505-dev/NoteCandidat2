package com.example.app.repository;

import com.example.app.entity.DemandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Integer> {
}