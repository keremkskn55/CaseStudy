package com.kerem.amadeusFlight.Repository;

import com.kerem.amadeusFlight.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}