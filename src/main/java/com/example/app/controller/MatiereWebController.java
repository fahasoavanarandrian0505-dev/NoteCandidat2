package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.app.entity.Matiere;
import com.example.app.service.MatiereService;
import java.util.List;

@Controller
@RequestMapping("/matieres")
public class MatiereWebController {

    private final MatiereService matiereService;

    public MatiereWebController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping("")
    public String listMatieres(Model model) {
        List<Matiere> matieres = matiereService.getAllMatieres();
        model.addAttribute("matieres", matieres);
        return "matieres";
    }

    @PostMapping("/creer")
    public String createMatiere(@RequestParam String nomMatiere) {
        Matiere matiere = new Matiere();
        matiere.setNomMatiere(nomMatiere);
        matiereService.saveMatiere(matiere);
        return "redirect:/matieres";
    }

    @GetMapping("/nouveau")
    public String showNewMatiereForm() {
        return "matiere-form";
    }

    @GetMapping("/editer/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Matiere matiere = matiereService.getMatiereById(id).orElse(null);
        if (matiere != null) {
            model.addAttribute("matiere", matiere);
            return "matiere-form";
        }
        return "redirect:/matieres";
    }

    @PostMapping("/mettre-a-jour/{id}")
    public String updateMatiere(@PathVariable Integer id, @RequestParam String nomMatiere) {
        Matiere matiere = matiereService.getMatiereById(id).orElse(null);
        if (matiere != null) {
            matiere.setNomMatiere(nomMatiere);
            matiereService.updateMatiere(matiere);
        }
        return "redirect:/matieres";
    }

    @PostMapping("/supprimer/{id}")
    public String deleteMatiere(@PathVariable Integer id) {
        matiereService.deleteMatiereById(id);
        return "redirect:/matieres";
    }
}
