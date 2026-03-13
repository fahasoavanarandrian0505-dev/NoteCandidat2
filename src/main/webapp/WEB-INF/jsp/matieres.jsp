<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.app.entity.Matiere" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Matières</title>
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
            padding: 20px;
        }
        .navbar {
            background: white;
            padding: 15px 20px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 10px;
        }
        .navbar a {
            color: #000000;
            text-decoration: none;
            font-weight: 600;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #000000;
            margin-bottom: 20px;
        }
        .btn-group {
            margin-bottom: 30px;
            display: flex;
            gap: 10px;
        }
        a.btn {
            padding: 10px 20px;
            background: #000000;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
        }
        a.btn:hover {
            opacity: 0.9;
        }
        .table-responsive {
            overflow-x: auto;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table th {
            background: #000000;
            color: white;
            padding: 12px;
            text-align: left;
        }
        table td {
            padding: 12px;
            border-bottom: 1px solid #eee;
        }
        table tr:hover {
            background: #f9f9f9;
        }
        .btn-edit, .btn-delete {
            padding: 5px 10px;
            margin: 2px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-weight: 600;
        }
        .btn-edit {
            background: #666666;
            color: white;
            text-decoration: none;
            display: inline-block;
        }
        .btn-delete {
            background: #f44336;
            color: white;
        }
        .btn-delete:hover {
            opacity: 0.8;
        }
        .empty-message {
            text-align: center;
            color: #999;
            padding: 40px;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="/">← Retour à l'accueil</a>
    </div>

    <div class="container">
        <h1>📚 Gestion des Matières</h1>
        
        <div class="btn-group">
            <a href="/matieres/nouveau" class="btn">+ Ajouter une Matière</a>
        </div>

        <div class="table-responsive">
            <%
                List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
                if (matieres != null && !matieres.isEmpty()) {
            %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Matiere matiere : matieres) {
                    %>
                    <tr>
                        <td><%= matiere.getIdMatiere() %></td>
                        <td><%= matiere.getNomMatiere() %></td>
                        <td>
                            <a href="/matieres/editer/<%= matiere.getIdMatiere() %>" class="btn-edit">✎ Modifier</a>
                            <form style="display:inline;" method="POST" action="/matieres/supprimer/<%= matiere.getIdMatiere() %>" onsubmit="return confirm('Êtes-vous sûr?');">
                                <button type="submit" class="btn-delete">✕ Supprimer</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
                } else {
            %>
            <div class="empty-message">
                <p>Aucune matière enregistrée. <a href="/matiere/nouveau">Créez-en une!</a></p>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
