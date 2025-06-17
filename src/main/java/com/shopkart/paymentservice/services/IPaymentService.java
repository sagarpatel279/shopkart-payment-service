package com.shopkart.paymentservice.services;

public interface IPaymentService {
    String createPaymentLink(Long orderId);
}
