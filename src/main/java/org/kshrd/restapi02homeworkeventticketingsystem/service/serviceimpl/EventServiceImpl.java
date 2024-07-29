package org.kshrd.restapi02homeworkeventticketingsystem.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.kshrd.restapi02homeworkeventticketingsystem.exception.BadRequestException;
import org.kshrd.restapi02homeworkeventticketingsystem.exception.NotFoundException;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Attendee;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Event;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Venue;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.EventRequest;
import org.kshrd.restapi02homeworkeventticketingsystem.repository.EventAttendeeRepository;
import org.kshrd.restapi02homeworkeventticketingsystem.repository.EventRepository;
import org.kshrd.restapi02homeworkeventticketingsystem.service.AttendeeService;
import org.kshrd.restapi02homeworkeventticketingsystem.service.EventService;
import org.kshrd.restapi02homeworkeventticketingsystem.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final VenueService venueService;
    private final AttendeeService attendeeService;

    @Override
    public Event addNewEvent(EventRequest eventRequest) {
        venueService.findVenueById(eventRequest.getVenueId());
//        validateEventField(eventRequest);
        Long eventId = eventRepository.addNewEvent(eventRequest);
        for (Long attendeeId : eventRequest.getAttendeesId()) {
            attendeeService.findAttendeeById(attendeeId);
            eventAttendeeRepository.insertEventIdAndAttendeeIdIntoEventAttendee(eventId, attendeeId);
        }
        return findEventById(eventId);
    }

    @Override
    public Event findEventById(Long eventId) {
        Event event = eventRepository.findEventById(eventId);
        if (event == null) {
            throw new NotFoundException("The event id " + eventId + " has not been founded.");
        }
        return event;
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest eventRequest) {
        Event event = findEventById(eventId);
//        validateEventField(eventRequest);
        Venue venue = venueService.findVenueById(eventRequest.getVenueId());
        eventRepository.updateEventById(eventId, eventRequest);
        eventAttendeeRepository.deleteEventIdAndAttendeeIdFromEventAttendee(eventId);
        List<Attendee> attendees = eventRequest.getAttendeesId().stream()
                .map(attendeeService::findAttendeeById)
                .peek(attendee -> eventAttendeeRepository.insertEventIdAndAttendeeIdIntoEventAttendee(eventId, attendee.getAttendeeId()))
                .collect(Collectors.toList());
        event.setEventId(eventId);
        event.setEventName(eventRequest.getEventName());
        event.setEventDate(eventRequest.getEventDate());
        event.setVenue(venue);
        event.setAttendees(attendees);
        return event;
    }

    @Override
    public void deleteEventById(Long eventId) {
        findEventById(eventId);
        eventRepository.deleteEventById(eventId);
    }

    @Override
    public List<Event> findAllEvents(Long offset, Long limit) {
//        if(offset <= 0){
//            throw new BadRequestException("Offset must be more than zero");
//        }
//        if (limit < 0){
//            throw new BadRequestException("Limit must be more than or equal zero");
//        }
        offset = (offset - 1) * limit;
        return eventRepository.findAllEvents(offset, limit);
    }

//    void validateEventField(EventRequest eventRequest) {
//        if (eventRequest.getEventName() == null) {
//            throw new BadRequestException("The event name cannot be null.");
//        }
//        if (eventRequest.getEventName().isBlank()) {
//            throw new BadRequestException("The event name cannot be blank or empty.");
//        }
//        if (eventRequest.getEventDate() == null) {
//            throw new BadRequestException("The event date cannot be null.");
//        }
//        if (eventRequest.getEventDate().toString().isBlank()) {
//            throw new BadRequestException("The event date cannot be blank or empty.");
//        }
//        if (eventRequest.getVenueId() == null) {
//            throw new BadRequestException("The venue id cannot be null.");
//        }
//        if (eventRequest.getVenueId().toString().isBlank()) {
//            throw new BadRequestException("The venue id cannot be blank or empty.");
//        }
//        if (eventRequest.getAttendeesId() == null) {
//            throw new BadRequestException("The attendee cannot be null.");
//        }
//        if (eventRequest.getAttendeesId().isEmpty()) {
//            throw new BadRequestException("The attendee cannot be blank or empty.");
//        }
//    }
}
