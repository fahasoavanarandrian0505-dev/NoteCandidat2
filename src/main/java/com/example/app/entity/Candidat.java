package com.example.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "candidat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidat")
    private Integer idCandidat;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;
}
