import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.json.Json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gson.JsonObject;

import static org.hamcrest.core.IsEqual.equalTo;
import io.restassured.filter.Filter;




public class TestRest{

    static final Filter FORCE_JSON_RESPONSE_BODY = (reqSpec, respSpec, ctx) -> {
        Response response = ctx.next(reqSpec, respSpec);
        ((RestAssuredResponseOptionsImpl) response).setContentType("application/json");
        return response;
    };

    String jsonendpoint
       = "http://dummy.restapiexample.com/api/v1/employees";

    
    
   /* @Test
    String deleteEmployee = "http://dummy.restapiexample.com/api/v1/delete/";
    public void clearsAllEmployees(){

        RestAssured.filters(FORCE_JSON_RESPONSE_BODY);

    // or per request
         List<Map<String,String>> bodyjson = RestAssured
        .given()
        .filters(FORCE_JSON_RESPONSE_BODY)
        .get(jsonendpoint)
        .jsonPath()
        .<List<Map<String,String>>>get();

        for (Map<String,String> el: bodyjson){
            String id = el.get("id");
            RestAssured
            .delete(deleteEmployee + id);

        }
        Assert.error("uma string qualquer uma mensagem");

    }  */  

    @Test
    public void getFirstEmployee(){
        String readFirstEmployee = "http://dummy.restapiexample.com/api/v1/employee/1";
        
        RestAssured.filters(FORCE_JSON_RESPONSE_BODY);

/*         Map<String,String> bodyjson = RestAssured
        .given()
        .filters(FORCE_JSON_RESPONSE_BODY)
        .get(readFirstEmployee)
        .jsonPath()
        .<Map<String,String>>get(); */

 /*        Response response = 
        given().
        param("id", "1").
        when().
        get(readFirstEmployee).
        then().
        contentType(JSON).
        body("/id", equalTo("id")).
        extract().
        path("id").
        response();  */

/*         RestAssured.
        given().
        when().
            get(readFirstEmployee).
        then().
            statusCode(200).
            JsonPath("body").
            body("/id", equalTo(1)); */
           

    }
    @Test
    public void DummyGetAll(){
        


        Response response = RestAssured.
        when().
        get(jsonendpoint).
        andReturn();

        JsonPath jsonPath = new JsonPath(response.body().asString());

       
        // multiple matches returned in an ArrayList
        List<HashMap<String,String>> ret = jsonPath.get("id");
        
    }
    // @Test
    // public void DummyPutUpdate(){
    //     RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/update/93950";


    //     RequestSpecification requestSpec = new RequestSpecBuilder().addParam("employee_name", "Marceline the Vampire Queen").build();

    //     RestAssured.
    //     given().
    //         spec(requestSpec).
    //     when().
    //         put("http://dummy.restapiexample.com/api/v1/update/93950").prettyPeek();
        
        
    //     Response response = RestAssured.get("http://dummy.restapiexample.com/api/v1/employee/93950");

    //     int return_status_code = response.getStatusCode();
    //     Assert.assertEquals(200, return_status_code);
    //     String response_body = response.body().asString();
        
    // }

    
    private JsonPath dummyGetEmployee (String employeeId){

		RequestSpecification requestSpec = new RequestSpecBuilder().build();
		requestSpec.baseUri("http://dummy.restapiexample.com/");
        requestSpec.basePath("api/v1/employee");
        
		Response response = RestAssured.given().contentType(ContentType.JSON).log().all()
							    .spec(requestSpec)
						    .when()
                                .get("/"+employeeId);

        if (response.statusCode() == 200){
            JsonPath ret = new JsonPath(response.getBody().asString());

            String employee_id = ret.get("id");
            String employee_name = ret.get("employee_name");
            String employee_salary = ret.get("employee_salary");
            String employee_age = ret.get("employee_age");
            String profile_image = ret.get("profile_image");
            
            System.out.println("Employee id is "+employee_id);
            System.out.println("Employee name is "+employee_name);
            System.out.println("employee_salary id is "+employee_salary);
            System.out.println("employee_age id is "+employee_age);
            System.out.println("profile_image id is "+profile_image);
            return ret;
        }
        else throw new IllegalStateException("Unresponsive URL");
    }     

    
    private JsonPath dummyGetCreate(String employeeName,String employeeSalary,String employeeAge,String employeeImage,
    String employeeId,String employeeRng){
        JsonObject params = new JsonObject();
        
        params.addProperty("employee_name", employeeName);
        params.addProperty("employee_salary", employeeSalary);
        params.addProperty("employee_age", employeeAge);
        params.addProperty("profile_image", employeeImage);
        params.addProperty("id", employeeId);
        params.addProperty("rng", employeeRng);

        Response response = RestAssured.given().
        body(params.toString()).
        post("http://dummy.restapiexample.com/api/v1/create");

        JsonPath ret = new JsonPath(response.getBody().asString());

        return ret;
    }
    @Test
    public void DummyGetEmployee_1(){
        JsonPath employee_info = dummyGetEmployee("1");
        Assert.assertEquals("1", employee_info.get("id")); 
    }

    @Test
    public void dummyGetCreate_1(){
        //JsonPath return_rest = dummyGetCreate("Marceline the Vampire Queen", "Souls", "10000", "", "", "");

        RequestSpecification httpRequest = RestAssured.given();


        // JsonObject params = new JsonObject();
        // params.addProperty("employee_name", "Marceline");
        // params.addProperty("employee_salary", "Souls");
        // params.addProperty("employee_age", "10000");
        // params.addProperty("profile_image", "");
        //httpRequest.body(params.toString()).when();
        
        JSONObject params = new JSONObject(); 
        params.put("employee_name", "Marceline");
        params.put("employee_salary", "Souls");
        params.put("employee_age", "10000");
        params.put("profile_image", "");

        String params_as_string = params.toString();

        Response response1 = httpRequest.accept(ContentType.JSON).contentType(ContentType.JSON).
        queryParam("employee_name", "Marceline").with().
        body(params_as_string.toString()).
        post("http://dummy.restapiexample.com/api/v1/create");


        
        

        //Response response = httpRequest.post("http://dummy.restapiexample.com/api/v1/create");

        System.out.println(response1.prettyPrint());
        // String authorizationHeadeString = response.getHeader("Accept");
        // System.out.println(authorizationHeadeString);


    }
    @Test 
    public void postClassTry5(){
        class Employee_User {
            private String employee_name;
            private String employee_salary;
            private String employee_age;
            private String employee_picture;
            private String employee_rng;

            public void set_name(String employee_name){
                this.employee_name = employee_name;
            }
            public void set_salary(String employee_salary){
                this.employee_salary = employee_salary;
            }
            public void set_age(String employee_age){
                this.employee_age = employee_age;
            }
            public String getId () {
                return employee_name;    
            }
            public String getSalary () {
                return employee_salary;    
            }
            public String getAge () {
                return employee_age;    
            }
        }
        Employee_User user = new Employee_User();
		user.set_name("Marceline");
		user.set_age("10000");
        user.set_salary("Souls");
        
		Response response = RestAssured.given().headers("Content-Type", "application/json").contentType(ContentType.JSON)
			.baseUri("http://dummy.restapiexample.com/api/v1")
			.body(user)
			.post("/create").prettyPeek();
    }
}