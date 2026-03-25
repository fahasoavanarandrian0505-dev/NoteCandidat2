<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de Forage</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: white; }
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
        .cards-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        .card {
            background: #f8f9fa;
            padding: 30px;
            border-radius: 8px;
            text-align: center;
            border: 1px solid #ddd;
        }
        .card .icon { font-size: 48px; margin-bottom: 15px; }
        .card h2 { color: #333; margin-bottom: 15px; }
        .card .btn {
            display: inline-block;
            padding: 8px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .card .btn:hover { background-color: #0056b3; }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; }
            .nav-links a { margin: 5px; display: inline-block; }
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>Gestion de Forage - ETU 3615</h1>
        <div class="nav-links">
            <a href="/">Accueil</a>
            <a href="/clients">Clients</a>
            <a href="/demandes">Demandes</a>
            <a href="/devis">Devis</a>
        </div>
    </div>

    <div class="cards-grid">
        <div class="card">
            <div class="icon">👥</div>
            <h2>Clients</h2>
            <a href="/clients" class="btn">VOIR</a>
        </div>

        <div class="card">
            <div class="icon">📋</div>
            <h2>Demandes</h2>
            <a href="/demandes" class="btn">VOIR</a>
        </div>
    </div>
</body>
</html>