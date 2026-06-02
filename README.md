# IMC Calculator - Application Web Spring Boot & Docker

Une application web moderne et légère développée avec **Spring Boot 3**, permettant de calculer l'Indice de Masse Corporelle (IMC), d'obtenir une interprétation de sa catégorie de poids selon l'OMS, et de suivre un historique complet des mesures enregistrées.

L'application est entièrement conteneurisée et configurée pour basculer de manière transparente entre un environnement de développement local et un hébergement de production Cloud (Render).

---

## 🚀 Fonctionnalités

* **Calcul de l'IMC en temps réel** : Prise en charge sécurisée côté serveur du poids (kg) et de la taille (cm).
* **Interprétation médicale automatique** : Classification selon les normes internationales (Maigreur, Normal, Surpoids, Obésité).
* **Persistance des données** : Sauvegarde automatique de l'historique de chaque requête utilisateur avec horodatage complet.
* **Architecture multi-environnement** : Utilisation de variables d'environnement pour une flexibilité totale de la base de données.

---

## 🛠️ Stack Technique

* **Back-End** : Java 21, Spring Boot 3.2.4 (Spring Web, Spring Data JPA)
* **Front-End** : HTML5, Thymeleaf, CSS3
* **Base de données** : PostgreSQL 15
* **Conteneurisation** : Docker, Docker Compose (Multi-stage build)
* **Hébergement Cloud** : Render (Infrastructure as Code via Blueprint)

---

## 📁 Structure du Projet
```text
CloudIMC/
├── CloudIMC/               # Code source de l'application Java Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/       # Code source (Controllers, Entities, Repositories)
│   │   │   └── resources/
│   │   │       ├── templates/              # Vues HTML (Thymeleaf)
│   │   │       └── application.properties  # Propriétés de configuration
│   ├── pom.xml             # Dépendances Maven (PostgreSQL, Spring)
│   └── docker-compose.yml  # Orchestration de l'environnement local (PostgreSQL)
├── Dockerfile              # Instructions de build multi-stage pour le Cloud
└── render.yaml             # Configuration Infrastructure as Code pour Render
```
---

## 💻 Installation et Lancement Local

### Prérequis
* Docker Desktop installé et démarré.
* Java 21 et Maven (optionnel, si vous souhaitez exécuter l'application sans Docker).

### Étape 1 : Cloner le dépôt
```bash
git clone git@github.com:Gatien-Menaud/CloudIMC.git
cd CloudIMC
```

### Étape 2 : Compilation et lancement de la base de données
Exécutez la commande suivante à la racine du projet pour lancer le conteneur PostgreSQL isolé :
```bash
.\build.bat
```
Pour simplifier les tâches répétitives de compilation et de nettoyage en local, un script de commandes automatisé `build.bat` est disponible à la racine du projet.

1. **Nettoyage automatique** : Il supprime les anciens fichiers compilés et les builds précédents (dossier `target/`).
2. **Compilation du code** : Il lance la compilation complète de l'application Spring Boot.
3. **Packaging JAR** : Il génère le fichier binaire `.jar` exécutable final et prêt à être déployé.

*La base de données sera accessible localement sur le port 5432.*

### Étape 3 : Lancer l'application Spring Boot
Lancement de l'application via Maven :
```bash
mvn -f CloudIMC/pom.xml spring-boot:run
```

L'application est maintenant accessible à l'adresse : http://localhost:8080

---

## ☁️ Déploiement en Ligne (Render)

Cette application est prête pour un déploiement automatisé et gratuit sur la plateforme Render grâce à son architecture basée sur Docker et son fichier Blueprint render.yaml.

### Procédure de déploiement :
1. Créez ou connectez-vous à votre compte sur Render.com en utilisant votre compte GitHub.
2. Sur le tableau de bord Render, cliquez sur New + en haut à droite, puis sélectionnez Blueprint.
3. Connectez et sélectionnez votre dépôt de projet IMC-Calculator.
4. Render va automatiquement scanner le fichier render.yaml et vous présenter un récapitulatif :
   * Une application Web (cloud-imc-app).
   * Une base de données PostgreSQL (imc-db-postgres).
5. Cliquez sur Apply (ou Deploy).

Le processus prend environ 3 à 5 minutes (compilation du code via le Dockerfile et provisionnement de la base de données). Une fois le badge Live affiché, votre application est en ligne partout dans le monde !

---

## ⚙️ Configuration Clé (Gestion des environnements)

L'application utilise le fichier application.properties configuré intelligemment pour détecter l'environnement :
* En local : Si la variable DATABASE_URL n'est pas trouvée, Spring Boot utilise les valeurs de secours par défaut (localhost:5432, utilisateur postgres, mot de passe root).
* Sur le Cloud : Render injecte dynamiquement l'URL JDBC, le nom d'utilisateur et le mot de passe générés de manière sécurisée directement dans le conteneur applicatif, évitant ainsi d'exposer des données sensibles dans le code source public.