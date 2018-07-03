package com.example.paymentdemo.query;

import com.example.paymentdemo.command.PaymentCreatedEvt;
import com.example.paymentdemo.command.PaymentNotValidatedEvt;
import com.example.paymentdemo.command.PaymentValidatedEvt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Controller
@RequiredArgsConstructor
@XSlf4j
public class PaymentProjection {

    private final EntityManager entityManager;

    @EventHandler
    public void on(PaymentCreatedEvt evt) {
        log.entry(evt);
        entityManager.persist(new PaymentRecord(evt.getPaymentId(), evt.getAmount().getCurrency().getCurrencyCode(),
                evt.getAmount().getNumberStripped(), null));
    }

    @EventHandler
    public void on(PaymentValidatedEvt evt) {
        log.entry(evt);
        entityManager.find(PaymentRecord.class, evt.getPaymentId()).setValidated(true);
    }

    @EventHandler
    public void on(PaymentNotValidatedEvt evt) {
        log.entry(evt);
        entityManager.find(PaymentRecord.class, evt.getPaymentId()).setValidated(false);
    }

    @QueryHandler
    public List<PaymentRecord> handle(GetPaymentsQuery query) {
        log.entry(query);
        Query jpaQuery = entityManager.createNamedQuery("PaymentRecord.findAll", PaymentRecord.class);
        return log.exit(jpaQuery.getResultList());
    }

}
