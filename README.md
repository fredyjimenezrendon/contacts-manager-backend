# Contacts Manager Backend

A RESTful API for managing contacts built with Spring Boot 3, Java 17, and PostgreSQL.

**Live Demo:** https://contacts-manager-backend-74tf.onrender.com

**Repository:** https://github.com/fredyjimenezrendon/contacts-manager-backend

## Tech Stack

- **Java 17** with **Spring Boot 3.4.2**
- **Spring Data JPA** (Hibernate) for ORM
- **PostgreSQL** database
- **Maven** build tool
- **Docker** multi-stage build
- **Swagger / OpenAPI** for API documentation
- Deployed on **Render**

## API Endpoints

Base path: `/api/v1/contacts`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/contacts` | Get all contacts (paginated) |
| GET | `/api/v1/contacts/{id}` | Get a contact by ID |
| POST | `/api/v1/contacts` | Create a new contact |
| PUT | `/api/v1/contacts/{id}` | Update a contact |
| DELETE | `/api/v1/contacts/{id}` | Delete a contact |

Interactive API docs are available at [`/swagger-ui.html`](https://contacts-manager-backend-74tf.onrender.com/swagger-ui.html).

### Contact Schema

| Field | Type | Required | Notes |
|-------|------|----------|-------|
| name | String | Yes | Max 100 characters |
| lastName | String | Yes | Max 100 characters |
| phone | String | No | Max 20 characters, validated format |
| email | String | No | Must be a valid email |
| addressLine1 | String | No | Max 255 characters |
| addressLine2 | String | No | Max 255 characters |
| country | String | No | Max 100 characters |
| state | String | No | Max 100 characters |
| city | String | No | Max 100 characters |
| birthday | Date | No | Must be in the past |

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/fredyjimenezrendon/contacts-manager-backend.git
   cd contacts-manager-backend
   ```

2. Create a `.env` file from the example:

   ```bash
   cp .env.example .env
   ```

3. Fill in your database credentials in `.env`:

   ```
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=contacts_db
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   ```

4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

The server starts at `http://localhost:8080`.

### Docker

```bash
docker build -t contacts-manager-backend .
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=contacts_db \
  -e DB_USERNAME=your_username \
  -e DB_PASSWORD=your_password \
  contacts-manager-backend
```

## Project Structure

```
src/main/java/com/contacts/manager/
├── config/          # CORS and OpenAPI configuration
├── controller/      # REST controllers and exception handler
├── service/         # Business logic
├── model/           # JPA entities
├── dto/             # Data transfer objects
└── dao/             # Spring Data repositories
```