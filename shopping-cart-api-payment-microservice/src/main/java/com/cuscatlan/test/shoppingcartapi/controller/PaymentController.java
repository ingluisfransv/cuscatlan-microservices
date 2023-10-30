package com.cuscatlan.test.shoppingcartapi.controller;
import com.cuscatlan.test.shoppingcartapi.model.Payment;
import com.cuscatlan.test.shoppingcartapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
    @PostMapping("/doPayment")
    public ResponseEntity<String> doPayment(@RequestBody Payment payment) {

        boolean paymentProcessed = paymentService.processPayment(payment);

        if (paymentProcessed) {
            return new ResponseEntity<>("Payment successful", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to process payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{paymentId}/changePaymentStatus")
    public ResponseEntity<String> changePaymentStatus(
            @PathVariable Long paymentId,
            @RequestParam Payment.PaymentStatus newStatus
    ) {

        boolean statusChanged = paymentService.changePaymentStatus(paymentId, newStatus);

        if (statusChanged) {
            return new ResponseEntity<>("Payment status changed to " + newStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment not found or status change failed", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{paymentId}/getPaymentInformation")
    public ResponseEntity<Payment> getPaymentInformation(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
