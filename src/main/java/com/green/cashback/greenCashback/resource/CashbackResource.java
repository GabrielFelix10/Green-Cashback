package com.green.cashback.greenCashback.resource;

import com.green.cashback.greenCashback.model.response.CashBackDTO;
import com.green.cashback.greenCashback.service.CashbackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author gfaraujo
 */
@RestController
public class CashbackResource {

    @Autowired
    private CashbackService service;

    @ApiOperation(value = "Obtain cashback value", response = CashBackDTO.class)
    @GetMapping("/cash-back/{bottles}")
    public ResponseEntity<CashBackDTO> obtainCashback(@PathVariable("bottles") final int bottlesQuantity) throws IOException, InterruptedException {

        return service.obtainCashback(bottlesQuantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
