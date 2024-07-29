package org.kshrd.restapi02homeworkeventticketingsystem.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.restapi02homeworkeventticketingsystem.model.Venue;
import org.kshrd.restapi02homeworkeventticketingsystem.model.dto.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Select("INSERT INTO venues(venue_name, location) VALUES (#{venue.venueName}, #{venue.location}) RETURNING *")
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    Venue addNewVenue(@Param("venue") VenueRequest venueRequest);

    @Select("SELECT * FROM venues WHERE venue_id = #{venueId}")
    @ResultMap("venueMapper")
    Venue findVenueById(Long venueId);

    @Select("UPDATE venues SET venue_name = #{venue.venueName}, location = #{venue.location} WHERE venue_id = #{venueId} RETURNING *")
    @ResultMap("venueMapper")
    Venue updateVenueById(Long venueId, @Param("venue") VenueRequest venueRequest);

    @Delete("DELETE FROM venues WHERE venue_id = #{venueId}")
    void deleteVenueById(Long venueId);

    @Select("SELECT * FROM venues OFFSET #{offset} LIMIT #{limit}")
    @ResultMap("venueMapper")
    List<Venue> findAllVenues(Long offset, Long limit);
}
