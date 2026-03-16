
-- ========================================
-- INSERTION DES DONNÉES - ORDRE IMPORTANT!
-- ========================================

-- 1. CANDIDATS
INSERT INTO candidat (nom) VALUES 
('CANDIDAT 1'),
('CANDIDAT 2');

-- 2. CORRECTEURS
INSERT INTO correcteur (nom) VALUES 
('Prof. CORRECTEUR 1'),
('Prof. CORRECTEUR 2'),
('Prof. CORRECTEUR 3');

-- 3. RESOLUTIONS (DOIT ÊTRE AVANT PARAMETRES)
INSERT INTO resolution (nom) VALUES 
('Petit'),      -- id_resolution = 1
('Grand'),        -- id_resolution = 2
('Moyenne');     -- id_resolution = 3

-- 4. OPERATEURS (DOIT ÊTRE AVANT PARAMETRES)
INSERT INTO operateur (nom_operateur) VALUES 
('inferieur'),      -- id_operateur = 1
('inferieurOuEgale'),     -- id_operateur = 2
('superieur'),
('superieurOuEgale');


-- 5. MATIERES (DOIT ÊTRE AVANT NOTES ET PARAMETRES)
INSERT INTO matiere (nom_matiere) VALUES 
('JAVA'),  -- id_matiere = 1
('PHP');       -- id_matiere = 2


-- Candidat 1 -
INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(1, 1, 1, 15),
(1, 1, 2, 10),
(1, 1, 3, 12),

(2, 1, 1, 9),
(2, 1, 2, 8),
(2, 1, 3, 11),

(1, 2, 1, 10),
(1, 2, 2, 10),

(2, 2, 1, 13),
(2, 2, 2, 11);

-- Paramètre 1: 
INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(1, 7, 1, 2),
(1, 7, 4, 3),

(2, 2, 2, 1),
(2, 2, 3, 2);

