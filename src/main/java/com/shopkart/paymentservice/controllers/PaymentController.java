package com.shopkart.paymentservice.controllers;

import com.shopkart.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    private IPaymentService paymentService;
    public PaymentController(IPaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/pay")
    public String getPaymentLink(@RequestParam("orderId")Long orderId){
        return paymentService.createPaymentLink(orderId);
    }
}
