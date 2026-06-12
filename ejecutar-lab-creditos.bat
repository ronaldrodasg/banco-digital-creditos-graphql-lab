@echo off
cd /d %~dp0
docker compose down -v --remove-orphans
docker compose build --no-cache
docker compose up -d
docker compose ps
start http://localhost:8080/graphiql
start http://localhost:8080/creditos.html
start http://localhost:3001
pause
