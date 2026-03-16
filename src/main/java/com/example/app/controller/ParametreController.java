package com.example.app.controller;

import com.example.app.entity.Parametre;
import com.example.app.service.ParametreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parametres")
@RequiredArgsConstructor
public class ParametreController {

    private final ParametreService parametreService;

    @GetMapping
    public ResponseEntity<List<Parametre>> getAllParametres() {
        return ResponseEntity.ok(parametreService.getAllParametres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parametre> getParametreById(@PathVariable Integer id) {
        return parametreService.getParametreById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parametre> createParametre(@RequestBody Parametre parametre) {
        return ResponseEntity.ok(parametreService.saveParametre(parametre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parametre> updateParametre(@PathVariable Integer id, @RequestBody Parametre parametre) {
        return parametreService.getParametreById(id)
                .map(existing -> {
                    parametre.setIdParametre(id);
                    return ResponseEntity.ok(parametreService.updateParametre(parametre));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParametre(@PathVariable Integer id) {
        if (parametreService.getParametreById(id).isPresent()) {
            parametreService.deleteParametreById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
