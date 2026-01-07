@echo off
echo ===============================================
echo 🧹 Cleaning ELK stack with Docker Compose
echo ===============================================
docker compose -f ..\docker\docker-compose.elk.yml down --volumes --rmi all

echo.
echo ===============================================
echo ✅ ELK containers, volumes and images have been removed
echo ===============================================
pause
