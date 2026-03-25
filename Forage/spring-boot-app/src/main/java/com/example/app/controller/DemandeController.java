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
        try {
            List<Demande> demandes = demandeService.getAllDemandes();
            List<DemandeDTO> demandeDTOs = new ArrayList<>();
            
            for (Demande demande : demandes) {
                DemandeDTO dto = new DemandeDTO();
                dto.setIdDemande(demande.getIdDemande());
                
                // Gestion du client
                if (demande.getClient() != null) {
                    dto.setClientNom(demande.getClient().getNom());
                    dto.setClientId(demande.getClient().getIdClient());
                } else {
                    dto.setClientNom("Client inconnu");
                    dto.setClientId(null);
                }
                
                dto.setLieu(demande.getLieu());
                dto.setDistrict(demande.getDistrict());
                dto.setDateDemande(demande.getDateDemande());
                
                demandeDTOs.add(dto);
            }
            
            model.addAttribute("demandes", demandeDTOs);
            
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des demandes: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Erreur lors du chargement des demandes");
            model.addAttribute("demandes", new ArrayList<>());
        }
        
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
        try {
            demandeService.createDemande(demande, clientId);
            return "redirect:/demandes";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la création de la demande: " + e.getMessage());
        }
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
        demandeService.updateDemandeWithClient(demande.getIdDemande(), demande, clientId);
        return "redirect:/demandes";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        demandeService.deleteDemande(id);
        return "redirect:/demandes";
    }
}