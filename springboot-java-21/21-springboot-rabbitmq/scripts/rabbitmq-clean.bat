@echo off
echo ===============================================
echo 🧹 Cleaning RabbitMQ with Docker Compose
echo ===============================================
docker compose -f ..\docker\docker-compose.rabbitmq.yml down --volumes --rmi all

echo.
echo ===============================================
echo ✅ RabbitMQ containers, volumes and images have been removed
echo ===============================================
pause
