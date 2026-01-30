docker compose -f ..\docker\docker-compose.qdrant.yml down --volumes --rmi all
docker compose -f ..\docker\docker-compose.qdrant.yml up -d
