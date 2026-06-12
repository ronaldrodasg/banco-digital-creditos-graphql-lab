# Laboratorio opcional 656 - Banco en linea + GraphQL + Docker + Observabilidad

## Integrantes

- Bryan David Molina Dominguez
- David Julian Penagos Arroyave
- Cristian Echeverry
- Ronald Rodas

## Objetivo

Implementar una funcionalidad para gestionar creditos de clientes del banco digital, tomando como base el primer laboratorio del banco en linea.

## Funcionalidades implementadas

1. Otorgar creditos de tipo `PERSONAL`, `HIPOTECARIO`, `VEHICULAR`, `EDUCATIVO` y `LIBRE_INVERSION`.
2. Consultar el estado de un credito por id.
3. Cambiar el estado de un credito a `APROBADO`, `PENDIENTE`, `RECHAZADO` o `EN_ESTUDIO`.
4. Listar creditos agrupados por categoria o tipo.
5. Consultar creditos por documento de cliente.
6. Exponer logs, metricas y monitoreo con Actuator, Prometheus y Grafana.
7. Incluir un frontend opcional para ejecutar las operaciones principales.

## Endpoints principales

- API GraphQL: <http://localhost:8080/graphql>
- GraphiQL: <http://localhost:8080/graphiql>
- Frontend opcional: <http://localhost:8080/creditos.html>
- Health: <http://localhost:8080/actuator/health>
- Prometheus metrics: <http://localhost:8080/actuator/prometheus>
- Prometheus UI: <http://localhost:9090>
- Grafana: <http://localhost:3001> usuario `admin`, clave `admin`

## Ejecucion con Docker

```powershell
docker compose down -v --remove-orphans
docker compose build --no-cache
docker compose up -d
docker compose ps
```

## Pruebas rapidas

Las operaciones de prueba estan en:

```text
docs/lab-creditos/operaciones-graphql.graphql
```

## Evidencias sugeridas

1. `docker compose ps` mostrando `postgres`, `backend`, `prometheus` y `grafana`.
2. GraphiQL ejecutando `otorgarCredito`.
3. GraphiQL ejecutando `creditoPorId` para consultar estado.
4. GraphiQL ejecutando `creditosPorCategoria`.
5. Frontend opcional en `/creditos.html`.
6. Actuator `/actuator/prometheus` mostrando metricas.
7. Grafana con el dashboard del backend y creditos.
