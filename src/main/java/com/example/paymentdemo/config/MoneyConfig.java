package com.example.paymentdemo.config;

import com.fasterxml.jackson.databind.Module;
import lombok.Data;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;
import springfox.documentation.spring.web.plugins.Docket;

import java.math.BigDecimal;

@Configuration
public class MoneyConfig {

    @Bean
    public Module moneyModule() {
        return new MoneyModule();
    }

    @Autowired
    public void configure(Docket docket) {
        docket.directModelSubstitute(Money.class, MoneyModel.class);
    }

    @Data
    public static class MoneyModel {
        BigDecimal amount;
        String currency;
    }

}
