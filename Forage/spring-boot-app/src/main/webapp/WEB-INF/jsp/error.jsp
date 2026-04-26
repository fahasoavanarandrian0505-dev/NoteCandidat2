<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Erreur - Gestion de Forage</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #a5a4a4; min-height: 100vh; padding: 20px; }
        .error-container { max-width: 1000px; margin: 50px auto; background: #0d1b3e; border-radius: 10px; padding: 30px; border: 1px solid #2c3e6d; }
        h1 { color: #dc3545; margin-bottom: 20px; }
        .error-details { background: #12234a; padding: 20px; border-radius: 8px; margin: 20px 0; overflow-x: auto; }
        pre { white-space: pre-wrap; word-wrap: break-word; font-family: monospace; font-size: 12px; margin: 0; color: #c8d6e5; }
        .btn-back { display: inline-block; padding: 10px 20px; background: #3498db; color: white; text-decoration: none; border-radius: 5px; margin-top: 20px; }
        .btn-back:hover { opacity: 0.8; }
        .info { margin: 10px 0; padding: 10px; background: #12234a; border-left: 4px solid #3498db; color: #c8d6e5; }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Erreur lors de l'operation</h1>
        <div class="info"><strong>Status:</strong> ${status}</div>
        <div class="info"><strong>URL:</strong> ${uri}</div>
        <div class="error-details"><strong>Message d'erreur:</strong><pre>${message}</pre></div>
        <div class="error-details"><strong>Exception:</strong><pre>${exception}</pre></div>
        <div class="error-details"><strong>Stack Trace:</strong><pre>${trace}</pre></div>
        <a href="/" class="btn-back">Retour à l'accueil</a>
        <a href="javascript:history.back()" class="btn-back" style="background: #6c757d; margin-left: 10px;">Retour en arriere</a>
    </div>
</body>
</html>