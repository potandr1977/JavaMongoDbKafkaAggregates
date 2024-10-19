package com.potandr1977.mongoPoc.persons.entities;

import com.potandr1977.mongoPoc.primitives.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Account extends Entity {

    private String name;

    private List<Payment> payments = new ArrayList<Payment>();

    private Account(){

    }

    private Account(String id, String name, List<Payment> payments)
    {
        this.setId(id);
        this.setName(name);
        if (payments != null) {
            this.payments.addAll(payments);
        }
    }

    public List<Payment> getPayments(){
        return Collections.unmodifiableList(payments);
    }

    public static Account create(String name, List<Payment> payments){
        var id = "Account-"+UUID.randomUUID();

        return new Account(id, name, payments);
    }

    public void AddPayment(Payment payment){
        payments.add(payment);
    }
}
