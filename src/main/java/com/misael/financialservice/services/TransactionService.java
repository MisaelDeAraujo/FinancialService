package com.misael.financialservice.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.misael.financialservice.exceptions.UnauthorizedTransactionException;
import com.misael.financialservice.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misael.financialservice.entities.Transaction;
import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.dtos.TransactionDto;
import com.misael.financialservice.repositories.TransactionRepository;
import com.misael.financialservice.repositories.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private NotificationService notificationService;

    public TransactionDto saveTransaction(TransactionDto dto){
        Optional<User> findPayer = userRepository.findById(dto.payer());
        Optional<User> findPayee = userRepository.findById(dto.payee());

        if(findPayee.isPresent() && findPayer.isPresent()){
            User payer = findPayer.get();
            User payee = findPayee.get();

            if(authorizationService.authorizer(payer, dto.value())){
                payer.setWallet(payer.getWallet() - dto.value());
                payee.setWallet(payee.getWallet() + dto.value());
                
                userRepository.save(payer);
                userRepository.save(payee);
                
                Transaction transacion = Transaction.builder()
                .payer(payer)
                .payee(payee)
                .transferredValue(dto.value())
                .transferDate(LocalDateTime.now())
                .build();
                repository.save(transacion);

                notificationService.sendNotification(payer, "Transferência efetuada");
                notificationService.sendNotification(payee, "Transfêrencia recebida em sua conta");

                return dto;

            }else{
                throw new UnauthorizedTransactionException();
            }
        }else{
            throw new UserNotFoundException();
        }

    }

}