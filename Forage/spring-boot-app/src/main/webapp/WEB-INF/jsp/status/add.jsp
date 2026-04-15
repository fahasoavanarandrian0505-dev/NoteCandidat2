<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Statut</title>
    <style>
        body { font-family: Arial; margin: 20px; background: white; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"] { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
        .btn { padding: 8px 15px; text-decoration: none; border: none; border-radius: 4px; cursor: pointer; margin-right: 10px; }
        .btn-save { background: #28a745; color: white; }
        .btn-cancel { background: #6c757d; color: white; }
        .navbar {
            background: #f8f9fa;
            padding: 15px 20px;
            border-bottom: 2px solid #ddd;
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
        }
        .navbar h1 { color: #333; margin: 0; }
        .nav-links a {
            margin-left: 15px;
            text-decoration: none;
            color: #333;
            padding: 5px 10px;
            border-radius: 3px;
        }
        .nav-links a:hover { background-color: #e9ecef; }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; }
            .nav-links a { margin: 5px; display: inline-block; }
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>Gestion de Forage</h1>
        <div class="nav-links">
            <a href="/">Accueil</a>
            <a href="/clients">Clients</a>
            <a href="/demandes">Demandes</a>
            <a href="/status">Statuts</a>
            <a href="/demande-statuts">Historiques Statuts</a>
            <a href="/devis">Devis</a>
        </div>
    </div>

    <h2>Ajouter un Statut</h2>
    
    <form action="/status/add" method="post">
        <div class="form-group">
            <label for="libelle">Libellé du statut:</label>
            <input type="text" id="libelle" name="libelle" required>
        </div>
        
        <button type="submit" class="btn btn-save">Enregistrer</button>
        <a href="/status" class="btn btn-cancel">Annuler</a>
    </form>
</body>
</html>
