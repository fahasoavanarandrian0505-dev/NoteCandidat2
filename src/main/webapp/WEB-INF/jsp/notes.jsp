<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.app.entity.Note" %>
<%@ page import="com.example.app.entity.Candidat" %>
<%@ page import="com.example.app.entity.Matiere" %>
<%@ page import="com.example.app.entity.Correcteur" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Notes</title>
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
        }
        h1 {
            color: white;
            margin-bottom: 30px;
            text-align: center;
        }
        .form-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
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
        select, input[type="number"] {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        select:focus, input:focus {
            outline: none;
            border-color: #000000;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        button {
            flex: 1;
            padding: 12px 20px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s;
            background: #000000;
            color: white;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
        }
        .table-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table th {
            background: #667eea;
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
        .btn-delete {
            padding: 5px 10px;
            border: none;
            background: #f44336;
            color: white;
            border-radius: 3px;
            cursor: pointer;
            font-weight: 600;
        }
        .btn-delete:hover {
            opacity: 0.8;
        }
        .empty-message {
            text-align: center;
            color: #999;
            padding: 40px;
        }
        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="/">← Retour à l'accueil</a>
    </div>

    <div class="container">
        <h1>📝 Gestion des Notes</h1>

        <div class="form-card">
            <h2 style="color: #667eea; margin-bottom: 20px;">➕ Ajouter une Note</h2>
            
            <form method="POST" action="/notes/creer">
                <div class="form-row">
                    <div class="form-group">
                        <label for="candidat">Candidat *</label>
                        <select id="candidat" name="idCandidat" required>
                            <option value="">-- Sélectionner --</option>
                            <%
                                List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");
                                if (candidats != null) {
                                    for (Candidat c : candidats) {
                            %>
                            <option value="<%= c.getIdCandidat() %>"><%= c.getIdCandidat() %> - <%= c.getNom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="matiere">Matière *</label>
                        <select id="matiere" name="idMatiere" required>
                            <option value="">-- Sélectionner --</option>
                            <%
                                List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
                                if (matieres != null) {
                                    for (Matiere m : matieres) {
                            %>
                            <option value="<%= m.getIdMatiere() %>"><%= m.getIdMatiere() %> - <%= m.getNomMatiere() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="correcteur">Correcteur *</label>
                        <select id="correcteur" name="idCorrecteur" required>
                            <option value="">-- Sélectionner --</option>
                            <%
                                List<Correcteur> correcteurs = (List<Correcteur>) request.getAttribute("correcteurs");
                                if (correcteurs != null) {
                                    for (Correcteur co : correcteurs) {
                            %>
                            <option value="<%= co.getIdCorrecteur() %>"><%= co.getIdCorrecteur() %> - <%= co.getNom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="note">Note (0-20) *</label>
                        <input type="number" 
                               id="note" 
                               name="note" 
                               placeholder="Ex: 15.5" 
                               min="0" 
                               max="20" 
                               step="0.5" 
                               required>
                    </div>
                </div>

                <div class="button-group">
                    <button type="submit">✓ Ajouter Note</button>
                </div>
            </form>
        </div>

        <div class="table-card">
            <h2 style="color: #667eea; margin-bottom: 20px;">📊 Toutes les Notes</h2>
            
            <%
                List<Note> notes = (List<Note>) request.getAttribute("notes");
                if (notes != null && !notes.isEmpty()) {
            %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Candidat</th>
                        <th>Matière</th>
                        <th>Correcteur</th>
                        <th>Note</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Note n : notes) {
                    %>
                    <tr>
                        <td><%= n.getIdNote() %></td>
                        <td><%= n.getCandidat() != null ? n.getCandidat().getNom() : "N/A" %></td>
                        <td><%= n.getMatiere() != null ? n.getMatiere().getNomMatiere() : "N/A" %></td>
                        <td><%= n.getCorrecteur() != null ? n.getCorrecteur().getNom() : "N/A" %></td>
                        <td><strong><%= n.getNote() %>/20</strong></td>
                        <td>
                            <form style="display:inline;" method="POST" action="/notes/supprimer/<%= n.getIdNote() %>" onsubmit="return confirm('Êtes-vous sûr?');">
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
                <p>Aucune note enregistrée. Créez-en une!</p>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
