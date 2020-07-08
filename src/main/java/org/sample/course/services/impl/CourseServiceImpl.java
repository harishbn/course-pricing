package org.sample.course.services.impl;

import org.sample.course.dto.CourseDto;
import org.sample.course.dto.CoursePriceBreakupDto;
import org.sample.course.mappers.CourseMapper;
import org.sample.course.model.Course;
import org.sample.course.model.CoursePrice;
import org.sample.course.model.enums.Country;
import org.sample.course.model.enums.CoursePricingType;
import org.sample.course.model.enums.CurrencyUom;
import org.sample.course.model.enums.PriceComponentType;
import org.sample.course.repositories.CoursePriceRepository;
import org.sample.course.repositories.CourseRepository;
import org.sample.course.services.ICourseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final CoursePriceRepository coursePriceRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository,
                             CoursePriceRepository coursePriceRepository,
                             CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.coursePriceRepository = coursePriceRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseDetail(Integer courseId, Country country) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(!courseOptional.isPresent()) {
            throw new Exception("Course not found");
        }
        Course course = courseOptional.get();

        CurrencyUom currencyUom = country.getCurrencyUom();
        CourseDto courseDto = courseMapper.modelToDto(course);
        courseDto.setCurrencyUom(currencyUom);

        if(course.getCoursePricingType().equals(CoursePricingType.FREE)) {
            return courseDto;
        }
        courseDto.setCoursePrice(calculateCourseNetPrice(courseId, country) );
        return courseDto;
    }

    private BigDecimal calculateCourseNetPrice(Integer courseId, Country country) {
        BigDecimal courseNetPrice = BigDecimal.ZERO;
        List<CoursePrice> coursePrices =
                coursePriceRepository.findByIdCourseCourseIdAndIdCurrencyUom(courseId, country.getCurrencyUom());

        if(coursePrices!=null && coursePrices.size()>0) {
            if(country.equals(Country.IND)) {
                // India - show whole price
                BigDecimal totalPrice = BigDecimal.ZERO;
                for(CoursePrice coursePrice: coursePrices) {
                    totalPrice = totalPrice.add(coursePrice.getPrice());
                }
                courseNetPrice = totalPrice;
            } else {
                // Other Countries - show only base price
                for(CoursePrice coursePrice: coursePrices) {
                    if(coursePrice.getId().getPriceComponentType().equals(PriceComponentType.BASE_PRICE)) {
                        courseNetPrice = coursePrice.getPrice();
                        break;
                    }
                }
            }
        }
        return courseNetPrice;
    }

    @Override
    public CoursePriceBreakupDto getCoursePriceDetail(Integer courseId, Country country) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(!courseOptional.isPresent()) {
            throw new Exception("Course not found");
        }
        CurrencyUom currencyUom = country.getCurrencyUom();
        CoursePriceBreakupDto coursePriceBreakupDto = new CoursePriceBreakupDto();

        List<CoursePrice> coursePrices = coursePriceRepository.findByIdCourseCourseIdAndIdCurrencyUom(courseId, currencyUom);
        Map<String, BigDecimal> priceComponents = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(coursePrices != null && coursePrices.size() > 0) {
            for(CoursePrice coursePrice: coursePrices) {
                priceComponents.put(coursePrice.getId().getPriceComponentType().toString(), coursePrice.getPrice());
                totalPrice = totalPrice.add(coursePrice.getPrice());
            }
        }
        coursePriceBreakupDto.setCourseNetPrice(totalPrice);
        coursePriceBreakupDto.setPriceBreakup(priceComponents);
        return coursePriceBreakupDto;
    }
}
