package org.kshrd.restapi02homeworkeventticketingsystem.service;

import org.kshrd.restapi02homeworkeventticketingsystem.model.Attendee;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    Attendee addNewAttendee(AttendeeRequest attendeeRequest);

    Attendee findAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest attendeeRequest);

    void deleteAttendeeById(Long attendeeId);

    List<Attendee> findAllAttendees(Long offset, Long limit);
}
