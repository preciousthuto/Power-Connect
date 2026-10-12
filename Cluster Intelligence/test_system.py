from analyzer import ClusterIntelligenceAnalyzer

def test_cluster_analyzer():
    # Mock data simulating database records for testing independent of a live database
    mock_data = [
        {"history_id": 1, "report": "Grid overload in Sector A", "status": "Critical", "changed_by": "Node-Alpha"},
        {"history_id": 2, "report": "Voltage drop", "status": "Pending", "changed_by": "Node-Alpha"},
        {"history_id": 3, "report": "Substation sync", "status": "Resolved", "changed_by": "Node-Beta"}
    ]
    
    analyzer = ClusterIntelligenceAnalyzer(failure_threshold=2)
    result = analyzer.analyze_status_logs(mock_data)
    
    # Assertions to verify expected results
    assert result["status"] == "Warning", "Expected system health status to trigger a Warning."
    assert "Node-Alpha" in result["flagged_nodes"], "Node-Alpha should be flagged due to threshold breaches."
    print("All cluster intelligence tests passed successfully!")

if __name__ == "__main__":
    test_cluster_analyzer()