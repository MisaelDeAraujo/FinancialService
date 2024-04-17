package com.misael.financialservice.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.misael.financialservice.entities.Transaction;
import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.User.UserType;
import com.misael.financialservice.entities.dtos.TransactionDto;
import com.misael.financialservice.repositories.TransactionRepository;
import com.misael.financialservice.repositories.UserRepository;

/**
 * TransactionService
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public TransactionDto saveTransaction(TransactionDto dto){
        Optional<User> findPayer = userRepository.findById(dto.payer());
        Optional<User> findPayee = userRepository.findById(dto.payee());

        if(findPayee.isPresent() && findPayer.isPresent()){
            User payer = findPayer.get();
            User payee = findPayee.get();
            ResponseEntity<Map> externalAuthorizer = restTemplate
            .getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
            if(payer.getWallet() >= dto.value() && payer.getUserType() == UserType.COMMON && 
            externalAuthorizer.getBody().containsValue("Autorizado") && payee.getEmail() != null
            && !payee.getEmail().isBlank()){
                payer.setWallet(payer.getWallet() - dto.value());
                payee.setWallet(payee.getWallet() + dto.value());
                
                userRepository.save(payer);
                userRepository.save(payee);
                
                Transaction transacion = Transaction.builder()
                .payer(payer)
                .payee(payee)
                .transferredValue(dto.value())
                .date(LocalDateTime.now())
                .build();

                repository.save(transacion);
                return dto;

            }else{
                throw new RuntimeException();
            }
        }else{
            throw new RuntimeException();
        }

    }

}