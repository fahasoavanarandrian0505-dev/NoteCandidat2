DROP DATABASE IF EXISTS NoteCorrectionDB;
CREATE DATABASE NoteCorrectionDB;
USE NoteCorrectionDB;


CREATE TABLE candidat (
    id_candidat INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE correcteur (
    id_correcteur INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE resolution (
    id_resolution INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL
);

CREATE TABLE operateur (
    id_operateur INT PRIMARY KEY AUTO_INCREMENT,
    nom_operateur VARCHAR(50) NOT NULL
);

CREATE TABLE matiere (
    id_matiere INT PRIMARY KEY AUTO_INCREMENT,
    nom_matiere VARCHAR(100) NOT NULL
);

CREATE TABLE parametre (
    id_parametre INT PRIMARY KEY AUTO_INCREMENT,
    id_matiere INT NOT NULL,
    difference DOUBLE NOT NULL,
    id_operateur INT NOT NULL,
    id_resolution INT NOT NULL,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere),
    FOREIGN KEY (id_operateur) REFERENCES operateur(id_operateur),
    FOREIGN KEY (id_resolution) REFERENCES resolution(id_resolution)
);

CREATE TABLE note (
    id_note INT PRIMARY KEY AUTO_INCREMENT,
    id_candidat INT NOT NULL,
    id_matiere INT NOT NULL,
    id_correcteur INT NOT NULL,
    note DOUBLE NOT NULL,
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat),
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere),
    FOREIGN KEY (id_correcteur) REFERENCES correcteur(id_correcteur)
);



