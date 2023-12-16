
# User Management System

This is a simple user management system developed using Spring Boot. It provides APIs for managing user data, including fetching all users, fetching a user by ID, and downloading user data as a CSV file. The project uses Apache Commons CSV for CSV operations, ModelMapper for object mapping, and PostgreSQL as the database.

## Features

- Create many users
- Get all users
- Get user by ID
- Download all user data as a CSV file

## Prerequisites

Before running this project, ensure you have the following installed:
- JDK
- Maven
- PostgreSQL

## Configuration

Update the `src/main/resources/application.properties` file with your PostgreSQL database details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user-m
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

## API Endpoints

The following API endpoints are available:

- `POST /api/users` - Create many users
- `GET /api/users` - Retrieve all users
- `GET /api/users/{id}` - Retrieve user by ID
- `GET /api/users/download/csv` - Download all user data as a CSV file

## Database Schema

The user entity is structured as follows:

- id: UUID
- name: String
- dob: LocalDate
- address: String
- contactNumber: String
- email: String
- department: String
- title: String
- hireDate: LocalDate
- salaryLpa: Double
- emergencyContact: String