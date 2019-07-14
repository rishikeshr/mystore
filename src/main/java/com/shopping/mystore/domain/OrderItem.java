package com.shopping.mystore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopping.mystore.domain.enumeration.OrderItemStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "order_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderItemStatus status;

    @OneToMany(mappedBy = "orderItem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> orders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private CustomerOrder order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderItem quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderItem totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

    public OrderItem status(OrderItemStatus status) {
        this.status = status;
        return this;
    }

    public Set<Product> getOrders() {
        return orders;
    }

    public void setOrders(Set<Product> products) {
        this.orders = products;
    }

    public OrderItem orders(Set<Product> products) {
        this.orders = products;
        return this;
    }

    public OrderItem addOrder(Product product) {
        this.orders.add(product);
        product.setOrderItem(this);
        return this;
    }

    public OrderItem removeOrder(Product product) {
        this.orders.remove(product);
        product.setOrderItem(null);
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItem product(Product product) {
        this.product = product;
        return this;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder customerOrder) {
        this.order = customerOrder;
    }

    public OrderItem order(CustomerOrder customerOrder) {
        this.order = customerOrder;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItem)) {
            return false;
        }
        return id != null && id.equals(((OrderItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + getId() +
                ", quantity=" + getQuantity() +
                ", totalPrice=" + getTotalPrice() +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
