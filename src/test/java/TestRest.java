
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

import java.util.ArrayList;
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
        person.set_name("otot2222");
        person.set_salary("10000");
        person.set_age("13");
        person.set_picture("https://");


        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        
            assertThat().
             statusCode(200).and().
            body("name", equalTo("otot2222"))
            .and().body("salary", equalTo("10000"))
            .and().body("profile_picture", equalTo(null))
            .and().body("age", equalTo("13"))
            .extract().jsonPath().getJsonObject("id");

            person.set_id(id_string);
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
 
        String id_string = new utils().GETOpsBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body("employee_name", equalTo("kek"))
             .and().body("employee_salary", equalTo("10000"))
             .and().body("profile_picture", equalTo(null))
             .and().body("employee_age", equalTo("13"))
             .extract().jsonPath().getJsonObject("id");

            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);

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
     * Tests POST Create Id doesnt set
     */
    @Test 
    public void DummyPostCreate_3(){
        Employee_Info person = new Employee_Info();
        person.set_name("rtfd");
        person.set_id("6");
        person.set_age("22");
        person.set_salary("73");
 
        String id_string = new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body("name", equalTo("rtfd"))
             .and().body("age", equalTo("22"))
             .and().body("salary", equalTo("73"))
             .and().body("profile_picture", equalTo(null))
             .and().body("id",not(equalTo(person.getId())))
             .extract().jsonPath().getJsonObject("id");

            
            person.set_id(id_string);
            String id = person.getId();
            System.out.println("Id of employee created: "+id);

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
     * Confirmation of previous test
     */
    @Test 
    public void DummyPostCreate_4_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("95944");

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", not(equalTo("kek93760")))
            .and().body("employee_salary", equalTo("10000"))
            .and().body("profile_picture", equalTo(null))
            .and().body("employee_age", equalTo("13"));
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
    /**
     * asserts salary input mandatory
     */
    @Test 
    public void DummyPostCreate_6(){
        Employee_Info person = new Employee_Info();
        person.set_name("Tigre");
        person.set_age("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body(containsString("error"));

    }
     /**
     * asserts salary input with ""
     */
    @Test 
    public void DummyPostCreate_6_1(){
        Employee_Info person = new Employee_Info();
        person.set_name("Ostra");
        person.set_age("13");
        person.set_picture("https://");
        person.set_salary("''");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body("name", equalTo("Ostra"))
             .and().body("age", equalTo("13"))
             .and().body("profile_picture", equalTo(null))
             .and().body("salary", equalTo("''"));

    }
         /**
     * asserts salary input with null
     */
    @Test 
    public void DummyPostCreate_6_2(){
        Employee_Info person = new Employee_Info();
        person.set_name("queque");
        person.set_age("13");
        person.set_picture("https://");
        person.set_salary(null);


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
             body(containsString("error"));

    }
    /**
     * asserts chars in salary
     */
    @Test 
    public void DummyPostCreate_7(){
        Employee_Info person = new Employee_Info();
        person.set_name("Spl");
        person.set_salary("Souls");
        person.set_age("13");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("Spl"))
            .and().body("age", equalTo("13"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("Souls"));

    }
    /**
     * confirms previous test
     */
    @Test 
    public void DummyPostCreate_7_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("96985");



        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("employee_name", equalTo("Spl"))
            .and().body("employee_age", equalTo("13"))
            .and().body("profile_picture", equalTo(null))
            .and().body("employee_salary", equalTo("0"));

    }
        /**
     * asserts chars in age
     */
    @Test 
    public void DummyPostCreate_8(){
        Employee_Info person = new Employee_Info();
        person.set_name("rgfvdregf");
        person.set_salary("12233");
        person.set_age("Crist");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("rgfvdregf"))
            .and().body("age", equalTo("Crist"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("12233"));

    }
    /**
     * confirms previous test
     */
    @Test 
    public void DummyPostCreate_8_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("97046");



        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("employee_name", equalTo("Troelff"))
            .and().body("employee_age", equalTo("0"))
            .and().body("profile_picture", equalTo(null))
            .and().body("employee_salary", equalTo("12233"));

    }
    /**
     * asserts chars in age
     */
    @Test 
    public void DummyPostCreate_8_2(){
        Employee_Info person = new Employee_Info();
        person.set_name("ergregr");
        person.set_salary("12233");
        person.set_age("Cr323ist1223");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("ergregr"))
            .and().body("age", equalTo("Cr323ist1223"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("12233"));

    }
    /**
     * confirms previous test
     */
    @Test 
    public void DummyPostCreate_8_3(){
        Employee_Info person = new Employee_Info();
        person.set_id("98164");



        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("employee_name", equalTo("ergregr"))
            .and().body("employee_age", equalTo("0"))
            .and().body("profile_picture", equalTo(null))
            .and().body("employee_salary", equalTo("12233"));

    }
    /**
     * Asserts wrong URL
     */
    @Test 
    public void DummyPostCreate_9(){
        Employee_Info person = new Employee_Info();
        person.set_id("97046");
        person.set_name("Thorfinn");
        person.set_age("43434");
        person.set_salary("95457046");
        person.set_picture("Ht");
        person.set_rng("createz");

        

        new utils().OpsSimplePOST(person).then().
        assertThat().
            statusCode(404).and().
            body(containsString("error"));

    }
    /**
     * Wrong body structure missing {}
     */
    @Test 
    public void DummyPostCreate_10(){
        Employee_Info person = new Employee_Info();
        person.set_id("97046");
        person.set_name("Thorfinn");
        person.set_age("43434");
        person.set_salary("95457046");
        person.set_picture("Ht");
        person.set_rng("createz");

        

        new utils().OpsSimplePOST(person).then().
        assertThat().
            statusCode(404).and().
            body(containsString("error"));

    }
    
     /**
     * Asserts int type in name
     */
    @Test 
    public void DummyPostCreate_11(){
        Employee_Info person = new Employee_Info();
        person.set_id("9638652");
        person.set_name_int(8765);
        person.set_age("43434");
        person.set_salary("30 in dog years");
        person.set_picture("Ht");

        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
        statusCode(200).and().
            body("name", equalTo("8765"))
            .and().body("age", equalTo("43434"))
            .and().body("id", not(equalTo("9638652")))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("30 in dog years"));
    }
    @Test
    public void DummyPostCreate_11_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("97125");//id returned from previous test

        new utils().GETOpsBodyParams(person).then().
        assertThat().
        statusCode(200).and().
        body("employee_name", equalTo("8765"))
        .and().body("employee_age", equalTo("43434"))
        .and().body("profile_picture", equalTo(null))
        .and().body("employee_salary", equalTo("30"));
    }
    /**
     * Asserts bool type in name
     */
    @Test 
    public void DummyPostCreate_12(){
        Employee_Info person = new Employee_Info();
        person.set_name_bool(false);
        person.set_salary("12233");
        person.set_age("Crist");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("false"))
            .and().body("age", equalTo("Crist"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("12233"));

    }
       /**
     * Asserts bool type in salary
     */
    @Test 
    public void DummyPostCreate_13(){
        Employee_Info person = new Employee_Info();
        person.set_name("wefevd");
        person.set_salary_bool(false);
        person.set_age("68514");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("wefevd"))
            .and().body("age", equalTo("68514"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("false"));

    }
    /**
     * Asserts bool type in age
     */
    @Test 
    public void DummyPostCreate_14(){
        Employee_Info person = new Employee_Info();
        person.set_name("wefvcd");
        person.set_salary("786");
        person.set_age_bool(false);
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("wefvcd"))
            .and().body("age", equalTo("false"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("786"));

    }
    /**
     * Asserts list type in name TODO
     * @param List 
     */
    @Test 
    public void DummyPostCreate_15(){
        Employee_Info person = new Employee_Info();
     //   person.set_name_array([73,42]);
        person.set_salary("786");
        person.set_age_bool(false);
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("wefvcd"))
            .and().body("age", equalTo("false"))
            .and().body("profile_picture", equalTo(null))
            .and().body("salary", equalTo("786"));

    }
    /**
     * Asserts Object type in name TODO
     *  
     */
    @Test 
    public void DummyPostCreate_16(){
        Employee_Info person = new Employee_Info();
        //person.set_name_object();
        person.set_salary("786");
        person.set_age_bool(false);
        person.set_picture("https://");


        // new utils(). .then().
        // assertThat().
        //     statusCode(200).and().
        //     body("name", equalTo("wefvcd"))
        //     .and().body("age", equalTo("false"))
        //     .and().body("profile_picture", equalTo(null))
        //     .and().body("salary", equalTo("786"));

    }
     /**
     * Asserts null type in name
     * 
     */
    @Test 
    public void DummyPostCreate_17(){
        Employee_Info person = new Employee_Info();
        person.set_name(null);
        person.set_salary("786");
        person.set_age("thr12rfr2");
        person.set_picture("https://");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body(containsString("error"));

    }
    
    /**
     * Asserts wrong method error when create into post
     * 
     */
    @Test 
    public void DummyPostCreate_18(){
        Employee_Info person = new Employee_Info();
        person.set_name("fpaa95");
        person.set_salary("786");
        person.set_age("3232");
        person.set_picture("https://");

        
        new utils().WrongGetMethod(person).then().
        assertThat().
            statusCode(405).and().
            body(containsString("Method not allowed"));

    }
    /**
     * Asserts creation of profile pictue
     * 
     */
    @Test 
    public void DummyPostCreate_19(){
        Employee_Info person = new Employee_Info();
        person.set_name("MarcelineQueen");
        person.set_salary("-786");
        person.set_age("-3232");
        person.set_picture("https://");

        
        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("name", equalTo("MarcelineQueen")).and().
            body("salary", equalTo("-786")).and().
            body("age", equalTo("-3232")).and().
            body("profile_picture",equalTo("https://"));

    }
    /**
     * confirms previous test
     */
    @Test 
    public void DummyPostCreate_19_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("98204");



        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200).and().
            body("employee_name", equalTo("MarcelineQueen"))
            .and().body("employee_age", equalTo("-3232"))
            .and().body("profile_picture", equalTo(null))
            .and().body("employee_salary", equalTo("-786"));

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

        String id_string = new utils().PUTOpsWithBodyAndPathParams(person).
        then().
        assertThat().
                statusCode(200).and().
                body("name", equalTo("Jasus"))
                .and().body("salary", equalTo("-99"))
                .and().body("age", equalTo("-30"))
                .and().body("profile_image", equalTo(""))
                .extract().jsonPath().getJsonObject("id");

            
                person.set_id(id_string);
                String id = person.getId();
                System.out.println("Id of employee created: "+id);
    }




}