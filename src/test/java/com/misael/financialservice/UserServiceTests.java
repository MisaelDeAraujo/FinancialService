package com.misael.financialservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.dtos.UserRegistrationRequestDto;
import com.misael.financialservice.repositories.UserRepository;
import com.misael.financialservice.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser(){
        UserRegistrationRequestDto dto = UserRegistrationRequestDto.builder()
        .completeName("user")
        .email("user@email.com")
        .document("06897525049")
        .wallet(1000.00)
        .build();
        userService.saveUser(dto);
        Optional<User> find = userRepository.findById(1);
        assertTrue(find.isPresent());
    }
    

}