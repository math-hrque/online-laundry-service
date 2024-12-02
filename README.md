# online-laundry-service
_An application to manage and track online laundry services seamlessly_

## Introduction
Online Laundry Service is a web application designed to simplify laundry management. Customers can register, request clothes to be washed, and track the status of their laundry online. Employees manage the laundry status through the system until the clothes are cleaned and ready to be returned.

- **Backend**: Spring Boot, PostgreSQL, Flyway  
- **Frontend**: Angular with AuthGuard for route protection  
- **Design Pattern**: Follows the MVC (Model-View-Controller) pattern  
- **Key Features**: Includes HTTP status codes for API responses, DTOs for data transfer, and secure user authentication  

---

## Requirements

- **JDK** 17+  
- **Spring Boot** 3.3.2+  
- **Maven** 3.9.7+  
- **PostgreSQL** 16
- **Node.js** 20+
- **Angular** 17+

---

## How to Run

### Clone the Repository

1. Clone this repository:
    ```bash
    git clone https://github.com/math-hrque/online-laundry-service.git
    ```
2. Open a terminal and navigate to the directory where the project files are located.

### Run the Backend

1. Navigate to the backend folder:
    ```bash
    cd server
    ```

2. Configure the database connection in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
    spring.datasource.username=<USERNAME>
    spring.datasource.password=<PASSWORD>
    ```

3. Build and start the backend:
    ```bash
    mvn clean package spring-boot:run
    ```

### Run the Frontend

1. Navigate to the frontend folder:
    ```bash
    cd client
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Start the frontend:
    ```bash
    ng serve
    ```

4. Open the application in your browser at:
    ```
    http://localhost:4200
    ```

---

## Notes

- Ensure the database is running before starting the backend.
- Use appropriate credentials for the database configuration.
- The application is designed for **local development**; ensure ports (e.g., 8080 for the backend, 4200 for the frontend) are not in use.
- Test user credentials for login can be pre-configured in the database using seed data or scripts provided in the `resources` folder.
