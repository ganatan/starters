@echo off
echo ===============================================
echo 🧹 Cleaning Kafka with Docker Compose
echo ===============================================
docker compose -f ..\docker\docker-compose.kafka.yml down --volumes --rmi all

echo.
echo ===============================================
echo ✅ Kafka containers, volumes and images have been removed
echo ===============================================
pause
