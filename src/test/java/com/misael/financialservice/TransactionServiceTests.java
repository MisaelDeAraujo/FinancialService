package com.misael.financialservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * TransactionServiceTests
 */
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class TransactionServiceTests {

    @Test
    public void shouldSaveTransaction(){
    }
}
