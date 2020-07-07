package org.sample.course.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Table(name="course")
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
