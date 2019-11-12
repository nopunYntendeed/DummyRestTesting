import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.apache.http.protocol.HTTP;
//import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.json.Json;

import java.io.File;
import java.net.URI;
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


	String jsonendpoint= "http://dummy.restapiexample.com/api/v1/employees";
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
}