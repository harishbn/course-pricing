package org.sample.course.model.enums;

import lombok.Getter;

import java.math.BigDecimal;

public enum Country {
    IND ("India",
            CurrencyUom.INR,
            new BigDecimal(10.0)
    ),
    USA ("United Stats of America",
            CurrencyUom.USD,
            new BigDecimal(10.0));

    @Getter
    String name;
    @Getter
    CurrencyUom currencyUom;
    @Getter
    BigDecimal defaultTaxPercent;

    Country(String name, CurrencyUom currencyUom, BigDecimal defaultTaxPercent) {
        this.name = name;
        this.currencyUom = currencyUom;
        this.defaultTaxPercent = defaultTaxPercent;
    }
}
