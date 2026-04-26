package com.example.app.controller;

import com.example.app.entity.*;
import com.example.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        List<Devis> devis = devisService.getAllDevis();
        BigDecimal totalGlobal = devisService.getSommeGlobaleDevis();
        model.addAttribute("devis", devis);
        model.addAttribute("totalGlobal", totalGlobal);
        return "devis/list";
    }
    
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());
        model.addAttribute("typeDevis", typeDevisService.getAllTypeDevis());
        return "devis/add";
    }
    
    @PostMapping("/add")
    public String add(@RequestParam Integer demandeId, 
                      @RequestParam Integer typeDevisId,
                      @RequestParam("libelle") List<String> libelles,
                      @RequestParam("prixUnitaire") List<BigDecimal> prixUnitaires,
                      @RequestParam("quantite") List<Integer> quantites) {
        
        List<DetailsDevis> details = new ArrayList<>();
        
        for (int i = 0; i < libelles.size(); i++) {
            if (libelles.get(i) != null && !libelles.get(i).trim().isEmpty()) {
                DetailsDevis detail = new DetailsDevis();
                detail.setLibelle(libelles.get(i));
                detail.setPrixUnitaire(prixUnitaires.get(i));
                detail.setQuantite(quantites.get(i));
                details.add(detail);
            }
        }
        
        if (details.isEmpty()) {
            return "redirect:/devis/add?error=noDetails";
        }
        
        Devis devis = new Devis();
        devisService.createDevisWithDetails(devis, demandeId, typeDevisId, details);
        
        return "redirect:/devis";
    }
    
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Devis devis = devisService.getDevisById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        List<DetailsDevis> details = devisService.getDetailsByDevis(id);
        
        model.addAttribute("devis", devis);
        model.addAttribute("details", details);
        model.addAttribute("demandes", demandeService.getAllDemandes());
        model.addAttribute("typeDevis", typeDevisService.getAllTypeDevis());
        
        return "devis/edit";
    }
    
    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                         @RequestParam Integer demandeId,
                         @RequestParam Integer typeDevisId,
                         @RequestParam("libelle") List<String> libelles,
                         @RequestParam("prixUnitaire") List<BigDecimal> prixUnitaires,
                         @RequestParam("quantite") List<Integer> quantites) {
        
        devisService.deleteDetailsByDevisId(id);
        
        List<DetailsDevis> details = new ArrayList<>();
        for (int i = 0; i < libelles.size(); i++) {
            if (libelles.get(i) != null && !libelles.get(i).trim().isEmpty()) {
                DetailsDevis detail = new DetailsDevis();
                detail.setLibelle(libelles.get(i));
                detail.setPrixUnitaire(prixUnitaires.get(i));
                detail.setQuantite(quantites.get(i));
                details.add(detail);
            }
        }
        
        Devis devis = devisService.getDevisById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        devisService.updateDevis(devis, demandeId, typeDevisId, details);
        
        return "redirect:/devis";
    }
    
    
    // @GetMapping("/accept/{id}")
    // public String accept(@PathVariable Integer id) {
    //     devisService.accepterDevis(id);
    //     return "redirect:/devis";
    // }
    
    // @GetMapping("/refuse/{id}")
    // public String refuse(@PathVariable Integer id) {
    //     devisService.refuserDevis(id);
    //     return "redirect:/devis";
    // }
    
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        devisService.deleteDevis(id);
        return "redirect:/devis";
    }
}