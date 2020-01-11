package com.green.cashback.greenCashback.service;

import com.green.cashback.greenCashback.model.response.CashBackDTO;
import com.green.cashback.greenCashback.repository.CashbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * @author gfaraujo
 */
@Service
public class CashbackService {

    @Autowired
    private CashbackRepository repository;

    public Optional<CashBackDTO> obtainCashback(final int bottlesQuantity) throws IOException, InterruptedException {

        var cashBack = repository.obtainCashbackCalculated(bottlesQuantity);

        return Optional.of(new CashBackDTO(cashBack.getCashBackValue()));
    }
}
