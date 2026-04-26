package com.example.app.controller;

import com.example.app.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/")
    public String home(Model model) {
        long nbClients = dashboardService.getNombreClients();
        long nbDevis = dashboardService.getNombreDevis();
        BigDecimal chiffreAffaire = dashboardService.getChiffreAffaire();
        Map<String, Long> statistiquesStatuts = dashboardService.getStatistiquesStatuts();

        model.addAttribute("nbClients", nbClients);
        model.addAttribute("nbDevis", nbDevis);
        model.addAttribute("chiffreAffaire", chiffreAffaire);
        model.addAttribute("statistiquesStatuts", statistiquesStatuts);

        return "index";
    }
}