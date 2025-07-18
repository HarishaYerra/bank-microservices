# 💳 Bank Microservices Project

A Spring Boot-based microservices application that simulates a simple banking system. This project is structured into independent services for handling **Customers**, **Accounts**, and **Transactions**, secured using **JWT-based Authentication**, **Role-Based Access Control (RBAC)**, and **Password Encryption**.

---

## Project Modules

The project consists of the following microservices:

1. Customer Service (`customerservice`)
- Customer creation and management
- JWT token generation (`/authenticate`)
- Implements DTOs for request/response
- Uses Spring Security for authentication & authorization

2.  Account Service (`account-service`)
- Account creation and management
- Deposit, Withdrawal, and Fund Transfer functionality
- Validates JWT token for secure communication
- Connects with `transactionservice` to log transactions
- Implements DTOs for request/response

3.Transaction Service (`transactionservice`) 
- Stores logs of all deposits, withdrawals, and fund transfers
- Accepts data from `account-service`

---

## Security Features

- JWT Authentication**: Only authenticated users can access protected endpoints.
- Password Encryption**: Uses BCryptPasswordEncoder to securely hash user passwords.
- JWT Token Validation Filter**: Validates token on every request using a `JwtRequestFilter`.

---

## Technologies Used

| Technology       | Description                            |
|------------------|----------------------------------------|
| Java 17+         | Programming Language                   |
| Spring Boot      | Microservices Framework                |
| Spring Security  | Security (JWT, Roles, Filters)         |
| Spring Data JPA  | ORM and DB Communication               |
| MySQL            | Database                               |
| Maven            | Build and Dependency Management        |
| Postman          | API Testing                            |
| Swagger (Planned)| API Documentation                      |
| Git & GitHub     | Version Control & Hosting              |

---

##API Overview:
All endpoints below require a valid JWT token in the Authorization header:
Authorization: Bearer <token>

POST /accounts/create — Create a new account
GET /accounts/{id}/balance — Check balance
POST /accounts/deposit — Deposit amount
POST /accounts/withdraw — Withdraw amount
POST /accounts/transfer — Transfer funds to another account
-------------------------------------------------------------------------------------------------

##Authentication (Customer Service)

http-
POST /authenticate

Request:
{
  "username": "username",
  "password": "password"
}


Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
}


##Architecture:
                +----------------------+
                |  customer-service    |
                |                      |
                +----------------------+
                          |
                          | JWT Token
                          ↓
                +----------------------+
                |  account-service     |
                |  (Accounts & Logic)  |
                +----------------------+
                          |
                          ↓
                +----------------------+
                | transaction-service  |
                |                      |
                +----------------------+
All services can be containerized and scaled independently.



##Folder Structure
arduino
Copy code
bank-microservices/
├── customer-service/
│   ├── controller/
│   ├── model/
│   ├── service/
│   └── config/
├── account-service/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── service/
│   └── config/
└── transaction-service/ (planned)


