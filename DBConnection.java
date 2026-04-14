import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv().getOrDefault("ALUMNI_DB_URL", "jdbc:mysql://localhost:3306/alumni_db");
            String user = System.getenv().getOrDefault("ALUMNI_DB_USER", "root");
            String pass = System.getenv().getOrDefault("ALUMNI_DB_PASS", "root");
            return DriverManager.getConnection(
                    url,
                    user,
                    pass
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
