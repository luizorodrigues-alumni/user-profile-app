# Spring Boot User Profile API

This project is a Spring Boot application that exposes a REST API to manage user profiles.

## Features
- Stores a fixed set of user profiles in memory, initialized at application startup.
- Exposes two REST endpoints to:
  - Retrieve a user profile by username via a path parameter.
  - List all user profiles filtered by age via a query parameter.
- Returns responses as plain strings.
- Does not use any database or file system.

## Technologies Used
- Java
- Spring Boot

## How to Run

1. Clone the repository:
   ```sh
   git clone https://github.com/luizorodrigues-alumni/user-profile-app.git
   cd user-profile-app
   ```
2. Build and run the application:
   ```sh
   ./mvnw spring-boot:run
   ```
   or, if using Maven installed:
   ```sh
   mvn spring-boot:run
   ```

## Endpoints

### Get a user profile by username
**GET** `/users/{username}`

**Request Example:**
```
GET /users/johndoe
```
**Response (JSON):**
```json
{
  "name": "John Doe",
  "age": 30,
  "email": "johndoe@example.com",
  "country": "Brazil"
}
```

### List user profiles filtered by age
**GET** `/users?age={age}`

**Request Example:**
```
GET /users?age=25
```
**Response (JSON):**
```json
[
  {
    "name": "Alice",
    "age": 25,
    "email": "alice@example.com",
    "country": "USA"
  }
]
```
