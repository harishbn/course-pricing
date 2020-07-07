package org.sample.course.mappers;

import org.modelmapper.ModelMapper;
import org.sample.course.dto.CourseDto;
import org.sample.course.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    @Autowired
    ModelMapper modelMapper;

    public CourseDto modelToDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }
}
