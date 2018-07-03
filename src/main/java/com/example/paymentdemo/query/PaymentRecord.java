package com.example.paymentdemo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "PaymentRecord.findAll",
                query = "SELECT p FROM PaymentRecord p ORDER BY p.id"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRecord {

    @Id
    private UUID id;

    @Basic
    private String currency;

    @Basic
    private BigDecimal amount;

    @Basic
    private Boolean validated;

}
