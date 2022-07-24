package com.intellias.kyrylov.practical_test.repository;

import com.intellias.kyrylov.practical_test.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
