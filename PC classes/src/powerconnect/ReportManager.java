

package powerconnect;

import java.util.ArrayList;
import java.util.List;

public class ReportManager {

    private final List<Report> reports = new ArrayList<>();
    private int nextId = 1;

    public void submitReport(String category, String description,
                             String location) {

        if (category == null || category.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || location == null || location.trim().isEmpty()) {

            System.out.println("Please complete all report fields.");
            return;
        }

        ServiceCategory1 cat;

        try {
            cat = new ServiceCategory1(
                ServiceCategory.valueOf(
                    category.trim().toUpperCase().replace(" ", "_")
                )
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Invalid category. Choose: water, electricity, "
                + "waste management, sanitation or road maintenance."
            );
            return;
        }

        // Look for an existing report about the same problem
        Report existingReport = findSimilarReport(
            cat, description, location
        );

        // Create a separate report for this submission
        Report report = new Report(
            "PC-" + nextId, cat, description, location
        );

        if (existingReport != null) {

            // Find the main report if the match is already linked
            String mainReportId =
                existingReport.getLinkedToReportId() != null
                ? existingReport.getLinkedToReportId()
                : existingReport.getReportId();

            report.setLinkedToReportId(mainReportId);

            System.out.println("Similar report found!");
            System.out.println(
                "Your report has been linked to main report: "
                + mainReportId
            );

        } else {
            System.out.println("A new main report has been created.");
        }

        reports.add(report);
        nextId++;

        System.out.println("Report submitted successfully!");
        System.out.println("Your reference is: " + report.getReportId());
    }

    // Find a report with the same category, description and location
    private Report findSimilarReport(ServiceCategory1 category,
                                     String description,
                                     String location) {

        for (Report report : reports) {

            boolean sameCategory = normalize(
                report.getCategory().getName()
            ).equals(normalize(category.getName()));

            boolean sameDescription = normalize(
                report.getDescription()
            ).equals(normalize(description));

            boolean sameLocation = normalize(
                report.getLocation()
            ).equals(normalize(location));

            if (sameCategory && sameDescription && sameLocation) {
                return report;
            }
        }

        return null;
    }

    // Ignore capitalization and repeated spaces
    private String normalize(String value) {
        return value.trim()
                    .replaceAll("\\s+", " ")
                    .toLowerCase();
    }

    public void viewReports() {
        checkForEscalations();

        if (reports.isEmpty()) {
            System.out.println("No reports have been submitted.");
            return;
        }

        for (Report report : reports) {
            report.displayReport();
        }
    }

    public void updateReportStatus(String reportId, String newStatus) {
        checkForEscalations();

        for (Report report : reports) {

            if (report.getReportId().equalsIgnoreCase(reportId)) {

                if (ReportStatus.ESCALATED.equalsIgnoreCase(newStatus)) {
                    System.out.println(
                        "Escalation is handled automatically by the system."
                    );
                    return;
                }

                if (!ReportStatus.isValidStatus(newStatus)) {
                    System.out.println("Invalid report status.");
                    return;
                }

                report.updateStatus(newStatus);
                System.out.println(
                    "Current status: " + report.getStatus()
                );
                return;
            }
        }

        System.out.println("Report not found: " + reportId);
    }

    public void checkForEscalations() {
        AutoEscalation.checkReports(reports);
    }
}
