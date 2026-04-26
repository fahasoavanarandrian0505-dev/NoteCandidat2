package com.example.app.controller;

import com.example.app.entity.*;
import com.example.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/demandes")
public class DemandeController {
    
    @Autowired
    private DemandeService demandeService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private DemandeStatutsService demandeStatutsService;
    
    @Autowired
    private StatusService statusService;
    
    
    @GetMapping
    public String list(Model model, @RequestParam(required = false) String statut) {
        List<Demande> demandes;
        
        if (statut != null && !statut.isEmpty()) {
            // Filtrer les demandes par statut
            demandes = demandeService.getDemandesByStatut(statut);
            model.addAttribute("statutFiltre", statut);
        } else {
            demandes = demandeService.getAllDemandes();
        }
        
        model.addAttribute("demandes", demandes);
        return "demandes/list";
    }
    
    
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Integer id, Model model) {
        Demande demande = demandeService.getDemandeById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        List<DemandeStatus> historiqueStatuts = demandeStatutsService.getDemandeStatusByDemandeId(id);
        DemandeStatus dernierStatut = historiqueStatuts.isEmpty() ? null : historiqueStatuts.get(historiqueStatuts.size() - 1);
        
        model.addAttribute("demande", demande);
        model.addAttribute("historiqueStatuts", historiqueStatuts);
        model.addAttribute("dernierStatut", dernierStatut);
        return "demandes/details";
    }
    
    
    @GetMapping("/change-statut/{id}")
    public String showChangeStatutForm(@PathVariable Integer id, Model model) {
        Demande demande = demandeService.getDemandeById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        List<Status> allStatus = statusService.getAllStatus();
        
        model.addAttribute("demande", demande);
        model.addAttribute("allStatus", allStatus);
        return "demandes/change-statut";
    }
    
    @PostMapping("/change-statut")
    public String changeStatut(@RequestParam Integer demandeId, 
                              @RequestParam Integer statusId, 
                              @RequestParam String observation) {
        demandeService.changerStatut(demandeId, statusId, observation);
        return "redirect:/demandes/details/" + demandeId;
    }
    
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.getAllClients());
        return "demandes/add";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute Demande demande, @RequestParam Integer clientId) {
        demandeService.createDemande(demande, clientId);
        return "redirect:/demandes";
    }
    
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Demande demande = demandeService.getDemandeById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        model.addAttribute("demande", demande);
        model.addAttribute("clients", clientService.getAllClients());
        return "demandes/edit";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute Demande demande, @RequestParam Integer clientId) {
        demandeService.updateDemande(demande.getIdDemande(), demande, clientId);
        return "redirect:/demandes";
    }

    @PostMapping("/update-observation")
    public String updateObservation(@RequestParam Integer demandeId, @RequestParam String observation) {
        demandeService.updateLastStatutObservation(demandeId, observation);
        return "redirect:/demandes/details/" + demandeId;
    }
        
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        demandeService.deleteDemande(id);
        return "redirect:/demandes";
    }
}