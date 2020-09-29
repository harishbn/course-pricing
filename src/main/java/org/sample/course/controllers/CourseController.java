package org.sample.course.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.sample.course.dto.CourseDto;
import org.sample.course.dto.CoursePriceBreakupDto;
import org.sample.course.dto.ResponseWrapper;
import org.sample.course.model.enums.Country;
import org.sample.course.services.ICourseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public ResponseWrapper<CourseDto> getCourseDetail(@PathVariable("course_id") Integer courseId,
                                                      @RequestParam(value = "country", defaultValue = "IND") String countryId) throws Exception {
        //TODO: Assumption that country is passed in as param (ideally its part of customer profile)
        Country country = Country.IND;
        if(countryId.equals(Country.USA.name())) country = Country.USA;

        return new ResponseWrapper<>(courseService.getCourseDetail(courseId, country), "Course Detail");
    }

    @GetMapping(value = "/courses/{course_id}/prices")
    @Operation(summary = "Get Course Price Detail")
    public ResponseWrapper<CoursePriceBreakupDto> getCoursePriceDetail(@PathVariable("course_id") Integer courseId,
                                                                       @RequestParam(value = "country", defaultValue = "IND") String countryId) throws Exception {
        //TODO: Assumption that country is passed in as param (ideally its part of customer profile)
        Country country = Country.IND;
        if(countryId.equals(Country.USA.name())) country = Country.USA;

        return new ResponseWrapper<>(courseService.getCoursePriceDetail(courseId, country), "Course Pricing Breakup Details");
    }
}
