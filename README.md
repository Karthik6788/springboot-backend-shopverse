# Spring Boot MySQL Project

This is a simple Spring Boot application connected to a MySQL database.

## Prerequisites

- Java 11 or higher
- MySQL 5.x or higher

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Karthik6788/springboot-backend-shopverse.git
2.Set Up the MySQL Database:
  CREATE DATABASE shopverse_db;
  
3.Configure Database Connection:
  spring.datasource.url=jdbc:mysql://localhost:3306/shopverse_db
  spring.datasource.username=root
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true

4.Build and Run the Project:
  mvn clean install
  mvn spring-boot:run


