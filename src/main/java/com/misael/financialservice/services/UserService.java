package com.misael.financialservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.User.UserType;
import com.misael.financialservice.entities.dtos.UserRegistrationRequestDto;
import com.misael.financialservice.entities.dtos.UserRegistrationResponseDto;
import com.misael.financialservice.repositories.UserRepository;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserRegistrationResponseDto saveUser(UserRegistrationRequestDto dto){
        if(dto.document().length() == 14){
            User merchantUser = User.builder()
            .completeName(dto.completeName())
            .email(dto.email())
            .cnpj(dto.document())
            .wallet(dto.wallet())
            .userType(UserType.MERCHANT)
            .build();

            repository.save(merchantUser);

            UserRegistrationResponseDto merchantUserDtoResponse = UserRegistrationResponseDto.builder()
            .id(merchantUser.getId())
            .completeName(merchantUser.getCompleteName())
            .document(merchantUser.getCnpj())
            .wallet(merchantUser.getWallet())
            .build();

            return merchantUserDtoResponse;

        }else if(dto.document().length() == 11){
            User commonUser = User.builder()
            .completeName(dto.completeName())
            .email(dto.email())
            .cpf(dto.document())
            .wallet(dto.wallet())
            .userType(UserType.COMMON)
            .build();

            repository.save(commonUser);

            UserRegistrationResponseDto commonUserDtoResponse = UserRegistrationResponseDto.builder()
            .id(commonUser.getId())
            .completeName(commonUser.getCompleteName())
            .document(commonUser.getCpf())
            .wallet(commonUser.getWallet())
            .build();

            return commonUserDtoResponse;
        }else{
            throw new RuntimeException();
        }
    }

    public List<UserRegistrationResponseDto> listAllUsers(){
        List<User> findUsers = repository.findAll();
        List<UserRegistrationResponseDto> dtos = new ArrayList<>();

        for(User user: findUsers){
            if(user.getUserType() == UserType.MERCHANT){
                UserRegistrationResponseDto dto = UserRegistrationResponseDto.builder()
                .id(user.getId())
                .completeName(user.getCompleteName())
                .document(user.getCnpj())
                .wallet(user.getWallet())
                .build();

                dtos.add(dto);

            }else if(user.getUserType() == UserType.COMMON){
                UserRegistrationResponseDto dto = UserRegistrationResponseDto.builder()
                .id(user.getId())
                .completeName(user.getCompleteName())
                .document(user.getCpf())
                .wallet(user.getWallet())
                .build();

                dtos.add(dto);
            }
        }

        return dtos;
    
    }

}