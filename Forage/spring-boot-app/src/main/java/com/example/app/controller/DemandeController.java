package com.example.app.controller;

import com.example.app.dto.DemandeDTO;
import com.example.app.entity.Demande;
import com.example.app.service.DemandeService;
import com.example.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/demandes")
public class DemandeController {
    
    @Autowired
    private DemandeService demandeService;
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping
    public String list(Model model) {
        List<DemandeDTO> demandeDTOs = new ArrayList<>();
        for (Demande demande : demandeService.getAllDemandes()) {
            DemandeDTO dto = new DemandeDTO();
            dto.setIdDemande(demande.getIdDemande());
            dto.setClientNom(demande.getClient() != null ? demande.getClient().getNom() : "Client inconnu");
            dto.setLieu(demande.getLieu());
            dto.setDistrict(demande.getDistrict());
            dto.setDateDemande(demande.getDateDemande());
            demandeDTOs.add(dto);
        }
        model.addAttribute("demandes", demandeDTOs);
        return "demandes/list";
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