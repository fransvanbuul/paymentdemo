package com.example.paymentdemo.command;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.javamoney.moneta.Money;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@ToString
@NoArgsConstructor
@XSlf4j
public class Payment {

    @AggregateIdentifier
    private UUID id;
    private Money amount;

    @CommandHandler
    public Payment(CreatePaymentCmd cmd) {
        log.entry(cmd);
        apply(new PaymentCreatedEvt(cmd.getPaymentId(), cmd.getAmount()));
    }

    @CommandHandler
    public void handle(ValidatePaymentCmd cmd) {
        log.entry(cmd);
        if(amount.isPositive()) {
            apply(new PaymentValidatedEvt(cmd.getPaymentId()));
        } else {
            apply(new PaymentNotValidatedEvt(cmd.getPaymentId(),
                    PaymentNotValidatedEvt.Reason.AMOUNT_NOT_POSITIVE));
        }
    }

    @EventSourcingHandler
    public void on(PaymentCreatedEvt evt) {
        log.entry(evt);
        id = evt.getPaymentId();
        amount = evt.getAmount();
    }

    @EventSourcingHandler
    public void on(PaymentValidatedEvt evt) {
        log.entry(evt);
    }

    @EventSourcingHandler
    public void on(PaymentNotValidatedEvt evt) {
        log.entry(evt);
    }
}
