@echo off
echo ===============================================
echo 🛑 Stopping SonarQube and removing containers
echo ===============================================
docker compose -f ..\docker\docker-compose.sonarqube.yml down

echo.
echo ===============================================
echo ✅ SonarQube has been stopped and containers removed
echo ===============================================
pause
