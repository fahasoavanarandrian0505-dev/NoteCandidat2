<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter Devis</title>
    <style>
        body { font-family: Arial; margin: 20px; background: white; }
        .container { max-width: 900px; margin: 0 auto; }
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
        .details-table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }
        .details-table th, .details-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .details-table th {
            background-color: #e9ecef;
        }
        .details-table input {
            width: 100%;
            padding: 5px;
        }
        .btn-add-row {
            background: #28a745;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            margin: 10px 0;
        }
        .btn-remove-row {
            background: #dc3545;
            color: white;
            padding: 3px 8px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
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
        .total-container {
            background: #e9ecef;
            padding: 15px;
            border-radius: 5px;
            margin: 20px 0;
            text-align: right;
            font-size: 18px;
            font-weight: bold;
        }
        .total-value {
            color: #28a745;
            font-size: 22px;
        }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; }
            .nav-links a { margin: 5px; display: inline-block; }
            .details-table { display: block; overflow-x: auto; }
        }
    </style>
    <script>
        var ligneIndex = 0;
        
        function ajouterLigne() {
            ligneIndex++;
            var table = document.getElementById("detailsTable").getElementsByTagName('tbody')[0];
            var newRow = table.insertRow();
            
            var cell1 = newRow.insertCell(0);
            var cell2 = newRow.insertCell(1);
            var cell3 = newRow.insertCell(2);
            var cell4 = newRow.insertCell(3);
            var cell5 = newRow.insertCell(4);
            
            cell1.innerHTML = '<input type="text" name="details[' + ligneIndex + '].libelle" required placeholder="Libellé">';
            cell2.innerHTML = '<input type="number" name="details[' + ligneIndex + '].prixUnitaire" step="0.01" required placeholder="Prix" onchange="calculerTotalLigne(this)">';
            cell3.innerHTML = '<input type="number" name="details[' + ligneIndex + '].quantite" required placeholder="Qté" onchange="calculerTotalLigne(this)">';
            cell4.innerHTML = '<span class="ligne-total" id="totalLigne-' + ligneIndex + '">0.00</span>';
            cell5.innerHTML = '<button type="button" class="btn-remove-row" onclick="supprimerLigne(this)">Supprimer</button>';
            
            // Ajouter un champ caché pour l'index
            var hiddenIndex = document.createElement("input");
            hiddenIndex.type = "hidden";
            hiddenIndex.name = "details[" + ligneIndex + "].index";
            hiddenIndex.value = ligneIndex;
            newRow.appendChild(hiddenIndex);
        }
        
        function supprimerLigne(btn) {
            var row = btn.parentNode.parentNode;
            row.parentNode.removeChild(row);
            calculerTotalGeneral();
        }
        
        function calculerTotalLigne(element) {
            var row = element.parentNode.parentNode;
            var prixInput = row.cells[1].getElementsByTagName('input')[0];
            var qteInput = row.cells[2].getElementsByTagName('input')[0];
            var totalSpan = row.cells[3].getElementsByTagName('span')[0];
            
            var prix = parseFloat(prixInput.value) || 0;
            var qte = parseFloat(qteInput.value) || 0;
            var total = prix * qte;
            
            totalSpan.innerHTML = total.toFixed(2);
            calculerTotalGeneral();
        }
        
                function calculerTotalGeneral() {
            var totals = document.querySelectorAll('.ligne-total');
            var totalGeneral = 0;
            for (var i = 0; i < totals.length; i++) {
                totalGeneral += parseFloat(totals[i].innerHTML) || 0;
            }
            document.getElementById('totalGeneral').innerHTML = totalGeneral.toFixed(2) + ' Ar';
            document.getElementById('montantTotalInput').value = totalGeneral.toFixed(2);
            
            // Debug: Afficher dans la console
            console.log("Total calculé: " + totalGeneral.toFixed(2));
        }
                
        function verifierDemande() {
            var demandeId = document.getElementById('demandeId').value;
            if (demandeId) {
                document.getElementById('infoMessage').style.display = 'block';
                setTimeout(function() {
                    document.getElementById('infoMessage').style.display = 'none';
                }, 3000);
            }
        }
        
        function ajouterPremiereLigne() {
            if (document.getElementById("detailsTable").getElementsByTagName('tbody')[0].rows.length === 0) {
                ajouterLigne();
            }
        }
        
        window.onload = ajouterPremiereLigne;
    </script>
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
            <h2>Ajouter un Devis</h2>
            
            <div id="infoMessage" class="info-message" style="display: none; background: #d1ecf1; padding: 10px; border-radius: 4px; margin-bottom: 15px;">
                ✅ Demande sélectionnée
            </div>
            
            <form action="/devis/add" method="post">
                <!-- Référence Demande -->
                <div class="form-group">
                    <label for="demandeId">Référence Demande *</label>
                    <select id="demandeId" name="demandeId" required onchange="verifierDemande()">
                        <option value="">Sélectionner une demande</option>
                        <c:forEach items="${demandes}" var="demande">
                            <option value="${demande.idDemande}">
                                #${demande.idDemande} - ${demande.client.nom} - ${demande.lieu}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <!-- Type de devis -->
                <div class="form-group">
                    <label for="typeDevisId">Type *</label>
                    <select id="typeDevisId" name="typeDevisId" required>
                        <option value="">Sélectionner un type</option>
                        <c:forEach items="${typeDevis}" var="type">
                            <option value="${type.idTypeDevis}">${type.libelle}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <!-- Table des détails -->
                <label>Détails du devis *</label>
                <table class="details-table" id="detailsTable">
                    <thead>
                        <tr>
                            <th>Libellé</th>
                            <th>Prix Unitaire (Ar)</th>
                            <th>Quantité</th>
                            <th>Sous-Total (Ar)</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                
                <button type="button" class="btn-add-row" onclick="ajouterLigne()">+ Ajouter une ligne</button>
                
                <!-- Montant total caché -->
                <input type="hidden" id="montantTotalInput" name="montantTotal" value="0">
                
                <!-- Affichage du total général -->
                <div class="total-container">
                    <span>Total Général : </span>
                    <span class="total-value" id="totalGeneral">0.00 Ar</span>
                </div>
                
                <button type="submit" class="btn-submit">Enregistrer le devis</button>
                <a href="/devis" class="btn-back">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>