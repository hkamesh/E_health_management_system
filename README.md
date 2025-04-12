
# E-Healthcare Management System 🏥

A simple JavaFX-based desktop application for managing user authentication and role-based dashboards in an E-Healthcare system. Supports login for both doctors and patients with basic UI and MySQL database integration.

## 🚀 Features

- 🔐 **Login System** – Secure login using username and password.
- 👨‍⚕️ **Doctor Dashboard** – Access a list of patients (mocked for demo).
- 👤 **Patient Dashboard** – View personal health information (placeholder).
- 💾 **MySQL Integration** – Connects to a local MySQL database to verify credentials.


## 🛠 Technologies Used

- Java 11+
- JavaFX (GUI)
- MySQL (Backend Database)
- JDBC (Java Database Connectivity)

## 🗃 Database Schema

Make sure to create a database named `e_healthcare` and a `users` table with the following schema:

```sql
CREATE DATABASE e_healthcare;

USE e_healthcare;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    role ENUM('doctor', 'patient')
);
