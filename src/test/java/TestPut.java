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

    @Test
    public void DummyPutUpdate() {
        Employee_Info person = new Employee_Info();
        person.set_name("Tres");
        person.set_salary("-99");
        person.set_age("-30");
        person.set_id("95728");

        new utils().PUTOpsWithBodyAndPathParams(person).
        then().
        assertThat().
                body("name", equalTo("Killa"))
                .and().body("salary", equalTo("-99"))
                .and().body("age", equalTo("-30"));
    }
    @Test 
    public void DummyGetCreate(){
        Employee_Info person = new Employee_Info();
        person.set_name("Tres");
        person.set_salary("-99");
        person.set_age("-30");
        person.set_id("95728");

    }

}