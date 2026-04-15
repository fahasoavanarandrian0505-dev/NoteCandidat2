package com.example.app.controller;

import com.example.app.entity.DemandeStatus;
import com.example.app.service.DemandeStatutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/demande-statuts")
public class DemandeStatutController {
    
    @Autowired
    private DemandeStatutsService demandeStatutsService;
    
    @GetMapping
    public String listAll(Model model) {
        List<DemandeStatus> allDemandeStatus = demandeStatutsService.getAllDemandeStatus();
        model.addAttribute("demandeStatusList", allDemandeStatus);
        return "demande-statuts/list";
    }
    
    @GetMapping("/demande/{demandeId}")
    public String listByDemande(@PathVariable Integer demandeId, Model model) {
        List<DemandeStatus> demandeStatus = demandeStatutsService.getDemandeStatusByDemandeId(demandeId);
        model.addAttribute("demandeStatusList", demandeStatus);
        model.addAttribute("demandeId", demandeId);
        return "demande-statuts/list";
    }
}
