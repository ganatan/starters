@echo off
setlocal enabledelayedexpansion

echo ===============================================
echo 🔐 Loading environment variables from ../.env
echo ===============================================

REM Charger toutes les variables depuis le fichier .env (racine du projet)
for /f "usebackq tokens=1,* delims==" %%a in ("..\.env") do (
    set %%a=%%b
)

if "%SONAR_TOKEN%"=="" (
    echo ❌ ERROR: SONAR_TOKEN not found in .env
    pause
    exit /b 1
)

echo ===============================================
echo 🚀 Running Tests + JaCoCo + SonarQube Analysis
echo ===============================================

REM Aller à la racine du projet (là où se trouve pom.xml)
cd ..

mvn clean verify sonar:sonar ^
  -Dsonar.projectKey=%SONAR_PROJECT_KEY% ^
  -Dsonar.host.url=%SONAR_HOST_URL% ^
  -Dsonar.login=%SONAR_TOKEN%

REM Retourner dans scripts/ à la fin
cd scripts

echo.
echo ===============================================
echo ✅ Analysis complete - check SonarQube UI
echo 🌍 %SONAR_HOST_URL%/projects
echo ===============================================
pause
