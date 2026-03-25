<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter Demande</title>
    <style>
        body { font-family: Arial; margin: 20px; background: white; }
        .container { max-width: 600px; margin: 0 auto; }
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
        .content {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        h2 { margin-bottom: 20px; color: #333; }
        .form-group { margin-bottom: 15px; }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        .btn-submit {
            background: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-submit:hover { background: #218838; }
        .btn-back {
            background: #6c757d;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            margin-left: 10px;
            display: inline-block;
        }
        .btn-back:hover { background: #5a6268; }
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
            <a href="/devis">Devis</a>
        </div>
    </div>

    <div class="container">
        <div class="content">
            <h2>Ajouter une Demande de Forage</h2>
            
            <c:if test="${empty clients}">
                <div style="background: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; margin-bottom: 15px;">
                    ⚠️ Aucun client trouvé. Veuillez d'abord <a href="/clients/add">ajouter un client</a>.
                </div>
            </c:if>
            
            <form action="/demandes/add" method="post">
                <div class="form-group">
                    <label for="clientId">Client *</label>
                    <select id="clientId" name="clientId" required ${empty clients ? 'disabled' : ''}>
                        <option value="">Sélectionner un client</option>
                        <c:forEach items="${clients}" var="client">
                            <option value="${client.idClient}">${client.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="lieu">Lieu</label>
                    <input type="text" id="lieu" name="lieu" placeholder="Ex: Antananarivo">
                </div>
                
                <div class="form-group">
                    <label for="district">District</label>
                    <input type="text" id="district" name="district" placeholder="Ex: Antananarivo Renivohitra">
                </div>
                
                <div class="form-group">
                    <label for="dateDemande">Date de Demande</label>
                    <input type="date" id="dateDemande" name="dateDemande">
                    <small style="color: #666;">Laissez vide pour utiliser la date d'aujourd'hui</small>
                </div>
                
                <button type="submit" class="btn-submit" ${empty clients ? 'disabled' : ''}>Enregistrer</button>
                <a href="/demandes" class="btn-back">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>