DROP DATABASE IF EXISTS ForageDB;
CREATE DATABASE ForageDB;
USE ForageDB;

-- Table Client
CREATE TABLE client (
    id_client INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE
);

-- Table Demande
CREATE TABLE demande (
    id_demande INT PRIMARY KEY AUTO_INCREMENT,
    id_client INT NOT NULL,
    lieu VARCHAR(255),
    district VARCHAR(100),
    date_demande DATE,
    FOREIGN KEY (id_client) REFERENCES client(id_client) ON DELETE CASCADE
);

-- Table TypeDevis
CREATE TABLE typedevis (
    id_typedevis INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(50)
);

-- Table Devis
CREATE TABLE devis (
    id_devis INT PRIMARY KEY AUTO_INCREMENT,
    id_demande INT NOT NULL,
    id_typedevis INT NOT NULL,
    date_devis DATE,
    montant_total DECIMAL(15, 2),
    est_accepte BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_demande) REFERENCES demande(id_demande) ON DELETE CASCADE,
    FOREIGN KEY (id_typedevis) REFERENCES typedevis(id_typedevis)
);

-- Table Détails Devis 
CREATE TABLE details_devis (
    id_detail INT PRIMARY KEY AUTO_INCREMENT,
    id_devis INT NOT NULL,
    libelle VARCHAR(255) NOT NULL,
    prix_unitaire DECIMAL(15, 2) NOT NULL,
    quantite INT NOT NULL,
    FOREIGN KEY (id_devis) REFERENCES devis(id_devis) ON DELETE CASCADE
);

-- Table Status
CREATE TABLE status (
    id_status INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100)
);

-- Table DemandeStatus
CREATE TABLE demandestatus (
    id_demandestatus INT PRIMARY KEY AUTO_INCREMENT,
    id_demande INT NOT NULL,
    id_status INT NOT NULL,
    date_changement DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_demande) REFERENCES demande(id_demande) ON DELETE CASCADE,
    FOREIGN KEY (id_status) REFERENCES status(id_status)
);



-- Données initiales
INSERT INTO typedevis (libelle) VALUES 
('Forage'),
('Etude');

INSERT INTO status (libelle) VALUES 
('Devis créé'),
('Devis accepté'),
('Devis refusé');

