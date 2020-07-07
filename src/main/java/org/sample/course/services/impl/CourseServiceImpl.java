package org.sample.course.services.impl;

import org.sample.course.dto.CourseDto;
import org.sample.course.mappers.CourseMapper;
import org.sample.course.model.Course;
import org.sample.course.repositories.CourseRepository;
import org.sample.course.services.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseDetail(Integer courseId) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(!courseOptional.isPresent()) {
            throw new Exception("Course not found");
        }
        return courseMapper.modelToDto(courseOptional.get());
    }
}
