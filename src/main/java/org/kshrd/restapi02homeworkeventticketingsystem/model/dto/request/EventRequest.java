package org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
    @NotNull
    @NotBlank
    private String eventName;

    @NotNull
    private LocalDateTime eventDate;

    @NotNull
    private Long venueId;

    @NotNull
    @NotEmpty
    private List<Long> attendeesId;
}
