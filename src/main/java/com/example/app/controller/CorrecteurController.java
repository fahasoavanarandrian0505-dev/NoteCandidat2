package com.example.app.controller;

import com.example.app.entity.Correcteur;
import com.example.app.service.CorrecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/correcteurs")
@RequiredArgsConstructor
public class CorrecteurController {

    private final CorrecteurService correcteurService;

    @GetMapping
    public ResponseEntity<List<Correcteur>> getAllCorrecteurs() {
        return ResponseEntity.ok(correcteurService.getAllCorrecteurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Correcteur> getCorrecteurById(@PathVariable Integer id) {
        return correcteurService.getCorrecteurById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Correcteur> createCorrecteur(@RequestBody Correcteur correcteur) {
        return ResponseEntity.ok(correcteurService.saveCorrecteur(correcteur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Correcteur> updateCorrecteur(@PathVariable Integer id, @RequestBody Correcteur correcteur) {
        return correcteurService.getCorrecteurById(id)
                .map(existing -> {
                    correcteur.setIdCorrecteur(id);
                    return ResponseEntity.ok(correcteurService.updateCorrecteur(correcteur));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorrecteur(@PathVariable Integer id) {
        if (correcteurService.getCorrecteurById(id).isPresent()) {
            correcteurService.deleteCorrecteurById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
