class ClusterIntelligenceAnalyzer:
    def __init__(self, failure_threshold: int = 2):
        self.failure_threshold = failure_threshold

    def analyze_status_logs(self, history_logs):
        """Analyzes status history to flag unhealthy cluster nodes and evaluate system health."""
        node_incident_counts = {}
        unresolved_reports = 0

        for log in history_logs:
            node = log.get("changed_by", "Unknown_Node")
            status = log.get("status", "").lower()
            
            if status in ["failed", "critical", "pending"]:
                node_incident_counts[node] = node_incident_counts.get(node, 0) + 1
                if status == "pending":
                    unresolved_reports += 1

        flagged_nodes = [
            node for node, count in node_incident_counts.items() 
            if count >= self.failure_threshold
        ]

        system_health_score = max(0.0, 1.0 - (len(flagged_nodes) * 0.25))

        return {
            "system_health_score": system_health_score,
            "flagged_nodes": flagged_nodes,
            "unresolved_reports_count": unresolved_reports,
            "status": "Warning" if flagged_nodes else "Optimal"
        }