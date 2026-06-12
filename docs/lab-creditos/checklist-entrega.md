# Checklist de entrega - Laboratorio Creditos GraphQL

| Requisito | Cumplimiento | Evidencia en el proyecto |
|---|---:|---|
| Base: banco en linea del primer laboratorio | ✅ | Proyecto Spring Boot Banco Digital preservado |
| Otorgar diferentes tipos de creditos | ✅ | Mutation `otorgarCredito` |
| Consultar estado de un credito | ✅ | Query `creditoPorId` y mutation `cambiarEstadoCredito` |
| Listar creditos por categoria | ✅ | Query `creditosPorCategoria` |
| GraphQL Schema | ✅ | `src/main/resources/graphql/schema.graphqls` |
| Queries | ✅ | `creditoPorId`, `creditosPorCliente`, `creditosPorEstado`, `creditosPorCategoria` |
| Mutations | ✅ | `otorgarCredito`, `cambiarEstadoCredito` |
| Resolvers | ✅ | `CreditoGraphQLController` + `CreditoServiceImpl` |
| Docker | ✅ | `Dockerfile` y `docker-compose.yml` |
| Observabilidad - logs | ✅ | logs SLF4J en `CreditoServiceImpl` |
| Observabilidad - metricas | ✅ | Actuator + Prometheus + metricas `banco_creditos_*` |
| Observabilidad - monitoreo | ✅ | Prometheus `:9090` y Grafana `:3001` |
| Frontend opcional | ✅ | `http://localhost:8080/creditos.html` |
