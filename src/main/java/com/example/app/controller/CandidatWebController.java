package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.app.entity.Candidat;
import com.example.app.service.CandidatService;
import java.util.List;

@Controller
@RequestMapping("/candidats")
public class CandidatWebController {

    private final CandidatService candidatService;

    public CandidatWebController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @GetMapping("")
    public String listCandidats(Model model) {
        List<Candidat> candidats = candidatService.getAllCandidats();
        model.addAttribute("candidats", candidats);
        return "candidats";
    }

    @PostMapping("/creer")
    public String createCandidat(@RequestParam String nom) {
        Candidat candidat = new Candidat();
        candidat.setNom(nom);
        candidatService.saveCandidatService(candidat);
        return "redirect:/candidats";
    }

    @GetMapping("/nouveau")
    public String showNewCandidatForm() {
        return "candidat-form";
    }

    @GetMapping("/editer/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Candidat candidat = candidatService.getCandidatById(id).orElse(null);
        if (candidat != null) {
            model.addAttribute("candidat", candidat);
            return "candidat-form";
        }
        return "redirect:/candidats";
    }

    @PostMapping("/mettre-a-jour/{id}")
    public String updateCandidat(@PathVariable Integer id, @RequestParam String nom) {
        Candidat candidat = candidatService.getCandidatById(id).orElse(null);
        if (candidat != null) {
            candidat.setNom(nom);
            candidatService.updateCandidat(candidat);
        }
        return "redirect:/candidats";
    }

    @PostMapping("/supprimer/{id}")
    public String deleteCandidat(@PathVariable Integer id) {
        candidatService.deleteCandidatById(id);
        return "redirect:/candidats";
    }
}
