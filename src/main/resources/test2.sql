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
('PHP'),
('PYTHON');   -- 2

-- ========================================
-- NOTES
-- ========================================
INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 

-- JAVA - Candidat 1 
(1, 3, 1, 14.5),
(1, 3, 2, 13);


-- JAVA :  paramètres
INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(3, 4, 3, 1),
(3, 1, 3, 3);

