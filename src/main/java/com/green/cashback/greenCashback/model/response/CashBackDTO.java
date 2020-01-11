package com.green.cashback.greenCashback.model.response;

import java.math.BigDecimal;

/**
 * @author gfaraujo
 */
public class CashBackDTO {

    public CashBackDTO() {
    }

    public CashBackDTO(BigDecimal cashBackValue) {
        this.cashBackValue = cashBackValue;
    }

    private BigDecimal cashBackValue;

    public BigDecimal getCashBackValue() {
        return cashBackValue;
    }
}
