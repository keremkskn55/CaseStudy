package com.kerem.amadeusFlight.Controller;

import com.kerem.amadeusFlight.Dto.SearchResponseDto;
import com.kerem.amadeusFlight.Model.Airport;
import com.kerem.amadeusFlight.Model.Flight;
import com.kerem.amadeusFlight.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/searchFlights", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SearchResponseDto> searchFlights(
            @RequestParam(required = false) Integer arrivalAirportId,
            @RequestParam(required = false) Integer departureAirportId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime
            ) {

        return searchService.searchFlights(arrivalAirportId, departureAirportId, departureDateTime, returnDateTime);
    }
}
