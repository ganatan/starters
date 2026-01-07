@echo off
echo ===============================================
echo 🛑 Stopping Kafka and removing containers
echo ===============================================
docker compose -f ..\docker\docker-compose.kafka.yml down

echo.
echo ===============================================
echo ✅ Kafka has been stopped and containers removed
echo ===============================================
pause
