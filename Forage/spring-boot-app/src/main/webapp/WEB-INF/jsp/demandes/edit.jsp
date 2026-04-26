<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Demande #${demande.idDemande}</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #a5a4a4; min-height: 100vh; }
        .navbar { background: #0d1b3e; padding: 15px 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.2); display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; border-bottom: 1px solid #2c3e6d; }
        .navbar h1 { color: white; margin: 0; font-size: 24px; }
        .nav-links a { margin-left: 20px; text-decoration: none; color: #c8d6e5; padding: 8px 16px; border-radius: 5px; transition: background 0.3s; font-weight: 500; }
        .nav-links a:hover { background: #2c3e6d; color: white; }
        .container { max-width: 600px; margin: 30px auto; padding: 0 20px; }
        .content { background: #0d1b3e; border-radius: 10px; padding: 25px; border: 1px solid #2c3e6d; }
        h2 { color: white; margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #3498db; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #c8d6e5; }
        input, select { width: 100%; padding: 10px; border: 1px solid #2c3e6d; border-radius: 5px; background: #12234a; color: white; font-size: 14px; box-sizing: border-box; }
        input:focus, select:focus { outline: none; border-color: #3498db; }
        .btn-submit { background: #3498db; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 14px; }
        .btn-submit:hover { opacity: 0.8; }
        .btn-back { background: #6c757d; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-left: 10px; display: inline-block; }
        .btn-back:hover { opacity: 0.8; }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; gap: 10px; }
            .nav-links a { margin: 5px; display: inline-block; }
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
            <h2>Modifier la Demande #${demande.idDemande}</h2>
            <form action="/demandes/update" method="post">
                <input type="hidden" name="idDemande" value="${demande.idDemande}">
                <div class="form-group">
                    <label for="clientId">Client *</label>
                    <select id="clientId" name="clientId" required>
                        <option value="">-- Selectionner un client --</option>
                        <c:forEach items="${clients}" var="client">
                            <option value="${client.idClient}" ${client.idClient == demande.client.idClient ? 'selected' : ''}>${client.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="lieu">Lieu</label>
                    <input type="text" id="lieu" name="lieu" value="${demande.lieu != null ? demande.lieu : ''}">
                </div>
                <div class="form-group">
                    <label for="district">District</label>
                    <input type="text" id="district" name="district" value="${demande.district != null ? demande.district : ''}">
                </div>
                <div class="form-group">
                    <label for="dateDemande">Date de Demande *</label>
                    <input type="date" id="dateDemande" name="dateDemande" value="${demande.dateDemande}" required>
                </div>
                <button type="submit" class="btn-submit">Enregistrer</button>
                <a href="/demandes/details/${demande.idDemande}" class="btn-back">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>