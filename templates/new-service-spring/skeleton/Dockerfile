FROM docker.io/library/maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

FROM docker.io/library/eclipse-temurin:21-jre
# Atualiza os pacotes do SO no build.
RUN apt-get update && apt-get upgrade -y && rm -rf /var/lib/apt/lists/*

# Agente Java do OpenTelemetry — instrumentação zero-code, embutido na imagem
# (sem OTel Operator). OTEL_EXPORTER_OTLP_ENDPOINT/OTEL_SERVICE_NAME vêm do
# ambiente (values do Helm), não daqui — mesma convenção do otelEndpoint do archetype.
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /otel/opentelemetry-javaagent.jar
RUN chmod 644 /otel/opentelemetry-javaagent.jar

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

RUN groupadd -r app && useradd -r -g app app
USER app

ENV JAVA_TOOL_OPTIONS="-javaagent:/otel/opentelemetry-javaagent.jar"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
