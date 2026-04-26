<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord - Gestion de Forage</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #a5a4a4;
            min-height: 100vh;
        }

        /* Navbar */
        .navbar {
            background: #0d1b3e;
            padding: 15px 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            border-bottom: 1px solid #2c3e6d;
        }

        .navbar h1 {
            color: white;
            margin: 0;
            font-size: 24px;
        }

        .nav-links a {
            margin-left: 20px;
            text-decoration: none;
            color: #c8d6e5;
            padding: 8px 16px;
            border-radius: 5px;
            transition: background 0.3s;
            font-weight: 500;
        }

        .nav-links a:hover {
            background: #2c3e6d;
            color: white;
        }

        /* Container principal */
        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }

        /* Titre de bienvenue */
        .welcome {
            background: #0d1b3e;
            padding: 25px 30px;
            border-radius: 10px;
            margin-bottom: 30px;
            border-left: 5px solid #3498db;
            color: white;
        }

        .welcome h2 {
            margin-bottom: 8px;
            font-size: 28px;
        }

        .welcome p {
            color: #c8d6e5;
            font-size: 16px;
        }

        /* Cartes du tableau de bord */
        .dashboard-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 25px;
            margin-bottom: 40px;
        }

        .card-dashboard {
            background: #0d1b3e;
            border-radius: 10px;
            padding: 25px;
            transition: transform 0.3s;
            text-align: center;
            border: 1px solid #2c3e6d;
        }

        .card-dashboard:hover {
            transform: translateY(-5px);
            background: #12234a;
        }

        .card-value {
            font-size: 42px;
            font-weight: bold;
            margin: 15px 0;
            color: #3498db;
        }

        .card-label {
            color: #c8d6e5;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 20px;
        }

        .card-link {
            display: inline-block;
            padding: 8px 20px;
            background: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 13px;
            transition: background 0.3s;
        }

        .card-link:hover {
            background: #2980b9;
        }

        /* Section des statuts */
        .statuts-section {
            background: #0d1b3e;
            border-radius: 10px;
            padding: 25px;
            border: 1px solid #2c3e6d;
        }

        .section-title {
            font-size: 22px;
            color: white;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #3498db;
        }

        .statuts-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-top: 15px;
        }

        .statut-card {
            background: #12234a;
            border-radius: 8px;
            padding: 15px;
            text-align: center;
            border: 1px solid #2c3e6d;
            transition: transform 0.3s;
        }

        .statut-card:hover {
            transform: translateY(-3px);
            background: #162a55;
        }

        .statut-nom {
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 10px;
            color: #c8d6e5;
        }

        .statut-nombre {
            font-size: 32px;
            font-weight: bold;
            margin: 10px 0;
            color: #3498db;
        }

        .statut-btn {
            display: inline-block;
            margin-top: 10px;
            padding: 5px 12px;
            background: #2c3e6d;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 12px;
            transition: background 0.3s;
        }

        .statut-btn:hover {
            background: #3498db;
        }

        .statut-zero {
            color: #6c8eb0;
            font-size: 12px;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .navbar {
                flex-direction: column;
                text-align: center;
                gap: 10px;
            }
            .nav-links a {
                margin: 5px;
                display: inline-block;
            }
            .card-value {
                font-size: 32px;
            }
            .dashboard-cards, .statuts-grid {
                grid-template-columns: 1fr;
            }
            .container {
                padding: 0 15px;
            }
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
        <div class="welcome">
            <h2>Tableau de bord</h2>
        </div>

        <div class="dashboard-cards">
            <div class="card-dashboard">
                <div class="card-value">
                    <fmt:formatNumber value="${chiffreAffaire}" type="number" minFractionDigits="0" maxFractionDigits="0"/> Ar
                </div>
                <div class="card-label">Chiffre d'affaire total</div>
                <a href="/devis" class="card-link">Voir les details</a>
            </div>

            <div class="card-dashboard">
                <div class="card-value">${nbClients}</div>
                <div class="card-label">Clients</div>
                <a href="/clients" class="card-link">Voir la liste</a>
            </div>

            <div class="card-dashboard">
                <div class="card-value">${nbDevis}</div>
                <div class="card-label">Devis crees</div>
                <a href="/devis" class="card-link">Voir la liste</a>
            </div>
        </div>

        <div class="statuts-section">
            <h2 class="section-title">Statistiques par statut</h2>
            <div class="statuts-grid">
                <c:forEach items="${statistiquesStatuts}" var="entry">
                    <c:set var="libelle" value="${entry.key}" />
                    <c:set var="nombre" value="${entry.value}" />
                    <div class="statut-card">
                        <div class="statut-nom">${libelle}</div>
                        <div class="statut-nombre">${nombre}</div>
                        <c:if test="${nombre > 0}">
                            <a href="/demandes?statut=${libelle}" class="statut-btn">Voir les details</a>
                        </c:if>
                        <c:if test="${nombre == 0}">
                            <span class="statut-zero">Aucune demande</span>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>