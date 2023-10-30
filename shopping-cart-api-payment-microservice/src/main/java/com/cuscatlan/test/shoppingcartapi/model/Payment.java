package com.cuscatlan.test.shoppingcartapi.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Payment  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    private float amount;
    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store enum values as strings
    private PaymentStatus status; // Use the PaymentStatus enum for status
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    // Define the enum for payment status
    public enum PaymentStatus {
        Pending, // Payment is pending
        Done,    // Payment is done
        Canceled // Payment is canceled
    }
}
