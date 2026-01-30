@echo off
echo ===============================================
echo 🧹 Suppression de tous les conteneurs Docker
echo ===============================================
FOR /f %%i IN ('docker ps -a -q') DO docker rm -f %%i

echo.
echo ===============================================
echo 🧹 Suppression de toutes les images Docker
echo ===============================================
FOR /f %%i IN ('docker images -q') DO docker rmi -f %%i

echo.
echo ===============================================
echo 🧹 Suppression de tous les volumes Docker
echo ===============================================
FOR /f %%i IN ('docker volume ls -q') DO docker volume rm %%i

