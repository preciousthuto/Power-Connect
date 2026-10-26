

package powerconnect;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AutoEscalation {

    public static void checkReports(List<Report> reports) {

        for (Report report : reports) {

            
            if (!ReportStatus.RESOLVED.equals(report.getStatus())
                    && !ReportStatus.ESCALATED.equals(report.getStatus())) {

                
                long hours = Duration.between(
                    report.getDateReported(),
                    LocalDateTime.now()
                ).toHours();

                
                if (hours >= 24) {

                    report.updateStatus(ReportStatus.ESCALATED);

                    System.out.println(
                        "AUTO-ESCALATION: Report "
                        + report.getReportId()
                        + " has been escalated after 24 hours."
                    );
                }
            }
        }
    }
}


