package com.example.app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "devis")
public class Devis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devis")  
    private Integer idDevis;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande", nullable = false)
    private Demande demande;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_typeDevis", nullable = false)
    private TypeDevis typeDevis;
    
    @Column(name = "date_devis")
    private LocalDate dateDevis;
    
    @Column(name = "montantTotal")
    private BigDecimal montantTotal;
    
    @Column(name = "est_accepte")
    private Boolean estAccepte = false;
    
    public Devis() {}
    
    public Devis(Integer idDevis, Demande demande, TypeDevis typeDevis, LocalDate dateDevis, BigDecimal montantTotal, Boolean estAccepte) {
        this.idDevis = idDevis;
        this.demande = demande;
        this.typeDevis = typeDevis;
        this.dateDevis = dateDevis;
        this.montantTotal = montantTotal;
        this.estAccepte = estAccepte;
    }
    
    public Integer getIdDevis() {
        return idDevis;
    }
    
    public void setIdDevis(Integer idDevis) {
        this.idDevis = idDevis;
    }
    
    public Demande getDemande() {
        return demande;
    }
    
    public void setDemande(Demande demande) {
        this.demande = demande;
    }
    
    public TypeDevis getTypeDevis() {
        return typeDevis;
    }
    
    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }
    
    public LocalDate getDateDevis() {
        return dateDevis;
    }
    
    public void setDateDevis(LocalDate dateDevis) {
        this.dateDevis = dateDevis;
    }
    
    public BigDecimal getMontantTotal() {
        return montantTotal;
    }
    
    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
    
    public Boolean getEstAccepte() {
        return estAccepte;
    }
    
    public void setEstAccepte(Boolean estAccepte) {
        this.estAccepte = estAccepte;
    }
}