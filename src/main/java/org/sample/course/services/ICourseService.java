package org.sample.course.services;

import org.sample.course.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    List<CourseDto> getCourses();
    CourseDto getCourseDetail(Integer courseId) throws Exception;
}
