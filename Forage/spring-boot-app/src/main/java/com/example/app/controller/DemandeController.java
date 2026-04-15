package com.example.app.controller;

import com.example.app.entity.Demande;
import com.example.app.entity.Client;
import com.example.app.entity.DemandeStatus;
import com.example.app.entity.Status;
import com.example.app.service.DemandeService;
import com.example.app.service.ClientService;
import com.example.app.service.DemandeStatutsService;
import com.example.app.service.StatusService;
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
    public String list(Model model) {
        List<Demande> demandes = demandeService.getAllDemandes();
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
        demandeService.ajouterStatutAvecObservation(demandeId, statusId, observation);
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
        model.addAttribute("demande", demandeService.getDemandeById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée")));
        model.addAttribute("clients", clientService.getAllClients());
        return "demandes/edit";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute Demande demande, @RequestParam Integer clientId) {
        demandeService.updateDemandeWithClient(demande.getIdDemande(), demande, clientId);
        return "redirect:/demandes";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        demandeService.deleteDemande(id);
        return "redirect:/demandes";
    }
}