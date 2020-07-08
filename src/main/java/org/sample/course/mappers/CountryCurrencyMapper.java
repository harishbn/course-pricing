package org.sample.course.mappers;

import org.sample.course.model.enums.Country;
import org.sample.course.model.enums.CurrencyUom;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryCurrencyMapper {

    private static Map<Country, CurrencyUom> countryCurrencyUomMap = new HashMap<>();
    static {
        //TODO: this could be loaded from DB/configuration
        countryCurrencyUomMap.put(Country.IND, CurrencyUom.INR);
        countryCurrencyUomMap.put(Country.USA, CurrencyUom.USD);
    }

    public CurrencyUom getCountryCurrency(Country country) {
        return countryCurrencyUomMap.get(country);
    }
}
