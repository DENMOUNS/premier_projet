package com.garageapp.garage.repository;

import org.springframework.data.repository.CrudRepository;

import com.garageapp.garage.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
