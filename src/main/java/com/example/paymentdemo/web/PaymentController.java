package com.example.paymentdemo.web;

import com.example.paymentdemo.command.CreatePaymentCmd;
import com.example.paymentdemo.query.GetPaymentsQuery;
import com.example.paymentdemo.query.PaymentRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.javamoney.moneta.Money;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@XSlf4j
@RequiredArgsConstructor
public class PaymentController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public void post(@RequestBody Money money) {
        log.entry(money);
        CreatePaymentCmd cmd = new CreatePaymentCmd(UUID.randomUUID(), money);
        commandGateway.sendAndWait(cmd);

        /*
         * Intentionally crashing here for demo purposes, only on EUR payments !
         * We exit with 'halt' rather than 'exit' to avoid the shutdown hooks from waiting
         * for processors to complete correctly.
         */
        if("EUR".equals(money.getCurrency().getCurrencyCode())) Runtime.getRuntime().halt(-1);

        log.exit();
    }

    @GetMapping
    public List<PaymentRecord> get() {
        log.entry();
        return log.exit(queryGateway.query(new GetPaymentsQuery(), ResponseTypes.multipleInstancesOf(PaymentRecord.class)).join());
    }

}
