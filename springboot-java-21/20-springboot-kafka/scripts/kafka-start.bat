@echo off
echo ===============================================
echo 🧹 Cleaning Kafka (containers + volumes + images)
echo ===============================================
docker compose -f ..\docker\docker-compose.kafka.yml down --volumes --rmi all

echo ===============================================
echo 🚀 Starting Kafka + Zookeeper + Kafka UI
echo ===============================================
docker compose -f ..\docker\docker-compose.kafka.yml up -d

echo.
echo ===============================================
echo 🌍 Kafka is starting on localhost:9092
echo 🌍 Zookeeper is running on localhost:2181
echo 🌍 Kafka UI is available at http://localhost:8085
echo ===============================================
echo.
pause
