<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Demande</title>
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
            background: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-submit:hover { background: #0056b3; }
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
            <h2>Modifier la Demande</h2>
            
            <form action="/demandes/update" method="post">
                <input type="hidden" name="idDemande" value="${demande.idDemande}">
                
                <div class="form-group">
                    <label for="clientId">Client *</label>
                    <select id="clientId" name="clientId" required>
                        <c:forEach items="${clients}" var="client">
                            <option value="${client.idClient}" ${demande.client.idClient == client.idClient ? 'selected' : ''}>
                                ${client.nom}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="lieu">Lieu</label>
                    <input type="text" id="lieu" name="lieu" value="${demande.lieu}">
                </div>
                
                <div class="form-group">
                    <label for="district">District</label>
                    <input type="text" id="district" name="district" value="${demande.district}">
                </div>
                
                <div class="form-group">
                    <label for="dateDemande">Date de Demande</label>
                    <input type="date" id="dateDemande" name="dateDemande" value="${demande.dateDemande}">
                </div>
                
                <button type="submit" class="btn-submit">Modifier</button>
                <a href="/demandes" class="btn-back">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>