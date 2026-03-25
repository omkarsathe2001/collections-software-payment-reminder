![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4.0.4-green)
![React](https://img.shields.io/badge/React-18-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)

# 💼 Collections Software (Payment Reminder & Delinquent Account Management)

🚀 Full Stack FinTech Application built using React and Spring Boot

A full-stack web application designed to handle **payment reminders, delinquent account tracking, and collection workflows** used in financial systems like banking and NBFCs.

---

## 🏦 Business Use Case

This system simulates a real-world **collections/recovery platform** used by financial institutions to:

* Track overdue (DPD) accounts
* Assign collectors to delinquent customers
* Send payment reminders
* Manage account recovery workflows

---

## 🚀 Tech Stack

### 🔹 Frontend

* React 18 (Vite)
* Axios
* React Toastify
* React Icons
* Recharts

### 🔹 Backend

* Java 17
* Spring Boot 4.0.4
* Spring MVC
* Spring Data JPA
* Hibernate

### 🔹 Database

* MySQL

---

## ⚙️ Prerequisites

Make sure the following are installed:

| Tool    | Version   |
| ------- | --------- |
| Node.js | v22.17.1  |
| npm     | 11.6.0    |
| Java    | 17        |
| Maven   | Latest    |
| MySQL   | Installed |

---

## 📦 Frontend Setup

### 1️⃣ Navigate to frontend folder

```bash
cd collection-frontend
```

### 2️⃣ Install dependencies

```bash
npm install
```

### 3️⃣ Install required packages

```bash
npm install axios jwt-decode react-toastify react-icons recharts
```

### 4️⃣ Run the frontend

```bash
npm run dev
```

👉 Frontend runs on:
http://localhost:5173

---

## ☕ Backend Setup

### 1️⃣ Navigate to backend folder

```bash
cd collection-system
```

### 2️⃣ Configure MySQL database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/collection_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Build project

```bash
mvn clean install
```

### 4️⃣ Run backend

```bash
mvn spring-boot:run
```

👉 Backend runs on:
http://localhost:8080

---

## 🔗 API Configuration

Make sure frontend API base URL is:

```javascript
http://localhost:8080
```

---

## 📁 Project Structure

```
collections-software-payment-reminder/
│
├── collection-frontend/   # React App
├── collection-system/     # Spring Boot Backend
└── README.md
```

---

## ✨ Features

* ✅ Customer Management (Create, Update, Delete, View)
* ✅ Account Management with Status Tracking
* ✅ Delinquent Account (DPD) Monitoring
* ✅ Collector Assignment System
* ✅ Payment Reminder Workflow
* ✅ REST API Integration (Frontend ↔ Backend)
* ✅ Clean Architecture (Controller → Service → Repository)
* ✅ Global Exception Handling & Validation

---

## 📸 Screenshots

*(Add your screenshots here)*

```
/screenshots/dashboard.png
/screenshots/customer.png
```

---

## 📄 API Documentation

Swagger UI:
http://localhost:8080/swagger-ui/index.html

---

## 🛠️ Future Enhancements

* 🔐 JWT Authentication & Authorization
* 👥 Role-based Access Control
* ☁️ Cloud Deployment (AWS / Render / Railway)

---

## 👨‍💻 Author

**Omkar Sathe**
📧 [satheomkar.dev@gmail.com](mailto:satheomkar.dev@gmail.com)
📍 Pune, India

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!

---
