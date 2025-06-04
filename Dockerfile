# -------- Etapa 1: Construcción con Maven y JDK 24 --------
FROM eclipse-temurin:24-jdk AS build

WORKDIR /app

# Copiar archivos de configuración y código fuente
COPY pom.xml .
COPY src src
COPY .mvn .mvn
COPY mvnw .

# Dar permisos y compilar sin tests
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# -------- Etapa 2: Imagen final con JDK 24 --------
FROM eclipse-temurin:24-jdk

VOLUME /tmp
EXPOSE 8080

# Copiar el .jar compilado desde la imagen anterior
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
