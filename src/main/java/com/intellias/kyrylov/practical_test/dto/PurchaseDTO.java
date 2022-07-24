package com.intellias.kyrylov.practical_test.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PurchaseDTO {

    private Long id;

    @NotEmpty(message = "Name may not be empty")
    private Long userId;

    @NotEmpty(message = "Name may not be empty")
    private Long productId;
}
