package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.Filter;
import io.restassured.internal.RestAssuredResponseOptionsImpl;


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

    //public Response GETbyname(Employee_Info person){
        //RequestSpecBuilder builder = new RequestSpecBuilder();
        // builder.setBaseUri("http://dummy.restapiexample.com/api/v1/employees");
        // builder.setContentType(ContentType.JSON);
        // var requestSpec = builder.build();
        // Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);
        
        // String name = person.getName();
        //first step from response to jsonstring

        //Request.body(string_json_body).get();

        // try{
        //     String inputLine;
        //     StringBuffer response = new StringBuffer();
        //     while((inputLine = in.readLine())!=null){
        //         response.append(inputLine);
        //     } in .close();
        // }catch (Exception e){
        //     System.out.println("null value");
        // }
        


        // JsonObject response_jsonObject= new JsonObject(response.body().asString());
        // List<Map<String,String>> ret = response_jsonObject.get(name);
        // //jsonstring to JsonArray
        // for(Map<String,String> el: ret){
        //     String target_name = el.get("employee_name");
        //     if (name == target_name){
        //         String target_id = el.get("id");
        //         return RestAssured.given().get("http://dummy.restapiexample.com/api/v1/employee/"+target_id);
                
        //     }
        //     else return null;
        // }
        //return null;


    //}
    public Response WrongGetMethod(Employee_Info person){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://dummy.restapiexample.com/api/v1/create");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);
        
        JsonObject json_body = (JsonObject) new Gson().toJsonTree(person);
        String string_json_body = json_body.toString();
        Request.body(string_json_body);

        return Request.get();
    }


    // class variable
    final String lexicon = "abcdefghijlrmnstquvxyzo.,ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890!#$%&/()=<>!?";

    final java.util.Random rand = new java.util.Random();

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+15;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }


    public Response OpsSimplePOST(Employee_Info person){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://dummy.restapiexample.com/api/v1/"+person.getRng());
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);
        
        JsonObject json_body = (JsonObject) new Gson().toJsonTree(person);
        String string_json_body = json_body.toString();
        Request.body(string_json_body);

        return Request.post();
    }
    public Response DELETE(Employee_Info person){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://dummy.restapiexample.com/api/v1/delete/"+person.getId());
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).filter(FORCE_JSON_RESPONSE_BODY);
        return Request.delete();
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