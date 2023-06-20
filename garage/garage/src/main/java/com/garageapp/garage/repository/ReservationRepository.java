package com.garageapp.garage.repository;

import org.springframework.data.repository.CrudRepository;

import com.garageapp.garage.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
}
