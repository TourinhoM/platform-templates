# ${{ values.name }}

${{ values.description }}

## Visão geral

TODO: descreva o que o serviço faz, suas rotas e como rodar localmente.

## Endpoints

- `GET /actuator/health/liveness` — liveness
- `GET /actuator/health/readiness` — readiness
- `GET /actuator/prometheus` — métricas Prometheus

## Instrumentação

Agente Java do OpenTelemetry embutido na imagem (zero-code, sem OTel Operator).
`OTEL_EXPORTER_OTLP_ENDPOINT` e `OTEL_SERVICE_NAME` vêm do `values.yaml` do arquétipo Helm.

## Como rodar

```bash
./mvnw spring-boot:run
```
