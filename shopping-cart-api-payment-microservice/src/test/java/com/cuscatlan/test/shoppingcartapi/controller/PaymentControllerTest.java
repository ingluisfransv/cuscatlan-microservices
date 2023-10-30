package com.cuscatlan.test.shoppingcartapi.controller;
import com.cuscatlan.test.shoppingcartapi.model.Payment;
import com.cuscatlan.test.shoppingcartapi.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoPayment() {
        Payment payment = new Payment();
        payment.setOrderId(1L);
        payment.setAmount(100.0f);

        when(paymentService.processPayment(payment)).thenReturn(true);

        ResponseEntity<String> response = paymentController.doPayment(payment);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Payment successful");
    }

    @Test
    void testChangePaymentStatus() {
        Long paymentId = 1L;
        Payment.PaymentStatus newStatus = Payment.PaymentStatus.Done;
        when(paymentService.changePaymentStatus(paymentId, newStatus)).thenReturn(true);
        ResponseEntity<String> response = paymentController.changePaymentStatus(paymentId, newStatus);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Payment status changed to Done");
    }

    @Test
    void testGetPaymentInformation() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setOrderId(1L);
        payment.setAmount(100.0f);
        when(paymentService.getPaymentById(paymentId)).thenReturn(payment);
        ResponseEntity<Payment> response = paymentController.getPaymentInformation(paymentId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(payment);
    }
}

