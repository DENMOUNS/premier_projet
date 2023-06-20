package com.garageapp.garage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.garageapp.garage.model.User;
import com.garageapp.garage.repository.UserRepository;
import com.garageapp.garage.validators.UserValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user->{
            users.add(user);
        });
        return users;
    }
    public User getUser(long id){
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        boolean isValidUser = UserValidator.validateUser(user);

        if (isValidUser) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User updateUser(User user, long id) {
        boolean isValidUser = UserValidator.validateUser(user);

        if (isValidUser) {
            Optional<User> optionalExistingUser = userRepository.findById(id);
            if (optionalExistingUser.isPresent()) {
                User existingUser = optionalExistingUser.get();

                existingUser.setName(user.getName());
                existingUser.setLastname(user.getLastname());
                existingUser.setPhone(user.getPhone());
                existingUser.setEmail(user.getEmail());
                existingUser.setCreated_at(user.getCreatedAt());
                existingUser.setUpdated_at(user.getUpdatedAt    ());

                userRepository.save(existingUser);

                return existingUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public boolean deleteUser(long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}

