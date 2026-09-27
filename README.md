# ⚖️ JUSTICE — Legal Consultation Platform

**JUSTICE** is a legal consultation platform that connects users with suitable lawyers through a digital consultation process.

Users can search for lawyers, send consultation requests, manage cases, communicate with lawyers, and submit reviews. The system also uses **Gemini AI** to help users identify a possible legal specialty.

---

## 🎯 Problem

Finding the right lawyer can be difficult, especially when users don't know which legal specialty they need.

JUSTICE simplifies the process by allowing users to:

* Search lawyers by specialty, city, and consultation price.
* Send consultation requests.
* Manage legal consultation cases.
* Communicate with lawyers.
* Submit reviews.
* Get AI assistance in identifying a possible legal specialty.

---

## 🔄 Main Flow

```text
User
 ↓
Search Lawyer / AI Assistance
 ↓
Select Lawyer
 ↓
Consultation Request
 ↓
Lawyer Accepts / Rejects
 ↓
Case
 ↓
Communication
 ↓
Close Case
 ↓
Review
```

---

## 👥 Roles

### User

* Search lawyers
* Send requests
* Manage cases
* Communicate with lawyers
* Submit reviews

### Lawyer

* Manage profile
* Accept/reject requests
* Manage cases
* Communicate with users

### Admin

* Manage users
* Manage lawyers
* Manage system data

---

## 🛠️ Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Lombok
* Jakarta Bean Validation
* MySQL
* REST API
* Maven
* Postman
* Git & GitHub

### External APIs

* **Google Gemini API** — AI-assisted legal specialty identification
* **Gmail API** — Email communication and notifications

---

# 🌐 API Endpoints

## 👤 User

| Method | Endpoint     | Description    |
| ------ | ------------ | -------------- |
| GET    | `/user`      | Get all users  |
| GET    | `/user/{id}` | Get user by ID |
| POST   | `/user`      | Create user    |
| PUT    | `/user/{id}` | Update user    |
| DELETE | `/user/{id}` | Delete user    |

## ⚖️ Lawyer

| Method | Endpoint         | Description           |
| ------ | ---------------- | --------------------- |
| GET    | `/lawyer`        | Get all lawyers       |
| GET    | `/lawyer/{id}`   | Get lawyer by ID      |
| POST   | `/lawyer`        | Create lawyer         |
| PUT    | `/lawyer/{id}`   | Update lawyer         |
| DELETE | `/lawyer/{id}`   | Delete lawyer         |
| GET    | `/lawyer/search` | Search/filter lawyers |

## 🛡️ Admin

| Method | Endpoint      | Description     |
| ------ | ------------- | --------------- |
| GET    | `/admin`      | Get all admins  |
| GET    | `/admin/{id}` | Get admin by ID |
| POST   | `/admin`      | Create admin    |
| PUT    | `/admin/{id}` | Update admin    |
| DELETE | `/admin/{id}` | Delete admin    |

## 📩 Request

| Method | Endpoint               | Description       |
| ------ | ---------------------- | ----------------- |
| GET    | `/request`             | Get all requests  |
| GET    | `/request/{id}`        | Get request by ID |
| POST   | `/request`             | Create request    |
| PUT    | `/request/{id}`        | Update request    |
| PUT    | `/request/{id}/accept` | Accept request    |
| PUT    | `/request/{id}/reject` | Reject request    |
| PUT    | `/request/{id}/cancel` | Cancel request    |
| DELETE | `/request/{id}`        | Delete request    |

## 📂 Case

| Method | Endpoint           | Description    |
| ------ | ------------------ | -------------- |
| GET    | `/case`            | Get all cases  |
| GET    | `/case/{id}`       | Get case by ID |
| POST   | `/case`            | Create case    |
| PUT    | `/case/{id}`       | Update case    |
| PUT    | `/case/{id}/close` | Close case     |
| DELETE | `/case/{id}`       | Delete case    |

## ⭐ Review

| Method | Endpoint       | Description      |
| ------ | -------------- | ---------------- |
| GET    | `/review`      | Get all reviews  |
| GET    | `/review/{id}` | Get review by ID |
| POST   | `/review`      | Create review    |
| PUT    | `/review/{id}` | Update review    |
| DELETE | `/review/{id}` | Delete review    |

---

## 🤖 Gemini AI

Gemini helps users who don't know which legal specialty they need.

```text
Legal Problem
     ↓
Gemini API
     ↓
Possible Legal Specialty
     ↓
Lawyer Search
```

> The AI provides assistance and does not replace professional legal advice.

---

## 📧 Gmail API

Used for email communication and system notifications.

```text
JUSTICE Backend
      ↓
  Gmail API
      ↓
User / Lawyer
```

---

## 🏗️ Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

External APIs are integrated through the backend:

```text
Spring Boot
 ├── Gemini API
 └── Gmail API
```

---

## 🗄️ Main Entities

```text
User
Lawyer
Admin
Request
Case
Review
```

Main relationships:

```text
User ──→ Request ←── Lawyer
             ↓
            Case
          ↙     ↘
       User     Lawyer
          ↓
        Review
```

---

## 🚀 Getting Started

### Requirements

* Java 21+
* Maven
* MySQL
* IntelliJ IDEA or another Java IDE

### Clone

```bash
git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
cd capstone2
```

### Database Configuration

Update:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/justice
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
```

Configure your **Gemini** and **Gmail** credentials securely and do not commit API keys to GitHub.

### Run

```bash
./mvnw spring-boot:run
```

The application runs by default on:

```text
http://localhost:8080
```

---

## 📌 Project

**JUSTICE — Legal Consultation Platform**

Built with **Java & Spring Boot** as a Computer Science Capstone 2 project.
