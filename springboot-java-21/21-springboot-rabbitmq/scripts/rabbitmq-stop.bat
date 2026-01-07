@echo off
echo ===============================================
echo 🛑 Stopping RabbitMQ and removing containers
echo ===============================================
docker compose -f ..\docker\docker-compose.rabbitmq.yml down

echo.
echo ===============================================
echo ✅ RabbitMQ has been stopped and containers removed
echo ===============================================
pause
