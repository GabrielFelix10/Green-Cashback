package com.green.cashback.greenCashback.repository;

import com.green.cashback.greenCashback.client.CashBackHttpClient;
import com.green.cashback.greenCashback.model.Bottle;
import com.green.cashback.greenCashback.model.CashBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * @author gfaraujo
 */
@Repository
public class CashbackRepository {

    @Autowired
    private CashBackHttpClient client;

    public CashBack obtainCashbackCalculated(final int bottlesQuantity) throws IOException, InterruptedException {
        return client.obtainCashbackCalculated(new Bottle(bottlesQuantity));
    }
}
