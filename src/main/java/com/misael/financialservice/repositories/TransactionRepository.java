package com.misael.financialservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misael.financialservice.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{

    
}