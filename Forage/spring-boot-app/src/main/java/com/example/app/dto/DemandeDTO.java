package com.example.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DemandeDTO {
    private Integer idDemande;
    private String clientNom;
    private Integer clientId;
    private String lieu;
    private String district;
    private LocalDate dateDemande;
    private String dernierStatut;
    private LocalDateTime dateDernierStatut;
    
    public DemandeDTO() {}
    
    public Integer getIdDemande() { return idDemande; }
    public void setIdDemande(Integer idDemande) { this.idDemande = idDemande; }
    public String getClientNom() { return clientNom; }
    public void setClientNom(String clientNom) { this.clientNom = clientNom; }
    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public LocalDate getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDate dateDemande) { this.dateDemande = dateDemande; }
    public String getDernierStatut() { return dernierStatut; }
    public void setDernierStatut(String dernierStatut) { this.dernierStatut = dernierStatut; }
    public LocalDateTime getDateDernierStatut() { return dateDernierStatut; }
    public void setDateDernierStatut(LocalDateTime dateDernierStatut) { this.dateDernierStatut = dateDernierStatut; }
}