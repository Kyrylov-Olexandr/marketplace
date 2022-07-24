package com.intellias.kyrylov.practical_test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intellias.kyrylov.practical_test.model.Purchase;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private BigDecimal price;

}
