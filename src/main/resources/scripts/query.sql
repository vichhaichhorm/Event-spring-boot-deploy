SELECT a.attendee_id, attendee_name, email FROM attendees a INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id WHERE event_id = 16;