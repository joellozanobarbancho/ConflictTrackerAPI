# 🌍 ConflictTrackerAPI

ConflictTrackerAPI es una aplicación REST desarrollada con Spring Boot 3, JPA/Hibernate y H2 Database, diseñada para gestionar información sobre conflictos internacionales, países implicados, facciones y eventos relevantes.

## 🧱 Arquitectura:
- Entities
- DTOs
- Mappers
- Services
- Repositories
- Controllers

## 📦 Datos iniciales: data.sql
Permite probar la API sin necesidad de insertar datos manualmente.

## ▶️ Cómo ejecutar el proyecto
1. Clonar el repositorio     → git clone https://github.com/joellozanobarbancho/ConflictTrackerAPI
2. Ejecutar Main             → ConflictTrackerApiApplication
3. Abrir interfaz web        → http://localhost:8080/index.html

Para acceder a la consola H2 → http://localhost:8080/h2-console

JDBC URL:  jdbc:h2:mem:conflictdb
User:      sa
Password:  (vacío)
