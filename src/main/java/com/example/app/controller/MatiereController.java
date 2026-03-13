package com.example.app.controller;

import com.example.app.entity.Matiere;
import com.example.app.service.MatiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matieres")
@RequiredArgsConstructor
public class MatiereController {

    private final MatiereService matiereService;

    @GetMapping
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        return ResponseEntity.ok(matiereService.getAllMatieres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Integer id) {
        return matiereService.getMatiereById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matiere> createMatiere(@RequestBody Matiere matiere) {
        return ResponseEntity.ok(matiereService.saveMatiere(matiere));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable Integer id, @RequestBody Matiere matiere) {
        return matiereService.getMatiereById(id)
                .map(existing -> {
                    matiere.setIdMatiere(id);
                    return ResponseEntity.ok(matiereService.updateMatiere(matiere));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Integer id) {
        if (matiereService.getMatiereById(id).isPresent()) {
            matiereService.deleteMatiereById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
