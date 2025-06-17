package com.shopkart.paymentservice.clients.paymentgateways;


public interface IPaymentGateWay {
    String createPaymentLink(Long amount);
}
