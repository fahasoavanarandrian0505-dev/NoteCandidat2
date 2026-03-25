package com.example.app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "typedevis")
public class TypeDevis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typedevis")
    private Integer idTypeDevis;
    
    @Column(name = "libelle")
    private String libelle;
    
    @OneToMany(mappedBy = "typeDevis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Devis> devis;
    
    public TypeDevis() {}
    
    // Getters et Setters
    public Integer getIdTypeDevis() { return idTypeDevis; }
    public void setIdTypeDevis(Integer idTypeDevis) { this.idTypeDevis = idTypeDevis; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public List<Devis> getDevis() { return devis; }
    public void setDevis(List<Devis> devis) { this.devis = devis; }
}