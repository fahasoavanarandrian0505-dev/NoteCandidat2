package com.example.app.dto;

import java.math.BigDecimal;
import java.util.List;

public class DevisRequestDTO {
    private Integer demandeId;
    private Integer typeDevisId;
    private BigDecimal montantTotal;
    private List<DetailDTO> details;
    
    public static class DetailDTO {
        private String libelle;
        private BigDecimal prixUnitaire;
        private Integer quantite;
        private Integer index;
        
        public String getLibelle() { return libelle; }
        public void setLibelle(String libelle) { this.libelle = libelle; }
        public BigDecimal getPrixUnitaire() { return prixUnitaire; }
        public void setPrixUnitaire(BigDecimal prixUnitaire) { this.prixUnitaire = prixUnitaire; }
        public Integer getQuantite() { return quantite; }
        public void setQuantite(Integer quantite) { this.quantite = quantite; }
        public Integer getIndex() { return index; }
        public void setIndex(Integer index) { this.index = index; }
    }
    
    public Integer getDemandeId() { return demandeId; }
    public void setDemandeId(Integer demandeId) { this.demandeId = demandeId; }
    public Integer getTypeDevisId() { return typeDevisId; }
    public void setTypeDevisId(Integer typeDevisId) { this.typeDevisId = typeDevisId; }
    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }
    public List<DetailDTO> getDetails() { return details; }
    public void setDetails(List<DetailDTO> details) { this.details = details; }
}