package clusterintelligence;

public class Report {
    private int reportId;
    private String category;    // e.g., "Electricity", "Water", "Sanitation"
    private String location;    // e.g., "Chelston", "Silverest"
    private String description;
    private String status;      // e.g., "Reported", "Assigned", "In Progress", "Resolved"

    public Report(int reportId, String category, String location, String description, String status) {
        this.reportId = reportId;
        this.category = category;
        this.location = location;
        this.description = description;
        this.status = status;
    }

    public int getReportId() { return reportId; }
    public String getCategory() { return category; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
}