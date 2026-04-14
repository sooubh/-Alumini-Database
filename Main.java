import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("=== Simple Employee Data App ===");
        int count = readEmployeeCount();
        inputEmployees(count);

        while (true) {
            printViewMenu();
            String choice = scanner.nextLine().trim();
            if ("1".equals(choice)) {
                showOneEmployee();
            } else if ("2".equals(choice)) {
                showAllEmployees();
            } else if ("3".equals(choice)) {
                System.out.println("Exiting...");
                return;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private int readEmployeeCount() {
        while (true) {
            System.out.print("Enter number of employees: ");
            String text = scanner.nextLine().trim();
            try {
                int count = Integer.parseInt(text);
                if (count <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                } else {
                    return count;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private void inputEmployees(int count) {
        for (int i = 1; i <= count; i++) {
            System.out.println();
            System.out.println("Enter details for employee " + i + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Employee ID: ");
            String employeeId = scanner.nextLine().trim();
            System.out.print("Department: ");
            String department = scanner.nextLine().trim();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Phone: ");
            String phone = scanner.nextLine().trim();
            employees.add(new Employee(name, employeeId, department, email, phone));
        }
    }

    private void printViewMenu() {
        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("1) Show one employee by number");
        System.out.println("2) Show all employee data");
        System.out.println("3) Exit");
        System.out.print("Enter choice: ");
    }

    private void showOneEmployee() {
        int index = readEmployeeIndex();
        printEmployee(index + 1, employees.get(index));
    }

    private int readEmployeeIndex() {
        while (true) {
            System.out.print("Enter employee number (1 to " + employees.size() + "): ");
            String text = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(text);
                if (number < 1 || number > employees.size()) {
                    System.out.println("Please enter a number in range.");
                } else {
                    return number - 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private void showAllEmployees() {
        for (int i = 0; i < employees.size(); i++) {
            printEmployee(i + 1, employees.get(i));
        }
    }

    private void printEmployee(int number, Employee employee) {
        System.out.println();
        System.out.println("Employee " + number + ":");
        System.out.println("Name: " + employee.name);
        System.out.println("Employee ID: " + employee.employeeId);
        System.out.println("Department: " + employee.department);
        System.out.println("Email: " + employee.email);
        System.out.println("Phone: " + employee.phone);
    }

    private static class Employee {
        private final String name;
        private final String employeeId;
        private final String department;
        private final String email;
        private final String phone;

        private Employee(String name, String employeeId, String department, String email, String phone) {
            this.name = name;
            this.employeeId = employeeId;
            this.department = department;
            this.email = email;
            this.phone = phone;
        }
    }
}
