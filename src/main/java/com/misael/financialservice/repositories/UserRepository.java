package com.misael.financialservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misael.financialservice.entities.User;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    
}