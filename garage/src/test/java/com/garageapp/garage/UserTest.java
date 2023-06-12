package com.garageapp.garage;

import com.garageapp.garage.model.User;
import com.garageapp.garage.repository.UserRepository;
import com.garageapp.validators.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    @Mock
    private UserRepository userRepository;

    // @Autowired
    // private UserRepository userRepository;

    //@Mock et InjectMocks sont utilises pour directement injecter UserValidator dans dans les champs correspondant de la classe UserTest

    @InjectMocks
    private UserValidator userValidator;

    public UserTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastname("Doe");
        user.setPhone("12345678");
        user.setEmail("john.doe@example.com");
        user.setCreated_at("01-01-2023");
        user.setUpdated_at("01-01-2023");

        boolean isValid = UserValidator.validateUser(user);

        assertTrue(isValid);
    }


    @Test
    void testInvalidUserMissingName() {
        User user = new User();
        user.setId(1L);
        user.setLastname("Doe");
        user.setPhone("12345678");
        user.setEmail("john.doe@example.com");
        user.setCreated_at("01-01-2023");
        user.setUpdated_at("01-01-2023");

        boolean isValid = UserValidator.validateUser(user);

        assertFalse(isValid);
    }

    @Test
    void testInvalidUserInvalidEmail() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastname("Doe");
        user.setPhone("12345678");
        user.setEmail("john.doe.example.com");
        user.setCreated_at("01-01-2023");
        user.setUpdated_at("01-01-2023");

        boolean isValid = UserValidator.validateUser(user);

        assertFalse(isValid);
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastname("Doe");
        user.setPhone("12345678");
        user.setEmail("john.doe@example.com");
        user.setCreated_at("01-01-2023");
        user.setUpdated_at("01-01-2023");

        when(userRepository.save(user)).thenReturn(user);

        userRepository.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        long userId = 1L;

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("John");
        existingUser.setLastname("Doe");
        existingUser.setPhone("12345678");
        existingUser.setEmail("john.doe@example.com");
        existingUser.setCreated_at("01-01-2023");
        existingUser.setUpdated_at("01-01-2023");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("Jane");
        updatedUser.setLastname("Smith");
        updatedUser.setPhone("87654321");
        updatedUser.setEmail("jane.smith@example.com");
        updatedUser.setCreated_at("01-01-2023");
        updatedUser.setUpdated_at("02-01-2023");

        doReturn(Optional.of(existingUser)).when(userRepository).findById(userId);
        doReturn(updatedUser).when(userRepository).save(updatedUser);

        userRepository.save(updatedUser);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void testDeleteUser() {
        
        long userId = 1L;

        userRepository.deleteById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
