package com.example.app.service;

import com.example.app.dto.NoteFinaleResponse;
import com.example.app.entity.*;
import com.example.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculNoteFinaleService {

    private final NoteRepository noteRepository;
    private final ParametreRepository parametreRepository;
    private final CandidatRepository candidatRepository;
    private final MatiereRepository matiereRepository;
    private final OperateurRepository operateurRepository;
    private final ResolutionRepository resolutionRepository;

    /**
     * Calcule la note finale d'un candidat pour une matière donnée
     * selon les paramètres définis
     */
    public NoteFinaleResponse calculerNoteFinale(Integer idCandidat, Integer idMatiere) {
        
        NoteFinaleResponse response = new NoteFinaleResponse();
        
        // Récupérer le candidat et la matière
        Candidat candidat = candidatRepository.findById(idCandidat)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé: " + idCandidat));
        
        Matiere matiere = matiereRepository.findById(idMatiere)
                .orElseThrow(() -> new RuntimeException("Matière non trouvée: " + idMatiere));
        
        // Définir les infos de base
        response.setIdCandidat(idCandidat);
        response.setNomCandidat(candidat.getNom());
        response.setIdMatiere(idMatiere);
        response.setNomMatiere(matiere.getNomMatiere());
        
        // Récupérer toutes les notes du candidat pour cette matière
        List<Double> notes = noteRepository.findAll().stream()
                .filter(n -> n.getCandidat().getIdCandidat().equals(idCandidat) && 
                           n.getMatiere().getIdMatiere().equals(idMatiere))
                .map(Note::getNote)
                .collect(Collectors.toList());
        
        if (notes.isEmpty()) {
            throw new RuntimeException("Aucune note trouvée pour le candidat " + idCandidat + 
                                     " en matière " + idMatiere);
        }
        
        // Calculer min et max
        Double noteMinimale = notes.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        Double noteMaximale = notes.stream().mapToDouble(Double::doubleValue).max().orElse(0);

        // Calculer l'écart : somme des différences entre toutes les paires de notes
        Double ecartNotes = 0.0;
        for (int i = 0; i < notes.size(); i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                ecartNotes += Math.abs(notes.get(i) - notes.get(j));
            }
        }
        
        response.setNotes(notes);
        response.setNoteMinimale(noteMinimale);
        response.setNoteMaximale(noteMaximale);
        response.setEcartNotes(ecartNotes);
        
        // Récupérer tous les paramètres pour cette matière
        List<Parametre> parametres = parametreRepository.findAll().stream()
                .filter(p -> p.getMatiere().getIdMatiere().equals(idMatiere))
                .collect(Collectors.toList());
        
        // Si aucun paramètre n'existe, créer un paramètre par défaut
        if (parametres.isEmpty()) {
            Parametre parametreParDefaut = creerParametreParDefaut(matiere);
            parametres = List.of(parametreParDefaut);
        }
        
        // Déterminer la note finale en fonction des paramètres et conditions
        Double noteFinale = determinerNoteFinaleAvecParametres(parametres, notes, ecartNotes);
        
        // Trouver le paramètre qui a été utilisé (pour les informations de réponse)
        Parametre parametreUtilise = trouverParametreUtilise(parametres, notes, ecartNotes);
        
        response.setParametreDifference(parametreUtilise.getDifference());
        response.setOperateur(parametreUtilise.getOperateur().getNomOperateur());
        response.setResolution(parametreUtilise.getResolution().getNom());
        
        // Vérifier si la condition du paramètre est remplie
        Boolean conditionRemplie = appliquerOperateur(
            ecartNotes, 
            parametreUtilise.getDifference(), 
            parametreUtilise.getOperateur().getNomOperateur()
        );
        
        response.setConditionRemplie(conditionRemplie);
        response.setNoteFinale(noteFinale);
        
        // Déterminer la méthode de calcul utilisée
        String methodeCalcul;
        if (conditionRemplie) {
            methodeCalcul = determinerMethodeCalcul(parametreUtilise.getResolution().getNom(), true);
        } else {
            methodeCalcul = "Moyenne des notes (aucune condition remplie)";
        }
        response.setMethodeCalcul(methodeCalcul);
        
        return response;
    }
    
    /**
     * Détermine la note finale en fonction des paramètres
     * Si une condition est remplie → applique la résolution du paramètre
     * Sinon → retourne la moyenne
     */
    private Double determinerNoteFinaleAvecParametres(List<Parametre> parametres, List<Double> notes, Double ecartNotes) {
        
        // Chercher un paramètre dont la condition est remplie
        for (Parametre p : parametres) {
            if (appliquerOperateur(ecartNotes, p.getDifference(), p.getOperateur().getNomOperateur())) {
                // Condition remplie → appliquer la résolution du paramètre
                return calculerNoteSelonResolution(notes, p.getResolution().getNom());
            }
        }
        
        // Aucune condition remplie → retourner la MOYENNE
        return notes.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }
    
    /**
     * Trouve le paramètre utilisé (pour l'affichage)
     * Priorité au paramètre dont la condition est remplie, sinon le premier
     */
    private Parametre trouverParametreUtilise(List<Parametre> parametres, List<Double> notes, Double ecartNotes) {
        
        // Chercher d'abord un paramètre dont la condition est remplie
        for (Parametre p : parametres) {
            if (appliquerOperateur(ecartNotes, p.getDifference(), p.getOperateur().getNomOperateur())) {
                return p;
            }
        }
        
        // Sinon retourner le premier
        return parametres.get(0);
    }
    
    /**
     * Applique la logique de l'opérateur : 
     * - superieur (>)
     * - superieurOuEgale (>=)
     * - inferieur (<)
     * - inferieurOuEgale (<=)
     */
    private Boolean appliquerOperateur(Double ecart, Double difference, String operateur) {
        if ("superieur".equalsIgnoreCase(operateur)) {
            return ecart > difference;  // écart > différence
        } else if ("superieurOuEgale".equalsIgnoreCase(operateur)) {
            return ecart >= difference;  // écart >= différence
        } else if ("inferieur".equalsIgnoreCase(operateur)) {
            return ecart < difference;  // écart < différence
        } else if ("inferieurOuEgale".equalsIgnoreCase(operateur)) {
            return ecart <= difference;  // écart <= différence
        }
        return false;
    }
    
    /**
     * Calcule la note finale selon la résolution
     * Les noms correspondent à votre base de données : 
     * - "Petit" → note minimale
     * - "Grand" → note maximale
     * - "Moyenne" → moyenne des notes
     */
    private Double calculerNoteSelonResolution(List<Double> notes, String resolution) {
        
        if (resolution == null) return 0.0;
        
        // "Petit" → retourner le minimum
        if ("Petit".equalsIgnoreCase(resolution)) {
            return notes.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        }
        
        // "Grand" → retourner le maximum
        if ("Grand".equalsIgnoreCase(resolution)) {
            return notes.stream().mapToDouble(Double::doubleValue).max().orElse(0);
        }
        
        // "Moyenne" → retourner la moyenne
        if ("Moyenne".equalsIgnoreCase(resolution)) {
            return notes.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        }
        
        return 0.0;
    }
    
    /**
     * Détermine la méthode de calcul utilisée
     */
    private String determinerMethodeCalcul(String resolution, Boolean conditionRemplie) {
        if (resolution == null) return "Méthode inconnue";
        
        String base;
        if ("Petit".equalsIgnoreCase(resolution)) {
            base = "Note minimale";
        } else if ("Grand".equalsIgnoreCase(resolution)) {
            base = "Note maximale";
        } else if ("Moyenne".equalsIgnoreCase(resolution)) {
            base = "Moyenne des notes";
        } else {
            return "Méthode inconnue";
        }
        
        return base + (conditionRemplie ? " (condition remplie)" : " (condition non remplie)");
    }

    /**
     * Crée un paramètre par défaut si aucun n'existe pour la matière
     * Paramètres par défaut : Opérateur "superieurOuEgale", Résolution "Moyenne", Différence 3
     */
    private Parametre creerParametreParDefaut(Matiere matiere) {
        try {
            // Chercher l'opérateur "superieurOuEgale" d'abord
            Operateur operateurParDefaut = operateurRepository.findAll().stream()
                    .filter(op -> "superieurOuEgale".equalsIgnoreCase(op.getNomOperateur()))
                    .findFirst()
                    .orElseGet(() -> {
                        // Si pas trouvé, prendre l'opérateur "superieur"
                        return operateurRepository.findAll().stream()
                                .filter(op -> "superieur".equalsIgnoreCase(op.getNomOperateur()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Opérateur par défaut non trouvé"));
                    });

            // Récupérer la résolution par défaut "Moyenne"
            Resolution resolutionParDefaut = resolutionRepository.findAll().stream()
                    .filter(res -> "Moyenne".equalsIgnoreCase(res.getNom()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Résolution par défaut 'Moyenne' non trouvée"));

            // Créer un nouveau paramètre
            Parametre parametreParDefaut = new Parametre();
            parametreParDefaut.setMatiere(matiere);
            parametreParDefaut.setOperateur(operateurParDefaut);
            parametreParDefaut.setResolution(resolutionParDefaut);
            parametreParDefaut.setDifference(3.0);  // Différence par défaut

            // Sauvegarder et retourner
            return parametreRepository.save(parametreParDefaut);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du paramètre par défaut : " + e.getMessage());
        }
    }
}