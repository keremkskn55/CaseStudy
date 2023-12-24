package com.kerem.amadeusFlight.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kerem.amadeusFlight.Dto.DummyFlightDto;
import com.kerem.amadeusFlight.Model.Airport;
import com.kerem.amadeusFlight.Model.Flight;
import com.kerem.amadeusFlight.Service.AirportService;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/data")
    List<DummyFlightDto> getDummyData() {
        List<DummyFlightDto> dummyFlights = new ArrayList<DummyFlightDto>();
        List<Airport> allAirports = airportService.getAllAirports();
        Random rd = new Random();

        for (int i = 0; i < 10; i++) {
            DummyFlightDto dummyFlightDto = new DummyFlightDto();

            Collections.shuffle(allAirports);

            Airport departureAirport = allAirports.get(0);
            Airport arrivalAirport = allAirports.get(1);

            dummyFlightDto.setDepartureAirport(departureAirport);
            dummyFlightDto.setArrivalAirport(arrivalAirport);

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime departureDateTime = now.plusDays(rd.nextInt(5)).plusHours(rd.nextInt(5)).plusMinutes(rd.nextInt(5)).withSecond(0).withNano(0);
            
            dummyFlightDto.setDepartureDateTime(departureDateTime);

            int forArrivalProbability = rd.nextInt(2);

            if (forArrivalProbability == 1) {
                LocalDateTime returnDateTime = departureDateTime.plusDays(rd.nextInt(5)).plusHours(rd.nextInt(5)).plusMinutes(rd.nextInt(5)).withSecond(0).withNano(0);
                dummyFlightDto.setReturnDateTime(returnDateTime);
            }

            dummyFlightDto.setPrice(rd.nextInt(500, 3000));
            dummyFlights.add(dummyFlightDto);
        }

        return dummyFlights;
    }
}
