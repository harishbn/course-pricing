package org.sample.course.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.sample.course.model.enums.PriceComponentType;
import org.sample.course.model.id.PriceId;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Table(name="course_price")
public class CoursePrice implements Serializable {
    private static final long serialVersionUID = 6026580924282784743L;

    @EmbeddedId
    private PriceId id;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="is_percent")
    private Boolean isPercent;
}
