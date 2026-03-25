<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Demandes</title>
    <style>
        body { font-family: Arial; margin: 20px; background: white; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; text-decoration: none; margin: 2px; display: inline-block; border-radius: 3px; }
        .btn-add { background: #28a745; color: white; }
        .btn-edit { background: #007bff; color: white; }
        .btn-delete { background: #dc3545; color: white; }
        .btn-back { background: #6c757d; color: white; padding: 8px 15px; text-decoration: none; display: inline-block; margin-top: 20px; border-radius: 3px; }
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
            table { display: block; overflow-x: auto; }
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
            <a href="/devis">Devis</a>
        </div>
    </div>

    <h2>Liste des Demandes</h2>
    
    <a href="/demandes/add" class="btn btn-add">+ Nouvelle Demande</a>
    
    <c:if test="${empty demandes}">
        <p>Aucune demande trouvée.</p>
    </c:if>
    
    <c:if test="${not empty demandes}">
         <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Client</th>
                    <th>Lieu</th>
                    <th>District</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${demandes}" var="demande">
                     <tr>
                        <td>${demande.idDemande}</td>
                        <td>${demande.clientNom}</td>
                        <td>${demande.lieu != null ? demande.lieu : '-'}</td>
                        <td>${demande.district != null ? demande.district : '-'}</td>
                        <td>${demande.dateDemande != null ? demande.dateDemande : '-'}</td>
                        <td>
                            <a href="/demandes/edit/${demande.idDemande}" class="btn btn-edit">Modifier</a>
                            <a href="/demandes/delete/${demande.idDemande}" class="btn btn-delete" onclick="return confirm('Supprimer cette demande ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <br>
    <a href="/" class="btn-back">Retour à l'accueil</a>
</body>
</html>