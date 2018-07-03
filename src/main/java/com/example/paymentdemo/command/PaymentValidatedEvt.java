package com.example.paymentdemo.command;

import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class PaymentValidatedEvt {

    @TargetAggregateIdentifier
    @NonNull
    UUID paymentId;

}
