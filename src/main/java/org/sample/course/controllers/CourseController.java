package org.sample.course.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.sample.course.dto.CourseDto;
import org.sample.course.dto.ResponseWrapper;
import org.sample.course.services.ICourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/courses")
    @Operation(summary = "Get list of courses")
    public ResponseWrapper<List<CourseDto>> getCourses() {
        return new ResponseWrapper<>(courseService.getCourses(), "Courses List");
    }

    @GetMapping(value = "/courses/{course_id}")
    @Operation(summary = "Get Course Detail")
    public ResponseWrapper<CourseDto> getCourseDetail(@PathVariable("course_id") Integer courseId) throws Exception {
        return new ResponseWrapper<>(courseService.getCourseDetail(courseId), "Course Detail");
    }
}
