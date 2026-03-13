package com.example.app.service;

import com.example.app.entity.Resolution;
import com.example.app.repository.ResolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResolutionService {

    private final ResolutionRepository resolutionRepository;

    public List<Resolution> getAllResolutions() {
        return resolutionRepository.findAll();
    }

    public Optional<Resolution> getResolutionById(Integer id) {
        return resolutionRepository.findById(id);
    }

    public Resolution saveResolution(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    public void deleteResolutionById(Integer id) {
        resolutionRepository.deleteById(id);
    }

    public Resolution updateResolution(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }
}
