package org.kshrd.restapi02homeworkeventticketingsystem.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventAttendeeRepository {

    @Insert("INSERT INTO event_attendee(event_id, attendee_id) VALUES (#{eventId}, #{attendeeId})")
    void insertEventIdAndAttendeeIdIntoEventAttendee(Long eventId, Long attendeeId);

    @Delete("DELETE FROM event_attendee WHERE event_id = #{eventId}")
    void deleteEventIdAndAttendeeIdFromEventAttendee(Long eventId);
}
