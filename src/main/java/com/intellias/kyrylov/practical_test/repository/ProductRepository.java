package com.intellias.kyrylov.practical_test.repository;

import com.intellias.kyrylov.practical_test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
