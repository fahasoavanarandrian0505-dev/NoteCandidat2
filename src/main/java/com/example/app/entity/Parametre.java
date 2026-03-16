package com.example.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parametre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametre")
    private Integer idParametre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @Column(name = "difference", nullable = false)
    private Double difference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operateur", nullable = false)
    private Operateur operateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resolution", nullable = false)
    private Resolution resolution;
}
