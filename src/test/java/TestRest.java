
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
             body(containsString("error")).log();

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



}