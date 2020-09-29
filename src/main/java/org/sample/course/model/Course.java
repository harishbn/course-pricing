package org.sample.course.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.sample.course.model.enums.CoursePricingType;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Table(name="course")
@Cacheable("course")
public class Course implements Serializable {
    private static final long serialVersionUID = 2783569129800004530L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="course_id")
    private Integer courseId;

    @Column(name="name")
    private String name;

    @Column(name="course_pricing_type")
    @Enumerated(EnumType.STRING)
    private CoursePricingType coursePricingType;
}
