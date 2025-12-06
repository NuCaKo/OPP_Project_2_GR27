# Contact Management System (Group 27)

A Java-based console application for managing contacts, featuring role-based access control, MySQL database integration, and a rich console UI with animations.

## Features

- **Role-Based Access Control:** For all Roles Testers, Junior Developers, Senior Developers, and Managers has diffirent access levels.
- **Contact Management:** Add, update, delete, and view contacts (functionality depends on user role).
- **Database Integration:** Using via MySQL database stores users and contatcs
- **Console UI:** Advanced console interface with animations (Loading Screen, Termination Screen, Menu Transitions).
- **Security:** User passwords are secured using SHA-256 hashing.

## Prerequisites

- **Java Development Kit (JDK):** Version 8 or higher.
- **MySQL Server:** A running MySQL instance.
- **MySQL Connector/J:** Included in the `lib/` directory.

## Installation and Setup

### 1. Database Setup

1.  Ensure your MySQL server is running.
2.  Execute the SQL script located at `sql/group27.sql` to create the database (`contact_mgmt_db`) and tables.
    ```bash
    mysql -u root -p < sql/group27.sql
    ```
    *Note: This script also inserts default users.*

### 2. Configuration

Open `src/com/group27/config/DatabaseHelper.java` and check the database connection settings.
By default, it is configured as:
- **URL:** `jdbc:mysql://localhost:3306/contact_mgmt_db`
- **User:** `root`
- **Password:** `pass1234`

**If your MySQL credentials differ, update the `USER` and `PASSWORD` constants in this file before compiling.**

## Compilation

 You need to include the MySQL Connector JAR in the classpath to run the project.

**On Linux/macOS:**
```bash
javac -cp .:lib/mysql-connector-j-9.5.0.jar -d out $(find src -name "*.java")
```

**On Windows (Command Prompt):**
```cmd
dir /s /b src\*.java > sources.txt
javac -cp .;lib\mysql-connector-j-9.5.0.jar -d out @sources.txt
del sources.txt
```

## Running the Application

After compilation, run the application using the following command (ensure you are in the project root):

**On Linux/macOS:**
```bash
java -cp out:lib/mysql-connector-j-9.5.0.jar com.group27.Main
```

**On Windows:**
```cmd
java -cp out;lib\mysql-connector-j-9.5.0.jar com.group27.Main
```

## Default Users

The system shows up with the following pre-configured users (username / password):

- **Tester:** `tt` / `tt`
- **Junior Developer:** `jd` / `jd`
- **Senior Developer:** `sd` / `sd`
- **Manager:** `man` / `man`

## Project Structure

- `src/com/group27/Main.java`: Entry point of the application.
- `src/com/group27/config/`: Configuration classes (Database connection).
- `src/com/group27/dao/`: Data Access Objects for database interaction.
- `src/com/group27/model/`: Data models (User, Contact).
- `src/com/group27/service/`: Business logic services.
- `src/com/group27/ui/`: Console UI classes and animations.
- `src/com/group27/util/`: Utility classes (Password hashing, Input helper).
- `lib/`: External libraries (MySQL Connector).
- `sql/`: Database initialization scripts.
