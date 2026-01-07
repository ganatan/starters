@echo off
echo ===============================================
echo 🧹 Cleaning ELK (containers + volumes + images)
echo ===============================================
docker compose -f ..\docker\docker-compose.elk.yml down --volumes --rmi all

echo ===============================================
echo 🚀 Starting ELK Stack (Elasticsearch + Logstash + Kibana)
echo ===============================================
docker compose -f ..\docker\docker-compose.elk.yml up -d

echo.
echo ===============================================
echo 🌍 Kibana is starting on http://localhost:5601
echo 📡 Logstash listening on port 5000 (TCP)
echo 🧠 Elasticsearch available on http://localhost:9200
echo ===============================================
echo.
pause
