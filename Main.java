import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    private final AlumniDAO dao = new AlumniDAO();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("=== Alumni Database (Terminal) ===");
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addAlumni();
                    break;
                case "2":
                    viewAllAlumni();
                    break;
                case "3":
                    searchAlumni();
                    break;
                case "4":
                    deleteAlumni();
                    break;
                case "5":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1) Add Alumni");
        System.out.println("2) View All Alumni");
        System.out.println("3) Search Alumni");
        System.out.println("4) Delete Alumni");
        System.out.println("5) Exit");
        System.out.print("Enter choice: ");
    }

    private void addAlumni() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Roll No: ");
            String rollNo = scanner.nextLine().trim();
            System.out.print("Year: ");
            String yearText = scanner.nextLine().trim();
            System.out.print("Branch: ");
            String branch = scanner.nextLine().trim();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Phone: ");
            String phone = scanner.nextLine().trim();
            System.out.print("Job: ");
            String job = scanner.nextLine().trim();

            if (name.isEmpty() || rollNo.isEmpty() || yearText.isEmpty()) {
                System.out.println("Name, Roll No and Year are required.");
                return;
            }

            int year = Integer.parseInt(yearText);
            boolean ok = dao.addAlumni(name, rollNo, year, branch, email, phone, job);
            System.out.println(ok ? "Saved successfully." : "Not saved.");
        } catch (NumberFormatException e) {
            System.out.println("Enter valid Year.");
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private void viewAllAlumni() {
        printAlumniResultSet(dao.getAllAlumni());
    }

    private void searchAlumni() {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine().trim();
        printAlumniResultSet(dao.searchAlumni(keyword));
    }

    private void deleteAlumni() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            boolean ok = dao.deleteAlumni(id);
            System.out.println(ok ? "Deleted successfully." : "Not deleted.");
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid numeric ID.");
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    private void printAlumniResultSet(ResultSet rs) {
        if (rs == null) {
            System.out.println("No data found or query failed.");
            return;
        }

        try {
            boolean hasRows = false;
            System.out.printf("%-4s %-20s %-12s %-6s %-12s %-25s %-15s %-20s%n",
                    "ID", "Name", "Roll No", "Year", "Branch", "Email", "Phone", "Job");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                hasRows = true;
                System.out.printf("%-4d %-20s %-12s %-6d %-12s %-25s %-15s %-20s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getInt("year"),
                        rs.getString("branch"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("job")
                );
            }
            if (!hasRows) {
                System.out.println("No records found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to load data: " + e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ignored) {
            }
        }
    }
}
