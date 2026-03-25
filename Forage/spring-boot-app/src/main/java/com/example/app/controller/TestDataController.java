package com.example.app.controller;

import com.example.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDataController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping("/api/test-clients")
    public String testClients() {
        try {
            return "✅ Clients trouvés: " + clientService.getAllClients().size();
        } catch (Exception e) {
            return "❌ Erreur: " + e.getMessage();
        }
    }
}