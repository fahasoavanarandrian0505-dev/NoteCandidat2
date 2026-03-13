<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.app.dto.NoteFinaleResponse"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Résultat - Calcul Note Matière</title>
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
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            padding: 40px;
            width: 100%;
            max-width: 600px;
        }

        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 28px;
        }

        .result-header {
            background: #000000;
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            text-align: center;
        }

        .note-finale {
            font-size: 48px;
            font-weight: bold;
            margin: 10px 0;
        }

        .info-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 30px;
        }

        .info-card {
            background: #f9f9f9;
            padding: 15px;
            border-radius: 8px;
            border-left: 4px solid #000000;
        }

        .info-card h3 {
            color: #000000;
            font-size: 12px;
            text-transform: uppercase;
            margin-bottom: 8px;
        }

        .info-card p {
            color: #333;
            font-size: 16px;
            font-weight: 600;
        }

        .details-section {
            background: #f0f0f0;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            border-left: 4px solid #000000;
        }

        .details-section h3 {
            color: #000000;
            margin-bottom: 15px;
            font-size: 16px;
        }

        .detail-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #e0e0e0;
        }

        .detail-row:last-child {
            border-bottom: none;
        }

        .detail-label {
            color: #666;
            font-size: 14px;
        }

        .detail-value {
            color: #333;
            font-weight: 600;
            font-size: 14px;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
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
        }

        .btn-nouveau {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-nouveau:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .success-badge {
            display: inline-block;
            background: #4caf50;
            color: white;
            padding: 8px 12px;
            border-radius: 5px;
            font-size: 12px;
            margin-top: 10px;
        }

        .condition {
            padding: 10px;
            border-radius: 5px;
            margin-top: 10px;
            font-size: 14px;
        }

        .condition.true {
            background: #d4edda;
            color: #155724;
            border-left: 4px solid #28a745;
        }

        .condition.false {
            background: #f8d7da;
            color: #721c24;
            border-left: 4px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>✓ Résultat du Calcul</h1>

        <% 
            NoteFinaleResponse resp = (NoteFinaleResponse) request.getAttribute("response");
            if (resp != null) {
        %>

        <div class="result-header">
            <div style="font-size: 14px; opacity: 0.9;">Note finale de</div>
            <div style="font-size: 18px; margin: 5px 0;"><%= resp.getNomCandidat() %></div>
            <div style="font-size: 14px; opacity: 0.9;">en <%= resp.getNomMatiere() %></div>
            <div class="note-finale"><%= String.format("%.2f", resp.getNoteFinale()) %>/20</div>
        </div>

        <div class="info-grid">
            <div class="info-card">
                <h3>Candidat</h3>
                <p><%= resp.getNomCandidat() %></p>
            </div>
            <div class="info-card">
                <h3>Matière</h3>
                <p><%= resp.getNomMatiere() %></p>
            </div>
        </div>

        <div class="details-section">
            <h3>📋 Détails du Calcul</h3>
            
            <div class="detail-row">
                <span class="detail-label">Notes du candidat:</span>
                <span class="detail-value">
                    <% 
                        for (Double note : resp.getNotes()) {
                            out.print(note + " ");
                        }
                    %>
                </span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Note minimale:</span>
                <span class="detail-value"><%= String.format("%.2f", resp.getNoteMinimale()) %></span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Note maximale:</span>
                <span class="detail-value"><%= String.format("%.2f", resp.getNoteMaximale()) %></span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Écart entre les notes:</span>
                <span class="detail-value"><%= String.format("%.2f", resp.getEcartNotes()) %></span>
            </div>
        </div>

        <div class="details-section">
            <h3>⚙️ Paramètres Appliqués</h3>
            
            <div class="detail-row">
                <span class="detail-label">Différence configurée:</span>
                <span class="detail-value"><%= String.format("%.2f", resp.getParametreDifference()) %></span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Opérateur:</span>
                <span class="detail-value">
                    <% 
                        String op = resp.getOperateur();
                        String symb = op.equals("superieur") ? ">=" : "<=";
                    %>
                    <%= symb + " (" + op + ")" %>
                </span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Résolution appliquée:</span>
                <span class="detail-value">
                    <% 
                        String res = resp.getResolution();
                        String descr = "";
                        if (res.equals("plusPetit")) descr = "Prendre le minimum";
                        else if (res.equals("moyenne")) descr = "Calculer la moyenne";
                        else if (res.equals("plusGrand")) descr = "Prendre le maximum";
                    %>
                    <%= descr %>
                </span>
            </div>
        </div>

        <div class="condition <%= resp.getConditionRemplie() ? "true" : "false" %>">
            <strong><%= resp.getConditionRemplie() ? "✓ Condition remplie" : "✗ Condition non remplie" %></strong><br>
            Écart <%= resp.getEcartNotes() %> 
            <%= resp.getOperateur().equals("superieur") ? ">=" : "<=" %> 
            Différence <%= resp.getParametreDifference() %>
        </div>

        <div class="details-section" style="background: #fffaf0; border-left-color: #ffa500;">
            <div class="detail-row">
                <span class="detail-label"><strong>Méthode de calcul:</strong></span>
                <span class="detail-value"><%= resp.getMethodeCalcul() %></span>
            </div>
        </div>

        <div class="button-group">
            <button class="btn-nouveau" onclick="window.location.href='/'">← Nouveau Calcul</button>
        </div>

        <% } else { %>
            <p style="color: red; text-align: center;">Erreur: Données non disponibles</p>
        <% } %>
    </div>
</body>
</html>
