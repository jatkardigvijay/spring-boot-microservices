package com.jbd.controller;

import com.jbd.bean.CurrencyConversion;
import com.jbd.proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        logger.info("calculateCurrencyConversion called with {} {} {}", from, to, quantity);

        CurrencyConversion exchangeResponse = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                exchangeResponse.getId(),
                from,
                to,
                exchangeResponse.getConversionMultiple(),
                quantity,
                quantity.multiply(exchangeResponse.getConversionMultiple()),
                exchangeResponse.getPort()
        );
    }
}
