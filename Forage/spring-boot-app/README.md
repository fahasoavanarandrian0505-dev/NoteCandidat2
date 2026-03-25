# Spring Boot Application avec MySQL

Projet Spring Boot configuré pour fonctionner avec une base de données MySQL.

## Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- MySQL 5.7 ou supérieur

## Installation

1. **Créer la base de données MySQL**

```sql
CREATE DATABASE ma_base;
```

2. **Configurer les paramètres de connexion**
   Modifier le fichier `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ma_base
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

3. **Compiler et lancer l'application**

```bash
mvn clean install
mvn spring-boot:run
```

## Structure du Projet

```
src/
├── main/
│   ├── java/com/example/app/
│   │   ├── SpringBootAppApplication.java (classe principale)
│   │   ├── entity/                       (modèles JPA)
│   │   ├── repository/                   (interfaces JPA Repository)
│   │   ├── service/                      (logique métier)
│   │   └── controller/                   (contrôleurs REST)
│   └── resources/
│       └── application.properties        (configuration)
└── test/
```

## Commandes Utiles

- Compiler: `mvn clean compile`
- Tester: `mvn test`
- Empaqueter: `mvn package`
"# NoteCandidat2" 
