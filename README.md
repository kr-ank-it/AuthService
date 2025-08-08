# Auth Service

A microservice responsible for authentication and authorization, built with **OAuth2** support for secure API access.  
Provides token-based authentication, role-based access control, and easy integration with other microservices in the ecosystem.  
Additionally, this service works as a **Kafka producer** to push **sign-up events** for downstream processing.

---

## 🚀 Features
- **OAuth2 Protocol** for secure authentication and authorization.
- **Token-based authentication** (Access Token + Refresh Token).
- **User management APIs** (registration, login, password reset).
- **Kafka Producer** for publishing user sign-up events.
- **Extensible architecture** for adding custom grant types.
- **Integration-ready** with API Gateway and other backend services.

---

## 🛠 Tech Stack
- **Language:** Java
- **Framework:** Spring Boot
- **Authentication Protocol:** OAuth2
- **Database:** MySQL
- **Token Standard:** JWT (JSON Web Token)
- **Message Broker:** Apache Kafka
- **Deployment:** Docker + Kubernetes

---


### 1️⃣ Clone the repository
```bash
git clone https://github.com/your-org/auth-service.git
cd auth-service
```


---

## 📡 Kafka Integration for Sign-Up Events
Whenever a new user successfully registers, the Auth Service publishes a sign-up event to the Kafka topic specified in the environment configuration (`sendWelcomeEmailEvent`).  
This enables downstream microservices to perform actions like sending welcome emails, creating default user settings, or updating analytics.

---

