

package powerconnect;

import java.time.LocalDateTime;

public class Report {

    private String reportId;
    private String description;
    private String location;
    private String status;
    private ServiceCategory1 category;
    private LocalDateTime dateReported;

    // Stores the main report ID if this report is linked
    private String linkedToReportId;

    public Report(String reportId, ServiceCategory1 category,
                  String description, String location) {

        this.reportId = reportId;
        this.description = description;
        this.location = location;
        this.category = category;
        this.status = ReportStatus.REPORTED;
        this.dateReported = LocalDateTime.now();
        this.linkedToReportId = null;
    }

    public String getReportId() {
        return reportId;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public ServiceCategory1 getCategory() {
        return category;
    }

    public LocalDateTime getDateReported() {
        return dateReported;
    }

    public void setDateReported(LocalDateTime dateReported) {
        this.dateReported = dateReported;
    }

    public String getLinkedToReportId() {
        return linkedToReportId;
    }

    public void setLinkedToReportId(String linkedToReportId) {
        this.linkedToReportId = linkedToReportId;
    }

    public void updateStatus(String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            System.out.println("Error: Status cannot be empty.");
            return;
        }

        this.status = newStatus.trim();
        System.out.println("Report status updated to: " + this.status);
    }

    public void displayReport() {
        System.out.println("------------------------------");
        System.out.println("Report ID: " + reportId);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("Category: " + category.getName());
        System.out.println("Status: " + status);
        System.out.println("Date Reported: " + dateReported);

        if (linkedToReportId != null) {
            System.out.println("Linked to main report: "
                    + linkedToReportId);
        } else {
            System.out.println("Type: Main Report");
        }
    }
}
