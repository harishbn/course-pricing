package org.sample.course.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.course.dto.CourseDto;
import org.sample.course.dto.CoursePriceBreakupDto;
import org.sample.course.model.enums.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CourseServiceImplTests {

    @Autowired
    CourseServiceImpl courseService;

    @Test
    public void shouldReturnCourseNetPriceForIndia() throws Exception {
        CourseDto courseDto = courseService.getCourseDetail(2000, Country.IND);
        assertNotNull(courseDto);
        assertEquals(new BigDecimal(1000.00).doubleValue(),
                courseDto.getCoursePrice().doubleValue(), 2);
    }

    @Test
    public void shouldReturnCourseNetPriceForUSA() throws Exception {
        CourseDto courseDto = courseService.getCourseDetail(2000, Country.USA);
        assertNotNull(courseDto);
        assertEquals(new BigDecimal(20).doubleValue(),
                courseDto.getCoursePrice().doubleValue(), 2);
    }

    @Test
    public void shouldReturnPriceBreakupForIndia() throws Exception {
        CoursePriceBreakupDto priceBreakupDto = courseService.getCoursePriceDetail(3000, Country.IND);
        assertNotNull(priceBreakupDto);
        assertNotNull(priceBreakupDto.getPriceBreakup());

        Map<String, BigDecimal> priceBreakup = priceBreakupDto.getPriceBreakup();
        assertTrue(priceBreakup.containsKey("BASE_PRICE"));
        assertEquals(new BigDecimal(2000.00).doubleValue(),
                priceBreakup.get("BASE_PRICE").doubleValue(), 2);

        assertTrue(priceBreakup.containsKey("OTHER_FEE"));
        assertEquals(new BigDecimal(100.00).doubleValue(),
                priceBreakup.get("OTHER_FEE").doubleValue(), 2);

        assertTrue(priceBreakup.containsKey("TAX"));
        assertEquals(new BigDecimal(160.00).doubleValue(),
                priceBreakup.get("TAX").doubleValue(), 2);
    }

    @Test
    public void shouldReturnPriceBreakupForUSA() throws Exception {
        CoursePriceBreakupDto priceBreakupDto = courseService.getCoursePriceDetail(3000, Country.USA);
        assertNotNull(priceBreakupDto);
        assertNotNull(priceBreakupDto.getPriceBreakup());

        assertEquals(new BigDecimal(53.00).doubleValue(),
                priceBreakupDto.getCourseNetPrice().doubleValue(), 2);

        Map<String, BigDecimal> priceBreakup = priceBreakupDto.getPriceBreakup();
        assertTrue(priceBreakup.containsKey("BASE_PRICE"));
        assertEquals(new BigDecimal(40).doubleValue(),
                priceBreakup.get("BASE_PRICE").doubleValue(), 2);

        assertTrue(priceBreakup.containsKey("CONVERSION_FEE"));
        assertEquals(new BigDecimal(2.00).doubleValue(),
                priceBreakup.get("CONVERSION_FEE").doubleValue(), 2);

        assertTrue(priceBreakup.containsKey("OTHER_FEE"));
        assertEquals(new BigDecimal(3.00).doubleValue(),
                priceBreakup.get("OTHER_FEE").doubleValue(), 2);

        assertTrue(priceBreakup.containsKey("TAX"));
        assertEquals(new BigDecimal(8.00).doubleValue(),
                priceBreakup.get("TAX").doubleValue(), 2);
    }
}
