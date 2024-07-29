package org.kshrd.restapi02homeworkeventticketingsystem.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Event;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("INSERT INTO events(event_name, event_date, venue_id) VALUES (#{event.eventName}, #{event.eventDate}, #{event.venueId}) RETURNING event_id")
    Long addNewEvent(@Param("event") EventRequest eventRequest);

    @Select("SELECT * FROM events WHERE event_id = #{eventId}")
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "org.kshrd.restapi02homeworkeventticketingsystem.repository.VenueRepository.findVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "org.kshrd.restapi02homeworkeventticketingsystem.repository.AttendeeRepository.findAllAttendeesByEventId"))
    })
    Event findEventById(Long eventId);

    @Update("UPDATE events SET event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId} WHERE event_id = #{eventId}")
    void updateEventById(Long eventId, @Param("event") EventRequest eventRequest);

    @Delete("DELETE FROM events WHERE event_id = #{eventId}")
    void deleteEventById(Long eventId);

    @Select("SELECT * FROM events OFFSET #{offset} LIMIT #{limit}")
    @ResultMap("eventMapper")
    List<Event> findAllEvents(Long offset, Long limit);
}
