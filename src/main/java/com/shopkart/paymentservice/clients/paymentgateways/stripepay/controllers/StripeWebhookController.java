package com.shopkart.paymentservice.clients.paymentgateways.stripepay.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks")
public class StripeWebhookController {
    private static int count=0;
    @PostMapping("/")
    public String settlePayments(){
        System.out.println("Webhook end point has been hit: "+(++count));
        return "Webhooks are called";
    }
}
