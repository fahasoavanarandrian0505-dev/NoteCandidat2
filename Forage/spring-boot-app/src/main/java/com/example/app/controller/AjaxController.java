package com.example.app.controller;

import com.example.app.entity.Demande;
import com.example.app.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
    
    @Autowired
    private DemandeService demandeService;
    
    @GetMapping("/demande/{id}")
    public ResponseEntity<Demande> getDemande(@PathVariable Integer id) {
        Demande demande = demandeService.getDemandeById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        return ResponseEntity.ok(demande);
    }
}
