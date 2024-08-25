# E-Commerce Application - 
# Server-Side Setup

## Overview

This guide explains how to set up and run the server-side of the E-Commerce Application, which handles business logic, database interactions, and API endpoints for managing customers, products, orders, and subscriptions.

## Prerequisites

Before running the server, ensure you have the following installed:

- **Java 8 or higher**: The application is built using Java, so you need JDK installed.
- **Maven**: Used for building and managing the project dependencies.
- **MySQL**: The relational database used to store application data.

## Project Structure

The server-side of the application follows a layered architecture:

- **Controller Layer**: Manages HTTP requests and responses.
- **Service Layer**: Contains business logic.
- **DAO Layer**: Handles data access and interactions with the database.
- **Model Layer**: Contains the entities representing the database tables.

## Configuration

### 1. Database Configuration

1. **Create a Database**: 
   - Create a MySQL database that the application can connect to.
   - Example SQL command:
     ```sql
     CREATE DATABASE ecommerce_db;
     ```

2. **Set Up Database Credentials**:
   - Update the `src/main/resources/application.properties` file with your MySQL database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
     spring.datasource.username=hunters
     spring.datasource.password=Hunters@123
     ```

### 2. Clone the Project

 **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/ecommerce-app.git
   cd ecommerce-app
```

#Application :
![WhatsApp Image 2024-08-25 at 23 02 09_8721b5![IMG-20240825-WA0017 1](https://github.com/user-attachments/assets/aa5bf6fd-c323-44fb-baf1-aa772fcfcc98)
4e](https://github.com/user-attachments/assets/c4bd4ace-0026-4593-acd6-027eb0cfea1b)![IMG-20240825-WA0031 1](https://github.com/user-attachments/assets/920995cc-2741-4b9d-9951-9bda06bfed7b)
![IMG-20240825-WA0018 1](https://github.com/user-attachments/assets/c9b2f117-9dde-407a-b777-6ce6225e1c41)
![WhatsApp Image 2024-08-25 at 23 39 52_80b856b7](https://github.com/user-attachments/assets/2f50c80e-dc6c-4d9d-ae53-c8f7efe3f5cb)
![IMG-20240825-WA0034 1](https://github.com/user-attachments/assets/f4de4a34-36fb-400d-8ae8-9673cf5706f7)
