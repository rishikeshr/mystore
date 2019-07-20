package com.shopping.mystore.repository;

import com.shopping.mystore.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Query(value = "SELECT * FROM CUSTOMER_ORDER o WHERE o.STATUS = 'PENDING' and o.CUSTOMER_ID = :customer_id",
            nativeQuery = true
    )
    CustomerOrder getPendingCustomerOrder(@Param("customer_id") Long id);
}
