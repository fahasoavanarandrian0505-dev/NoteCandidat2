<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Devis</title>
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
        .accepte { color: green; font-weight: bold; }
        .non-accepte { color: orange; font-weight: bold; }
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
            <a href="/status">Statuts</a>
            <a href="/demande-statuts">Historiques Statuts</a>
            <a href="/devis">Devis</a>
        </div>
    </div>

    <h2>Liste des Devis</h2>
    
    <a href="/devis/add" class="btn btn-add">+ Nouveau Devis</a>

    
    <c:if test="${empty devis}">
        <p>Aucun devis trouvé.</p>
    </c:if>
    
    <c:if test="${not empty devis}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Demande</th>
                    <th>Client</th>
                    <th>Type</th>
                    <th>Date</th>
                    <th>Montant</th>
                    <!-- <th>Statut</th> -->
                    <th>Actions</th>
                </thead>

            <tbody>
                <c:forEach items="${devis}" var="d">
                    <tr>
                        <td>${d.idDevis}</td>
                        <td>#${d.demande.idDemande}</td>
                        <td>${d.demande.client.nom}</td>
                        <td>${d.typeDevis.libelle}</td>
                        <td>${d.dateDevis}</td>
                        <td>
                            <c:set var="montant" value="${d.calculerMontantTotal()}" />
                            <fmt:formatNumber value="${montant}" type="number" minFractionDigits="0" maxFractionDigits="0"/> Ar
                        </td>
                        <!-- <td>
                            <c:if test="${d.estAccepte}">
                                <span class="accepte">Accepté</span>
                            </c:if>
                            <c:if test="${not d.estAccepte}">
                                <span class="non-accepte">En attente</span>
                            </c:if>
                        </td> -->
                        <td>
                            <!-- <c:if test="${not d.estAccepte}">
                                <a href="/devis/accept/${d.idDevis}" class="btn-edit" onclick="return confirm('Accepter ce devis ?')">Accepter</a>
                                <a href="/devis/refuse/${d.idDevis}" class="btn-delete" onclick="return confirm('Refuser ce devis ?')">Refuser</a>
                            </c:if> -->
                            <a href="/devis/edit/${d.idDevis}" class="btn btn-edit">Modifier</a>
                            <a href="/devis/delete/${d.idDevis}" class="btn btn-delete" onclick="return confirm('Supprimer ce devis ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <br>
    
    <h2>Somme Globale des devis : ${totalGlobal} Ar</h2>

    <a href="/" class="btn-back">Retour à l'accueil</a>
</body>
</html>