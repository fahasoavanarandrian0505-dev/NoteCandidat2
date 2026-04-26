<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Details de la Demande #${demande.idDemande}</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #a5a4a4; min-height: 100vh; }
        .navbar { background: #0d1b3e; padding: 15px 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.2); display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; border-bottom: 1px solid #2c3e6d; }
        .navbar h1 { color: white; margin: 0; font-size: 24px; }
        .nav-links a { margin-left: 20px; text-decoration: none; color: #c8d6e5; padding: 8px 16px; border-radius: 5px; transition: background 0.3s; font-weight: 500; }
        .nav-links a:hover { background: #2c3e6d; color: white; }
        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }
        .content { background: #0d1b3e; border-radius: 10px; padding: 25px; border: 1px solid #2c3e6d; }
        h2, h3 { color: white; margin-bottom: 15px; padding-bottom: 10px; border-bottom: 2px solid #3498db; }
        .info-section { background: #12234a; padding: 15px; border-radius: 8px; margin-bottom: 20px; }
        .info-row { display: flex; padding: 8px 0; border-bottom: 1px solid #2c3e6d; }
        .info-label { font-weight: bold; width: 150px; color: #c8d6e5; }
        .info-value { flex: 1; color: white; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #2c3e6d; padding: 10px; text-align: left; color: #c8d6e5; }
        th { background-color: #12234a; color: white; }
        .btn { padding: 8px 15px; text-decoration: none; margin: 5px; display: inline-block; border-radius: 5px; border: none; cursor: pointer; font-size: 13px; }
        .btn-primary { background: #3498db; color: white; }
        .btn-warning { background: #ffc107; color: #333; }
        .btn-secondary { background: #6c757d; color: white; }
        .statut-badge { display: inline-block; padding: 5px 12px; border-radius: 5px; font-size: 13px; font-weight: bold; }
        .statut-cree { background: #6c757d; color: white; }
        .statut-valide { background: #28a745; color: white; }
        .statut-rejeter { background: #dc3545; color: white; }
        .observation-edit { margin-left: 10px; padding: 3px 10px; font-size: 11px; background: #3498db; color: white; border: none; border-radius: 3px; cursor: pointer; }
        .modal { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.7); z-index: 1000; }
        .modal-content { background: #0d1b3e; width: 500px; margin: 100px auto; padding: 25px; border-radius: 10px; border: 1px solid #2c3e6d; }
        .modal-content h3 { color: white; margin-bottom: 15px; }
        .modal-content textarea { width: 100%; padding: 10px; background: #12234a; border: 1px solid #2c3e6d; color: white; border-radius: 5px; min-height: 100px; }
        @media (max-width: 768px) {
            .navbar { flex-direction: column; text-align: center; gap: 10px; }
            .nav-links a { margin: 5px; display: inline-block; }
            .info-row { flex-direction: column; }
            .info-label { width: auto; margin-bottom: 5px; }
            table { display: block; overflow-x: auto; }
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
        <div class="content">
            <h2>Details de la Demande #${demande.idDemande}</h2>
            
            <div class="info-section">
                <h3>Informations Client</h3>
                <div class="info-row"><div class="info-label">Nom du Client :</div><div class="info-value">${demande.client.nom}</div></div>
                <div class="info-row"><div class="info-label">Email :</div><div class="info-value">${demande.client.email != null ? demande.client.email : '-'}</div></div>
            </div>
            
            <div class="info-section">
                <h3>Informations Demande</h3>
                <div class="info-row"><div class="info-label">Lieu :</div><div class="info-value">${demande.lieu != null ? demande.lieu : '-'}</div></div>
                <div class="info-row"><div class="info-label">District :</div><div class="info-value">${demande.district != null ? demande.district : '-'}</div></div>
                <div class="info-row"><div class="info-label">Date de Demande :</div><div class="info-value">${demande.dateDemande != null ? demande.dateDemande : '-'}</div></div>
                <div class="info-row"><div class="info-label">Statut Actuel :</div>
                    <div class="info-value">
                        <c:if test="${dernierStatut != null}"><span class="statut-badge">${dernierStatut.status.libelle}</span></c:if>
                        <c:if test="${dernierStatut == null}"><span class="statut-badge statut-cree">Nouvelle Demande</span></c:if>
                    </div>
                </div>
                <div class="info-row"><div class="info-label">Observation :</div>
                    <div class="info-value">
                        <span id="observationText">${dernierStatut != null ? (dernierStatut.observation != null ? dernierStatut.observation : '-') : '-'}</span>
                        <button class="observation-edit" onclick="showEditObservation()">Modifier</button>
                    </div>
                </div>
            </div>
            
            <div style="margin: 20px 0;">
                <a href="/demandes/edit/${demande.idDemande}" class="btn btn-primary">Modifier la Demande</a>
                <a href="/demandes/change-statut/${demande.idDemande}" class="btn btn-warning">Changer le Statut</a>
                <a href="/demandes" class="btn btn-secondary">← Retour à la liste</a>
            </div>
            
            <h3>Historique des Statuts</h3>
            <c:if test="${empty historiqueStatuts}"><p style="color: #c8d6e5;">Aucun historique de statut disponible.</p></c:if>
            <c:if test="${not empty historiqueStatuts}">
                <table>
                    <thead><tr><th>Date de Changement</th><th>Statut</th><th>Observation</th></tr></thead>
                    <tbody>
                        <c:forEach items="${historiqueStatuts}" var="statut">
                            <tr><td>${statut.dateChangement}</td><td>${statut.status.libelle}</td><td>${statut.observation != null ? statut.observation : '-'}</td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    
    <div id="modalObservation" class="modal">
        <div class="modal-content">
            <h3>Modifier l'observation</h3>
            <form action="/demandes/update-observation" method="post">
                <input type="hidden" name="demandeId" value="${demande.idDemande}">
                <textarea name="observation">${dernierStatut != null ? dernierStatut.observation : ''}</textarea>
                <div style="margin-top: 15px;">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <button type="button" class="btn btn-secondary" onclick="closeModal()">Annuler</button>
                </div>
            </form>
        </div>
    </div>
    
    <script>
        function showEditObservation() { document.getElementById('modalObservation').style.display = 'block'; }
        function closeModal() { document.getElementById('modalObservation').style.display = 'none'; }
    </script>
</body>
</html>