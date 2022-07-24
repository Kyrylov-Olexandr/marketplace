package com.intellias.kyrylov.practical_test.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal amountOfMoney;

}
