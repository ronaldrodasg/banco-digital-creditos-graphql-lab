$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot
Write-Host "Levantando Banco Digital - Laboratorio Creditos GraphQL" -ForegroundColor Cyan
docker compose down -v --remove-orphans
docker compose build --no-cache
docker compose up -d
docker compose ps
Start-Sleep -Seconds 10
Start-Process "http://localhost:8080/graphiql"
Start-Process "http://localhost:8080/creditos.html"
Start-Process "http://localhost:3001"
Write-Host "Listo: GraphiQL http://localhost:8080/graphiql | Frontend http://localhost:8080/creditos.html | Grafana http://localhost:3001" -ForegroundColor Green
