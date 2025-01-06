# Multi-Database Spring Boot Application

a Spring Boot application that connect to multiple PostgreSQL databases using JPA

The application manages `Product`, `Provider`, and `ProductDetail` entities across two different databases

## Technologies Used

- Spring Boot 3.4.1
- Spring Data JPA
- PostgreSQL
- Gradle
- JDK 21

## Overview

The application is structured to interact with two PostgreSQL databases:

- **Database A**: Contains the `Product` entity.
- **Database B**: Contains the `Provider` and `ProductDetail` entities, which have relationships with the `Product` entity in Database A.