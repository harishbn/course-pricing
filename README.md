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

**Currency**: 

Currency is associated with Country. 
For example, user from USA will always purchase with USD currency, 
and users from IND will purchase with INR.

Country to Currency mapping is currently static and is configured in `CountryCurrencyMapper.java` and can easily be
moved to external source such as Database/Configuration/External-Service.

**Taxes**:

TBD...

## Course Price Definition

A course price is always specified along with a currency. If a course is being sold in both USA and IND, 
then price needs to be configured for both currencies.

Example:
* Course: "Advanced Spring Boot"
* Base Price: 
    * INR 
        * Base Price: 2000
        * Other Fee: 100
        * Total Price would be: INR 2100
    * USD
        * Base Price: 40 
        * Conversion Fee: 2
        * Other Fee: 1
        * Total price would be: USD 43

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

## API Endpoints

##### 1. Get Course Detail

Given a course id, this API will retrieve the course details along with course price (base-price).

The price depends on the country passed in as param (defaults to IND if not specified)
~~~
GET http://localhost:8080/courses/3000'
~~~
The API response would be as below:
~~~
{
    "now": "2020-07-08T03:36:05.066+0000",
    "message": "Course Detail",
    "data": {
        "courseId": 3000,
        "name": "Spring Boot Advanced",
        "coursePricingType": "ONE_TIME_PURCHASE",
        "currencyUom": "INR",
        "coursePrice": 2000.00
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
    "now": "2020-07-08T03:34:45.360+0000",
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

##### 2. Course Pricing Breakup

Now during checkout the complete break of the course price can be retrieved using the below API

~~~
GET 'http://localhost:8080/courses/3000/prices'
~~~
Response:
~~~
{
    "now": "2020-07-08T03:37:12.833+0000",
    "message": "Course Pricing Breakup Details",
    "data": {
        "courseNetPrice": 2000.00,
        "priceBreakup": {
            "BASE_PRICE": 2000.00
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
    "now": "2020-07-08T03:37:49.278+0000",
    "message": "Course Pricing Breakup Details",
    "data": {
        "courseNetPrice": 45.00,
        "priceBreakup": {
            "BASE_PRICE": 40.00,
            "OTHER_FEE": 3.00,
            "CONVERSION_FEE": 2.00
        }
    }
}
~~~
