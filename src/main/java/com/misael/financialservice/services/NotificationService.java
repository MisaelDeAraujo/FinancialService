package com.misael.financialservice.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.dtos.NotificationDto;

/**
 * NotificationService
 */
@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;


    public void sendNotification(User user, String message){
        String email = user.getEmail();

        NotificationDto notificationDto = NotificationDto.builder()
        .email(email)
        .message(message)
        .build();

        ResponseEntity<Map> externalNotification = restTemplate
        .postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6",notificationDto, Map.class);

        if(!externalNotification.getBody().containsValue(true)){
            throw new RuntimeException();
        }

        
        
    }
    
}