package com.misael.financialservice;

import com.misael.financialservice.entities.User;
import com.misael.financialservice.entities.dtos.TransactionDto;
import com.misael.financialservice.repositories.TransactionRepository;
import com.misael.financialservice.repositories.UserRepository;
import com.misael.financialservice.services.AuthorizationService;
import com.misael.financialservice.services.NotificationService;
import com.misael.financialservice.services.TransactionService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class TransactionServiceTests {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorizationService authorizationService;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldSaveTransaction(){
        User payer = User.builder()
                .id(1)
                .completeName("João")
                .email("joao@email.com")
                .cpf("86357672092")
                .wallet(1000.00)
                .userType(User.UserType.COMMON).build();
        User payee = User.builder()
                .id(2)
                .completeName("Ana")
                .email("ana@email.com")
                .cpf("21496857011")
                .wallet(1000.00)
                .userType(User.UserType.COMMON).build();


        if(userRepository.findById(1).isPresent() && userRepository.findById(2).isPresent()){
            when(authorizationService.authorizer(payer,any())).thenReturn(true);
            TransactionDto transactionDto = TransactionDto.builder()
                    .value(500.00)
                    .payer(1)
                    .payee(2)
                    .build();

            transactionService.saveTransaction(transactionDto);

            verify(transactionRepository,times(1)).save(any());

            payer.setWallet(500.00);
            verify(userRepository,times(1)).save(payer);
            payee.setWallet(1500.00);
            verify(userRepository,times(1)).save(payee);

            verify(notificationService,times(1))
                    .sendNotification(payer,"Transferência efetuada");

            verify(notificationService,times(1))
                    .sendNotification(payee,"Transfêrencia recebida em sua conta");
        }

    }
}
