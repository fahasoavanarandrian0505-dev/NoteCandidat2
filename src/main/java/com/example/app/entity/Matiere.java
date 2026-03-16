package com.example.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "matiere")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matiere")
    private Integer idMatiere;

    @Column(name = "nom_matiere", nullable = false)
    private String nomMatiere;

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parametre> parametres;

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;
}
