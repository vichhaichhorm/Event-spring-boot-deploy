package org.kshrd.restapi02homeworkeventticketingsystem.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.kshrd.restapi02homeworkeventticketingsystem.exception.BadRequestException;
import org.kshrd.restapi02homeworkeventticketingsystem.exception.NotFoundException;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Attendee;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.AttendeeRequest;
import org.kshrd.restapi02homeworkeventticketingsystem.repository.AttendeeRepository;
import org.kshrd.restapi02homeworkeventticketingsystem.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public Attendee addNewAttendee(AttendeeRequest attendeeRequest) {
//        validateAttendeeField(attendeeRequest);
        return attendeeRepository.addNewAttendee(attendeeRequest);
    }

    @Override
    public Attendee findAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
        if (attendee == null){
            throw new NotFoundException("The attendee id " + attendeeId + " has not been founded.");
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest attendeeRequest) {
        findAttendeeById(attendeeId);
//        validateAttendeeField(attendeeRequest);
        return attendeeRepository.updateAttendeeById(attendeeId, attendeeRequest);
    }

    @Override
    public void deleteAttendeeById(Long attendeeId) {
        findAttendeeById(attendeeId);
        attendeeRepository.deleteAttendeeById(attendeeId);
    }

    @Override
    public List<Attendee> findAllAttendees(Long offset, Long limit) {
//        if(offset <= 0){
//            throw new BadRequestException("Offset must be more than zero");
//        }
//        if (limit < 0){
//            throw new BadRequestException("Limit must be more than or equal zero");
//        }
        offset = (offset - 1) * limit;
        return attendeeRepository.findAllAttendees(offset, limit);
    }

//    void validateAttendeeField(AttendeeRequest attendeeRequest){
//        if (attendeeRequest.getAttendeeName() == null) {
//            throw new BadRequestException("The attendee name cannot be null.");
//        }
//        if (attendeeRequest.getAttendeeName().isBlank()) {
//            throw new BadRequestException("The attendee name cannot be blank or empty.");
//        }
//        if (attendeeRequest.getEmail() == null) {
//            throw new BadRequestException("The email cannot be null.");
//        }
//        if (attendeeRequest.getEmail().isBlank()) {
//            throw new BadRequestException("The email cannot be blank or empty.");
//        }
//    }
}
