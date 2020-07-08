# Course Pricing Strategy


## Sample Data Setup

Create a database by name `coursedb` 

Load the sample data from the sql file `data.sql` in src/main/resources

#### About Sample Data

The sample data contains 4 courses

* 1 Free course
* 2 One-Time Payment Course 
    * with pricing details in both INR and USD currencies
* 1 Subscription based Course
    * Again pricing are in both INR and USD currencies


## Assumptions 

**Countries** 

The countries supported are USA and IND. This is easily configurable to include more countries.

For Each country, the global configuration are set, some are:

* Currency uom
* Default tax percentage

**Currency**: 

Currency is associated with Country. 
For example, user from USA will always purchase with USD currency, 
and users from IND will purchase with INR. However, we can support multiple currency for a country
with minimal changes. 

**Taxes**:

Default Tax percentage is configured at country level.

At course level, tax percentage can be configured which will override 
any country level configuration.

Example: India - default tax is configured at 10%, and USA default tax is configured at 15%

## Course Price Definition

A course price is always specified along with a currency. If a course is being sold in both USA and IND, 
then price needs to be configured for both currencies.

Example course data:
* Course: "Advanced Spring Boot"
* Price Definition: 
    * INR 
        * Base Price: 2000
        * Other Fee: 100
        * Tax(override): 8%
    * USD
        * Base Price: 40 
        * Conversion Fee: 2
        * Other Fee: 1
        * Tax (override): 20%

## Running the App

Configure the database (mysql) connection details in `application-local.yml` file.

Run the spring boot app with spring profile set to `local`

~~~
mvnw spring-boot:run -Dspring-boot.run.profiles=local
~~~

Ensure the service is up and running by invoking health-check endpoint:
~~~
GET 'http://localhost:8080/health'
~~~

OpenAPI (Swagger) documentation can be found here:

http://localhost:8080/swagger-ui.html

## Unit Tests

Refer to `CourseServiceImplTests.java` for tests with use cases for calculating course pricing.

## API Endpoints

**1. Get Course Detail**

Given a course id, this API will retrieve the course details along with course price (base-price).

The price depends on the country passed in as param (defaults to IND if not specified)
~~~
GET http://localhost:8080/courses/3000'
~~~
The API response would be as below:
~~~
{
    "now": "2020-07-08T15:19:23.737+0000",
    "message": "Course Detail",
    "data": {
        "courseId": 3000,
        "name": "Spring Boot Advanced",
        "coursePricingType": "ONE_TIME_PURCHASE",
        "currencyUom": "INR",
        "coursePrice": 2260.00
    }
}
~~~

If we pass USA as a country param, then the same API would be as:

~~~
GET 'http://localhost:8080/courses/3000?country=USA' 
~~~
And the response would be
~~~
{
    "now": "2020-07-08T15:19:07.168+0000",
    "message": "Course Detail",
    "data": {
        "courseId": 3000,
        "name": "Spring Boot Advanced",
        "coursePricingType": "ONE_TIME_PURCHASE",
        "currencyUom": "USD",
        "coursePrice": 40.00
    }
}
~~~

**2. Course Pricing Breakup**

Now during checkout the complete break of the course price can be retrieved using the below API

~~~
GET 'http://localhost:8080/courses/3000/prices'
~~~
Response:
~~~
{
    "now": "2020-07-08T15:16:01.481+0000",
    "message": "Course Pricing Breakup Details",
    "data": {
        "courseNetPrice": 2260.00,
        "priceBreakup": {
            "BASE_PRICE": 2000.00,
            "OTHER_FEE": 100.00,
            "TAX": 160.00
        }
    }
}
~~~
And for a user from USA
~~~
GET 'http://localhost:8080/courses/3000/prices?country=USA'
~~~
Response:
~~~
{
    "now": "2020-07-08T15:15:52.386+0000",
    "message": "Course Pricing Breakup Details",
    "data": {
        "courseNetPrice": 53.00,
        "priceBreakup": {
            "BASE_PRICE": 40.00,
            "OTHER_FEE": 3.00,
            "CONVERSION_FEE": 2.00,
            "TAX": 8.00
        }
    }
}
~~~
