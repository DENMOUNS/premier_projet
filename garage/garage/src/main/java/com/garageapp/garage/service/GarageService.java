package com.garageapp.garage.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garageapp.garage.model.Car;
import com.garageapp.garage.model.Reservation;
import com.garageapp.garage.model.User;
import com.garageapp.garage.repository.GarageRepository;
import com.garageapp.garage.repository.ReservationRepository;
import com.garageapp.garage.repository.UserRepository;

@Service
public class GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        garageRepository.findAll().forEach(car -> {
            cars.add(car);
        });
        return cars;
    }

    public Car getCar(long id) {
        return garageRepository.findById(id).orElse(null);
    }

    public void deleteCar(long id) {
        garageRepository.deleteById(id);
    }

    public void addCar(Car car) {
        garageRepository.save(car);
    }

    public void updateCar(Car car, long id) {
        garageRepository.save(car);
    }

    public String reserveCar(long carId, long userId) {
        Optional<Car> optionalCar = garageRepository.findById(carId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car car = optionalCar.get();
            User user = optionalUser.get();

            if (car.getReservedBy() != 0) {
                return "deja reserve";
            }

            car.setReservedBy(user.getId());
            garageRepository.save(car);

            Reservation reservation = new Reservation(car, user);
            reservationRepository.save(reservation);

            return "success";
        }

        return "notFound";
    }

    public String cancelReservation(long carId) {
        Optional<Car> optionalCar = garageRepository.findById(carId);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();

            if (car.getReservedBy() == 0L) {
                return "Voiture non reservee";
            }

            car.setReservedBy(0L);
            garageRepository.save(car);

            return "Reservation annullee avec succes";
        }

        return "Voiture non trouvee";
    }

}
