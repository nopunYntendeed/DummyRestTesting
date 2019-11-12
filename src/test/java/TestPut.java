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


class Employee_Info {
    private String name;
    private String salary;
    private String age;
    private String picture;
    private String rng;
    private String id;

    public void set_name(String employee_name){
        this.name = employee_name;
    }
    public void set_salary(String employee_salary){
        this.salary = employee_salary;
    }
    public void set_age(String employee_age){
        this.age = employee_age;
    }
    public void set_id(String id){
        this.id = id;
    }
    public String getName () {
        return name;    
    }
    public String getSalary () {
        return salary;    
    }
    public String getAge () {
        return age;    
    }
    public String getId () {
        return id;    
    }
}

public class TestPut {

    static final Filter FORCE_JSON_RESPONSE_BODY = (reqSpec, respSpec, ctx) -> {
        Response response = ctx.next(reqSpec, respSpec);
        ((RestAssuredResponseOptionsImpl) response).setContentType("application/json");
        return response;
    };
    public static RequestSpecification Request;

    public Response PUTOpsWithBodyAndPathParams(Employee_Info person) {
        
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://dummy.restapiexample.com/api/v1/update/"+person.getId());
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);


        JsonObject json_body = (JsonObject) new Gson().toJsonTree(person);
        json_body.remove("id");
        String string_json_body = json_body.toString();
        

        Request.body(string_json_body);
        return Request.put();
    }
    @Test
    public void PerformPutWithBodyParameter() {
        Employee_Info person = new Employee_Info();
        person.set_name("Killa");
        person.set_salary("-99");
        person.set_age("-30");
        person.set_id("95728");

        PUTOpsWithBodyAndPathParams(person).
        then().
        assertThat().
                body("name", equalTo("Killa"))
                .and().body("salary", equalTo("-99"))
                .and().body("age", equalTo("-30"));
    }
    @Test 
    public void PerformPut(){


    }

}