package com.kerem.amadeusFlight.Repository;

import com.kerem.amadeusFlight.Model.Airport;
import com.kerem.amadeusFlight.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

        @Query("SELECT f FROM Flight f " + 
                "WHERE (:arrivalId IS NULL OR f.arrivalAirport.id = :arrivalId) " + 
                "AND (:departureId IS NULL OR f.departureAirport.id = :departureId) " +
                "AND (:departureDateTime IS NULL OR f.departureDateTime = :departureDateTime) " + 
                "AND (:returnDateTime IS NULL OR f.returnDateTime = :returnDateTime) "
                )
        List<Flight> searchFlightsByArrivalAirportId(
                @Param("arrivalId") Integer arrivalId,
                @Param("departureId") Integer departureId,
                @Param("departureDateTime") LocalDateTime departureDateTime,
                @Param("returnDateTime") LocalDateTime returnDateTime
                );
}
