package org.kshrd.restapi02homeworkeventticketingsystem.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Attendee;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("INSERT INTO attendees(attendee_name, email) VALUES (#{attendee.attendeeName}, #{attendee.email}) RETURNING *")
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    Attendee addNewAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    @Select("SELECT * FROM attendees WHERE attendee_id = #{attendeeId}")
    @ResultMap("attendeeMapper")
    Attendee findAttendeeById(Long attendeeId);

    @Select("UPDATE attendees SET attendee_name = #{attendee.attendeeName}, email = #{attendee.email} WHERE attendee_id = #{attendeeId} RETURNING *")
    @ResultMap("attendeeMapper")
    Attendee updateAttendeeById(Long attendeeId, @Param("attendee") AttendeeRequest attendeeRequest);

    @Delete("DELETE FROM attendees WHERE attendee_id = #{attendeeId}")
    void deleteAttendeeById(Long attendeeId);

    @Select("SELECT * FROM attendees OFFSET #{offset} LIMIT #{limit}")
    @ResultMap("attendeeMapper")
    List<Attendee> findAllAttendees(Long offset, Long limit);

    @Select("SELECT a.attendee_id, attendee_name, email FROM attendees a INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id WHERE event_id = #{eventId};")
    @ResultMap("attendeeMapper")
    List<Attendee> findAllAttendeesByEventId(Long eventId);
}
