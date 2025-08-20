# Spring Boot Project Management API

A simple REST API built with **Spring Boot** and **Spring Data JPA** for managing **Projects** and **Software Engineers**.  
It demonstrates **One-to-Many** and **Many-to-One** relationships, full CRUD operations, and cascade delete functionality.  

---

## 🚀 Features
- Manage **Projects** and **Software Engineers**
- **One Project → Many Engineers** relationship
- **Cascade delete** → deleting a project removes assigned engineers automatically
- Full **CRUD operations** on both entities
- Built with **Spring Boot**, **Spring Data JPA**, and **PostgreSQL/MySQL**

---

## 🗂️ Tech Stack
- **Backend**: Spring Boot 3, Spring Data JPA, Hibernate  
- **Database**: PostgreSQL / MySQL (configurable)  
- **Build Tool**: Maven  
- **Language**: Java 17+  

---

## ⚙️ Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/your-username/springboot-project-management.git
cd springboot-project-management
```
### 2. Configure the database

- Update src/main/resources/application.properties with your DB credentials:

- For PostgreSQL
  ```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
  spring.datasource.username=postgres
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
  ```
###  3. Run the application
```bash
mvn spring-boot:run
```

- The app will start at: http://localhost:8080

## 📌 API Endpoints

### 🏗️ Project Endpoints
- **GET** `/projects/getAll` → List all projects  
- **POST** `/projects` → Create a new project  
- **PUT** `/projects/{id}` → Update project  
- **DELETE** `/projects/{id}` → Delete project (also deletes engineers assigned to it)  

### 👨‍💻 Software Engineer Endpoints
- **GET** `/engineers/getAll` → List all engineers  
- **POST** `/engineers` → Create a new engineer (assign to a project)  
- **PUT** `/engineers/{id}` → Update engineer  
- **DELETE** `/engineers/{id}` → Delete engineer  

---

## 🗃️ Database Schema

### 📂 Project Table (`projects`)
| Column      | Type         | Description            |
|-------------|--------------|------------------------|
| project_id  | Integer (PK) | Unique ID for project  |
| name        | String       | Project name           |

### 👨‍💻 Software Engineer Table (`software_engineers`)
| Column      | Type         | Description                    |
|-------------|--------------|--------------------------------|
| id          | Integer (PK) | Unique ID for engineer         |
| name        | String       | Engineer's name               |
| tech_stack  | String       | Technologies the engineer uses |
| project_id  | Integer (FK) | Foreign key → `projects.project_id` |

### 📖 Learnings  

While building this project, I gained hands-on experience with:  
- Structuring a Spring Boot application with entities, repositories, services, and controllers.  
- Implementing RESTful APIs with proper CRUD operations.  
- Establishing relationships between tables (One-to-Many, Many-to-One) using JPA and Hibernate.  
- Connecting a Spring Boot application to a PostgreSQL database.  
- Testing APIs using tools like Postman.  

This project helped me strengthen my understanding of **Spring Boot, JPA, and database relationships** in a practical way. 🚀  

  
