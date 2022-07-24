package com.intellias.kyrylov.practical_test.repository;

import com.intellias.kyrylov.practical_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true,
            value = "select * from users u " +
                    "left join purchase p on u.id = p.user_id " +
                    "where p.product_id = :product_id " +
                    "group by u.id , p.id")
    List<User> findByPurchasedProductId(@Param("product_id") Long productId);
}
