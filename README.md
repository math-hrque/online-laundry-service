# online-laundry-service

_An application to manage and track online laundry services seamlessly_

## Introduction
**Online Laundry Service** is a web application designed to simplify laundry management. Customers can register, request clothes to be washed, and track the status of their laundry online. Employees manage the laundry status through the system until the clothes are cleaned and ready to be returned.

- **Frontend**: Built with Angular, using AuthGuard for route protection.
- **Backend**: Java, Spring Boot and PostgreSQL.
- **Database Management**: Versioning of transactional databases is handled using Flyway.  
- **Design Pattern**: The architecture is based on the MVC (Model-View-Controller) pattern.  
- **Security**: Implements secure authentication with user passwords hashed using Bcrypt.  
- **API Best Practices**: Consistent HTTP status codes and DTOs (Data Transfer Objects) for efficient communication between services.  

The system supports two user profiles, **customers** and **employees**, with detailed functionality tailored to each, prioritizing security, efficiency, and ease of use.

---

## Requirements

- **Docker**

---

## How to Run

### Clone the Repository

1. Clone this repository:

    ```bash
    git clone https://github.com/math-hrque/online-laundry-service.git
    ```
2. Open a terminal and navigate to the directory where the project files are located.

### Run the Project

1. Using the Automated Shell Script:

   1.1. Grant execute permission to the script located in the project's root directory:
    
    ```bash
    chmod +x start.sh
    ```

   1.2. Run the script to build the images and start the services:
    
    ```bash
    ./start.sh
    ```

2. Using Docker Compose Directly:

   2.1. In the project's root directory, execute the following command to build the images and start the services:
    
    ```bash
    docker-compose up --build -d
    ```
---

## Access the Application

- **Frontend (Angular)**: Access the UI at [http://localhost:4200](http://localhost:4200).
- **Server**: The server is running on: [http://localhost:8080](http://localhost:8080).

---

## Pre-Configured Users

To simplify testing, the system includes the following pre-configured users:

- **Customer**:  
  - Email: `joao@gmail.com`  
  - Password: `pass`  

- **Employee**:  
  - Email: `maria@gmail.com`  
  - Password: `pass`  

You can use these credentials to explore the system without creating new accounts.

---

## Email Configuration for Account Creation

The system sends a randomly generated 4-digit password to the user's email when they create an account. To enable this feature, you must configure the email settings in the `application.properties` file of the **server**. 

### Steps to Configure:

1. Open the `application.properties` file in the **server** directory.
2. Replace the placeholders in the email configuration section with your email credentials:

    ```properties
    spring.mail.username=your-email@gmail.com  # Replace with your email
    spring.mail.password=your-email-password   # Replace with your email password
    ```

4. Ensure that your email account allows less secure app access (or configure an app password if using Gmail with 2FA). Refer to your email provider's documentation for details.

5. Save the changes and restart the **server** to apply the new configuration.

---

## Access Supporting Services

- **PostgreSQL**:  
  PostgreSQL is running on port `5432`. You can connect using a PostgreSQL client like **pgAdmin** or the command line.  
  - **Connection Details**:
    - Host: `localhost`
    - Port: `5432`
    - Username: `postgres`
    - Password: `postgres`

---

## Additional Notes

- Logs: To check service logs, use:
  ```bash
  docker-compose logs -f
  ```

- Stop Services: To stop all running services, execute:
  ```bash
  docker-compose down
  ```

- Rebuild and Restart: If needed, rebuild and restart services:
  ```bash
  docker-compose up --build -d
  ```
