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