package org.kshrd.restapi02homeworkeventticketingsystem.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.kshrd.restapi02homeworkeventticketingsystem.exception.BadRequestException;
import org.kshrd.restapi02homeworkeventticketingsystem.exception.NotFoundException;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Venue;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.VenueRequest;
import org.kshrd.restapi02homeworkeventticketingsystem.repository.VenueRepository;
import org.kshrd.restapi02homeworkeventticketingsystem.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public Venue addNewVenue(VenueRequest venueRequest) {
//        validateVenueField(venueRequest);
        return venueRepository.addNewVenue(venueRequest);
    }

    @Override
    public Venue findVenueById(Long venueId) {
        Venue venue = venueRepository.findVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("The venue id " + venueId + " has not been founded.");
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest venueRequest) {
        findVenueById(venueId);
//        validateVenueField(venueRequest);
        return venueRepository.updateVenueById(venueId, venueRequest);
    }

    @Override
    public void deleteVenueById(Long venueId) {
        findVenueById(venueId);
        venueRepository.deleteVenueById(venueId);
    }

    @Override
    public List<Venue> findAllVenues(Long offset, Long limit) {
//        if(offset <= 0){
//            throw new BadRequestException("Offset must be more than zero");
//        }
//        if (limit < 0){
//            throw new BadRequestException("Limit must be more than or equal zero");
//        }
        offset = (offset - 1) * limit;
        return venueRepository.findAllVenues(offset, limit);
    }

//    void validateVenueField(VenueRequest venueRequest) {
//        if (venueRequest.getVenueName() == null) {
//            throw new BadRequestException("The venue name cannot be null.");
//        }
//        if (venueRequest.getVenueName().isBlank()) {
//            throw new BadRequestException("The venue name cannot be blank or empty.");
//        }
//        if (venueRequest.getLocation() == null) {
//            throw new BadRequestException("The location cannot be null.");
//        }
//        if (venueRequest.getLocation().isBlank()) {
//            throw new BadRequestException("The location cannot be blank or empty.");
//        }
//    }
}
