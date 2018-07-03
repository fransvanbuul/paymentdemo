package com.example.paymentdemo.command;

import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.UUID;

@Value
public class CreatePaymentCmd {

    @TargetAggregateIdentifier
    @NonNull
    UUID paymentId;

    @NonNull
    Money amount;

}
