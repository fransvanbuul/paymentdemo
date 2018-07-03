package com.example.paymentdemo.command;

import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class PaymentNotValidatedEvt {

    public enum Reason {
        AMOUNT_NOT_POSITIVE,
    }

    @TargetAggregateIdentifier
    @NonNull
    UUID paymentId;

    @NonNull
    Reason reason;

}
