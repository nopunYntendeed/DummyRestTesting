
import org.junit.Test;

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

import java.util.HashMap;
import java.util.List;

public class TestRest {

        /**
     * Asserts employee /1 body
     */
    @Test 
    public void DummyGetSingle_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("1");

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("employee_name", equalTo("Archer"))
            .and().body("employee_salary", equalTo("10"))
            .and().body("id", equalTo("1"))
            .and().body("profile_image", equalTo(""))
            .and().body("employee_age", equalTo("30"));
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
            statusCode(200)
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
     * Assert error
     */
    @Test 
    public void DummyPostCreate_1(){
        Employee_Info person = new Employee_Info();


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body(containsString("error"));

    }
    /**
     * Creates valid Employee
     */
    @Test 
    public void DummyPostCreate_2(){
        Employee_Info person = new Employee_Info();
        person.set_name("kek");
        person.set_salary("10000");
        person.set_age("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body("name", equalTo("kek"))
            .and().body("salary", equalTo("10000"))
            .and().body("age", equalTo("13"));

            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    }
    /**
     * Asserts error because duplication of name
     */
    @Test 
    public void DummyPostCreate_2_1(){
        Employee_Info person = new Employee_Info();
        person.set_name("kek");
        person.set_salary("10000");
        person.set_age("13");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body(containsString("Integrity constraint violation: 1062"));

    }
     /**
     * Asserts GET can send body 
     * and
     * asserts GET created employee of DummyPostCreate_2
     * 
     */
    @Test 
    public void DummyPostCreate_2_2(){
        Employee_Info person = new Employee_Info();
        person.set_name("Noheck");
        person.set_id("95923");
 
        new utils().GETOpsBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body("employee_name", equalTo("kek"))
             .and().body("employee_salary", equalTo("10000"))
             .and().body("employee_age", equalTo("13"));

    }
    /**
     * Asserts DELETE can send body 
     * and
     * asserts DELETE
     * 
     */
    @Test 
    public void DummyPostCreate_2_3(){
    }
    /**
     * Confirms DELETE with GET method
     */
    @Test 
    public void DummyPostCreate_2_4(){

    }
    /**
     * Confirms POST Create cant submit with empty salary
     */
    @Test 
    public void DummyPostCreate_3(){
        Employee_Info person = new Employee_Info();
        person.set_name("other");
        person.set_age("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body(containsString("error"));
    }
     /**
     * Confirms POST Create cant submit with empty age
     */
    @Test 
    public void DummyPostCreate_3_1(){
        Employee_Info person = new Employee_Info();
        person.set_name("other1");
        person.set_salary("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body(containsString("error"));
    }
    /**
     * 
     */
    @Test
    public void DummyPutUpdate() {
        Employee_Info person = new Employee_Info();
        person.set_name("Jasus");
        person.set_salary("-99");
        person.set_age("-30");
        person.set_id("1");

        new utils().PUTOpsWithBodyAndPathParams(person).
        then().
        assertThat().
                statusCode(200).and().
                body("name", equalTo("Jasus"))
                .and().body("salary", equalTo("-99"))
                .and().body("age", equalTo("-30"))
                .and().body("profile_image", equalTo(""));
    }
    /**
     * asserts ints in name
     */
    @Test 
    public void DummyPostCreate_4(){
        Employee_Info person = new Employee_Info();
        person.set_name("kek93760");
        person.set_salary("10000");
        person.set_age("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body("name", equalTo("kek93760"))
            .and().body("salary", equalTo("10000"))
            .and().body("age", equalTo("13"));

            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    }
     /**
     * asserts wierd chars in name
     */
    @Test 
    public void DummyPostCreate_5(){
        Employee_Info person = new Employee_Info();
        person.set_name("ºçç+*ł#$%&/()");
        person.set_salary("10000");
        person.set_age("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body("name", equalTo("ºçç+*ł#$%&/()"))
            .and().body("salary", equalTo("10000"))
            .and().body("age", equalTo("13"));

            String id = person.getId();
            System.out.println("Id of employee created: "+id);

    }
     /**
     * Confirmation of previous test
     */
    @Test 
    public void DummyPostCreate_5_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("95950");

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", not(equalTo("ºçç+*ł#$%&/()")))
            .and().body("employee_salary", equalTo("10000"))
            .and().body("employee_age", equalTo("13"));
    }



}