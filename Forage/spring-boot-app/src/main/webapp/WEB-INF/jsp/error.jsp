<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Erreur - Gestion de Forage</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .error-container {
            max-width: 1000px;
            margin: 50px auto;
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        h1 {
            color: #d32f2f;
            margin-bottom: 20px;
        }
        .error-details {
            background: #f5f5f5;
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
            overflow-x: auto;
        }
        pre {
            white-space: pre-wrap;
            word-wrap: break-word;
            font-family: monospace;
            font-size: 12px;
            margin: 0;
        }
        .btn-back {
            display: inline-block;
            padding: 10px 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        .info {
            margin: 10px 0;
            padding: 10px;
            background: #e3f2fd;
            border-left: 4px solid #2196f3;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>❌ Erreur lors de l'opération</h1>
        
        <div class="info">
            <strong>Status:</strong> ${status}
        </div>
        
        <div class="info">
            <strong>URL:</strong> ${uri}
        </div>
        
        <div class="error-details">
            <strong>Message d'erreur:</strong>
            <pre>${message}</pre>
        </div>
        
        <div class="error-details">
            <strong>Exception:</strong>
            <pre>${exception}</pre>
        </div>
        
        <div class="error-details">
            <strong>Stack Trace:</strong>
            <pre>${trace}</pre>
        </div>
        
        <a href="/" class="btn-back">Retour à l'accueil</a>
        <a href="javascript:history.back()" class="btn-back" style="background: #6c757d; margin-left: 10px;">Retour en arrière</a>
    </div>
</body>
</html>