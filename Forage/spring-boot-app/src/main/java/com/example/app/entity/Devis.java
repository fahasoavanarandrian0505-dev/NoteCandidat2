package com.example.app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devis")
public class Devis {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devis")
    private Integer idDevis;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande", nullable = false)
    private Demande demande;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_typedevis", nullable = false)
    private TypeDevis typeDevis;
    
    private LocalDate dateDevis;
    private BigDecimal montantTotal;
    private Boolean estAccepte = false;
    
    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetailsDevis> detailsDevis = new ArrayList<>();
    
    public Devis() {}
    
    public Integer getIdDevis() { return idDevis; }
    public void setIdDevis(Integer idDevis) { this.idDevis = idDevis; }
    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
    public TypeDevis getTypeDevis() { return typeDevis; }
    public void setTypeDevis(TypeDevis typeDevis) { this.typeDevis = typeDevis; }
    public LocalDate getDateDevis() { return dateDevis; }
    public void setDateDevis(LocalDate dateDevis) { this.dateDevis = dateDevis; }
    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }
    public Boolean getEstAccepte() { return estAccepte; }
    public void setEstAccepte(Boolean estAccepte) { this.estAccepte = estAccepte; }
    public List<DetailsDevis> getDetailsDevis() { return detailsDevis; }
    public void setDetailsDevis(List<DetailsDevis> detailsDevis) { this.detailsDevis = detailsDevis; }
    
    public void addDetailDevis(DetailsDevis detail) {
        detailsDevis.add(detail);
        detail.setDevis(this);
        recalculerMontantTotal();
    }
    
    public void removeDetailDevis(DetailsDevis detail) {
        detailsDevis.remove(detail);
        detail.setDevis(null);
        recalculerMontantTotal();
    }
    
    public BigDecimal calculerMontantTotal() {
        return detailsDevis.stream()
            .map(DetailsDevis::getSousTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void recalculerMontantTotal() {
        this.montantTotal = calculerMontantTotal();
    }
    
    public void accepter() { this.estAccepte = true; }
    public void refuser() { this.estAccepte = false; }
    public boolean isAccepte() { return estAccepte != null && estAccepte; }
}