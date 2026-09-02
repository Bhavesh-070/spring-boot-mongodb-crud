# Spring Boot MongoDB CRUD REST API

A clean, production-ready Spring Boot RESTful API demonstrating full **CRUD (Create, Read, Update, Delete)** operations with **MongoDB**, structured **SLF4J logging**, and a comprehensive **JUnit 5 + Mockito** test suite.

---

## 🚀 Features

- **Create Employee (`POST /emp`)**: Validates employee payloads (`@Validated`, `@NotBlank`) and persists records to MongoDB.
- **Get All Employees (`GET /emp`)**: Fetches all employee records from MongoDB.
- **Get Employee By ID (`GET /emp/{id}`)**: Retrieves a single employee by their unique MongoDB identifier.
- **Update Employee (`PUT /emp/{id}`)**: Updates existing employee fields (`empName`, `empNo`, `empDesignation`) with existence check.
- **Delete Employee By ID (`DELETE /emp/{id}`)**: Deletes an employee by ID and returns a status confirmation message.
- **Delete All Employees (`DELETE /emp/all`)**: Purges all employee documents.
- **Clean 3-Tier Layered Architecture**: Clear separation of concerns between Controller, Service, DAO, and Repository layers.
- **Structured SLF4J Logging**: Parameterized logging across Controller, Service, and DAO layers.
- **Unit & MockMvc Testing**: 100% unit and mock web layer coverage using **JUnit 5**, **Mockito**, and **MockMvc**.

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.4.5** (Spring Web, Spring Data MongoDB, Spring Validation)
- **MongoDB**
- **SLF4J / Logback**
- **JUnit 5 & Mockito** (Testing)

---

## 📋 Prerequisites

- **Java JDK 17+**
- **Maven 3.8+** (or use included `./mvnw`)
- **MongoDB** running on `localhost:27017`

---

## ⚙️ Configuration

Configure your database connection in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
spring.application.name=spring-boot-mongodb-crud

# MongoDB connection URI
spring.data.mongodb.uri=mongodb://localhost:27017/mydatabase
```

---

## 🔌 API Endpoints

All endpoints are hosted under the base path: `/emp`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/emp` | Create / add a new employee |
| `GET` | `/emp` | Fetch all employees |
| `GET` | `/emp/{empId}` | Fetch employee by ID |
| `PUT` | `/emp/{empid}` | Update employee by ID |
| `DELETE` | `/emp/{empid}` | Delete employee by ID |
| `DELETE` | `/emp/all` | Delete all employees |

---

## 📝 Request & Response Examples

### 1. Add Employee (`POST /emp`)
**Request Body:**
```json
{
  "empName": "John Doe",
  "empNo": "EMP001",
  "empDesignation": "Software Engineer"
}
```
**Response (200 OK):**
```json
{
  "empId": "65e0a12f9b8c3d4e5f6a7b8c",
  "empName": "John Doe",
  "empNo": "EMP001",
  "empDesignation": "Software Engineer"
}
```

### 2. Update Employee (`PUT /emp/{empid}`)
**Request Body:**
```json
{
  "empName": "John Doe",
  "empNo": "EMP001",
  "empDesignation": "Senior Software Engineer"
}
```

### 3. Delete Employee by ID (`DELETE /emp/{empid}`)
**Response (200 OK):**
```text
Employee with ID 65e0a12f9b8c3d4e5f6a7b8c deleted successfully.
```

---

## 🧪 Running Tests

Run all unit and controller tests using the Maven wrapper:

```bash
# On Windows
./mvnw.cmd test

# On Linux/macOS
./mvnw test
```

### Test Coverage Summary:
- **`EmpControllerTest`**: Tests HTTP endpoints with MockMvc.
- **`EmpServiceTest`**: Tests service layer logic with Mockito mocks.
- **`EmployeeDaoImplTest`**: Tests DAO operations & repository interactions.

---

## 📦 Running the Application

```bash
# On Windows
./mvnw.cmd spring-boot:run

# On Linux/macOS
./mvnw spring-boot:run
```
