package com.example.app.repository;

import com.example.app.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Integer> {
}
