package com.kerem.amadeusFlight.Dto;

import java.time.LocalDateTime;

import com.kerem.amadeusFlight.Model.Airport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseDto {  
    private Airport departureAirport;

    private Airport arrivalAirport;

    private LocalDateTime flightDateTime;
}
