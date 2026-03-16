package com.example.app.controller;

import com.example.app.entity.Candidat;
import com.example.app.service.CandidatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidats")
@RequiredArgsConstructor
public class CandidatController {

    private final CandidatService candidatService;

    @GetMapping
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        return ResponseEntity.ok(candidatService.getAllCandidats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable Integer id) {
        return candidatService.getCandidatById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        return ResponseEntity.ok(candidatService.saveCandidatService(candidat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable Integer id, @RequestBody Candidat candidat) {
        return candidatService.getCandidatById(id)
                .map(existing -> {
                    candidat.setIdCandidat(id);
                    return ResponseEntity.ok(candidatService.updateCandidat(candidat));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable Integer id) {
        if (candidatService.getCandidatById(id).isPresent()) {
            candidatService.deleteCandidatById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
