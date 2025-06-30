
# MindsMeet

**A Java‑based Learning Management & Student Collaboration System**  
A full‑stack application built using Java, MySQL, and MVC architecture to facilitate student collaboration, course administration, and user management.

---

## 🚀 Features

- **User Authentication**: Role‑based login (Admin, Student, Faculty)
- **Course Management**: CRUD operations for courses and enrollments
- **Student Collaboration**: Discussion forums or group assignments (if applicable)
- **Admin Dashboard**: Manage users, courses, and permissions
- **MVC Pattern**: Clean separation of Model, View, Controller
- **DAO Layer & Query Constants**: Organized database access logic
- **Connection Pooling**: Efficient DB connections via singleton database manager
- **Unit Testing**: JUnit coverage for core components
- **Graceful Shutdown**: DB connections closed safely with shutdown hooks

---

## 🧩 Tech Stack

- Java (JDK 8+)
- MySQL (8.x+)
- JPA
- EJB
- JUnit
- JSF and Primefaces for UI

---

## 📁 Project Structure

```text
MindsMeet/
├── lib/                # JDBC driver, JUnit, other JARs
├── src/main/java/      # Application source code
│   ├── model/…         # Data entities
│   ├── dao/…           # DAO interfaces & implementations
│   ├── util/…          # DB manager, Config loader
│   ├── controller/…    # MVC logic
│   └── view/…          # UI classes
└── src/test/java/      # JUnit tests
```

---

## ⚙️ Prerequisites

- JDK 8 or higher
- MySQL Server running locally (or remote)
- MySQL Connector/J driver in `lib/`

---

## 📦 Setup Instructions

1. Clone the repo:
   git clone https://github.com/PatelShakil/MindsMeet.git
   cd MindsMeet

2. Create the MySQL database and required tables. You may use a script like `schema.sql` if provided or manually set up the database as per your DAO layer.

3. Update the `config.properties` file (usually in `src/main/resources/` or similar) with your DB credentials:
   db.url=jdbc:mysql://localhost:3306/mindsmeet
   db.username=root
   db.password=your_password

4. Ensure all required JARs in `lib/` (like `mysql-connector-java`, `junit`, etc.) are included in the classpath.

5. Compile and run the application:
   javac -cp ".:lib/*" -d bin src/main/java/**/*.java
   java -cp ".:lib/*:bin" com.yourpackage.Main
   (Replace com.yourpackage.Main with your actual entry point class name.)

---

## 🧪 Running Tests

To execute JUnit tests:

java -jar lib/junit-platform-console-standalone-1.10.0.jar \
  --class-path bin:lib/* --scan-class-path

Ensure test classes follow JUnit conventions and are located in the appropriate `src/test/java/` directory.

---

## 📈 Usage

- Login using credentials (admin/student/faculty) depending on your user role.
- Admins can create subjects, assign faculty, and view system analytics.
- Students can access uploaded notes, post questions, and interact.
- The entire system follows the MVC structure for maintainability and clarity.
- Database connections are automatically closed using JVM shutdown hooks.

---

## 🤝 Contributing

1. Fork the repository  
2. Create a feature branch (`git checkout -b feature‑X`)  
3. Commit your changes with clear messages  
4. Push to your fork and submit a pull request  

Pull requests are welcome for features, bug fixes, and documentation updates.

---

## 📄 License

Distributed under the MIT License. You are free to use, modify, and distribute with proper attribution.

---

## 📞 Contact

**Developed & Maintained by:**  
M.Shakil Patel  
GitHub: https://github.com/PatelShakil  
Email: patelshakil.dev@gmail.com  

For feedback, collaboration, or deployment consultation, feel free to open an issue or connect via GitHub.
