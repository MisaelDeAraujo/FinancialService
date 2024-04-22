package com.misael.financialservice.services;

import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.dtos.AuthorizationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean authorizer(User payer, double value){
        boolean authorized = false;

        AuthorizationDto authorizationDto = AuthorizationDto.builder()
                .payer(payer)
                .value(value)
                .build();

        ResponseEntity<Map> auth = restTemplate
                .postForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",authorizationDto,Map.class);

        if(auth.getStatusCode() == HttpStatus.OK && auth.getBody().containsValue("Autorizado")
        && payer.getUserType() == User.UserType.COMMON && value >=1 && payer.getWallet() >= value){
            System.out.println("Usuario autorizado");
            return true;
        }
        return authorized;
    }
}
