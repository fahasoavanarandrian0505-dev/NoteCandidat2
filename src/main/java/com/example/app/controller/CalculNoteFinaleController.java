package com.example.app.controller;

import com.example.app.dto.NoteFinaleResponse;
import com.example.app.service.CalculNoteFinaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calcul-note")
@RequiredArgsConstructor
public class CalculNoteFinaleController {

    private final CalculNoteFinaleService calculNoteFinaleService;

    /**
     * Calcule la note finale d'un candidat pour une matière
     * 
     * @param idCandidat ID du candidat
     * @param idMatiere ID de la matière
     * @return NoteFinaleResponse avec tous les détails du calcul
     * 
     * Exemple: GET /api/calcul-note/finale?idCandidat=1&idMatiere=1
     */
    @GetMapping("/finale")
    public ResponseEntity<NoteFinaleResponse> calculerNoteFinal(
            @RequestParam Integer idCandidat,
            @RequestParam Integer idMatiere) {
        
        try {
            NoteFinaleResponse response = calculNoteFinaleService.calculerNoteFinale(idCandidat, idMatiere);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
