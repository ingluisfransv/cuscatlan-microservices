package com.cuscatlan.test.shoppingcartapi.service;

import com.cuscatlan.test.shoppingcartapi.model.Payment;
import com.cuscatlan.test.shoppingcartapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public boolean processPayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment != null;
    }

    public boolean changePaymentStatus(Long paymentId, Payment.PaymentStatus newStatus) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setStatus(newStatus);
            paymentRepository.save(payment);
            return true;
        }
        return false;
    }

    public Payment getPaymentById(Long paymentId) {
        // Retrieve payment information by paymentId
        // For example, use the PaymentRepository to find the payment

        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        return optionalPayment.orElse(null);
    }
}
