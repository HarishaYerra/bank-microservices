Bank Server Microservices Project
A scalable, microservices-based banking application designed for handling customer accounts, transactions, and secure API access. Built with Spring Boot, Spring Cloud, and Spring Security (JWT), the project follows a distributed architecture with service discovery, API gateway, centralized configuration, API versioning, and documentation via Swagger.
________________________________________
✨ Features
•	Account Management — Create accounts, view balances, deposit, withdraw, and transfer funds.
•	Transaction Management — Log and view transaction history.
•	Customer Management — Add, update, and retrieve customer details.
•	Centralized API Gateway — Single entry point for client communication.
•	Service Discovery — Eureka server for dynamic service registry and discovery.
•	Centralized Configuration — Externalized configuration via Spring Cloud Config.
•	Security — JWT authentication & authorization for all services.
•	API Versioning — Versioned endpoints for backward compatibility.
•	API Documentation — Swagger UI for each service.
________________________________________
🛠 Tech Stack
•	Backend Framework: Spring Boot, Spring Cloud, Spring Security
•	Service Discovery: Eureka Server
•	Gateway: Spring Cloud Gateway
•	Database: MySQL
•	Build Tool: Maven
•	Containerization: Docker
•	Orchestration: Kubernetes
•	API Documentation: Swagger 
________________________________________
🏗 Microservices Structure
bank-server/
├── api-gateway/
├── config-server/
├── customer-service/
├── account-service/
├── transaction-service/
|__ eureka-service

________________________________________
📌 Sample APIs
Authentication
POST /authenticate
Content-Type: application/json

{
    "username": "admin",
    "password": "password"
}
________________________________________
#Get all customers
GET /api/v1/customers

#Create account
POST /api/v1/accounts

#Get transaction details of the particular account
GET /api/v1/transactions/account/{accountId}
________________________________________
▶ Running Locally
1. Clone the Repository
git clone https://github.com/HarishaYerra/bank-server-microservices.git
cd bank-server-microservices
2. Start All Services
cd customer-service && mvn spring-boot:run
cd account-service && mvn spring-boot:run
cd transaction-service && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
3. Access
•	API Gateway: http://localhost:8080
•	Eureka Dashboard: http://localhost:8761
•	Swagger UI (per service): http://localhost:<port>/swagger-ui.html
________________________________________
🚀 Future Enhancements
•	Implement Resilience4j Circuit Breaker
•	Integrate Email/SMS Notification Service
•	Enable Asynchronous Communication with Kafka
________________________________________
🙏 Acknowledgements
Special thanks to:
•	Spring Boot & Spring Cloud team
•	Docker & Kubernetes community
•	Swagger & OpenAPI contributors
________________________________________
📬 Contact
Yerra Harisha
📧 
🔗 GitHub: github.com/HarishaYerra
💼 LinkedIn: linkedin.com/in/yerra-harisha-112a3a23b/

