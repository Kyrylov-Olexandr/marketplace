
package com.intellias.kyrylov.practical_test.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Product product;
}

