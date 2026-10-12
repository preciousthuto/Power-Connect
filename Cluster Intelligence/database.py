import mysql.connector

class DatabaseConnector:
    def __init__(self, host="localhost", user="root", password="your_password", database="powerconnect_database"):
        self.config = {
            "host": host,
            "user": user,
            "password": password,
            "database": database
        }

    def fetch_status_history(self):
        """Connects to MySQL and fetches report status logs."""
        try:
            db = mysql.connector.connect(**self.config)
            cursor = db.cursor(dictionary=True)
            query = """
                SELECT 
                    h.id AS history_id,
                    r.description AS report,
                    s.name AS status,
                    u.name AS changed_by,
                    h.notes,
                    h.changed_at
                FROM report_status_history h
                JOIN reports r ON h.report_id = r.id
                JOIN statuses s ON h.status_id = s.id
                JOIN users u ON h.changed_by = u.id;
            """
            cursor.execute(query)
            results = cursor.fetchall()
            cursor.close()
            db.close()
            return results
        except Exception as e:
            print(f"Database connection error: {e}")
            return []