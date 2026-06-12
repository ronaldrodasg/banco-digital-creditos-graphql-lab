# Banco Digital - Módulo de Gestión de Créditos con GraphQL

Proyecto desarrollado para el curso de Arquitectura de Software.

Este laboratorio amplía una aplicación de banco digital incorporando un módulo de gestión de créditos basado en GraphQL, con persistencia en PostgreSQL, despliegue mediante Docker Compose y observabilidad utilizando Prometheus y Grafana.

---

## Integrantes

- Bryan David Molina Domínguez
- David Julián Penagos Arroyave
- Cristian Echeverry
- Ronald Rodas Goez

**Profesor:** Diego José Luis Botía Valderrama

---

## Descripción del Proyecto

El sistema permite administrar créditos asociados a clientes registrados en el banco digital mediante una API GraphQL.

Entre las funcionalidades implementadas se encuentran:

- Registro de créditos.
- Consulta de créditos por cliente.
- Consulta de créditos por estado.
- Agrupación de créditos por categoría.
- Cambio de estado de créditos.
- Persistencia de información en PostgreSQL.
- Monitoreo mediante Prometheus.
- Visualización de métricas en Grafana.

---

## Tecnologías Utilizadas

### Backend

- Java 21
- Spring Boot
- Spring GraphQL
- Spring Data JPA
- Spring Actuator
- Micrometer

### Base de Datos

- PostgreSQL 16

### Observabilidad

- Prometheus
- Grafana

### Contenedores

- Docker
- Docker Compose

---

## Arquitectura

```text
┌───────────────┐
│    Cliente    │
│ GraphiQL/Web  │
└───────┬───────┘
        │
        ▼
┌─────────────────────┐
│ Spring Boot + GraphQL│
│      Puerto 8080     │
└───────┬──────────────┘
        │
 ┌──────┴──────┐
 ▼             ▼
PostgreSQL   Prometheus
5432         9090
                │
                ▼
            Grafana
             3001
```

---

## Modelo de Dominio

### Crédito

Un cliente puede tener múltiples créditos.

### Tipos de Crédito

- PERSONAL
- HIPOTECARIO
- VEHICULAR
- EDUCATIVO
- LIBRE_INVERSION

### Estados del Crédito

- APROBADO
- PENDIENTE
- RECHAZADO
- EN_ESTUDIO

---

## API GraphQL

### Queries

```graphql
creditoPorId(id: ID!)

creditosPorCliente(documentoCliente: String!)

creditosPorEstado(estado: EstadoCredito!)

creditosPorCategoria(tipo: TipoCredito)

tiposCredito

estadosCredito
```

### Mutations

```graphql
otorgarCredito(input: OtorgarCreditoInput!)

cambiarEstadoCredito(
  id: ID!,
  estado: EstadoCredito!,
  observacion: String
)
```

---

## Ejemplo: Crear Crédito

```graphql
mutation {
  otorgarCredito(
    input: {
      documentoCliente: "123456789"
      tipo: PERSONAL
      monto: 8500000
      plazoMeses: 36
      tasaInteresAnual: 19.5
      estado: EN_ESTUDIO
      destinoCredito: "Capital de trabajo"
      observacion: "Creado desde GraphiQL"
    }
  ) {
    id
    idCredito
    tipo
    estado
    monto
  }
}
```

---

## Ejemplo: Consultar Créditos por Cliente

```graphql
query {
  creditosPorCliente(documentoCliente: "123456789") {
    id
    idCredito
    tipo
    estado
    monto
  }
}
```

---

## Ejemplo: Consultar Créditos por Estado

```graphql
query {
  creditosPorEstado(estado: EN_ESTUDIO) {
    id
    idCredito
    tipo
    estado
    monto
  }
}
```

---

## Despliegue con Docker

### Construir y levantar servicios

```bash
docker compose down -v --remove-orphans

docker compose build --no-cache

docker compose up -d

docker compose ps
```

---

## Servicios Disponibles

| Servicio | URL |
|-----------|------|
| GraphiQL | http://localhost:8080/graphiql?path=/graphql |
| GraphQL API | http://localhost:8080/graphql |
| Frontend Créditos | http://localhost:8080/creditos.html |
| Actuator Health | http://localhost:8080/actuator/health |
| Actuator Prometheus | http://localhost:8080/actuator/prometheus |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3001 |

---

## Observabilidad

El proyecto incorpora métricas técnicas y de negocio mediante Micrometer.

### Métricas personalizadas

#### Créditos otorgados

```text
banco_creditos_otorgados_total
```

Etiquetas:

- tipo
- estado

#### Cambios de estado

```text
banco_creditos_cambio_estado_total
```

#### Consultas por ID

```text
banco_creditos_consultas_id_total
```

---

## Estructura del Proyecto

```text
src
├── main
│   ├── java
│   │   ├── graphql
│   │   │   └── CreditoGraphQLController.java
│   │   ├── service
│   │   │   └── CreditoServiceImpl.java
│   │   ├── entity
│   │   │   └── Credito.java
│   │   └── repository
│   ├── resources
│   │   ├── graphql
│   │   │   └── schema.graphqls
│   │   └── static
│   │       └── creditos.html
│
monitoring
├── prometheus
│   └── prometheus.yml
│
Dockerfile
docker-compose.yml
```

---

## Resultados

✔ Implementación completa de GraphQL (Schema, Queries y Mutations)

✔ Gestión de créditos y estados

✔ Persistencia con PostgreSQL

✔ Despliegue con Docker Compose

✔ Monitoreo con Prometheus

✔ Visualización de métricas con Grafana

✔ Frontend de pruebas para operaciones GraphQL

---

## Conclusiones

La solución desarrollada demuestra la integración de GraphQL con Spring Boot en una arquitectura moderna basada en contenedores. Además, incorpora mecanismos de observabilidad que permiten monitorear el comportamiento del sistema en tiempo real mediante Prometheus y Grafana, facilitando la administración y seguimiento de las operaciones de crédito.
