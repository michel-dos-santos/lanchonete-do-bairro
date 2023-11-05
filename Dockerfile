# Usando a imagem do
FROM maven:3.9.5-amazoncorretto-17 as builder

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o fonte para o diretório de trabalho
COPY . .

# Roda os comandos clean e install para limpar os targets e gera-los novamente
RUN mvn clean install

# Use a imagem oficial do OpenJDK 17 como a imagem base
FROM openjdk:17-jdk-slim

ENV PROFILE local
ENV DB_URL url
ENV DB_USER user
ENV DB_PASSWORD password

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o fonte já buildado para ser executado
COPY --from=builder /app .

# Exponha a porta em que a aplicação Spring Boot irá escutar (substitua pela porta correta)
EXPOSE 8080

# Comando para executar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/app/adapter-rest/target/adapter-rest-1.1.0.jar", "--spring.profiles.active=${PROFILE}", "--spring.datasource.url=${DB_URL}", "--spring.datasource.username=${DB_USER}", "--spring.datasource.password=${DB_PASSWORD}"]
