package com.shopkart.paymentservice.clients.paymentgateways.stripepay;

import com.shopkart.paymentservice.clients.paymentgateways.IPaymentGateWay;
import com.shopkart.paymentservice.exceptions.StripePaymentException;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateWayAdapter implements IPaymentGateWay {
    private final StripeClient stripeClient;

    public StripePaymentGateWayAdapter(StripeClient stripeClient){
        this.stripeClient=stripeClient;
    }

    @Override
    public String createPaymentLink(Long amount) {
        amount*=100;
        try {
            PriceCreateParams priceCreateParams =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();

            Price price = stripeClient.prices().create(priceCreateParams);

            PaymentLinkCreateParams paymentLinkCreateParams =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .build();
            PaymentLink paymentLink = stripeClient.paymentLinks().create(paymentLinkCreateParams);
            return paymentLink.getUrl();
        }catch (StripeException se){
            throw new StripePaymentException(se.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Something went wrong on server");
        }
    }
}
