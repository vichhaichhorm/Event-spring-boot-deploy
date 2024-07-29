package org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueRequest {
    @NotNull
    @NotBlank
    private String venueName;

    @NotNull
    @NotBlank
    private String location;
}
