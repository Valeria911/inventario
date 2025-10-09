# Se obtiene desde una imagen de Java 17 con Alpine (más ligera)
FROM openjdk:17-jdk-slim

# Se establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Se establece el directorio donde se montará la wallet de Oracle dentro del contenedor
ENV ORACLE_WALLET_DIR=/app/Wallet_AR20EXT947101BPD

# Copia el wallet al contenedor
COPY Wallet_AR20EXT947101BPD/ $ORACLE_WALLET_DIR/

# Establece la variable de entorno
ENV TNS_ADMIN=/app/Wallet_AR20EXT947101BPD

# Copia el JAR generado en el contenedor
COPY target/inventario-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 (el que usa Spring Boot por defecto)
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor arranque
ENTRYPOINT ["java", "-jar", "app.jar"]

