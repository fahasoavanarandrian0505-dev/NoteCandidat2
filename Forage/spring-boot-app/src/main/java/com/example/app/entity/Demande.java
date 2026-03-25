package com.example.app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "demande")
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private Integer idDemande;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;
    
    @Column(name = "lieu")
    private String lieu;
    
    @Column(name = "district")
    private String district;
    
    @Column(name = "date_demande")
    private LocalDate dateDemande;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Devis> devis;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeStatus> demandeStatuses;
    
    public Demande() {}
    
    // Getters et Setters
    public Integer getIdDemande() { return idDemande; }
    public void setIdDemande(Integer idDemande) { this.idDemande = idDemande; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public LocalDate getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDate dateDemande) { this.dateDemande = dateDemande; }
    public List<Devis> getDevis() { return devis; }
    public void setDevis(List<Devis> devis) { this.devis = devis; }
    public List<DemandeStatus> getDemandeStatuses() { return demandeStatuses; }
    public void setDemandeStatuses(List<DemandeStatus> demandeStatuses) { this.demandeStatuses = demandeStatuses; }
}