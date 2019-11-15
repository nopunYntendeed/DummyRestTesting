
import org.junit.Test;

import cucumber.api.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

import utils.utils;
import utils.Employee_Info;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRest {
    /**
     * Asserts GET of body of employee id /1
     * and 
     * valid PUT request body 
     */
    @Test 
    public void DummyGetSingle_1(){
        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_id("1");
        person.set_name(rngName);
        person.set_age("174343 434343 -133434 ");
        person.set_salary("29345533+3312312333213");

        new utils().PUTOpsWithBodyAndPathParams(person).then().
        assertThat().
        statusCode(200).and().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8").and().
        body("name", equalTo(rngName))
        .and().body("salary", equalTo("29345533+3312312333213"))
        .and().body("profile_picture", equalTo(null))
        .and().body("age", equalTo("174343 434343 -133434 "))
        .extract().jsonPath().getJsonObject("id");

        // person.set_id(id_string);
        // String id = person.getId();
        // System.out.println("Id of employee created: "+id);

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
            header("Content-Type", "text/html; charset=UTF-8").and().
            header("Connection","keep-alive").and().
            body("employee_name", equalTo(rngName))
            .and().body("employee_salary", equalTo("29345533"))
            .and().body("id", equalTo("1"))
            .and().body("profile_image", equalTo(""))
            .and().body("employee_age", equalTo("174343"));
    }
    /**
     * Asserts that employee /2 has false response body
     * 
     */
    @Test 
    public void DummyGetSingle_1_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("2");

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
            header("Content-Type", "text/html; charset=UTF-8")
            .and().body(containsString("false"));
    }
    /**
     * Asserts Get all size is not 3
     * 
     * 
     */
    @Test
    public void DummyGetAll(){
        
        String jsonendpoint= "http://dummy.restapiexample.com/api/v1/employees";

        Response response = RestAssured.given().
        when().
        get(jsonendpoint).
        andReturn();

        JsonPath jsonPath = new JsonPath(response.body().asString());       
        // multiple matches returned in an ArrayList
        List<HashMap<String,String>> ret = jsonPath.get("id");

        assertThat(ret.size(), is(not(3)));
        
    }
    /**
     * Creates empty body employer
     * Assert error for empty name
     */
    @Test 
    public void DummyPostCreate_1(){
        Employee_Info person = new Employee_Info();



        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body(containsString("error"));

    }
        /**
     * Creates empty body employer
     * Assert error empty salary
     */
    @Test 
    public void DummyPostCreate_1_1(){
        Employee_Info person = new Employee_Info();
        String rngName = new utils().randomIdentifier();
        person.set_name(rngName);


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body(containsString("error"));

    }
    /**
     * Creates empty body employer
     * Assert error with empty age
     */
    @Test 
    public void DummyPostCreate_1_2(){
        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_name(rngName);
        person.set_salary("234324243");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body(containsString("error"));

    }
    /**
     * Creates valid Employee
     */

    @Test 
    public void DummyPostCreate_2(){

        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_name(rngName);
        person.set_salary("29345533+3312312333213");
        person.set_age("4343 434343 -133434 ");
        person.set_picture("https://");


        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        
            assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
            header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", equalTo(rngName))
            .and().body("salary", equalTo("29345533+3312312333213"))
            .and().body("profile_picture", equalTo("https://"))
            .and().body("age", equalTo("4343 434343 -133434 "))
            .extract().jsonPath().getJsonObject("id");

            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    //}
    /**
     * Asserts error because duplication of name
     * Former DummyPostCreate_2_1
     */
    // @Test 
    // public void DummyPostCreate_2_1(){
        //Employee_Info person = new Employee_Info();
        person.set_name(rngName);
        person.set_salary("10000");
        person.set_age("13");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body(containsString("Integrity constraint violation: 1062"));

    //}
     /**
     * Asserts GET can send body 
     * and
     * asserts GET created employee of DummyPostCreate_2
     * and
     * asserts math symbols and " " are not valid 
     * Former DummyPostCreate_2_2
     * 
     */
    // @Test 
    // public void DummyPostCreate_2_2(){
        // Employee_Info person = new Employee_Info();
        person.set_name("Noheck");
        person.set_age("34343");
        person.set_id(id_string);
 
        new utils().GETOpsBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body("employee_name", equalTo(rngName))
             .and().body("employee_salary", equalTo("29345533"))
             .and().body("profile_image", equalTo(""))
             .and().body("employee_age", equalTo("4343"))
             .and().body("id",equalTo(id_string));

            
            person.set_id(id_string);
            // String id = person.getId();
            System.out.println("Id of employee created: "+id);

    //}
    /**
     * Asserts DELETE can send body 
     * and
     * asserts DELETE
     * Former DummyPostCreate_2_3
     */
    // @Test 
    // public void DummyPostCreate_2_3(){

        person.set_id(id_string);

        new utils().DELETE(person).then().
        assertThat().
        statusCode(200).and().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8").and().
        body(containsString("successfully! deleted Records"));
    //}
    /**
     * Confirms DELETE with GET method
     * Former DummyPostCreate_2_4
     */
    // @Test 
    // public void DummyPostCreate_2_4(){
        // Employee_Info person = new Employee_Info();
        person.set_id(id_string);

        new utils().GETOpsBodyParams(person).then().
        assertThat().statusCode(200).and().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8").and().
        body(containsString("false"));

    }
    /**
     * Tests POST Create Id doesnt set
     * and
     * Tests Min for Salary and Age
     * and 
     * Asserts picture is not set as string
     */
    @Test 
    public void DummyPostCreate_3(){
        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_name(rngName);
        person.set_id("24443");
        person.set_age("-9999999999999999999999999999999999999999999");
        person.set_salary("-99999999999999999999999999999999999999999");
        person.set_picture("rmfirfrirfior4i4444");
 
        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body("name", equalTo(rngName))
             .and().body("age", equalTo("-9999999999999999999999999999999999999999999"))
             .and().body("salary", equalTo("-99999999999999999999999999999999999999999"))
             .and().body("profile_picture", equalTo("rmfirfrirfior4i4444"))
             .and().body("id",not(equalTo("24443")))
             .extract().jsonPath().getJsonObject("id");

            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);
            

            new utils().GETOpsBodyParams(person).then().
            assertThat().
                 statusCode(200).and().
                 header("Server", "nginx/1.16.0").and().
                 header("Content-Type", "text/html; charset=UTF-8").and().
                 body("employee_name", equalTo(rngName))
                 .and().body("employee_salary", equalTo("-2147483648"))
                 .and().body("profile_image", equalTo(""))
                 .and().body("employee_age", equalTo("-2147483648"))
                 .and().body("id",equalTo(id_string));
    
                
                person.set_id(id_string);
                //String id = person.getId();
                System.out.println("Id of employee created: "+id);

    }

    /**
     * asserts Wierd chars in name
     * and
     * Max of Salary and Age
     */
    @Test 
    public void DummyPostCreate_4(){

        Employee_Info person = new Employee_Info();
        person.set_name("ºçç+*ł#$%&'/'!()");
        person.set_salary("9999999999999999999999999999999999999999999999999999999999999999999");
        person.set_age("9999999999999999999999999999999999999999999999999999999999999999999");
        person.set_picture("https://");


       String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", equalTo("ºçç+*ł#$%&'/'!()"))
            .and().body("salary", equalTo("9999999999999999999999999999999999999999999999999999999999999999999"))
            .and().body("age", equalTo("9999999999999999999999999999999999999999999999999999999999999999999")).and()
            .and().body("profile_picture", equalTo("https://"))
            .extract().jsonPath().getJsonObject("id");
            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    //}
    /**
     * Confirmation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", not(equalTo("ºçç+*ł#$%&'/'!()")))
            .and().body("employee_salary", equalTo("2147483647"))
            .and().body("profile_image", equalTo(""))
            .and().body("employee_age", equalTo("2147483647"))
            .and().body("id",equalTo(id_string));
    }
    
    /**
     * asserts salary,name(it would if i could find it and delete it)  age  "" 
     */
    @Test 
    public void DummyPostCreate_5(){
        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_name(rngName);
        person.set_age("''");
        person.set_salary("''");
        person.set_picture(null);


        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8")
             .and().body("employee_salary", equalTo(null))
             .and().body("profile_picture", equalTo(null))
             .and().body("employee_age", equalTo(null))
             .extract().jsonPath().getJsonObject("id");


    /**
     * Confirmation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body(containsString("false"));

    }
     /**
     * asserts salary input with chars
     * and 
     * name with type array 
     * 
     */
    @Test 
    public void DummyPostCreate_6(){
        Employee_Info person = new Employee_Info();

        person.set_name_bool(true);
        person.set_age("2332c4343");
        person.set_picture("3132312332");
        person.set_salary("3v12v331v");


       String id_string =
         new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body("name", equalTo("true"))
             .and().body("age", equalTo("2332c4343"))
             .and().body("profile_picture", equalTo("3132312332"))
             .and().body("salary", equalTo("3v12v331v")).and()
             .extract().jsonPath().getJsonObject("id");
            
             person.set_id(id_string);
             String id = person.getId();
             System.out.println("Id of employee created: "+id);
      
     /**
     * Confirmation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", equalTo(null))
            .and().body("employee_salary", equalTo("3"))
            .and().body("profile_image", equalTo(""))
            .and().body("employee_age", equalTo("2332"))
            .and().body("id",equalTo(id_string));

            /**
             * Deletes previous employee for repeatability
             */
            new utils().DELETE(person).then().
            assertThat().
            statusCode(200).and().
            body(containsString("successfully! deleted Records"));

    }
    /**
     * asserts min of age name salary type int
     * 
     */
    @Test 
    public void DummyPostCreate_7(){
        Employee_Info person = new Employee_Info();
        person.set_name_int(-999999999);
        person.set_age_int(-999999999);
        person.set_picture("https://");
        person.set_salary_int(-999999999);


        String id_string = 
        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
             body("name", equalTo("-999999999"))
             .and().body("age", equalTo("-999999999"))
             .and().body("profile_picture", equalTo("https://"))
             .and().body("salary", equalTo("-999999999")).and()
             .extract().jsonPath().getJsonObject("id");
            
             person.set_id(id_string);
             String id = person.getId();
             System.out.println("Id of employee created: "+id);

     /**
     * Confirmation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body("employee_name", equalTo("-999999999"))
            .and().body("employee_salary", equalTo("-999999999"))
            .and().body("profile_image", equalTo(""))
            .and().body("employee_age", equalTo("-999999999"))
            .and().body("id",equalTo(id_string));

            /**
             * Deletes previous employee for repeatability
             */
            new utils().DELETE(person).then().
            assertThat().
            statusCode(200).and().
            body(containsString("successfully! deleted Records"));

    }
    /**
     * Asserts wrong URL
     */
    @Test 
    public void DummyPostCreate_8(){
        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_id("97046");
        person.set_name(rngName);
        person.set_age("43434");
        person.set_salary("95457046");
        person.set_picture("Ht");
        person.set_rng("create/2");

        new utils().OpsSimplePOST(person).then().
        assertThat().
            statusCode(404).and().
            body(containsString("error"));

    }
    /**
     * Wrong body structure missing {} TODO
     */
    @Test 
    public void DummyPostCreate_9(){
        Employee_Info person = new Employee_Info();
        person.set_id("97046");
        person.set_name("Thorfinn{'treotre':'fheed'}");
        person.set_age("43434");
        person.set_salary("95457046");
        person.set_picture("Ht");
        person.set_rng("create/99999999");

        

        new utils().OpsSimplePOST(person).then().
        assertThat().
            statusCode(404).and().
            body(containsString("error"));

    }
    
     /**
     * Asserts max int type in name, age and salary
     * and 
     * asserts POST cant create id in body but it is accepted
     */
    @Test 
    public void DummyPostCreate_10(){
        Employee_Info person = new Employee_Info();
        person.set_id("2323122");
        person.set_name_int(999999999);
        person.set_age_int(999999999);
        person.set_salary_int(999999999);
        person.set_picture("Ht");

        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
        statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
             header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", equalTo("999999999"))
            .and().body("age", equalTo("999999999"))
            .and().body("id", not(equalTo("2323122")))
            .and().body("profile_picture", equalTo("Ht"))
            .and().body("salary", equalTo("999999999")).and()
            .extract().jsonPath().getJsonObject("id");
            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);
    
    /**
     * Confirms creation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
        statusCode(200).and().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8").and().
       body("employee_name", equalTo("999999999"))
       .and().body("employee_salary", equalTo("999999999"))
       .and().body("profile_image", equalTo(""))
       .and().body("employee_age", equalTo("999999999"))
       .and().body("id",equalTo(id_string));

        /**
         * Deletes previous employee for repeatability
         */
        new utils().DELETE(person).then().
        assertThat().
        statusCode(200).and().
        body(containsString("successfully! deleted Records"));
    }
    /**
     * Asserts bool type in salary and age
     */
    @Test 
    public void DummyPostCreate_11(){
        String rngName = new utils().randomIdentifier();
        Employee_Info person = new Employee_Info();
        person.set_name(rngName);
        person.set_salary_bool(true);
        person.set_age_bool(true);
        person.set_picture("https://");


       String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
            header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", equalTo(rngName))
            .and().body("age", equalTo("true"))
            .and().body("profile_picture", equalTo("https://"))
            .and().body("salary", equalTo("true")).and()
            .extract().jsonPath().getJsonObject("id");
            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    /**
     * Confirms creation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
        statusCode(200).and().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8").and().
       body("employee_name", equalTo(rngName))
       .and().body("employee_salary", equalTo("0"))
       .and().body("profile_image", equalTo(""))
       .and().body("employee_age", equalTo("0"))
       .and().body("id",equalTo(id_string));

            

    }
    /**
     * Asserts list type in name 
     * Only fails because name has already been populated
     * andd cant be deleted with DELETE
     * @param List 
     */
    @Test 
    public void DummyPostCreate_12(){
        Employee_Info person = new Employee_Info();
        List list = new ArrayList<>();

        list.add("popcorn");
        list.add("73");
        person.set_name_array(list);
        person.set_salary("786");
        person.set_age("123");
        person.set_picture("https://");


        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            header("Server", "nginx/1.16.0").and().
            header("Content-Type", "text/html; charset=UTF-8").and().
            body("name", equalTo("Array"))
            .and().body("age", equalTo("123"))
            .and().body("profile_picture", equalTo("https://"))
            .and().body("salary", equalTo("786")).and()
            .extract().jsonPath().getJsonObject("id");
            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    /**
     * Confirms creation of previous test
     * 
     */

        new utils().GETOpsBodyParams(person).then().
        assertThat().
        statusCode(200).and().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8")
       .and().body("employee_salary", equalTo("768"))
       .and().body("profile_image", equalTo(""))
       .and().body("employee_age", equalTo("123"))
       .and().body("id",equalTo(id_string));

    }
    /**
     * Asserts Object type in name 
     *  
     */
    @Test 
    public void DummyPostCreate_13(){
        Employee_Info person = new Employee_Info();
        Map<String, String> map = new HashMap<String,String>();
        person.set_name_obj(map);
        person.set_salary("786");
        person.set_age_bool(false);
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body(containsString("error"));
            // .and().body("age", equalTo("false"))
            // .and().body("profile_picture", equalTo(null))
            // .and().body("salary", equalTo("786"));
    }
    //    /**
    //  * Asserts bool type in salary
    //  */
    // @Test 
    // public void DummyPostCreate_13(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("wefevd");
    //     person.set_salary_bool(false);
    //     person.set_age("68514");
    //     person.set_picture("https://");


    //     String id_string = new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("name", equalTo("wefevd"))
    //         .and().body("age", equalTo("68514"))
    //         .and().body("profile_picture", equalTo(null))
    //         .and().body("salary", equalTo("false")).and()
    //         .extract().jsonPath().getJsonObject("id");
            
    //         person.set_id(id_string);
    //         String id = person.getId();
    //         System.out.println("Id of employee created: "+id);

    // }
    // /**
    //  * Asserts bool type in age
    //  */
    // @Test 
    // public void DummyPostCreate_14(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("wefvcd");
    //     person.set_salary("786");
    //     person.set_age_bool(false);
    //     person.set_picture("https://");


    //     String id_string = new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("name", equalTo("wefvcd"))
    //         .and().body("age", equalTo("false"))
    //         .and().body("profile_picture", equalTo(null))
    //         .and().body("salary", equalTo("786")).and()
    //         .extract().jsonPath().getJsonObject("id");
            
    //         person.set_id(id_string);
    //         String id = person.getId();
    //         System.out.println("Id of employee created: "+id);

    // }
    // /**
    //  * Asserts list type in name 
    //  * @param List 
    //  */
    // @Test 
    // public void DummyPostCreate_15(){
    //     Employee_Info person = new Employee_Info();
    //     List list = new ArrayList<>();

    //     list.add("popcorn");
    //     list.add("73");
    //     person.set_name_array(list);
    //     person.set_salary("786");
    //     person.set_age_bool(false);
    //     person.set_picture("https://");


    //     String id_string = new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("name", equalTo("Array"))
    //         .and().body("age", equalTo("false"))
    //         .and().body("profile_picture", equalTo(null))
    //         .and().body("salary", equalTo("786")).and()
    //         .extract().jsonPath().getJsonObject("id");
            
    //         person.set_id(id_string);
    //         String id = person.getId();
    //         System.out.println("Id of employee created: "+id);

    // }
    // /**
    //  * Asserts Object type in name 
    //  *  
    //  */
    // @Test 
    // public void DummyPostCreate_16(){
    //     Employee_Info person = new Employee_Info();
    //     Map<String, String> map = new HashMap<String,String>();
    //     person.set_name_obj(map);
    //     person.set_salary("786");
    //     person.set_age_bool(false);
    //     person.set_picture("https://");


    //     new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //          statusCode(200).and().
    //          body(containsString("error"));
    //     //     .and().body("age", equalTo("false"))
    //     //     .and().body("profile_picture", equalTo(null))
    //     //     .and().body("salary", equalTo("786"));

    // }
    //  /**
    //  * Asserts null type in name
    //  * 
    //  */
    // @Test 
    // public void DummyPostCreate_17(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name(null);
    //     person.set_salary("786");
    //     person.set_age("thr12rfr2");
    //     person.set_picture("https://");


    //     new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body(containsString("error"));

    // }
    
    // /**
    //  * Asserts wrong method error when create into post
    //  * 
    //  */
    // @Test 
    // public void DummyPostCreate_18(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("fpaa95");
    //     person.set_salary("786");
    //     person.set_age("3232");
    //     person.set_picture("https://");

        
    //     new utils().WrongGetMethod(person).then().
    //     assertThat().
    //         statusCode(405).and().
    //         body(containsString("Method not allowed"));

    // }
    // /**
    //  * Asserts creation of profile pictue
    //  * 
    //  */
    // @Test 
    // public void DummyPostCreate_19(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("MarcelineQueen");
    //     person.set_salary("-786");
    //     person.set_age("-3232");
    //     person.set_picture("https://");

        
    //     String id_string = new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("name", equalTo("MarcelineQueen")).and().
    //         body("salary", equalTo("-786")).and().
    //         body("age", equalTo("-3232")).and().
    //         body("profile_picture",equalTo("https://")).and()
    //         .extract().jsonPath().getJsonObject("id");
            
    //         person.set_id(id_string);
    //         String id = person.getId();
    //         System.out.println("Id of employee created: "+id);

    // }
    // /**
    //  * confirms previous test
    //  */
    // @Test 
    // public void DummyPostCreate_19_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("98204");



    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("employee_name", equalTo("MarcelineQueen"))
    //         .and().body("employee_age", equalTo("-3232"))
    //         .and().body("profile_picture", equalTo(null))
    //         .and().body("employee_salary", equalTo("-786"));

    // }
    // /**
    //  * Asserts permission of not valid fields
    //  * 
    //  */
    // @Test 
    // public void DummyPostCreate_20(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("Reactsss");
    //     person.set_salary("-786");
    //     person.set_age("-3232");
    //     person.set_picture("https://");
    //     person.set_rng("kmrrng");
        
    //    String id_string =  new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("name", equalTo("Reactsss")).and().
    //         body("salary", equalTo("-786")).and().
    //         body("age", equalTo("-3232")).and().
    //         body("profile_picture",equalTo(null)).and().
    //         body("rng",equalTo("kmrrng")).and()
    //         .extract().jsonPath().getJsonObject("id");
            
    //         person.set_id(id_string);
    //         String id = person.getId();
    //         System.out.println("Id of employee created: "+id);

    // }
    // /**
    //  * confirms previous test 
    //  */
    // @Test 
    // public void DummyPostCreate_20_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("98226");



    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //         statusCode(200).and().
    //         body("employee_name", equalTo("Reactsss"))
    //         .and().body("employee_age", equalTo("-3232"))
    //         .and().body("profile_picture", equalTo(null))
    //         .and().body("employee_salary", equalTo("-786"))
    //         .and().body(not(containsString("rng")));

    // }

    //  /**
    //  * asserts Age input with null
    //  */
    // @Test 
    // public void DummyPostCreate_21(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("null");
    //     person.set_age("13");
    //     person.set_picture("");
    //     person.set_salary("233fff21212");


    //     new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //          statusCode(200).and().
    //          body(containsString("error"));

    // }
    // /**
    //  * asserts age input with ""
    //  */
    // @Test 
    // public void DummyPostCreate_21_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("null232");
    //     person.set_age("''");
    //     person.set_picture("");
    //     person.set_salary("233fff21212");


    //     String id_string = new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //          statusCode(200).and().
    //          body("name",equalTo("null232")).and().
    //          body("age",equalTo("''")).and().
    //          body("salary",equalTo("233fff21212")).and().
    //          body("profile_picture",equalTo(null)).and()
    //          .extract().jsonPath().getJsonObject("id");
            
    //          person.set_id(id_string);
    //          String id = person.getId();
    //          System.out.println("Id of employee created: "+id);

    // }
    // /**
    //  * Asserts update name not valid if name already exists
    //  */
    // @Test
    // public void DummyPutUpdate_1() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("null232");
    //     person.set_salary("cookie");
    //     person.set_age("-30");
    //     person.set_id("1");

    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body(containsString("error"));
    // }


    // /**
    //  * Asserts update name correct
    //  */
    // @Test
    // public void DummyPutUpdate_1_1() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("driving");
    //     person.set_salary("cookie");
    //     person.set_age("-30");
    //     person.set_id("1");

    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("driving"))
    //             .and().body("salary", equalTo("cookie"))
    //             .and().body("age", equalTo("-30"))
    //             .and().body("profile_image", equalTo(null))
    //             .extract().jsonPath().getJsonObject("id");

            
    //             // person.set_id(id_string);
    //             // String id = person.getId();
    //             // System.out.println("Id of employee created: "+id);
    // }
    // /**
    //  * Confirms creation of previous test
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_1_2(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("1");//id returned from previous test

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body("employee_name", equalTo("driving"))
    //     .and().body("employee_age", equalTo("-30"))
    //     .and().body("profile_picture", equalTo(null))
    //     .and().body("employee_salary", equalTo("0"));
    // }
    // /**
    //  * GET command for control of valid employee
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_2(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("1");

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body("employee_name", equalTo("driving"))
    //     .and().body("employee_age", equalTo("-30"))
    //     .and().body("profile_picture", equalTo(null))
    //     .and().body("employee_salary", equalTo("0"));
    // }
    // /**
    //  * Asserts update name with negative int
    //  */
    // @Test
    // public void DummyPutUpdate_2_1() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name_int(-73);
    //     person.set_salary("rfn43kfr333'''");
    //     person.set_age("-30");
    //     person.set_id("1");
    //     person.set_picture("ttyppssndfdofnofU$%&/()=NEOF4FI4NFOH4W89c4NU9WJE");

    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("-73"))
    //             .and().body("salary", equalTo("rfn43kfr333'''"))
    //             .and().body("age", equalTo("-30"))
    //             .and().body("profile_image", equalTo(null))
    //             .extract().jsonPath().getJsonObject("id");
    // }
    // /**
    //  * GET command to validate previous test
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_2_2(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("1");

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body("employee_name", equalTo("-73"))
    //     .and().body("employee_age", equalTo("-30"))
    //     .and().body("profile_picture", equalTo(null))
    //     .and().body("employee_salary", equalTo("0"));
    // }
    // /**
    //  * Asserts update name with valid body to invalid empoyee
    //  */
    // @Test
    // public void DummyPutUpdate_2_3() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name_int(-74);
    //     person.set_salary("7080");
    //     person.set_age("-30");
    //     person.set_id("2");


    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("-74"))
    //             .and().body("salary", equalTo("7080"))
    //             .and().body("age", equalTo("-30"))
    //             .and().body("profile_image", equalTo(null))
    //             .extract().jsonPath().getJsonObject("id");
    // }
    // /**
    //  * GET command to validate previous test
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_2_4(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("2");

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body(containsString("false"));
    // }
    // /**
    //  * Asserts update id with wierd url
    //  * TODO brings method 405 on postman
    //  */
    // @Test
    // public void DummyPutUpdate_3() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("valid");
    //     person.set_salary("7080");
    //     person.set_age("-30");
    //     person.set_id("bacon");


    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("valid"))
    //             .and().body("salary", equalTo("7080"))
    //             .and().body("age", equalTo("-30"))
    //             .and().body("profile_image", equalTo(null))
    //             .extract().jsonPath().getJsonObject("id");
    // }
    // /**
    //  * GET command to validate previous test
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_3_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("bacon");

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body(containsString("error"));
    // }
    //  /**
    //  * Asserts invalid fields for valid employee
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_4() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("F  4FFdw3334rfvdd%&/()");
    //     person.set_salary_bool(false);
    //     person.set_age("-999999999999999999999999999999999999999999999999");
    //     person.set_id("96700");


    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("F  4FFdw3334rfvdd%&/()"))
    //             .and().body("salary", equalTo("false"))
    //             .and().body("age", equalTo("-999999999999999999999999999999999999999999999999"))
    //             .and().body("profile_image", equalTo(null))
    //             .extract().jsonPath().getJsonObject("id");
    // }
    // /**
    //  * GET command to validate previous test
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_4_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("96700");

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body("employee_name", equalTo("F  4FFdw3334rfvdd%&/()"))
    //     .and().body("employee_salary", equalTo("0"))
    //     .and().body("employee_age", equalTo("-2147483648"))
    //     .and().body("profile_picture",equalTo(null));
    // }
    // /**
    //  * Asserts invalid extra fields for valid employee
    //  * and explore testing for salary and age
    //  */
    // @Test
    // public void DummyPutUpdate_5() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("Thorn.243,21'''");
    //     person.set_salary("99999999999999999999999999999999999999999999999999999999999999999999999");
    //     person.set_age_int(-9999999);
    //     person.set_id("96700");
    //     person.set_rng("random col info");


    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("Thorn.243,21'''"))
    //             .and().body("salary", equalTo("99999999999999999999999999999999999999999999999999999999999999999999999"))
    //             .and().body("age", equalTo("-9999999"))
    //             .and().body("profile_image", equalTo(null))
    //             .and().body("rng", equalTo("random col info"))
    //             .extract().jsonPath().getJsonObject("id");
    // }
    // /**
    //  * GET command to validate previous test
    //  * 
    //  */
    // @Test
    // public void DummyPutUpdate_5_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("96700");

    //     new utils().GETOpsBodyParams(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body("employee_name", equalTo("Thorn.243,21'''"))
    //     .and().body("employee_salary", equalTo("2147483647"))
    //     .and().body("employee_age", equalTo("-9999999"))
    //     .and().body(not(containsString("rng")))
    //     .and().body("profile_picture",equalTo(null));
    // }
    // /**
    //  * Asserts DELETE all with * supposed to fail
    //  * 
    //  */
    // @Test
    // public void DummyDeleteEmployees(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("*");

    //     new utils().DELETE(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body(containsString("successfully! deleted Records"));
    // }
    /**
     * Asserts DELETE valid employee
     * 
     */
    @Test
    public void DummyDeleteEmployee_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("108738");

        new utils().DELETE(person).then().
        assertThat().
        statusCode(200).and().
        body(containsString("successfully! deleted Records"));
    }
    // /**
    //  * Asserts DELETE invalid employee string
    //  * 
    //  */
    // @Test
    // public void DummyDeleteEmployee_1_1(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_id("crispy");

    //     new utils().DELETE(person).then().
    //     assertThat().
    //     statusCode(200).and().
    //     body(containsString("successfully! deleted Records"));
    // }

}