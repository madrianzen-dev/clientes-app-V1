# clientes-app

Registro de clientes (nombre, apellido, fecha de nacimiento, nacionalidad).
Java 21 + Spring Boot 3.3 + JPA + Thymeleaf. Web y API REST sobre la misma capa de servicio.

- Web: `/clientes`
- API: `GET/POST /api/clientes`, `GET/PUT/DELETE /api/clientes/{id}`
- Swagger: `/swagger-ui.html`
- Health: `/actuator/health`

## Ejecutar local
    mvn spring-boot:run        # usa H2 en memoria
    mvn test

## Publicar gratis (Render + Neon)
1. Sube este proyecto a tu GitHub (repo nuevo, por ejemplo `clientes-app`).
2. En neon.tech crea un proyecto Postgres gratis y copia host, base, usuario y clave.
3. En render.com: New > Blueprint > elige el repo (lee `render.yaml`).
4. Variables de entorno en Render:
   - `DATABASE_URL` = `jdbc:postgresql://<host>/<db>?sslmode=require`
   - `DB_USER`, `DB_PASSWORD`
5. Deploy. Render te da una URL `https://clientes-app.onrender.com`.

Nota: en el plan gratis el servicio se duerme tras unos minutos sin uso y el primer
acceso tarda. Verifica los limites vigentes de cada plan antes de depender de ellos.

## Probar contra Neon desde tu PC (opcional, antes de Render)
En PowerShell, en la carpeta del proyecto (la contrasena solo en tu ventana, nunca en archivos):

    $env:SPRING_PROFILES_ACTIVE="prod"
    $env:DATABASE_URL="jdbc:postgresql://<host>/<base>?sslmode=require"
    $env:DB_USER="<usuario>"
    $env:DB_PASSWORD="<contrasena>"
    mvn spring-boot:run

Registra un cliente, detén la app, vuelve a arrancarla: el cliente debe seguir ahí.

## Escalar
Capas separadas (controller / service / repository). Para crecer: agregar Flyway para
migraciones, Spring Security (login), DTOs en la API y mas servicios sin tocar la web.
