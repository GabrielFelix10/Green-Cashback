package com.green.cashback.greenCashback.model;

import javax.validation.constraints.Min;

/**
 * @author gfaraujo
 */
public class Bottle {

    private Bottle() {

    }

    public Bottle(final int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    public int getQuantity() {
        return quantity;
    }
}
