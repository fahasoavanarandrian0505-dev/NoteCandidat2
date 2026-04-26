<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Historique des Statuts des Demandes</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #a5a4a4; min-height: 100vh; }
        .navbar { background: #0d1b3e; padding: 15px 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.2); display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; border-bottom: 1px solid #2c3e6d; }
        .navbar h1 { color: white; margin: 0; font-size: 24px; }
        .nav-links a { margin-left: 20px; text-decoration: none; color: #c8d6e5; padding: 8px 16px; border-radius: 5px; transition: background 0.3s; font-weight: 500; }
        .nav-links a:hover { background: #2c3e6d; color: white; }
        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }
        .content { background: #0d1b3e; border-radius: 10px; padding: 25px; border: 1px solid #2c3e6d; }
        h2 { color: white; margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #3498db; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #2c3e6d; padding: 10px; text-align: left; color: #c8d6e5; }
        th { background-color: #12234a; color: white; }
        .btn-back { background: #6c757d; color: white; padding: 8px 15px; text-decoration: none; display: inline-block; margin-top: 20px; border-radius: 5px; }
        .btn-back:hover { opacity: 0.8; }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; gap: 10px; }
            .nav-links a { margin: 5px; display: inline-block; }
            table { display: block; overflow-x: auto; }
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>Forage - ETU 3615</h1>
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
        <div class="content">
            <h2>Historique des Statuts des Demandes</h2>
            <c:if test="${empty demandeStatusList}">
                <p style="color: #c8d6e5;">Aucun historique de statut trouve.</p>
            </c:if>
            <c:if test="${not empty demandeStatusList}">
                <table>
                    <thead>
                        <tr><th>ID Statut</th><th>ID Demande</th><th>Client</th><th>Lieu</th><th>Statut</th><th>Date de changement</th><th>Observations</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${demandeStatusList}" var="dS">
                            <tr>
                                <td>${dS.idDemandeStatus}</d>
                                <td>${dS.demande.idDemande}</d>
                                <td>${dS.demande.client.nom}</d>
                                <td>${dS.demande.lieu}</d>
                                <td>${dS.status.libelle}</d>
                                <td>${dS.dateChangement}</d>
                                <td>${dS.observation}</d>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <br>
            <a href="/" class="btn-back">Retour à l'accueil</a>
        </div>
    </div>
</body>
</html>