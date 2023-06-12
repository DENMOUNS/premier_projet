package com.garageapp.garage.model;

import com.garageapp.color.Color;

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

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Color color;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String model;
    private String brand;
    private int year;

    private boolean isReserved;
    private Long reservedBy;
    private Date reservationDate;

    public Car(String model, String brand, int year) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.isReserved = false;
        this.reservedBy = null;
        this.reservationDate = null;
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
        // Autres validations si nécessaire
    }


    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }


    public void setReserved(boolean reserved) {
        if (!reserved && !isReserved) {
            throw new IllegalStateException("La voiture n'est pas réservée.");
        }
        this.isReserved = reserved;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public boolean isReserved() {
        return isReserved;
    }

    public void setReservedBy(long reservedBy) {
        if (isReserved) {
            throw new IllegalStateException("La voiture est déjà réservée.");
        }
        this.reservedBy = reservedBy;
        this.isReserved = true;
    }

    public long getReservedBy() {
        return reservedBy;
    }


}