package com.example.app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "details_devis")
public class DetailsDevis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Integer idDetail;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devis", nullable = false)
    private Devis devis;
    
    @Column(name = "libelle", nullable = false)
    private String libelle;
    
    @Column(name = "prix_unitaire", nullable = false)
    private BigDecimal prixUnitaire;
    
    @Column(name = "quantite", nullable = false)
    private Integer quantite;
    
    public DetailsDevis() {}
    
    public Integer getIdDetail() { return idDetail; }
    public void setIdDetail(Integer idDetail) { this.idDetail = idDetail; }
    public Devis getDevis() { return devis; }
    public void setDevis(Devis devis) { this.devis = devis; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public BigDecimal getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(BigDecimal prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
    
    public BigDecimal getSousTotal() {
        return (prixUnitaire != null && quantite != null) 
            ? prixUnitaire.multiply(BigDecimal.valueOf(quantite)) 
            : BigDecimal.ZERO;
    }
}