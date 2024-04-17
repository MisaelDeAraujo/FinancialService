package com.misael.financialservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String completeName;
    @Column
    private String email;
    @Column
    private String cpf;
    @Column
    private String cnpj;
    @Column
    private Double wallet;

    @Column
    private UserType userType;

    public enum UserType{
        COMMON("COMMON"),
        MERCHANT("MERCHANT");

        UserType(String userType){
        }
    }
}
