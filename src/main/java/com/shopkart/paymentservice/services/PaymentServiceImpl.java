package com.shopkart.paymentservice.services;

import com.shopkart.paymentservice.clients.paymentgateways.IPaymentGateWay;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PaymentServiceImpl implements IPaymentService{
    private IPaymentGateWay paymentGateWay;

    public PaymentServiceImpl(IPaymentGateWay paymentGateWay){
        this.paymentGateWay=paymentGateWay;
    }
    @Override
    public String createPaymentLink(Long orderId) {
        return paymentGateWay.createPaymentLink(1000L)  ;
    }
}
