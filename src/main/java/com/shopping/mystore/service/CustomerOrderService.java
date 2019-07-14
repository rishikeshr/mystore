package com.shopping.mystore.service;

import com.shopping.mystore.domain.CustomerOrder;
import com.shopping.mystore.repository.CustomerOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerOrder}.
 */
@Service
@Transactional
public class CustomerOrderService {

    private final Logger log = LoggerFactory.getLogger(CustomerOrderService.class);

    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    /**
     * Save a customerOrder.
     *
     * @param customerOrder the entity to save.
     * @return the persisted entity.
     */
    public CustomerOrder save(CustomerOrder customerOrder) {
        log.debug("Request to save CustomerOrder : {}", customerOrder);
        return customerOrderRepository.save(customerOrder);
    }

    /**
     * Get all the customerOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerOrder> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerOrders");
        return customerOrderRepository.findAll(pageable);
    }


    /**
     * Get one customerOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CustomerOrder> findOne(Long id) {
        log.debug("Request to get CustomerOrder : {}", id);
        return customerOrderRepository.findById(id);
    }

    /**
     * Delete the customerOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CustomerOrder : {}", id);
        customerOrderRepository.deleteById(id);
    }
}
