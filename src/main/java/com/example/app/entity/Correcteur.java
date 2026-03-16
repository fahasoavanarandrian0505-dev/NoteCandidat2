package com.example.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "correcteur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Correcteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correcteur")
    private Integer idCorrecteur;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "correcteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;
}
