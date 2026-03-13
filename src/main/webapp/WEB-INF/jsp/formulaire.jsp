<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calcul Note Matière - Candidat</title>
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
            max-width: 500px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
            text-align: center;
            font-size: 28px;
        }

        .subtitle {
            color: #666;
            text-align: center;
            margin-bottom: 30px;
            font-size: 14px;
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

        input[type="number"],
        select {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }

        input[type="number"]:focus,
        select:focus {
            outline: none;
            border-color: #000000;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
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

        .btn-submit {
            background: #000000;
            color: white;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-reset {
            background: #f0f0f0;
            color: #333;
            border: 2px solid #e0e0e0;
        }

        .btn-reset:hover {
            background: #e8e8e8;
        }

        .info-box {
            background: #f9f9f9;
            border-left: 4px solid #667eea;
            padding: 15px;
            margin-top: 20px;
            border-radius: 5px;
            font-size: 13px;
            color: #666;
        }

        .error {
            display: none;
            background: #fee;
            color: #c33;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            border-left: 4px solid #c33;
        }

        .error.show {
            display: block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📊 Calcul Note Finale</h1>
        <p class="subtitle">Entrez l'ID du candidat et de la matière</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error show">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form method="POST" action="/calculer-note">
            <div class="form-group">
                <label for="idCandidat">ID du Candidat *</label>
                <input type="number" 
                       id="idCandidat" 
                       name="idCandidat" 
                       placeholder="Ex: 1" 
                       min="1"
                       required>
            </div>

            <div class="form-group">
                <label for="idMatiere">ID de la Matière *</label>
                <input type="number" 
                       id="idMatiere" 
                       name="idMatiere" 
                       placeholder="Ex: 1" 
                       min="1"
                       required>
            </div>

            <div class="button-group">
                <button type="submit" class="btn-submit">Voir Note Finale</button>
                <button type="reset" class="btn-reset">Effacer</button>
            </div>
        </form>

    </div>
</body>
</html>
