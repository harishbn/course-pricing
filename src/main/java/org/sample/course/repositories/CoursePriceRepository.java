package org.sample.course.repositories;

import org.sample.course.model.Course;
import org.sample.course.model.CoursePrice;
import org.sample.course.model.enums.CurrencyUom;
import org.sample.course.model.enums.PriceComponentType;
import org.sample.course.model.id.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePriceRepository extends JpaRepository<CoursePrice, PriceId> {
    List<CoursePrice> findByIdCourseCourseIdAndIdCurrencyUom(Integer courseId, CurrencyUom currencyUom);
    List<CoursePrice> findByIdCourseCourseIdAndIdCurrencyUomAndIdPriceComponentType(Integer courseId,
                                                                                    CurrencyUom currencyUom,
                                                                                    PriceComponentType priceComponentType);
}
