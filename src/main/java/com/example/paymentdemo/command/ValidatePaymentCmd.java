package com.example.paymentdemo.command;

import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.money.MonetaryAmount;
import java.util.UUID;

@Value
public class ValidatePaymentCmd {

    @TargetAggregateIdentifier
    @NonNull
    UUID paymentId;

}
