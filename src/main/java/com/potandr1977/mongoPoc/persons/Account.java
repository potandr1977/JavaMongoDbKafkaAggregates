package com.potandr1977.mongoPoc.persons;

import com.potandr1977.mongoPoc.primitives.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Account extends Entity {

    private String name;

    private Date createDate;

    private List<Payment> payments = new ArrayList<Payment>();

    protected Account(){

    }

    public List<Payment> getPayments(){
        return Collections.unmodifiableList(payments);
    }

    public static Account create(UUID id, String name, Date createDate, List<Payment> payments){
        var account = new Account();
        account.setId(id);
        account.setName(name);
        account.setCreateDate(createDate);

        if (payments != null) {
            account.payments.addAll(payments);
        }

        return account;
    }

    public void AddPayment(Payment payment){
        payments.add(payment);
    }
}
