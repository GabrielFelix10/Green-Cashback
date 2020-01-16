package com.green.cashback.greenCashback.repository;

import com.green.cashback.greenCashback.client.CashBackHttpClient;
import com.green.cashback.greenCashback.model.Bottle;
import com.green.cashback.greenCashback.model.CashBack;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author gfaraujo
 */
@Repository
public class CashbackRepository {

    @Autowired
    private CashBackHttpClient client;

    @CircuitBreaker(name = "cacheBackCalculate", fallbackMethod = "fallbackCalculate")
    public CashBack obtainCashbackCalculated(final int bottlesQuantity) throws IOException, InterruptedException {
        return client.obtainCashbackCalculated(new Bottle(bottlesQuantity));
    }

    private CashBack fallbackCalculate(final int bottlesQuantity,HttpServerErrorException ex) {
        return new CashBack(BigDecimal.ZERO);
    }
}
