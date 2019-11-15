import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;


public class TestHello{
    @Test
    public void hihello(){
        RestAssured.given().
        get("http://dummy.restapiexample.com/api/v1/employees").
        then().
        assertThat().
        header("Server", "nginx/1.16.0").and().
        header("Content-Type", "text/html; charset=UTF-8").and().
        header("Transfer-Encoding","chunked").and().
        log().all().
        statusCode(200).and().
                contentType(ContentType.JSON);
    }

}