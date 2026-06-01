package com.jbd.controller;

import com.jbd.entity.ExchangeValue;
import com.jbd.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private ExchangeValueRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        if (exchangeValue == null) {
            throw new RuntimeException(
                "Unable to find exchange rate for " + from + " to " + to);
        }
        exchangeValue.setPort(
            Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
