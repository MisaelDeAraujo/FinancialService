package com.misael.financialservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.financialservice.entities.dtos.UserRegistrationRequestDto;
import com.misael.financialservice.entities.dtos.UserRegistrationResponseDto;
import com.misael.financialservice.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * UserController
 */
@RestController
@RequestMapping(value = "/users",produces ={"application/json"})
@Tag(name = "Financial Service")
public class UserController {

    @Autowired
    private UserService service;
    @Operation(summary = "Realiza cadastro de usuarios")
    @PostMapping
    public ResponseEntity<UserRegistrationResponseDto> saveUser(UserRegistrationRequestDto dto){
        UserRegistrationResponseDto dto2 = service.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto2);
    }
    @Operation(summary = "Realiza listagem de todos os usuarios registrados")
    @GetMapping
    public ResponseEntity<List<UserRegistrationResponseDto>> listAllUsers(){
        return ResponseEntity.ok().body(service.listAllUsers());
    }


}