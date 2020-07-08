package org.sample.course.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.sample.course.model.enums.CoursePricingType;
import org.sample.course.model.enums.CurrencyUom;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CoursePriceBreakupDto  {
    private BigDecimal courseNetPrice = BigDecimal.ZERO;
    private Map<String, BigDecimal> priceBreakup;
}
