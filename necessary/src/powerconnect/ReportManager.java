package powerconnect;


import java.util.ArrayList;
import java.util.List;

public class ReportManager {

    private final List<Report> reports = new ArrayList<>();
    private int nextId = 1;

    public void submitReport(String category, String description, String location) {

        if (category == null || category.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || location == null || location.trim().isEmpty()) {
            System.out.println("Please complete all report fields.");
            return;
        }

        
        ServiceCategory1 cat;
        try {
            cat = new ServiceCategory1(
                ServiceCategory.valueOf(category.trim().toUpperCase().replace(" ", "_"))
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Choose: water, electricity, "
                    + "waste management, sanitation or road maintenance.");
            return;
        }

        Report report = new Report("PC-" + nextId, cat, description, location);

        reports.add(report);
        nextId++;

        System.out.println("Report submitted successfully!");
        System.out.println("Your reference is: " + report.getReportId());
    }

    public void viewReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports have been submitted.");
            return;
        }

        for (Report report : reports) {
            report.displayReport();
        }
    }

    public void updateReportStatus(String reportId, String newStatus) {
        for (Report report : reports) {
            if (report.getReportId().equalsIgnoreCase(reportId)) {
                report.updateStatus(newStatus);
                System.out.println("Current status: " + report.getStatus());
                return;
            }
        }
        System.out.println("Report not found: " + reportId);
    }
}