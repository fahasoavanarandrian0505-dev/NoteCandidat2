-- ========================================
-- FICHIER DE TEST SIMPLE
-- ========================================

-- 1. CANDIDATS
INSERT INTO candidat (nom) VALUES 
('CANDIDAT 1'),
('CANDIDAT 2'),
('CANDIDAT 3');

-- 2. CORRECTEURS
INSERT INTO correcteur (nom) VALUES 
('Prof. CORRECTEUR 1'),
('Prof. CORRECTEUR 2'),
('Prof. CORRECTEUR 3');

-- 3. RESOLUTIONS
INSERT INTO resolution (nom) VALUES 
('Petit'),      -- 1
('Grand'),      -- 2
('Moyenne');    -- 3

-- 4. OPERATEURS
INSERT INTO operateur (nom_operateur) VALUES 
('inferieur'),        -- 1
('inferieurOuEgale'), -- 2
('superieur'),        -- 3
('superieurOuEgale'); -- 4

-- 5. MATIERES
INSERT INTO matiere (nom_matiere) VALUES 
('JAVA'),  -- 1
('PHP');   -- 2

-- ========================================
-- NOTES
-- ========================================
INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 

-- JAVA - Candidat 1 : 3 notes
(1, 1, 1, 15),
(1, 1, 2, 10),
(1, 1, 3, 12);  -- écart = |15-10| + |15-12| + |10-12| = 5 + 3 + 2 = 10


-- JAVA : deux paramètres
INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(1, 7, 4, 2),   
(1, 12, 1, 1);   

