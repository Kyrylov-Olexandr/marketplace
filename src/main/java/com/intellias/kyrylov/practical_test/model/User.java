package com.intellias.kyrylov.practical_test.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal amountOfMoney;

    @OneToMany(mappedBy = "user", orphanRemoval = true,  cascade = CascadeType.ALL)
    private List<Purchase> purchases;


}

