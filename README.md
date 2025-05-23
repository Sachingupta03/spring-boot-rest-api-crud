# Spring Boot REST API - CRUD Application ⚙️🚀

This is a simple **Spring Boot CRUD REST API** project for managing employee records. It demonstrates how to build a RESTful web service with Create, Read, Update, and Delete operations using Spring Boot, JPA, MySQL, and Maven.

---

##  Technologies Used

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL Database
- Maven
- Postman (for API testing)

---

## 🧾 API Endpoints

| Method | Endpoint                  | Description                     |
|--------|---------------------------|---------------------------------|
| GET    | `/api/employees/all`      | Fetch all employees             |
| GET    | `/api/employees/{id}`     | Fetch employee by ID            |
| POST   | `/api/employees/save`     | Create new employee             |
| PUT    | `/api/employees/update/{id}` | Update existing employee     |
| DELETE | `/api/employees/removeId/{id}` | Delete employee by ID      |

### Sample Request Body (POST/PUT)
```json
{
  "employeeName": "Sachin Kumar",
  "email": "sachin@example.com",
  "password": "sachin123"
}
```

---

  ## Setup and Run Locally

### 1. 📥 Clone the Repository
```bash
git clone https://github.com/Sachingupta03/spring-boot-rest-api-crud.git
cd spring-boot-rest-api-crud
```
