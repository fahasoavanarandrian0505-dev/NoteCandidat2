package com.example.app.controller;

import com.example.app.entity.Resolution;
import com.example.app.service.ResolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resolutions")
@RequiredArgsConstructor
public class ResolutionController {

    private final ResolutionService resolutionService;

    @GetMapping
    public ResponseEntity<List<Resolution>> getAllResolutions() {
        return ResponseEntity.ok(resolutionService.getAllResolutions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resolution> getResolutionById(@PathVariable Integer id) {
        return resolutionService.getResolutionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resolution> createResolution(@RequestBody Resolution resolution) {
        return ResponseEntity.ok(resolutionService.saveResolution(resolution));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resolution> updateResolution(@PathVariable Integer id, @RequestBody Resolution resolution) {
        return resolutionService.getResolutionById(id)
                .map(existing -> {
                    resolution.setIdResolution(id);
                    return ResponseEntity.ok(resolutionService.updateResolution(resolution));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResolution(@PathVariable Integer id) {
        if (resolutionService.getResolutionById(id).isPresent()) {
            resolutionService.deleteResolutionById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
