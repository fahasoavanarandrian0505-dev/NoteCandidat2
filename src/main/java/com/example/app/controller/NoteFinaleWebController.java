package com.example.app.controller;

import com.example.app.dto.NoteFinaleResponse;
import com.example.app.service.CalculNoteFinaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NoteFinaleWebController {

    private final CalculNoteFinaleService calculNoteFinaleService;

    /**
     * Affiche le formulaire de saisie
     */
    @GetMapping("/calculer")
    public String afficherFormulaire() {
        return "formulaire";
    }

    /**
     * Traite la soumission du formulaire et affiche le résultat
     */
    @PostMapping("/calculer-note")
    public String calculerNote(
            @RequestParam Integer idCandidat,
            @RequestParam Integer idMatiere,
            Model model) {
        
        try {
            // Calculer la note finale
            NoteFinaleResponse response = calculNoteFinaleService.calculerNoteFinale(idCandidat, idMatiere);
            
            // Ajouter la réponse au modèle
            model.addAttribute("response", response);
            
            return "resultat";
        } catch (RuntimeException e) {
            // En cas d'erreur, retourner au formulaire avec message d'erreur
            model.addAttribute("error", "❌ Erreur: " + e.getMessage());
            return "formulaire";
        }
    }
}
