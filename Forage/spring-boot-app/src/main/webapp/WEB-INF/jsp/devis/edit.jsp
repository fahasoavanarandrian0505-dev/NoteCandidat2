<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Devis #${devis.idDevis}</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #a5a4a4; min-height: 100vh; }
        .navbar { background: #0d1b3e; padding: 15px 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.2); display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; border-bottom: 1px solid #2c3e6d; }
        .navbar h1 { color: white; margin: 0; font-size: 24px; }
        .nav-links a { margin-left: 20px; text-decoration: none; color: #c8d6e5; padding: 8px 16px; border-radius: 5px; transition: background 0.3s; font-weight: 500; }
        .nav-links a:hover { background: #2c3e6d; color: white; }
        .container { max-width: 1000px; margin: 30px auto; padding: 0 20px; }
        .content { background: #0d1b3e; border-radius: 10px; padding: 25px; border: 1px solid #2c3e6d; }
        h2 { color: white; margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #3498db; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #c8d6e5; }
        input, select { width: 100%; padding: 10px; border: 1px solid #2c3e6d; border-radius: 5px; background: #12234a; color: white; font-size: 14px; box-sizing: border-box; }
        input:focus, select:focus { outline: none; border-color: #3498db; }
        .info-client { background: #12234a; padding: 15px; border-radius: 5px; margin: 15px 0; border-left: 4px solid #28a745; }
        .info-client h3 { color: white; margin-bottom: 10px; }
        .info-client p { color: #c8d6e5; margin: 5px 0; }
        .info-remise { background: #12234a; padding: 10px 15px; margin: 15px 0; border-radius: 5px; border-left: 4px solid #ffc107; font-size: 13px; color: #c8d6e5; }
        table { width: 100%; border-collapse: collapse; margin: 15px 0; }
        th, td { border: 1px solid #2c3e6d; padding: 8px; text-align: left; color: #c8d6e5; }
        th { background-color: #12234a; color: white; }
        td input { width: 100%; padding: 5px; background: #12234a; border: 1px solid #2c3e6d; color: white; border-radius: 3px; }
        .btn { padding: 8px 15px; border: none; border-radius: 5px; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
        .btn-add { background: #28a745; color: white; margin: 10px 0; }
        .btn-submit { background: #3498db; color: white; }
        .btn-back { background: #6c757d; color: white; margin-left: 10px; }
        .btn-remove { background: #dc3545; color: white; padding: 5px 10px; font-size: 12px; }
        .total-container { background: #12234a; padding: 15px; border-radius: 5px; margin: 20px 0; text-align: right; font-size: 18px; border: 1px solid #2c3e6d; }
        .total-value { color: #28a745; font-size: 22px; font-weight: bold; }
        .prix-remise { background-color: #1a3a2a !important; border: 1px solid #28a745 !important; }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; gap: 10px; }
            .nav-links a { margin: 5px; display: inline-block; }
            table { display: block; overflow-x: auto; }
        }
    </style>
    <script>
        let ligneIndex = ${details.size()};
        let demandesData = [];
        <c:forEach items="${demandes}" var="demande">
        demandesData.push({ id: ${demande.idDemande}, clientNom: "${demande.client.nom}", clientEmail: "${demande.client.email != null ? demande.client.email : ''}", lieu: "${demande.lieu != null ? demande.lieu : ''}", district: "${demande.district != null ? demande.district : ''}" });
        </c:forEach>
        function afficherInfoClient() {
            let demandeId = document.getElementById('demandeId').value;
            let infoDiv = document.getElementById('infoClient');
            if (demandeId) {
                let demande = demandesData.find(d => d.id == demandeId);
                if (demande) {
                    infoDiv.innerHTML = `<h3>Informations du client</h3><p><strong>Nom :</strong> ${demande.clientNom}</p><p><strong>Email :</strong> ${demande.clientEmail || '-'}</p><p><strong>Lieu :</strong> ${demande.lieu || '-'}</p><p><strong>District :</strong> ${demande.district || '-'}</p>`;
                    infoDiv.style.display = 'block';
                } else { infoDiv.style.display = 'none'; }
            } else { infoDiv.style.display = 'none'; }
        }
        function appliquerRemise(element) {
            let prix = parseFloat(element.value) || 0;
            if (prix >= 1000000) { let prixRemise = prix * 0.9; element.value = prixRemise.toFixed(0); element.classList.add('prix-remise'); }
            else { element.classList.remove('prix-remise'); }
            calculerTotalLigne(element);
        }
        function ajouterLigne() {
            let table = document.getElementById("detailsTable").getElementsByTagName('tbody')[0];
            let newRow = table.insertRow();
            let idx = ligneIndex++;
            newRow.innerHTML = `<td><input type="text" name="libelle" required placeholder="Libelle"></td><td><input type="number" name="prixUnitaire" step="0.01" required placeholder="Prix" onblur="appliquerRemise(this)" onchange="calculerTotalLigne(this)"></td><td><input type="number" name="quantite" required placeholder="Qte" onchange="calculerTotalLigne(this)"></td><td><span class="ligne-total" id="totalLigne-${idx}">0</span></td><td><button type="button" class="btn btn-remove" onclick="supprimerLigne(this)">Supprimer</button></td>`;
        }
        function supprimerLigne(btn) { btn.parentElement.parentElement.remove(); calculerTotalGeneral(); }
        function calculerTotalLigne(element) {
            let row = element.parentElement.parentElement;
            let prix = parseFloat(row.cells[1].querySelector('input').value) || 0;
            let qte = parseFloat(row.cells[2].querySelector('input').value) || 0;
            row.cells[3].querySelector('span').innerHTML = (prix * qte).toFixed(0);
            calculerTotalGeneral();
        }
        function calculerTotalGeneral() {
            let totals = document.querySelectorAll('.ligne-total');
            let totalGeneral = 0;
            totals.forEach(t => totalGeneral += parseFloat(t.innerHTML) || 0);
            document.getElementById('totalGeneral').innerHTML = totalGeneral.toFixed(0) + ' Ar';
        }
        function chargerDetailsExistants() {
            <c:forEach items="${details}" var="detail" varStatus="status">
                let table = document.getElementById("detailsTable").getElementsByTagName('tbody')[0];
                let newRow = table.insertRow();
                let prix = ${detail.prixUnitaire};
                let qte = ${detail.quantite};
                newRow.innerHTML = `<td><input type="text" name="libelle" value="${detail.libelle}" required></td><td><input type="number" name="prixUnitaire" step="0.01" value="${prix}" required onblur="appliquerRemise(this)" onchange="calculerTotalLigne(this)"></td><td><input type="number" name="quantite" value="${qte}" required onchange="calculerTotalLigne(this)"></td><td><span class="ligne-total">${prix * qte}</span></td><td><button type="button" class="btn btn-remove" onclick="supprimerLigne(this)">Supprimer</button></td>`;
                if (${detail.prixUnitaire} >= 1000000) { newRow.cells[1].querySelector('input').classList.add('prix-remise'); }
            </c:forEach>
            calculerTotalGeneral();
        }
        window.onload = function() {
            chargerDetailsExistants();
            document.getElementById('demandeId').value = ${devis.demande.idDemande};
            document.getElementById('typeDevisId').value = ${devis.typeDevis.idTypeDevis};
            afficherInfoClient();
        };
    </script>
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
        <div class="content">
            <h2>Modifier Devis #${devis.idDevis}</h2>
            <form action="/devis/update" method="post">
                <input type="hidden" name="id" value="${devis.idDevis}">
                <div class="form-group">
                    <label for="demandeId">Demande *</label>
                    <select id="demandeId" name="demandeId" required onchange="afficherInfoClient()">
                        <option value="">-- Selectionner une demande --</option>
                        <c:forEach items="${demandes}" var="demande">
                            <option value="${demande.idDemande}">#${demande.idDemande} - ${demande.client.nom} - ${demande.lieu != null ? demande.lieu : 'Sans lieu'}</option>
                        </c:forEach>
                    </select>
                </div>
                <div id="infoClient" class="info-client" style="display: none;"></div>
                <div class="form-group">
                    <label for="typeDevisId">Type de devis *</label>
                    <select id="typeDevisId" name="typeDevisId" required>
                        <option value="">-- Selectionner un type --</option>
                        <c:forEach items="${typeDevis}" var="type">
                            <option value="${type.idTypeDevis}">${type.libelle}</option>
                        </c:forEach>
                    </select>
                </div>
                <label>Details du devis *</label>
                <table id="detailsTable">
                    <thead><tr><th>Libelle</th><th>Prix unitaire (Ar)</th><th>Quantite</th><th>Sous-total (Ar)</th><th>Action</th></tr></thead>
                    <tbody></tbody>
                </table>
                <button type="button" class="btn btn-add" onclick="ajouterLigne()">+ Ajouter une ligne</button>
                <div class="total-container"><span>Total General : </span><span class="total-value" id="totalGeneral">0 Ar</span></div>
                <div style="margin-top: 20px;">
                    <button type="submit" class="btn btn-submit">Mettre a jour</button>
                    <a href="/devis" class="btn btn-back">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>