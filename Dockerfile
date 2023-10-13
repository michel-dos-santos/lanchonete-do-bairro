# Use a imagem oficial do OpenJDK 17 como a imagem base
FROM openjdk:17-jdk-slim


ENV PROFILE local
ENV DB_URL url
ENV DB_USER user
ENV DB_PASSWORD password


# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo "pom.xml" e o arquivo "src" (código-fonte) para o diretório de trabalho
COPY application/target/application-1.0.0.jar /app/spring-app.jar


# Exponha a porta em que a aplicação Spring Boot irá escutar (substitua pela porta correta)
EXPOSE 8080

# Comando para executar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "spring-app.jar", "--spring.profiles.active=${PROFILE}", "--spring.datasource.url=${DB_URL}", "--spring.datasource.username=${DB_USER}", "--spring.datasource.password=${DB_PASSWORD}"]

