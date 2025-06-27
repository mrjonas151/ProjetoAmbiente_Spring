Projeto de Atividades Sustentáveis
📋 Sobre o Projeto
API REST para gerenciamento de atividades sustentáveis, desenvolvida com Spring Boot, incluindo funcionalidades de relatórios personalizados com IA e notificações por email.

🛠️ Tecnologias Utilizadas
Java 17
Spring Boot 3.2.0
Spring Data JPA
PostgreSQL (produção)
Spring Mail
JUnit 5
Maven

# Compilar e rodar (sem testes)
./mvnw clean spring-boot:run -DskipTests

# OU compilar primeiro, depois rodar
./mvnw clean compile
./mvnw spring-boot:run

# Rodar todos os testes (25 testes)
./mvnw test