@echo off
echo ===============================================
echo 🧹 Cleaning SonarQube (containers + volumes + images)
echo ===============================================
docker compose -f ..\docker\docker-compose.sonarqube.yml down --volumes --rmi all

echo ===============================================
echo 🚀 Starting SonarQube
echo ===============================================
docker compose -f ..\docker\docker-compose.sonarqube.yml up -d

echo.
echo ===============================================
echo 🌍 SonarQube is starting on http://localhost:9000
echo Default login: admin / admin
echo ===============================================
echo.
pause
