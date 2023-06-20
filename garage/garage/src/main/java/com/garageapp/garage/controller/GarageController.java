package com.garageapp.garage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.garageapp.garage.model.Car;
import com.garageapp.garage.service.GarageService;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class GarageController {

    @Autowired
    private GarageService garageService;

    @GetMapping
    public List<Car> getCars() {
        return garageService.getCars();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable long id) {
        return garageService.getCar(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long id) {
        garageService.deleteCar(id);
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        garageService.addCar(car);
    }

    @PutMapping("/{id}")
    public void updateCar(@RequestBody Car car, @PathVariable long id) {
        car.setId(id);
        garageService.updateCar(car, id);
    }
}
