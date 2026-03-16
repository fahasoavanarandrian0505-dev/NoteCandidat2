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
        
        // Trouver le meilleur paramètre selon les règles :
        // 1. Condition vraie
        // 2. Distance la plus proche de l'écart
        // 3. En cas d'égalité de distance, différence la plus petite
        Parametre meilleurParametre = trouverMeilleurParametre(parametres, ecartNotes);
        
        Double noteFinale;
        String methodeCalcul;
        
        if (meilleurParametre != null) {
            // Un paramètre valide a été trouvé
            noteFinale = calculerNoteSelonResolution(notes, meilleurParametre.getResolution().getNom());
            
            response.setParametreDifference(meilleurParametre.getDifference());
            response.setOperateur(meilleurParametre.getOperateur().getNomOperateur());
            response.setResolution(meilleurParametre.getResolution().getNom());
            
            Boolean conditionRemplie = appliquerOperateur(
                ecartNotes, 
                meilleurParametre.getDifference(), 
                meilleurParametre.getOperateur().getNomOperateur()
            );
            
            response.setConditionRemplie(conditionRemplie);
            methodeCalcul = determinerMethodeCalcul(meilleurParametre.getResolution().getNom(), conditionRemplie);
        } else {
            // Aucun paramètre valide trouvé → moyenne par défaut
            noteFinale = notes.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            
            // Pour l'affichage, prendre le premier paramètre
            Parametre premierParametre = parametres.get(0);
            response.setParametreDifference(premierParametre.getDifference());
            response.setOperateur(premierParametre.getOperateur().getNomOperateur());
            response.setResolution(premierParametre.getResolution().getNom());
            response.setConditionRemplie(false);
            
            methodeCalcul = "Moyenne des notes (aucune condition remplie)";
        }
        
        response.setNoteFinale(noteFinale);
        response.setMethodeCalcul(methodeCalcul);
        
        return response;
    }
    
    /**
     * Trouve le meilleur paramètre selon les règles :
     * 1. Condition vraie
     * 2. Distance la plus proche de l'écart
     * 3. En cas d'égalité de distance, différence la plus petite
     */
    private Parametre trouverMeilleurParametre(List<Parametre> parametres, Double ecartNotes) {
        Parametre meilleur = null;
        Double meilleureDistance = null;
        Double plusPetiteDifference = null;

        for (Parametre p : parametres) {
            // Règle 1 : la condition doit être vraie
            if (!appliquerOperateur(ecartNotes, p.getDifference(), p.getOperateur().getNomOperateur())) {
                continue;
            }

            double distance = Math.abs(p.getDifference() - ecartNotes);

            if (meilleur == null) {
                // Premier paramètre valide trouvé
                meilleur = p;
                meilleureDistance = distance;
                plusPetiteDifference = p.getDifference();
                continue;
            }

            // Règle 2 : comparer d'abord la distance
            if (distance < meilleureDistance) {
                // Distance plus petite → nouveau meilleur
                meilleur = p;
                meilleureDistance = distance;
                plusPetiteDifference = p.getDifference();
            } 
            // Si distance égale, on prend la plus petite différence
            else if (distance == meilleureDistance) {
                if (p.getDifference() < plusPetiteDifference) {
                    meilleur = p;
                    plusPetiteDifference = p.getDifference();
                }
            }
        }

        return meilleur; // peut être null si aucun paramètre valide
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