package com.garageapp.garage.model;

import com.garageapp.enums.Color;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Year;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Color color;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    private String model;
    private String brand;
    private int year;

    private boolean isReserved;
    private Long reservedBy;
    private Date reservationDate;

    public Car(String model, String brand, int year, boolean isReserved, Long reservedBy, Date reservationDate) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.isReserved = false;
        this.reservedBy = null;
        this.reservationDate = null;
    }

    public Car(String model2, String brand2, int year2, boolean isReserved2, Object reservedBy2,
			Object reservationDate2) {
	}

	public void validate() {
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Le modèle de la voiture est requis.");
        }
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("La marque de la voiture est requise.");
        }
        int currentYear = Year.now().getValue();
        if (year < 1900 || year > currentYear) {
            throw new IllegalArgumentException("L'année de la voiture est invalide.");
        }
        // Autres validations si nécessaire(tu vas me montrer une autre maniere pardon)
    }

    public void setReserved(boolean reserved) {
        if (!reserved && !isReserved) {
            throw new IllegalStateException("La voiture n'est pas réservée.");
        }
        this.isReserved = reserved;
    }

    public void setReservedBy(long reservedBy) {
        if (isReserved) {
            throw new IllegalStateException("La voiture est déjà réservée.");
        }
        this.reservedBy = reservedBy;
        this.isReserved = true;
    }
}
