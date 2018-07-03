package com.example.paymentdemo.process;

import com.example.paymentdemo.command.PaymentCreatedEvt;
import com.example.paymentdemo.command.ValidatePaymentCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@XSlf4j
public class ValidationProcess {

    private final CommandGateway commandGateway;

    @EventHandler
    public void on(PaymentCreatedEvt evt) throws InterruptedException {
        log.entry(evt);
        commandGateway.sendAndWait(new ValidatePaymentCmd(evt.getPaymentId()));
        log.exit();
    }

}
