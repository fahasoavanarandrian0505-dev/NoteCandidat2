<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Notes - Accueil</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #003d82;
            min-height: 100vh;
            padding: 20px;
        }
        .navbar {
            background: #1e5a96;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 10px;
        }
        .navbar h1 {
            color: #ffffff;
            font-size: 24px;
        }
        .nav-links {
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
        }
        .nav-links a {
            padding: 10px 15px;
            background: #2d7ac4;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: all 0.3s;
            font-weight: 600;
        }
        .nav-links a:hover {
            background: #0d47a1;
            color: white;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
        }
        .cards-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .card {
            background: #1e5a96;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .card h2 {
            color: #ffffff;
            margin-bottom: 15px;
            font-size: 22px;
        }
        .card p {
            color: #e0e0e0;
            margin-bottom: 20px;
            font-size: 14px;
        }
        .card .btn {
            display: inline-block;
            padding: 12px 20px;
            background: #2d7ac4;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
            transition: all 0.3s;
            margin: 5px;
        }
        .card .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(45, 122, 196, 0.5);
        }
        .feature-list {
            background: #1e5a96;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
        }
        .feature-list h3 {
            color: #667eea;
            margin-bottom: 20px;
        }
        .feature-list ul {
            list-style: none;
            color: #666;
        }
        .feature-list li {
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }
        .feature-list li:before {
            content: "✓ ";
            color: #4caf50;
            font-weight: bold;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>📊 Système de Gestion des Notes - ETU 3615</h1>
        <div class="nav-links">
            <a href="/">Accueil</a>
            <a href="/candidats">Candidats</a>
            <a href="/matieres">Matières</a>
            <a href="/notes">Notes</a>
            <a href="/calculer">Calcul Note</a>
        </div>
    </div>

    <div class="container">
        <div class="cards-grid">
            <div class="card">
                <h2>👥 Candidats</h2>
                <a href="/candidats" class="btn">Voir</a>
            </div>

            <div class="card">
                <h2>📚 Matières</h2>
                <a href="/matieres" class="btn">Voir</a>
            </div>

            <div class="card">
                <h2>📝 Notes</h2>
                <a href="/notes" class="btn">Voir</a>
            </div>

            <div class="card">
                <h2>🔍 Calcul Note</h2>
                <a href="/calculer" class="btn">Voir</a>
            </div>
        </div>

    </div>
</body>
</html>
