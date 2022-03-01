package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * @author Harsh Hariramani
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository mockedUserRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUpUser(){
        user = new User("John Doe", "johndoe@xyz.com", "password123");
    }

    @Test
    public void testRegisterUser() {
        when(mockedUserRepository.save(any(User.class))).thenReturn(user);
        User actualUser = userService.registerUser("John Doe", "johndoe@xyz.com", "password123");
        Assertions.assertEquals(user, actualUser);
    }

    @Test
    public void testInputParametersForRegisterUser(){
        IllegalArgumentException exceptionForName1 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser(null,"dummy","dummy"));
        Assertions.assertEquals("name cannot be null or empty or blank",exceptionForName1.getMessage());
        IllegalArgumentException exceptionForName2 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("","dummy","dummy"));
        Assertions.assertEquals("name cannot be null or empty or blank",exceptionForName2.getMessage());
        IllegalArgumentException exceptionForName3 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser(" ","dummy","dummy"));
        Assertions.assertEquals("name cannot be null or empty or blank",exceptionForName3.getMessage());

        IllegalArgumentException exceptionForEmail1 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy",null,"dummy"));
        Assertions.assertEquals("email cannot be null or empty or blank",exceptionForEmail1.getMessage());
        IllegalArgumentException exceptionForEmail2 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy","","dummy"));
        Assertions.assertEquals("email cannot be null or empty or blank",exceptionForEmail2.getMessage());
        IllegalArgumentException exceptionForEmail3 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy"," ","dummy"));
        Assertions.assertEquals("email cannot be null or empty or blank",exceptionForEmail3.getMessage());

        IllegalArgumentException exceptionForPassword1 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy","dummy",null));
        Assertions.assertEquals("password cannot be null or empty or blank",exceptionForPassword1.getMessage());
        IllegalArgumentException exceptionForPassword2 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy","dummy",""));
        Assertions.assertEquals("password cannot be null or empty or blank",exceptionForPassword2.getMessage());
        IllegalArgumentException exceptionForPassword3 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy","dummy"," "));
        Assertions.assertEquals("password cannot be null or empty or blank",exceptionForPassword3.getMessage());

        IllegalArgumentException exceptionForEmail4 = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("dummy","dummy","dummy"));
        Assertions.assertEquals("given email is not valid",exceptionForEmail4.getMessage());

    }











}
