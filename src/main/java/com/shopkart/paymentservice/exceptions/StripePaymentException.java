package com.shopkart.paymentservice.exceptions;

public class StripePaymentException extends RuntimeException{
    public StripePaymentException(String message){
        super(message);
    }
}
