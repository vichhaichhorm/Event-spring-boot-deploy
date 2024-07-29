package org.kshrd.restapi02homeworkeventticketingsystem.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Attendee;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.AttendeeRequest;
import org.kshrd.restapi02homeworkeventticketingsystem.model.response.APIResponse;
import org.kshrd.restapi02homeworkeventticketingsystem.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @PostMapping
    public ResponseEntity<APIResponse<Attendee>> addNewAttendee(@RequestBody @Valid AttendeeRequest attendeeRequest){
        APIResponse<Attendee> response = APIResponse
                .<Attendee>builder()
                .message("The attendee has been successfully added.")
                .payload(attendeeService.addNewAttendee(attendeeRequest))
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> findAttendeeById(@PathVariable("id") @Positive Long attendeeId){
        APIResponse<Attendee> response = APIResponse
                .<Attendee>builder()
                .message("The attendee has been successfully founded.")
                .payload(attendeeService.findAttendeeById(attendeeId))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> updateAttendeeById(@PathVariable("id") @Positive Long attendeeId, @RequestBody @Valid AttendeeRequest attendeeRequest){
        APIResponse<Attendee> response = APIResponse
                .<Attendee>builder()
                .message("The attendee has been successfully updated.")
                .payload(attendeeService.updateAttendeeById(attendeeId, attendeeRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> deleteAttendeeById(@PathVariable("id") @Positive Long attendeeId){
        attendeeService.deleteAttendeeById(attendeeId);
        APIResponse<Attendee> response = APIResponse
                .<Attendee>builder()
                .message("The attendee has been successfully deleted.")
                .payload(null)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Attendee>>> findAllAttendees(@RequestParam(defaultValue = "1") @Positive Long offset, @RequestParam(defaultValue = "3") @Positive Long limit){
        APIResponse<List<Attendee>> response = APIResponse
                .<List<Attendee>>builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendeeService.findAllAttendees(offset, limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
