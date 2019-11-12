import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.Filter;
import io.restassured.internal.RestAssuredResponseOptionsImpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

import utils.utils;
import utils.Employee_Info;


public class TestPut {

    // @Test
    // public void DummyPutUpdate() {
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("Tres");
    //     person.set_salary("-99");
    //     person.set_age("-30");
    //     person.set_id("95728");

    //     new utils().PUTOpsWithBodyAndPathParams(person).
    //     then().
    //     assertThat().
    //             statusCode(200).and().
    //             body("name", equalTo("Killa"))
    //             .and().body("salary", equalTo("-99"))
    //             .and().body("age", equalTo("-30"));
    // }
    // @Test 
    // public void DummyPostCreate(){
    //     Employee_Info person = new Employee_Info();
    //     person.set_name("Somebody");
    //     person.set_salary("-99");
    //     person.set_age("-73");


    //     new utils().POSTOpsWithBodyParams(person).then().
    //     assertThat().
    //          statusCode(200).and().
    //         body("name", equalTo("Somebody"))
    //         .and().body("salary", equalTo("-99"))
    //         .and().body("age", equalTo("-73"));

    // }
    @Test 
    public void DummyGetSingle(){
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

        
        
        // new utils().(person).then().
        // assertThat().
        //     body("name", equalTo("Somebody"))
        //     .and().body("salary", equalTo("-99"))
        //     .and().body("age", equalTo("-73"));

    }

}