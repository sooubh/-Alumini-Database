# Alumni Database (Simple Java + MySQL)

Very simple alumni database project with:
- SQL schema
- Java DB connection
- Java DAO (add/view/search/delete)
- Java Swing UI

## Files
- `/home/runner/work/-Alumini-Database/-Alumini-Database/schema.sql`
- `/home/runner/work/-Alumini-Database/-Alumini-Database/DBConnection.java`
- `/home/runner/work/-Alumini-Database/-Alumini-Database/AlumniDAO.java`
- `/home/runner/work/-Alumini-Database/-Alumini-Database/Main.java`

## 1) Create database
Run `schema.sql` in MySQL.

## 2) Update DB credentials
Edit username/password in `DBConnection.java` if needed.

## 3) Compile
Make sure MySQL JDBC jar is available (example: `mysql-connector-j-8.4.0.jar`).

Linux/macOS:
```bash
javac -cp .:mysql-connector-j-8.4.0.jar DBConnection.java AlumniDAO.java Main.java
```

Windows:
```bat
javac -cp .;mysql-connector-j-8.4.0.jar DBConnection.java AlumniDAO.java Main.java
```

## 4) Run
Linux/macOS:
```bash
java -cp .:mysql-connector-j-8.4.0.jar Main
```

Windows:
```bat
java -cp .;mysql-connector-j-8.4.0.jar Main
```
