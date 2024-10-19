package com.potandr1977.mongoPoc.persons.models;

public enum PaymentTypeEnum{
    SALARY(1),
    BANK_CREDIT(2),
    GIFT(3);

    PaymentTypeEnum (int i)
    {
        this.type = i;
    }

    private int type;

    public int getNumericType()
    {
        return type;
    }
}
