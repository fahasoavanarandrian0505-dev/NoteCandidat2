package com.example.app.dto;

import java.time.LocalDate;
import java.util.Date;

public class DemandeDTO {
    private Integer idDemande;
    private String clientNom;
    private Integer clientId;
    private String lieu;
    private String district;
    private LocalDate dateDemande;
    
    public DemandeDTO() {}
    
    public DemandeDTO(Integer idDemande, String clientNom, Integer clientId, String lieu, String district, LocalDate dateDemande) {
        this.idDemande = idDemande;
        this.clientNom = clientNom;
        this.clientId = clientId;
        this.lieu = lieu;
        this.district = district;
        this.dateDemande = dateDemande;
    }
    
    // Getters et Setters
    public Integer getIdDemande() {
        return idDemande;
    }
    
    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }
    
    public String getClientNom() {
        return clientNom;
    }
    
    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }
    
    public Integer getClientId() {
        return clientId;
    }
    
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    
    public String getLieu() {
        return lieu;
    }
    
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public LocalDate getDateDemande() {
        return dateDemande;
    }
    
    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }
}