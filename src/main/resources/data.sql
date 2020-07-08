
-- Free course
INSERT INTO course (course_id, name, course_pricing_type) VALUES (1000, 'Free Guide to Java', 'FREE');

-- One-Time Payment Courses
INSERT INTO course (course_id, name, course_pricing_type) VALUES (2000, 'Basics of Spring Boot', 'ONE_TIME_PURCHASE');
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (2000, 'BASE_PRICE', 'INR', 1000);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (2000, 'BASE_PRICE', 'USD', 20);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (2000, 'CONVERSION_FEE', 'USD', 1);

INSERT INTO course (course_id, name, course_pricing_type) VALUES (3000, 'Spring Boot Advanced', 'ONE_TIME_PURCHASE');
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (3000, 'BASE_PRICE', 'INR', 2000);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (3000, 'BASE_PRICE', 'USD', 40);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (3000, 'CONVERSION_FEE', 'USD', 2);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (3000, 'OTHER_FEE', 'USD', 3);

-- Subscription based courses (price is per month - recurring)
INSERT INTO course (course_id, name, course_pricing_type) VALUES (4000, 'Novice to Ninja in 6 months', 'SUBSCRIPTION');
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (4000, 'BASE_PRICE', 'INR', 500);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (4000, 'BASE_PRICE', 'USD', 10);
INSERT INTO course_price (course_id, price_type, currency_uom, price) VALUES (4000, 'CONVERSION_FEE', 'USD', 1);