package com.garageapp.garage.repository;

import org.springframework.data.repository.CrudRepository;

import com.garageapp.garage.model.Car;

public interface GarageRepository extends CrudRepository<Car, Long>{
    
}
