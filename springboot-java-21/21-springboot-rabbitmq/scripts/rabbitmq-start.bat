@echo off
echo ===============================================
echo 🧹 Cleaning RabbitMQ (containers + volumes + images)
echo ===============================================
docker compose -f ..\docker\docker-compose.rabbitmq.yml down --volumes --rmi all

echo ===============================================
echo 🚀 Starting RabbitMQ
echo ===============================================
docker compose -f ..\docker\docker-compose.rabbitmq.yml up -d

echo.
echo ===============================================
echo 🌍 RabbitMQ is starting on http://localhost:15672
echo Default login: guest / guest
echo ===============================================
echo.
pause
