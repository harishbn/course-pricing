package org.sample.course.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.sample.course.model.CoursePricingType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CourseDto {
    private Integer courseId;
    private String name;
    private CoursePricingType coursePricingType;
}
