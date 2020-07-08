package org.sample.course.model.id;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.sample.course.model.Course;
import org.sample.course.model.enums.CurrencyUom;
import org.sample.course.model.enums.PriceComponentType;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Accessors(chain = true)
public class PriceId implements Serializable {
    private static final long serialVersionUID = 5872087145016651062L;

    @ManyToOne()
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    @Column(name="currency_uom")
    @Enumerated(EnumType.STRING)
    private CurrencyUom currencyUom;

    @Column(name="price_type")
    @Enumerated(EnumType.STRING)
    private PriceComponentType priceComponentType;
}
