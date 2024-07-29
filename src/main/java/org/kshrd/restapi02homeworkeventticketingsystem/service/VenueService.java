package org.kshrd.restapi02homeworkeventticketingsystem.service;

import org.kshrd.restapi02homeworkeventticketingsystem.model.Venue;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.VenueRequest;

import java.util.List;

public interface VenueService {
    Venue addNewVenue(VenueRequest venueRequest);

    Venue findVenueById(Long venueId);

    Venue updateVenueById(Long venueId, VenueRequest venueRequest);

    void deleteVenueById(Long venueId);

    List<Venue> findAllVenues(Long offset, Long limit);
}
