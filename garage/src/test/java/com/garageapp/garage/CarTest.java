package com.garageapp.garage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import com.garageapp.garage.model.Car;
import com.garageapp.garage.repository.GarageRepository;
import com.garageapp.garage.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {

    @Mock
    private GarageRepository garageRepository;

    @Mock
    private UserRepository userRepository;

    private Car car;

    @BeforeEach
    public void setUp() {
        car.validate(); // Valide la voiture avant de l'utiliser
        car = new Car("Model", "Brand", 2023);
    }

    @Test
    public void testGetModel() {
        Car car = new Car("Aventador", "Lamborghini", 2022);
        car.validate(); // Valide la voiture avant de l'utiliser
        assertThat(car.getModel()).isEqualTo("Aventador");
    }

    @Test
    public void testGetBrand() {
        Car car = new Car("Aventador", "Lamborghini", 2022);
        assertThat(car.getBrand()).isEqualTo("Lamborghini");
    }

    @Test
    public void testGetYear() {
        Car car = new Car("Aventador", "Lamborghini", 2022);
        assertThat(car.getYear()).isEqualTo(2022);
    }

    @Test
    public void testSetModel() {
        Car car = new Car("Aventador", "Lamborghini", 2022);
        car.setModel("Huracan");
        assertThat(car.getModel()).isEqualTo("Huracan");
    }


    @Test
    public void testSetBrand() {
        Car car = new Car("Aventador", "Lamborghini", 2022);
        car.setBrand("Ferrari");
        assertThat(car.getBrand()).isEqualTo("Ferrari");
    }

    @Test
    public void testSetYear() {
        Car car = new Car("Aventador", "Lamborghini", 2022);
        car.setYear(2023);
        assertThat(car.getYear()).isEqualTo(2023);
    }

    @Test
    public void testValidation() {
        Assertions.assertEquals("Model", car.getModel());
        Assertions.assertEquals("Brand", car.getBrand());
        Assertions.assertEquals(2023, car.getYear());
        car.setYear(0);
        assertThrows(IllegalArgumentException.class, () -> car.validate());
    }

    

}
