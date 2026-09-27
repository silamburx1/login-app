-- Run this once to create the database.
-- (Spring Boot / Hibernate will auto-create the "users" table itself
--  thanks to spring.jpa.hibernate.ddl-auto=update, but this is here
--  for reference / manual setup.)

CREATE DATABASE IF NOT EXISTS logindb;
USE logindb;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- To create a test user, call POST /api/auth/register with:
-- { "username": "testuser", "password": "test1234" }
-- The backend will hash the password with BCrypt before storing it.
