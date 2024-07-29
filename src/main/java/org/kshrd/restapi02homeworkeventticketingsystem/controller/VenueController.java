package org.kshrd.restapi02homeworkeventticketingsystem.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Venue;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.VenueRequest;
import org.kshrd.restapi02homeworkeventticketingsystem.model.response.APIResponse;
import org.kshrd.restapi02homeworkeventticketingsystem.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<APIResponse<Venue>> addNewVenue(@RequestBody @Valid VenueRequest venueRequest){
        APIResponse<Venue> response = APIResponse
                .<Venue>builder()
                .message("The venue has been successfully added.")
                .payload(venueService.addNewVenue(venueRequest))
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Venue>> findVenueById(@PathVariable("id") @Positive Long venueId){
        APIResponse<Venue> response = APIResponse
                .<Venue>builder()
                .message("The venue has been successfully founded.")
                .payload(venueService.findVenueById(venueId))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Venue>> updateVenueById(@PathVariable("id") @Positive Long venueId, @RequestBody @Valid VenueRequest venueRequest){
        APIResponse<Venue> response = APIResponse
                .<Venue>builder()
                .message("The venue has been successfully updated.")
                .payload(venueService.updateVenueById(venueId, venueRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Venue>> deleteVenueById(@PathVariable("id") @Positive Long venueId){
        venueService.deleteVenueById(venueId);
        APIResponse<Venue> response = APIResponse
                .<Venue>builder()
                .message("The venue has been successfully deleted.")
                .payload(null)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Venue>>> findAllVenues(@RequestParam(defaultValue = "1") @Positive Long offset, @RequestParam(defaultValue = "3") @Positive Long limit){
        APIResponse<List<Venue>> response = APIResponse
                .<List<Venue>>builder()
                .message("All venues have been successfully fetched.")
                .payload(venueService.findAllVenues(offset, limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
