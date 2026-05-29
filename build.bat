@echo off
setlocal

:: Définition du chemin vers le dossier du projet
set "PROJECT_DIR=CloudIMC"

echo =======================================================
echo     DEPLACEMENT VERS LE DOSSIER : %PROJECT_DIR%
echo =======================================================
cd /d "%~dp0%PROJECT_DIR%"

echo.
echo [1/3] Verification de l'environnement Java...
java -version
if %errorlevel% neq 0 (
    echo [ERREUR] Java 21 non trouve.
    pause & exit /b 1
)

echo.
echo [2/3] Demarrage de la base de donnees (Docker)...
docker compose up -d
if %errorlevel% neq 0 (
    echo [ERREUR] Docker Compose a echoue.
    pause & exit /b 1
)

echo.
echo [3/3] Nettoyage et compilation du projet...
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo [ERREUR] Compilation Maven echouee.
    pause & exit /b 1
)

echo.
echo =======================================================
echo [SUCCES] Environnement pret !
echo Pour lancer l'appli : mvn -f CloudIMC/pom.xml spring-boot:run
echo =======================================================
