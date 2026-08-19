# CTRL ALT KEEB

CTRL ALT KEEB is a Spring Boot e-commerce web application for split and ergonomic mechanical keyboards.

The application allows users to browse keyboard products, view product details, register and log in, manage a shopping cart, and place orders. Administrators have access to additional management features for users and products.

The project uses Spring Boot, Spring Data JPA, Spring Security, Thymeleaf, Bootstrap, H2, and MySQL.

---

## Technologies Used

- Java 21
- Spring Boot 4.1.0
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- Bootstrap
- Maven
- H2 Database
- MySQL

---

## Features

### Product Management

The application provides a product catalog for split and ergonomic keyboards.

Features include:

- Browse available products
- View individual product details
- Add new products
- Edit existing products
- Server-side form validation
- Product filtering
- Product sorting
- Database persistence using Spring Data JPA
- Sample product data for development

### User Management and Security

The application uses Spring Security for authentication and authorization.

Features include:

- User registration
- BCrypt password encoding
- Custom login page
- Login error handling
- Logout functionality
- Role-based authorization
- Protected application routes

The application supports the following roles:

- `ROLE_ADMIN`
- `ROLE_STAFF`
- `ROLE_CUSTOMER`

### Admin Interface

Administrators have access to a protected admin interface.

Admin functionality includes:

- Admin dashboard
- View registered users
- Edit user information
- Edit user roles
- Delete users

Routes under `/admin/**` are restricted to administrators.

### Shopping Cart and Checkout

Authenticated users can:

- Add products to their cart
- View cart contents
- Manage product quantities
- View order totals
- Proceed to checkout
- Enter shipping information
- Place an order

Orders and order items are stored using JPA entities.

---

# Configuration and Spring Profiles

The application uses YAML configuration files instead of traditional `.properties` configuration.

Configuration is divided into:

```text
src/main/resources/application.yml
src/main/resources/application-dev.yml
src/main/resources/application-qa.yml
```

`application.yml` contains configuration shared between environments.

The `dev` profile is used for local development with H2.

The `qa` profile is used with a persistent MySQL database.

The default profile is `dev`.

Profiles can be switched from the command line without modifying Java source code.

---

# Development Profile - H2

The `dev` profile uses an in-memory H2 database.

Default configuration:

```text
Database: H2
Database Name: ctrlaltkeeb
JDBC URL: jdbc:h2:mem:ctrlaltkeeb
Username: sa
Password: blank
```

The H2 console is enabled at:

```text
http://localhost:8080/h2-console
```

The development database is created when the application starts.

## Run Using the Development Profile

Open a terminal in the `ctrl-alt-keeb-app` directory:

```powershell
cd ctrl-alt-keeb-app
```

Run:

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=dev"
```

The application will be available at:

```text
http://localhost:8080
```

---

# QA Profile - MySQL

The `qa` profile uses a persistent MySQL database.

MySQL must be installed and running before starting the application with this profile.

The default configuration is:

```text
Host: localhost
Port: 3306
Database: ctrlaltkeeb
Username: root
Password: root
```

Create the database before starting the QA profile:

```sql
CREATE DATABASE ctrlaltkeeb;
```

The default JDBC connection is:

```text
jdbc:mysql://localhost:3306/ctrlaltkeeb
```

---

## Environment Variables

Database configuration can be changed using environment variables.

Supported variables:

```text
DB_HOST
DB_PORT
DB_NAME
DB_USERNAME
DB_PASSWORD
```

The QA configuration uses placeholders with default values.

For example:

```text
${DB_PASSWORD:root}
```

This allows database credentials to be provided without changing the application's source code.

Example using PowerShell:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="ctrlaltkeeb"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="root"
```

If your local MySQL configuration uses different credentials, change these environment variables accordingly.

---

## Run Using the QA Profile

From the `ctrl-alt-keeb-app` directory:

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=qa"
```

The application will be available at:

```text
http://localhost:8080
```

---

# Switching Profiles

No Java source-code modifications are required when changing database environments.

### Development / H2

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=dev"
```

### QA / MySQL

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=qa"
```

Spring Boot automatically loads the appropriate profile-specific YAML configuration.

---

# Building the Application

Java 21 is required to build and run the application.

Check the installed Java version:

```powershell
java -version
```

From the `ctrl-alt-keeb-app` directory, build the project using:

```powershell
./mvnw package -DskipTests
```

After a successful build, Maven will display:

```text
BUILD SUCCESS
```

---

# Running the Project After a Fresh Clone

1. Clone the repository:

```powershell
git clone <repository-url>
```

2. Enter the project directory:

```powershell
cd cpan-228-group-project-ecommerce
cd ctrl-alt-keeb-app
```

3. Make sure Java 21 is installed:

```powershell
java -version
```

4. To run using H2:

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=dev"
```

5. Open the application at:

```text
http://localhost:8080
```

For the QA environment, start MySQL, create the `ctrlaltkeeb` database, configure environment variables if necessary, and run:

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=qa"
```

---

# Project Structure

```text
cpan-228-group-project-ecommerce/
|
|-- ctrl-alt-keeb-app/
|   |
|   |-- src/main/java/com/ctrlaltkeeb/app/
|   |   |-- config/
|   |   |-- controller/
|   |   |-- model/
|   |   |-- repository/
|   |   `-- service/
|   |
|   |-- src/main/resources/
|   |   |-- templates/
|   |   |-- static/
|   |   |-- data.sql
|   |   |-- application.yml
|   |   |-- application-dev.yml
|   |   `-- application-qa.yml
|   |
|   |-- pom.xml
|   |-- mvnw
|   `-- mvnw.cmd
|
|-- inventory-service/
|
`-- README.md
```

---

# Database Configuration

The application supports two database environments.

## Development

The development environment uses H2 in-memory storage.

```text
Profile: dev
Database: H2
```

This environment is intended for quick local development and testing.

## QA

The QA environment uses MySQL.

```text
Profile: qa
Database: MySQL
```

This provides persistent storage and demonstrates that the application can switch database environments using Spring profiles without source-code changes.

---

# Security

Spring Security is used throughout the application.

Security features include:

- BCrypt password encoding
- User authentication
- Custom login page
- Logout
- Role-based authorization
- Protected routes
- Admin-only management functionality

User information is persisted through Spring Data JPA.

---

# Team Contributions

## Yash Patel

- Product form functionality
- Product validation
- Product management and UI improvements
- Shopping cart functionality and improvements
- Registration functionality
- Security and user management improvements
- Admin user management
- User editing and deletion
- Cart persistence improvements
- Checkout and order placement fixes
- YAML configuration
- Development H2 profile
- QA MySQL profile
- Environment-variable database configuration
- Profile testing and application stabilization
- Final project documentation

## Lukas Myhal

- Initial Spring Boot project structure
- Core e-commerce application setup
- Application domain and model development
- Security and admin dashboard foundation
- Checkout development
- Order and OrderItem models
- Inventory service initialization
- Repository integration
- General project integration and maintenance

---

# Optional Inventory Service

The repository also contains an `inventory-service` Spring Boot project that was started as an optional microservice extension.

The main CTRL ALT KEEB application does not depend on this optional service to run. The primary application can run independently using either the H2 development profile or the MySQL QA profile.

---

# Deliverable 3 Configuration Summary

The final application demonstrates:

- YAML-based Spring Boot configuration
- Common configuration using `application.yml`
- Separate development configuration using `application-dev.yml`
- Separate QA configuration using `application-qa.yml`
- H2 in-memory development database
- Persistent MySQL QA database
- Environment-variable database credentials
- Command-line Spring profile switching
- No source-code modifications required when changing environments
- Existing security, product, cart, checkout, and administration functionality maintained across the application