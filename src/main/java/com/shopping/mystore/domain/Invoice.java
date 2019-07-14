package com.shopping.mystore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopping.mystore.domain.enumeration.InvoiceStatus;
import com.shopping.mystore.domain.enumeration.PaymentMethod;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "details")
    private String details;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InvoiceStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private Instant paymentDate;

    @NotNull
    @Column(name = "payment_amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal paymentAmount;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private CustomerOrder order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Invoice date(Instant date) {
        this.date = date;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Invoice details(String details) {
        this.details = details;
        return this;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Invoice status(InvoiceStatus status) {
        this.status = status;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Invoice paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Invoice paymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Invoice paymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder customerOrder) {
        this.order = customerOrder;
    }

    public Invoice order(CustomerOrder customerOrder) {
        this.order = customerOrder;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return id != null && id.equals(((Invoice) o).id);
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + getId() +
                ", date='" + getDate() + "'" +
                ", details='" + getDetails() + "'" +
                ", status='" + getStatus() + "'" +
                ", paymentMethod='" + getPaymentMethod() + "'" +
                ", paymentDate='" + getPaymentDate() + "'" +
                ", paymentAmount=" + getPaymentAmount() +
                "}";
    }
}
