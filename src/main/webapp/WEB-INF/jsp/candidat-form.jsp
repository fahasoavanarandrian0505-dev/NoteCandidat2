<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.app.entity.Candidat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter/Modifier Candidat</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f0f0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 500px;
        }
        h1 {
            color: #000000;
            margin-bottom: 20px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 600;
            font-size: 14px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        input:focus {
            outline: none;
            border-color: #000000;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }
        button, a {
            flex: 1;
            padding: 12px 20px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s;
            text-align: center;
            text-decoration: none;
        }
        .btn-submit {
            background: #000000;
            color: white;
        }
        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
        }
        .btn-cancel {
            background: #f0f0f0;
            color: #333;
            border: 2px solid #e0e0e0;
        }
        .btn-cancel:hover {
            background: #e8e8e8;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            Candidat candidat = (Candidat) request.getAttribute("candidat");
            boolean isEdit = candidat != null;
        %>
        
        <h1><%= isEdit ? "✎ Modifier Candidat" : "➕ Ajouter Candidat" %></h1>

        <form method="POST" action="<%= isEdit ? "/candidats/mettre-a-jour/" + candidat.getIdCandidat() : "/candidats/creer" %>">
            <div class="form-group">
                <label for="nom">Nom du Candidat *</label>
                <input type="text" 
                       id="nom" 
                       name="nom" 
                       placeholder="Ex: Ahmed Ben Ali" 
                       value="<%= isEdit ? candidat.getNom() : "" %>"
                       required>
            </div>

            <div class="button-group">
                <button type="submit" class="btn-submit">✓ <%= isEdit ? "Mettre à jour" : "Créer" %></button>
                <a href="/candidats" class="btn-cancel">✕ Annuler</a>
            </div>
        </form>
    </div>
</body>
</html>
