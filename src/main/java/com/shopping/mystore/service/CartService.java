package com.shopping.mystore.service;

import com.shopping.mystore.domain.Customer;
import com.shopping.mystore.domain.CustomerOrder;
import com.shopping.mystore.domain.OrderItem;
import com.shopping.mystore.domain.enumeration.OrderStatus;
import com.shopping.mystore.model.Cart;
import com.shopping.mystore.repository.CustomerOrderRepository;
import com.shopping.mystore.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {

    private final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> processCart(Cart cart, String name) {
        log.info(" Processing Cart for :: " + name);
        Customer customer = customerService.findByName(name);
        CustomerOrder pendingOrder = customerOrderRepository.getPendingCustomerOrder(customer.getId());

        if (pendingOrder != null) {
            log.info(" Processing Pending Order For : " + name);
            return processPendingOrder(cart, name);
        } else {
            log.info(" Processing New Order For : " + name);
            return processNewOrder(cart, name);
        }

    }

    public Cart getPendingOrders(String name) {
        Cart pendingCart = new Cart();
        log.info(" Fetching Pending Customer Orders ");
        Customer customer = customerService.findByName(name);
        CustomerOrder pendingOrder = customerOrderRepository.getPendingCustomerOrder(customer.getId());
        if (pendingOrder != null) {
            log.info(" Order Pending ");
            List<OrderItem> pendingOrderItemList = orderItemRepository.findByCustomerOrderId(pendingOrder.getId());
            log.info(" Pending Order :: " + pendingOrderItemList);

            for (OrderItem oi : pendingOrderItemList) {
                List<String> productCodes = Collections.nCopies(oi.getQuantity(), oi.getProduct().getProductcode());
                log.info(" ProductCodes :: " + productCodes);
                pendingCart.getProductList().addAll(productCodes);
                log.info(" Added To Cart  :: " + pendingCart.getProductList());
            }

            log.info(" Updated Cart  :: " + pendingCart);
        } else {
            log.info(" No Order Pending ");
        }

        return pendingCart;
    }

    public void postPaymentProcessing(String name) {
        log.info(" Fetching Pending Customer Orders ");
        Customer customer = customerService.findByName(name);
        CustomerOrder pendingOrder = customerOrderRepository.getPendingCustomerOrder(customer.getId());
        pendingOrder.setStatus(OrderStatus.COMPLETED);

        customerOrderService.save(pendingOrder);
    }

    private List<OrderItem> processPendingOrder(Cart cart, String name) {
        log.info("Processing Pending Order for --> " + name);
        log.info("Processing For Updated Cart --> " + cart.getProductList());
        Customer customer = customerService.findByName(name);
        CustomerOrder pendingCustomerOrder = customerOrderRepository.getPendingCustomerOrder(customer.getId());

        Map<String, Long> updatedProductList =
                cart.getProductList().stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        List<OrderItem> existingOrderItemList = orderItemRepository.findByCustomerOrderId(pendingCustomerOrder.getId());
        int quantity;
        // Iterate for existing objects.
        for (OrderItem orderItem : existingOrderItemList) {
            if (updatedProductList.keySet().contains(orderItem.getProduct().getProductcode())) {
                quantity = updatedProductList.get(orderItem.getProduct().getProductcode()).intValue();
                orderItem.setQuantity(quantity);
                orderItem.setTotalPrice(new BigDecimal(quantity * orderItem.getProduct().getPrice().intValue()));
            }
            updatedProductList.keySet().remove(orderItem.getProduct().getProductcode());
        }

        //Iterate for new objects.
        for (Map.Entry<String, Long> productCode : updatedProductList.entrySet()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(pendingCustomerOrder);
            orderItem.setQuantity(productCode.getValue().intValue());
            orderItem.setTotalPrice(new BigDecimal(productService.getProductByCode(productCode.getKey()).getPrice().intValue() * productCode.getValue().intValue()));
            orderItem.setProduct(productService.getProductByCode(productCode.getKey()));

            existingOrderItemList.add(orderItem);

            log.info("Order Item ::" + orderItem);
        }

        log.info(" Saving Updated Order Item List " + existingOrderItemList);
        orderItemService.saveList(existingOrderItemList);

        return existingOrderItemList;
    }

    private List<OrderItem> processNewOrder(Cart cart, String name) {
        log.info("Processing New Order for --> " + name);

        Customer customer = customerService.findByName(name);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setStatus(OrderStatus.PENDING);
        customerOrder.setPlacedDate(Instant.now());

        Map<String, Long> productListing =
                cart.getProductList().stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for (Map.Entry<String, Long> productCode : productListing.entrySet()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(customerOrder);
            orderItem.setQuantity(productCode.getValue().intValue());
            orderItem.setTotalPrice(new BigDecimal(productService.getProductByCode(productCode.getKey()).getPrice().intValue() * productCode.getValue().intValue()));
            orderItem.setProduct(productService.getProductByCode(productCode.getKey()));

            orderItemList.add(orderItem);

            log.info("Order Item ::" + orderItem);
        }

        orderItemService.saveList(orderItemList);
        customerOrderService.save(customerOrder);
        Set<CustomerOrder> customerOrderSet = new HashSet<CustomerOrder>();
        customerOrderSet.add(customerOrder);
        customer.setOrders(customerOrderSet);

        return orderItemList;
    }
}
