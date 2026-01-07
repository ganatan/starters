@echo off
echo ===============================================
echo 🧹 Cleaning SonarQube with Docker Compose
echo ===============================================
docker compose -f ..\docker\docker-compose.sonarqube.yml down --volumes --rmi all

echo.
echo ===============================================
echo ✅ SonarQube containers, volumes and images have been removed
echo ===============================================
pause
