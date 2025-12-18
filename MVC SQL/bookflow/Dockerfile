# Estágio 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia tudo para dentro do container (incluindo a pasta bookflow)
COPY . .

# Entra na pasta onde o pom.xml e o mvnw realmente estão
RUN cd bookflow && chmod +x mvnw && ./mvnw clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Busca o JAR dentro da subpasta target de bookflow
COPY --from=build /app/bookflow/target/*.jar app.jar

# Variável de porta obrigatória para o Render
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]