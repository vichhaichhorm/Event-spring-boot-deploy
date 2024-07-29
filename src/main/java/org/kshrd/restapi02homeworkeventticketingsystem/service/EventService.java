package org.kshrd.restapi02homeworkeventticketingsystem.service;

import org.kshrd.restapi02homeworkeventticketingsystem.model.Event;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Venue;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.EventRequest;

import java.util.List;

public interface EventService {
    Event addNewEvent(EventRequest eventRequest);

    Event findEventById(Long eventId);

    Event updateEventById(Long eventId, EventRequest eventRequest);

    void deleteEventById(Long eventId);

    List<Event> findAllEvents(Long offset, Long limit);
}
