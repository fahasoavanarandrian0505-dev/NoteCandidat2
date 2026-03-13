package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteFinaleResponse {
    
    private Integer idCandidat;
    private String nomCandidat;
    private Integer idMatiere;
    private String nomMatiere;
    
    private List<Double> notes;
    private Double noteMinimale;
    private Double noteMaximale;
    private Double ecartNotes;
    
    private Double parametreDifference;
    private String operateur;
    private String resolution;
    
    private Boolean conditionRemplie;
    private Double noteFinale;
    private String methodeCalcul;
}
