package powerconnect;
    
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ReportManager manager = new ReportManager();

        int choice;

        do {
            System.out.println("\n=== POWER CONNECT ===");
            System.out.println("1. Submit a report");
            System.out.println("2. View all reports");
            System.out.println("3. Update report status");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Category (Electricity, Water, Sanitation): ");
                    String category = input.nextLine();

                    System.out.print("Description: ");
                    String description = input.nextLine();

                    System.out.print("Location: ");
                    String location = input.nextLine();

                    manager.submitReport(
                            category, description, location
                    );
                }

                case 2 -> manager.viewReports();

                case 3 -> {
                    System.out.print("Report reference: ");
                    String id = input.nextLine();

                    System.out.print("New status (Assigned, In Progress, Resolved): ");
                    String status = input.nextLine();

                    manager.updateReportStatus(id, status);
                }

                case 0 -> System.out.println("Goodbye!");

                default -> System.out.println("Invalid menu option.");
            }

        } while (choice != 0);

        input.close();
    }
}

    

