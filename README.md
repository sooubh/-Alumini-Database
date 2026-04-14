# Alumni Database (Simple Java + MySQL)

Very simple alumni database project with:
- SQL schema
- Java DB connection
- Java DAO (add/view/search/delete)
- Java terminal app (CLI)

## Files
- `schema.sql`
- `DBConnection.java`
- `AlumniDAO.java`
- `Main.java`

## 1) Create database
Run `schema.sql` in MySQL.

## 2) Database configuration (optional)
You can set environment variables:
- `ALUMNI_DB_URL` (default: `jdbc:mysql://localhost:3306/alumni_db`)
- `ALUMNI_DB_USER` (default: `root`)
- `ALUMNI_DB_PASS` (default: `root`)

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

The app runs fully in terminal with menu options for add, view, search, and delete.
