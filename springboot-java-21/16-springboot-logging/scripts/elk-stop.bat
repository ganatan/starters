@echo off
echo ===============================================
echo 🛑 Stopping ELK stack and removing containers
echo ===============================================
docker compose -f ..\docker\docker-compose.elk.yml down

echo.
echo ===============================================
echo ✅ ELK stack has been stopped and containers removed
echo ===============================================
pause
