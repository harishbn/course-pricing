
-- Free course
INSERT INTO course (course_id, name, course_pricing_type) VALUES (1000, 'Free Guide to Java', 'FREE');

-- One-Time Payment Courses
INSERT INTO course (course_id, name, course_pricing_type) VALUES (2000, 'Basics of Spring Boot', 'ONE_TIME_PURCHASE');
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (2000, 'BASE_PRICE', 'INR', 1000, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (2000, 'BASE_PRICE', 'USD', 20, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (2000, 'CONVERSION_FEE', 'USD', 1, false);

INSERT INTO course (course_id, name, course_pricing_type) VALUES (3000, 'Spring Boot Advanced', 'ONE_TIME_PURCHASE');
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'BASE_PRICE', 'INR', 2000, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'TAX', 'INR', 8, true);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'OTHER_FEE', 'INR', 100, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'BASE_PRICE', 'USD', 40, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'CONVERSION_FEE', 'USD', 2, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'OTHER_FEE', 'USD', 3, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (3000, 'TAX', 'USD', 20, true);


-- Subscription based courses (price is per month - recurring)
INSERT INTO course (course_id, name, course_pricing_type) VALUES (4000, 'Novice to Ninja in 6 months', 'SUBSCRIPTION');
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (4000, 'BASE_PRICE', 'INR', 500, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (4000, 'BASE_PRICE', 'USD', 10, false);
INSERT INTO course_price (course_id, price_type, currency_uom, price, is_percent)
VALUES (4000, 'CONVERSION_FEE', 'USD', 1, false);