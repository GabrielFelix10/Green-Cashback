package com.green.cashback.greenCashback.model;

import java.math.BigDecimal;

/**
 * @author gfaraujo
 */
public class CashBack {

    private CashBack() {

    }

    public CashBack(final BigDecimal cashBackValue) {
        this.cashBackValue = cashBackValue;
    }

    public BigDecimal getCashBackValue() {
        return cashBackValue;
    }

    private BigDecimal cashBackValue;
}
