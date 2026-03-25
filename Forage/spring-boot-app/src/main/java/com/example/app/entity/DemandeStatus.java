package com.example.app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demandestatus")
public class DemandeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demandestatus")  // Note: nom en minuscules sans underscore
    private Integer idDemandeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande", nullable = false)
    private Demande demande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @Column(name = "date_changement")
    private LocalDateTime dateChangement;

    public DemandeStatus() {
        this.dateChangement = LocalDateTime.now();
    }

    // Getters et Setters
    public Integer getIdDemandeStatus() { return idDemandeStatus; }
    public void setIdDemandeStatus(Integer idDemandeStatus) { this.idDemandeStatus = idDemandeStatus; }
    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getDateChangement() { return dateChangement; }
    public void setDateChangement(LocalDateTime dateChangement) { this.dateChangement = dateChangement; }
}