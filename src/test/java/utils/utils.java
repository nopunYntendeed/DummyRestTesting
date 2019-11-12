package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.Filter;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.path.json.JsonPath;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class utils{
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
    public Response POSTOpsWithBodyParams(Employee_Info person){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://dummy.restapiexample.com/api/v1/create");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);

        JsonObject json_body = (JsonObject) new Gson().toJsonTree(person);
        String string_json_body = json_body.toString();
        Request.body(string_json_body);
        return Request.post();

    }

    public Response GETOpsBodyParams(Employee_Info person){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://dummy.restapiexample.com/api/v1/employee/"+person.getId());
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);
        
        JsonObject json_body = (JsonObject) new Gson().toJsonTree(person);
        String string_json_body = json_body.toString();
        Request.body(string_json_body);

        return Request.get();
    }
    public Employee_Info GETisHealthy(Response response){
        if (response.statusCode() == 200){
            JsonPath ret = new JsonPath(response.getBody().asString());
            Employee_Info person = new Employee_Info();
            person.set_id(ret.get("id"));
            person.set_name(ret.get("employee_name"));
            person.set_salary(ret.get("employee_salary"));
            person.set_age(ret.get("employee_age"));
            person.set_picture(ret.get("profile_image"));
            return person;
        }
        else throw new IllegalStateException("Unresponsive URL");
    }
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
}