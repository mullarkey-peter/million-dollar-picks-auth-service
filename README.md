# Authentication Service

A microservice for handling user authentication in the Million Dollar Picks application.

## Overview

The Authentication Service provides a centralized authentication mechanism for the Million Dollar Picks platform. It handles user authentication, credential management, and token validation through GraphQL and gRPC interfaces.

## Features

- User authentication via username/password
- JWT token generation and validation
- GraphQL API for client applications
- gRPC API for internal service-to-service communication
- Account security features (failed login tracking, account locking)
- Password reset functionality

## Tech Stack

- Java 17
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway for database migrations
- JWT for token-based authentication
- Netflix DGS Framework for GraphQL
- gRPC for service-to-service communication

## Getting Started

### Prerequisites

- Java 17 or later
- Docker and Docker Compose (for local development)
- Gradle 8.x

### Running Locally with Docker

```bash
# Clone the repository
git clone https://github.com/your-org/auth-service.git
cd auth-service

# Start the service with Docker Compose
docker-compose up
```

The service will be available at:
- GraphQL Endpoint: http://localhost:8080/graphql
- GraphiQL UI: http://localhost:8080/graphiql
- gRPC: localhost:9090
- PgAdmin: http://localhost:5050 (email: admin@example.com, password: admin)

### Running Locally without Docker

```bash
# Set up a PostgreSQL database
# Update application.yml with your database configuration

# Run the service
./gradlew bootRun
```

## API Documentation

### GraphQL API

The service exposes the following GraphQL operations:

#### Queries

- `validateToken(token: String): Boolean` - Validates a JWT token
- `me(token: String): UserInfo` - Returns user information from a token

#### Mutations

- `login(username: String!, password: String!): AuthResponse` - Authenticates a user and returns a token
- `logout(token: String!): AuthResponse` - Logs out a user
- `resetPassword(username: String!, newPassword: String!): Boolean` - Resets a user's password
- `createCredentials(username: String!, password: String!, userId: ID!): Credentials` - Creates credentials for a new user

### gRPC API

The service also exposes a gRPC API for service-to-service communication:

- `ValidateToken` - Validates a JWT token
- `GetUserInfo` - Retrieves user information from a token

## Configuration

Configuration is managed through application.yml and environment variables:

### Database Configuration

```yaml
spring:
  datasource:
    url: jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:authdb}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}
```

### JWT Configuration

```yaml
jwt:
  secret: ${JWT_SECRET:your-secret-key}
  expiration: ${JWT_EXPIRATION:86400000}
```

## Development

### Building the Project

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

### Database Migrations

Database migrations are managed by Flyway and stored in `src/main/resources/db/migration`.

## UML Diagrams

### Class Diagram

Here's a simplified class diagram of the core components:

```
+-------------------+      +-------------------+      +-------------------+
|   AuthResolver    |----->|    AuthService    |----->|CredentialsRepository|
+-------------------+      +-------------------+      +-------------------+
        |                        |
        |                        |
        v                        v
+-------------------+      +-------------------+
|  AuthGrpcService  |      |  AuthServiceImpl  |
+-------------------+      +-------------------+
```

## Security

This service implements several security features:

- Password hashing using BCrypt
- JWT-based authentication
- Account locking after 5 failed login attempts
- Stateless authentication

## License

[Your License]

## Contributing

[Your Contribution Guidelines]