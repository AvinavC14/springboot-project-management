# 🚀 Spring Boot Project Management API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17.5-blue.svg)](https://www.postgresql.org/)

A secure REST API for managing projects and software engineers with **JWT authentication** and **role-based access control**. Built with Spring Boot, Spring Security, and PostgreSQL.


## ✨ Features

- 🔐 **JWT Authentication** - Stateless token-based authentication
- 👥 **Role-Based Access Control** - Three levels of authorization (ADMIN, MANAGER, ENGINEER)
- 🔒 **Secure Password Storage** - BCrypt hashing algorithm
- 📊 **Project Management** - Full CRUD operations for projects
- 👨‍💻 **Engineer Management** - Manage software engineers and assign them to projects
- 🔗 **JPA Relationships** - One-to-Many and Many-to-One mappings
- ⚡ **RESTful API** - Clean, intuitive endpoint structure
- 🛡️ **Security Best Practices** - CSRF protection, stateless sessions, secure headers

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Spring Boot** | 3.5.4 | Backend framework |
| **Spring Security** | 6.5.2 | Authentication & Authorization |
| **Spring Data JPA** | - | Database ORM |
| **PostgreSQL** | 17.5 | Database |
| **JWT (JJWT)** | 0.11.5 | Token generation & validation |
| **BCrypt** | - | Password encryption |
| **Maven** | 3.9+ | Build tool |
| **Java** | 17+ | Programming language |

## 🏗️ Architecture

```
┌─────────────┐
│   Client    │
└──────┬──────┘
       │ HTTP Request + JWT Token
       ▼
┌─────────────────────┐
│ Security Filter     │──→ JWT Validation
│ Chain               │
└──────┬──────────────┘
       │ Authorized Request
       ▼
┌─────────────────────┐
│   Controllers       │
│ (REST Endpoints)    │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│    Services         │
│ (Business Logic)    │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│   Repositories      │
│ (Data Access)       │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│   PostgreSQL DB     │
└─────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **PostgreSQL 12** or higher
- **Maven 3.6** or higher
- **Postman** or **cURL** (for API testing)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/springboot-project-management.git
   cd springboot-project-management
   ```

2. **Create PostgreSQL database**
   ```bash
   # Connect to PostgreSQL
   psql -U postgres
   
   # Create database
   CREATE DATABASE store_db;
   
   # Exit
   \q
   ```

3. **Configure application properties**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/store_db
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```

4. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the application**
   ```
   http://localhost:8080
   ```

## 📚 API Documentation

### Base URL
```
http://localhost:8080
```

### Authentication Endpoints

#### Register User
```http
POST /auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "securepass123",
  "role": "ENGINEER"
}
```

**Roles:** `ADMIN`, `MANAGER`, `ENGINEER`

**Response:**
```json
"User registered successfully"
```

---

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "securepass123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiRU5HSU5F..."
}
```

---

### Project Endpoints

| Method | Endpoint | Access Level | Description |
|--------|----------|--------------|-------------|
| `GET` | `/Project/getAll` | 🟢 All | Get all projects |
| `POST` | `/Project/add?name=ProjectName` | 🟡 ADMIN, MANAGER | Create project |
| `PUT` | `/Project/update/{id}?n=NewName` | 🟡 ADMIN, MANAGER | Update project |
| `DELETE` | `/Project/delete/{id}` | 🟡 ADMIN, MANAGER | Delete project |



---

### Engineer Endpoints

| Method | Endpoint | Access Level | Description |
|--------|----------|--------------|-------------|
| `GET` | `/SoftwareEngineers/getAll` | 🟢 All | Get all engineers |
| `POST` | `/SoftwareEngineers/AddEngineer` | 🟡 ADMIN, MANAGER | Create engineer |
| `PUT` | `/SoftwareEngineers/UpdateEngineer/{id}` | 🟡 ADMIN, MANAGER | Update engineer |
| `DELETE` | `/SoftwareEngineers/DeleteEngineer/{id}` | 🟡 ADMIN, MANAGER | Delete engineer |



---

### User Management Endpoints

| Method | Endpoint | Access Level | Description |
|--------|----------|--------------|-------------|
| `GET` | `/users/getAll` | 🔴 ADMIN | Get all users |

---



## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
   
);
```

### Projects Table
```sql
CREATE TABLE projects (
    project_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

### Software Engineers Table
```sql
CREATE TABLE software_engineers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tech_stack VARCHAR(255),
    project_id INTEGER,
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);
```





## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/springboot/store/
│   │   ├── controllers/
│   │   │   ├── AuthController.java
│   │   │   ├── ProjectController.java
│   │   │   ├── SoftwareEngineerController.java
│   │   │   └── UserController.java
│   │   ├── dto/
│   │   │   ├── AuthResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   └── RegisterRequest.java
│   │   ├── repositories/
│   │   │   ├── ProjectRepository.java
│   │   │   ├── SoftwareEngineerRepository.java
│   │   │   └── UserRepository.java
│   │   ├── services/
│   │   │   ├── JwtService.java
│   │   │   ├── ProjectService.java
│   │   │   ├── SoftwareEngineerService.java
│   │   │   └── UserService.java
│   │   ├── JWTAuthFilter.java
│   │   ├── SecurityConfig.java
│   │   ├── SecurityBeansConfig.java
│   │   ├── Role.java
│   │   ├── User.java
│   │   ├── Project.java
│   │   ├── SoftwareEngineer.java
│   │   └── StoreApplication.java
│   └── resources/
│       └── application.properties
└── test/
```

## 🛡️ Security Implementation

### JWT Configuration
- **Algorithm:** HMAC-SHA256
- **Token Expiry:** 1 hour
- **Secret Key:** Configurable in `JwtService.java`

### Password Security
- **Hashing:** BCrypt with auto-generated salt
- **Strength:** 10 rounds (default)

### Role-Based Access Matrix

| Endpoint | ADMIN | MANAGER | ENGINEER |
|----------|-------|---------|----------|
| Register/Login | ✅ | ✅ | ✅ |
| View Projects | ✅ | ✅ | ✅ |
| Create Project | ✅ | ✅ | ❌ |
| Update Project | ✅ | ✅ | ❌ |
| Delete Project | ✅ | ✅ | ❌ |
| View Engineers | ✅ | ✅ | ✅ |
| Manage Engineers | ✅ | ✅ | ❌ |
| View Users | ✅ | ❌ | ❌ |


---

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ and Spring Boot

</div>
