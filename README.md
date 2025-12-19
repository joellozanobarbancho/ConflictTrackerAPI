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

## ▶️ Cómo ejecutar el proyecto:
1. **Clonar el repositorio     →** git clone https://github.com/joellozanobarbancho/ConflictTrackerAPI
2. **Ejecutar Main             →** ConflictTrackerApiApplication
3. **Abrir interfaz web        →** http://localhost:8080/index.html

**Para acceder a la consola H2 →** http://localhost:8080/h2-console

**JDBC URL:**  jdbc:h2:mem:conflictdb

**User:**      sa

**Password:**  (vacío)
## 🧪 Ejemplos de uso de los endpoints principales con curl:
1. **Listar todos los conflictos →** curl -X GET http://localhost:8080/api/v1/conflicts
2. **Obtener conflictos por ID →** curl -X GET http://localhost:8080/api/v1/conflicts/1
3. **Crear un nuevo conflicto →** curl -X POST http://localhost:8080/api/v1/conflicts -H "Content-Type: application/json" -d '{
        "name": "Nuevo Conflicto",
        "startDate": "2024-01-15",
        "status": "ACTIVE",
        "description": "Tensiones en la frontera",
        "countryCodes": ["ESP", "FRA"]
   }'
4. **Actualizar un conflicto →** curl -X PUT http://localhost:8080/api/v1/conflicts/1 -H "Content-Type: application/json" -d '{
        "name": "Conflicto Actualizado",
        "startDate": "2024-02-01",
        "status": "FROZEN",
        "description": "Negociaciones en curso",
        "countryCodes": ["DEU", "ESP"]
   }'
5. **Eliminar un conflicto →** curl -X DELETE http://localhost:8080/api/v1/conflicts/1
6. **Crear un nuevo evento →** curl -X POST http://localhost:8080/api/v1/events -H "Content-Type: application/json" -d '{
        "eventDate": "2024-03-10",
        "location": "Barcelona",
        "description": "Reunión diplomática",
        "conflictId": 1
   }'
7. **Crear una nueva facción →** curl -X POST http://localhost:8080/api/v1/factions -H "Content-Type: application/json" -d '{
        "name": "Alianza Mediterránea",
        "conflictId": 2,
        "supporterCountryCodes": ["ESP", "FRA"]
  }'




