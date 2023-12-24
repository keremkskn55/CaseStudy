package com.kerem.amadeusFlight.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kerem.amadeusFlight.Dto.DummyFlightDto;
import com.kerem.amadeusFlight.Model.Airport;
import com.kerem.amadeusFlight.Model.Flight;
import com.kerem.amadeusFlight.Repository.AirportRepository;
import com.kerem.amadeusFlight.Repository.FlightRepository;

@Service
public class ScheduledJobsService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 0 12 * * *") 
    public void fetchFlights() {
        String apiUrl = "http://localhost:8080/dummy/data";

        DummyFlightDto[] dummyFlights = restTemplate.getForObject(apiUrl, DummyFlightDto[].class);

        

        if (dummyFlights != null) {
            Flight[] finalFlights = new Flight[dummyFlights.length];
            for (int i = 0; i < dummyFlights.length; i++) {
                Flight tempFlight = new Flight();

                tempFlight.setDepartureAirport(dummyFlights[i].getDepartureAirport());
                tempFlight.setDepartureDateTime(dummyFlights[i].getDepartureDateTime());
                tempFlight.setArrivalAirport(dummyFlights[i].getArrivalAirport());
                tempFlight.setReturnDateTime(dummyFlights[i].getReturnDateTime());
                tempFlight.setPrice(dummyFlights[i].getPrice());

                finalFlights[i] = tempFlight;
            }

            flightRepository.saveAll(Arrays.asList(finalFlights));
        }
    }
}
