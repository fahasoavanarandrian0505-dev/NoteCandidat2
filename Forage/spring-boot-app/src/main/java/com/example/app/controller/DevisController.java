package com.example.app.controller;

import com.example.app.entity.Devis;
import com.example.app.entity.DetailsDevis;
import com.example.app.service.DevisService;
import com.example.app.service.DemandeService;
import com.example.app.service.TypeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
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
        model.addAttribute("devis", devisService.getAllDevis());
        return "devis/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("devis", new Devis());
        model.addAttribute("demandes", demandeService.getAllDemandes());
        model.addAttribute("typeDevis", typeDevisService.getAllTypeDevis());
        return "devis/add";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute Devis devis, @RequestParam Integer demandeId, @RequestParam Integer typeDevisId) {
        devisService.createDevis(devis, demandeId, typeDevisId);
        return "redirect:/devis";
    }
    
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        Devis devis = devisService.getDevisById(id).orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        model.addAttribute("devis", devis);
        model.addAttribute("details", devisService.getDetailsByDevis(id));
        model.addAttribute("montantTotal", devis.calculerMontantTotal());
        return "devis/view";
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
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        devisService.deleteDevis(id);
        return "redirect:/devis";
    }
    
    @GetMapping("/detail/add/{devisId}")
    public String showAddDetailForm(@PathVariable Integer devisId, Model model) {
        model.addAttribute("devis", devisService.getDevisById(devisId).orElseThrow());
        model.addAttribute("detail", new DetailsDevis());
        return "devis/add-detail";
    }
    
    @PostMapping("/detail/add")
    public String addDetail(@RequestParam Integer devisId, @RequestParam String libelle, 
                            @RequestParam BigDecimal prixUnitaire, @RequestParam Integer quantite) {
        DetailsDevis detail = new DetailsDevis();
        detail.setLibelle(libelle);
        detail.setPrixUnitaire(prixUnitaire);
        detail.setQuantite(quantite);
        devisService.addDetailToDevis(devisId, detail);
        return "redirect:/devis/view/" + devisId;
    }
    
    @GetMapping("/detail/delete/{detailId}")
    public String deleteDetail(@PathVariable Integer detailId) {
        List<DetailsDevis> details = devisService.getDetailsByDevis(detailId);
        Integer devisId = details.stream().findFirst().map(d -> d.getDevis().getIdDevis()).orElse(null);
        devisService.removeDetailFromDevis(detailId);
        return "redirect:/devis/view/" + (devisId != null ? devisId : "");
    }
}