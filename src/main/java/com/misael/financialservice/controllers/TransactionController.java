package com.misael.financialservice.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.financialservice.entities.dtos.TransactionDto;
import com.misael.financialservice.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/transactions",produces ={"application/json"})
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Operation(summary = "Realiza transferência de valor da carteira ao inserir valor, id do pagador, e depois id do que vai receber.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos, verificar se possui valor disponível para transação" +
                    "ou se Usuário está cadastrado."),
    })
    @PostMapping
    public ResponseEntity<TransactionDto> saveTransaction(TransactionDto dto){
        TransactionDto dto2 = service.saveTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto2);
    }
}
