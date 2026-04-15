<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Détails de la Demande #${demande.idDemande}</title>
    <style>
        body { font-family: Arial; margin: 20px; background: #f4f4f4; }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
        h3 { color: #555; margin-top: 20px; }
        .info-section {
            background: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .info-row {
            display: flex;
            padding: 8px 0;
            border-bottom: 1px solid #dee2e6;
        }
        .info-label {
            font-weight: bold;
            width: 150px;
        }
        .info-value {
            flex: 1;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            padding: 8px 15px;
            text-decoration: none;
            margin: 5px;
            display: inline-block;
            border-radius: 3px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-primary { background: #007bff; color: white; }
        .btn-success { background: #28a745; color: white; }
        .btn-warning { background: #ffc107; color: #333; }
        .btn-danger { background: #dc3545; color: white; }
        .btn-secondary { background: #6c757d; color: white; }
        .btn-back { background: #6c757d; color: white; }
        .statut-badge {
            display: inline-block;
            padding: 5px 12px;
            border-radius: 3px;
            font-size: 14px;
            font-weight: bold;
        }
        .statut-cree { background: #6c757d; color: white; }
        .statut-valide { background: #28a745; color: white; }
        .statut-rejeter { background: #dc3545; color: white; }
        .statut-non-valide { background: #ffc107; color: #333; }
        .statut-devis-etude-cree { background: #17a2b8; color: white; }
        .statut-devis-forage-cree { background: #17a2b8; color: white; }
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
            .info-row { flex-direction: column; }
            .info-label { width: auto; margin-bottom: 5px; }
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

    <div class="container">
        <h2>Détails de la Demande #${demande.idDemande}</h2>
        
        <!-- Section Client -->
        <div class="info-section">
            <h3>Informations Client</h3>
            <div class="info-row">
                <div class="info-label">Nom du Client :</div>
                <div class="info-value">${demande.client.nom}</div>
            </div>
            <div class="info-row">
                <div class="info-label">Email :</div>
                <div class="info-value">${demande.client.email != null ? demande.client.email : '-'}</div>
            </div>
        </div>
        
        <!-- Section Demande -->
        <div class="info-section">
            <h3>Informations Demande</h3>
            <div class="info-row">
                <div class="info-label">Lieu :</div>
                <div class="info-value">${demande.lieu != null ? demande.lieu : '-'}</div>
            </div>
            <div class="info-row">
                <div class="info-label">District :</div>
                <div class="info-value">${demande.district != null ? demande.district : '-'}</div>
            </div>
            <div class="info-row">
                <div class="info-label">Date de Demande :</div>
                <div class="info-value">${demande.dateDemande != null ? demande.dateDemande : '-'}</div>
            </div>
            <div class="info-row">
                <div class="info-label">Statut Actuel :</div>
                <div class="info-value">
                    <c:if test="${dernierStatut != null}">
                        <span class="statut-badge">
                            ${dernierStatut.status.libelle}
                        </span>
                    </c:if>
                    <c:if test="${dernierStatut == null}">
                        <span class="statut-badge statut-cree">Nouvelle Demande</span>
                    </c:if>
                </div>
            </div>
        </div>
        
        <!-- Bouton Modifier Statut -->
        <div style="margin: 20px 0;">
            <a href="/demandes/change-statut/${demande.idDemande}" class="btn btn-warning">✏️ Modifier le Statut</a>
            <a href="/demandes" class="btn btn-secondary">← Retour à la liste</a>
        </div>
        
        <!-- Section Historique des Statuts -->
        <h3>Historique des Statuts</h3>
        <c:if test="${empty historiqueStatuts}">
            <p>Aucun historique de statut disponible.</p>
        </c:if>
        <c:if test="${not empty historiqueStatuts}">
            <table>
                <thead>
                    <tr>
                        <th>Date de Changement</th>
                        <th>Statut</th>
                        <th>Observation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${historiqueStatuts}" var="statut">
                        <tr>
                            <td>${statut.dateChangement}</td>
                            <td>
                                <span class="statut-badge">
                                    ${statut.status.libelle}
                                </span>
                            </td>
                            <td>${statut.observation != null ? statut.observation : '-'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>