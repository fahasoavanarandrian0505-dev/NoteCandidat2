package com.example.app.controller;

import com.example.app.entity.Operateur;
import com.example.app.service.OperateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/operateurs")
@RequiredArgsConstructor
public class OperateurController {

    private final OperateurService operateurService;

    @GetMapping
    public ResponseEntity<List<Operateur>> getAllOperateurs() {
        return ResponseEntity.ok(operateurService.getAllOperateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operateur> getOperateurById(@PathVariable Integer id) {
        return operateurService.getOperateurById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Operateur> createOperateur(@RequestBody Operateur operateur) {
        return ResponseEntity.ok(operateurService.saveOperateur(operateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operateur> updateOperateur(@PathVariable Integer id, @RequestBody Operateur operateur) {
        return operateurService.getOperateurById(id)
                .map(existing -> {
                    operateur.setIdOperateur(id);
                    return ResponseEntity.ok(operateurService.updateOperateur(operateur));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperateur(@PathVariable Integer id) {
        if (operateurService.getOperateurById(id).isPresent()) {
            operateurService.deleteOperateurById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
