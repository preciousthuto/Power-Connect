package clusterintelligence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClusterIntelligenceAnalyzer {

    private int alertThreshold;

    public ClusterIntelligenceAnalyzer(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }

    public List<ReportCluster> analyzeAndFlagClusters(List<Report> allReports) {
        Map<String, ReportCluster> clusterMap = new HashMap<>();

        // Step 1: Group reports by category and location
        for (Report report : allReports) {
            String key = report.getCategory().trim().toLowerCase() + "|" + report.getLocation().trim().toLowerCase();
            clusterMap.putIfAbsent(key, new ReportCluster(report.getCategory(), report.getLocation()));
            clusterMap.get(key).addReport(report);
        }

        // Step 2: Evaluate each cluster against the alert threshold
        List<ReportCluster> processedClusters = new ArrayList<>();
        for (ReportCluster cluster : clusterMap.values()) {
            if (cluster.getReportCount() >= alertThreshold) {
                cluster.setFlagged(true);
            }
            processedClusters.add(cluster);
        }

        return processedClusters;
    }
}