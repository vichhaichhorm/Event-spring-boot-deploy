package org.kshrd.restapi02homeworkeventticketingsystem.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Event;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.EventRequest;
import org.kshrd.restapi02homeworkeventticketingsystem.model.response.APIResponse;
import org.kshrd.restapi02homeworkeventticketingsystem.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<APIResponse<Event>> addNewEvent(@RequestBody @Valid EventRequest eventRequest){
        APIResponse<Event> response = APIResponse
                .<Event>builder()
                .message("The event has been successfully added.")
                .payload(eventService.addNewEvent(eventRequest))
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> findEventById(@PathVariable("id") @Positive Long eventId){
        APIResponse<Event> response = APIResponse
                .<Event>builder()
                .message("The event has been successfully founded.")
                .payload(eventService.findEventById(eventId))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> updateEventById(@PathVariable("id") @Positive Long eventId, @RequestBody @Valid EventRequest eventRequest){
        APIResponse<Event> response = APIResponse
                .<Event>builder()
                .message("The event has been successfully updated.")
                .payload(eventService.updateEventById(eventId, eventRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> deleteEventById(@PathVariable("id") @Positive Long eventId){
        eventService.deleteEventById(eventId);
        APIResponse<Event> response = APIResponse
                .<Event>builder()
                .message("The event has been successfully deleted.")
                .payload(null)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Event>>> findAllEvents(@RequestParam(defaultValue = "1") @Positive Long offset, @RequestParam(defaultValue = "3") @Positive Long limit){
        APIResponse<List<Event>> response = APIResponse
                .<List<Event>>builder()
                .message("All events have been successfully fetched.")
                .payload(eventService.findAllEvents(offset, limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
