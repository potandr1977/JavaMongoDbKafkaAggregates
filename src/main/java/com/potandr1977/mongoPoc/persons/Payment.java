package com.potandr1977.mongoPoc.persons;

import com.potandr1977.mongoPoc.primitives.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Payment extends Entity {

    private BigDecimal sum;

    private Date createDate;

    private PaymentTypeEnum type;

    protected Payment(){

    }

    public static Payment create(UUID id,BigDecimal sum, Date createDate, PaymentTypeEnum type)
    {
        var payment = new Payment();
        payment.setId(id);
        payment.setSum(sum);
        payment.setCreateDate(createDate);

        return payment;
    }
}

