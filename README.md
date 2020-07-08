# Course Pricing Strategy


## Sample Data Setup

To load the sample data, import the sql file `data.sql` from src/main/resources

#### About Sample Data

The sample data contains 4 courses

* Free course
* One-Time Payment Course 
    * with pricing details in both INR and USD currencies
* Subscription based Course
    * Again pricing are in both INR and USD currencies

## Running the App

Configure the database (mysql) connection details in `application-local.yml` file.

Run the spring boot app with spring profile set to `local`

~~~
mvnw spring-boot:run -Dspring-boot.run.profiles=local
~~~

## Assumptions

Countries supported are USA and IND. This is easily configurable to include more countries.

Currency used - currency is associated with Country. For example, user from USA will always purchase with USD currency, 
and users from IND will purchase with INR.
Country to Currency mapping is currently static and is configured in `CountryCurrencyMapper.java` and can easily be
moved to external source such as Database/Configuration/External-Service.

## Course Pricing

A course price is always specified along with a currency. Example: if a course is being sold in both USA and IND, 
then price needs to be configured for both currencies.

Example
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


#### Use Case: 1) User from India 

User from India trying to purchase above course, will be shown course fee as 2000 INR on course detail page.

And during checkout the total purchase price will be INR 2100, with break up details (Base price: 2000, Other fee: 100)

#### Use Case: 2) User from USA

A user from USA trying to purchase same course, will be shown USD 40 as price in course detail page.

During checkout, the total price to pay will be USD 43 with break up details (base price: 40, conversion fee: 2, other fee: 1)


