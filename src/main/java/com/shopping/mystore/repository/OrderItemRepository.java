package com.shopping.mystore.repository;

import com.shopping.mystore.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the OrderItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "SELECT * FROM ORDER_ITEM o WHERE o.ORDER_ID = :order_id",
            nativeQuery = true
    )
    List<OrderItem> findByCustomerOrderId(@Param("order_id") Long id);

    void deleteByOrderId(Long order_id);
}
