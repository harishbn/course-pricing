package org.sample.course.services;

import org.sample.course.dto.CourseDto;
import org.sample.course.dto.CoursePriceBreakupDto;
import org.sample.course.model.enums.Country;

import java.util.List;

public interface ICourseService {
    List<CourseDto> getCourses();
    CourseDto getCourseDetail(Integer courseId, Country country) throws Exception;
    CoursePriceBreakupDto getCoursePriceDetail(Integer courseId, Country country) throws Exception;
}
