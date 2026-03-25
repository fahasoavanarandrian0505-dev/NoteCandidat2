package com.example.app.controller;

import com.example.app.dto.DevisRequestDTO;
import com.example.app.entity.Devis;
import com.example.app.service.DevisService;
import com.example.app.service.DemandeService;
import com.example.app.service.TypeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/devis")
public class DevisController {
    
    @Autowired
    private DevisService devisService;
    
    @Autowired
    private DemandeService demandeService;
    
    @Autowired
    private TypeDevisService typeDevisService;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("devis", devisService.getAllDevis());
        return "devis/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());
        model.addAttribute("typeDevis", typeDevisService.getAllTypeDevis());
        return "devis/add";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute DevisRequestDTO request) {
        Devis devis = new Devis();
        devis.setDateDevis(LocalDate.now());
        devis.setEstAccepte(false);
        devis.setMontantTotal(request.getMontantTotal());
        
        devisService.createDevisWithDetails(devis, request.getDemandeId(), 
                                              request.getTypeDevisId(), 
                                              request.getDetails());
        return "redirect:/devis";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        devisService.deleteDevis(id);
        return "redirect:/devis";
    }
    
    @GetMapping("/accept/{id}")
    public String accept(@PathVariable Integer id) {
        devisService.acceptDevis(id);
        return "redirect:/devis";
    }
    
    @GetMapping("/refuse/{id}")
    public String refuse(@PathVariable Integer id) {
        devisService.refuseDevis(id);
        return "redirect:/devis";
    }
}