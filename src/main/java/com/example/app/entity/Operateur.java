package com.example.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "operateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operateur")
    private Integer idOperateur;

    @Column(name = "nom_operateur", nullable = false)
    // Valeurs: superieur, inferieur
    private String nomOperateur;

    @OneToMany(mappedBy = "operateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parametre> parametres;
}
