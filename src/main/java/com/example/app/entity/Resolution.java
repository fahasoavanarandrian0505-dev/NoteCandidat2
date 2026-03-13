package com.example.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "resolution")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolution")
    private Integer idResolution;

    @Column(name = "nom", nullable = false)
    // Valeurs: plusPetit, moyenne, plusGrand
    private String nom;

    @OneToMany(mappedBy = "resolution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parametre> parametres;
}
