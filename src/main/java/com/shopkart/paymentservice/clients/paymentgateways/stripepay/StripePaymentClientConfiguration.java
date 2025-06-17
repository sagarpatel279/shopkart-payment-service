package com.shopkart.paymentservice.clients.paymentgateways.stripepay;

import com.stripe.StripeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripePaymentClientConfiguration {
    private String apiKey;
    private final Logger logger= LoggerFactory.getLogger(StripePaymentClientConfiguration.class);
    public StripePaymentClientConfiguration(@Value("${stripe.payment.api.key}") String apiKey){
        this.apiKey=apiKey;
    }
    @Bean
    public StripeClient createStripeClient(){
        if (apiKey == null || apiKey.trim().isEmpty()) {
            logger.error("Stripe API key is null or empty");
            throw new IllegalStateException("Stripe API key must not be null or empty");
        }
        logger.info("Creating StripeClient bean");
        return new StripeClient(apiKey);

    }
}
