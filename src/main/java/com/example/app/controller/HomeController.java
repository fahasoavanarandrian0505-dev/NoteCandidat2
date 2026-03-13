package com.example.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "accueil";
    }

    @RestController
    @Deprecated
    public static class ApiInfoController {
        @GetMapping("/api/info")
        public ResponseEntity<Map<String, String>> info() {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Bienvenue dans l'API NoteCandidat");
            response.put("version", "1.0.0");
            response.put("interface_web", "Allez à http://localhost:8081 pour l'interface");
            response.put("endpoints_api", "Consultez /api/candidats, /api/matieres, /api/notes, /api/calcul-note/finale?idCandidat=1&idMatiere=1, etc.");
            return ResponseEntity.ok(response);
        }
    }
}
