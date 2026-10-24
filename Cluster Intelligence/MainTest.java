package clusterintelligence;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        List<Report> mockReports = new ArrayList<>();

        // Simulating 6 reports for Water in Chelston (should cross the threshold of 5 and be flagged)
        mockReports.add(new Report(101, "Water", "Chelston", "No water supply since morning", "Reported"));
        mockReports.add(new Report(102, "Water", "Chelston", "Low pressure / dry taps", "Reported"));
        mockReports.add(new Report(103, "Water", "Chelston", "Water cut off completely", "Reported"));
        mockReports.add(new Report(104, "Water", "Chelston", "No running water", "Reported"));
        mockReports.add(new Report(105, "Water", "Chelston", "Dry taps reported", "Reported"));
        mockReports.add(new Report(106, "Water", "Chelston", "Water outage across block", "Reported"));

        // Simulating 2 reports for Electricity in Silverest (below threshold, unflagged)
        mockReports.add(new Report(201, "Electricity", "Silverest", "Transformer sparking near main road", "Reported"));
        mockReports.add(new Report(202, "Electricity", "Silverest", "Voltage fluctuation", "Reported"));

        // Instantiate analyzer with a threshold of 5 reports
        int threshold = 5;
        ClusterIntelligenceAnalyzer analyzer = new ClusterIntelligenceAnalyzer(threshold);
        List<ReportCluster> clusters = analyzer.analyzeAndFlagClusters(mockReports);

        System.out.println("=================================================");
        System.out.println("     POWER CONNECT - CLUSTER INTELLIGENCE        ");
        System.out.println("=================================================");

        for (ReportCluster cluster : clusters) {
            System.out.println("Cluster: " + cluster.getClusterKey());
            System.out.println("Total Reports Grouped: " + cluster.getReportCount());

            if (cluster.isFlagged()) {
                System.out.println("STATUS: [FLAGGED PATTERN] Exceeds threshold of " + threshold + " reports!");
            } else {
                System.out.println("STATUS: [NORMAL] Below threshold.");
            }

            System.out.println("Report Details:");
            for (Report r : cluster.getReports()) {
                System.out.println("   - ID #" + r.getReportId() + ": " + r.getDescription());
            }
            System.out.println("-------------------------------------------------");
        }
    }
}