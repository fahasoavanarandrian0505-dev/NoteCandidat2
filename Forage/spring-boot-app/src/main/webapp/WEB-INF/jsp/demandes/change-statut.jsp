<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Changer le Statut - Demande #${demande.idDemande}</title>
    <style>
        body { font-family: Arial; margin: 20px; background: #f4f4f4; }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 { color: #333; margin-bottom: 20px; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }
        select, textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        select:focus, textarea:focus {
            outline: none;
            border-color: #007bff;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
            margin-right: 10px;
        }
        .btn-primary {
            background: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background: #0056b3;
        }
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background: #545b62;
        }
        .info-box {
            background: #e9ecef;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .info-box p {
            margin: 5px 0;
        }
        .error-message {
            color: #dc3545;
            font-size: 12px;
            margin-top: 5px;
        }
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
            .container { margin: 20px; padding: 20px; }
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

    <div class="container">
        <h2>Changer le Statut de la Demande #${demande.idDemande}</h2>
        
        <div class="info-box">
            <p><strong>Client :</strong> ${demande.client.nom}</p>
            <p><strong>Lieu :</strong> ${demande.lieu != null ? demande.lieu : '-'}</p>
            <p><strong>District :</strong> ${demande.district != null ? demande.district : '-'}</p>
        </div>
        
        <form action="/demandes/change-statut" method="post">
            <input type="hidden" name="demandeId" value="${demande.idDemande}">
            
            <div class="form-group">
                <label for="statusId">Nouveau Statut :</label>
                <select name="statusId" id="statusId" required>
                    <option value="">-- Sélectionnez un statut --</option>
                    <c:forEach items="${allStatus}" var="status">
                        <option value="${status.idStatus}">${status.libelle}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="observation">Observation / Description (obligatoire) :</label>
                <textarea name="observation" id="observation" required 
                          placeholder="Veuillez décrire la raison du changement de statut..."></textarea>
            </div>
            
            <div>
                <button type="submit" class="btn btn-primary">Valider le Changement</button>
                <a href="/demandes/details/${demande.idDemande}" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
    </div>
</body>
</html>