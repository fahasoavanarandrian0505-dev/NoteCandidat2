package com.example.app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer idStatus;

    @Column(name = "libelle", length = 100)
    private String libelle;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeStatus> demandeStatuses;

    public Status() {}

    public Integer getIdStatus() { return idStatus; }
    public void setIdStatus(Integer idStatus) { this.idStatus = idStatus; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public List<DemandeStatus> getDemandeStatuses() { return demandeStatuses; }
    public void setDemandeStatuses(List<DemandeStatus> demandeStatuses) { this.demandeStatuses = demandeStatuses; }
}