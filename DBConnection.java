import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            String url = System.getenv().getOrDefault("ALUMNI_DB_URL", "jdbc:mysql://localhost:3306/alumni_db");
            String user = System.getenv().getOrDefault("ALUMNI_DB_USER", "root");
            String pass = System.getenv().getOrDefault("ALUMNI_DB_PASS", "root");
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed. Check DB URL/user/password and that MySQL is running.", e);
        }
    }
}
