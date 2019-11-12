

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

import utils.utils;
import utils.Employee_Info;


public class TestPut {

    @Test
    public void DummyPutUpdate() {
        Employee_Info person = new Employee_Info();
        person.set_name("Jasus");
        person.set_salary("-99");
        person.set_age("-30");
        person.set_id("95728");

        new utils().PUTOpsWithBodyAndPathParams(person).
        then().
        assertThat().
                statusCode(200).and().
                body("name", equalTo("Jasus"))
                .and().body("salary", equalTo("-99"))
                .and().body("age", equalTo("-30")).log().all()
                .and().body("profile_image", equalTo(""));
    }
    @Test 
    public void DummyPostCreate(){
        Employee_Info person = new Employee_Info();
        person.set_name("Truth");
        person.set_salary("-99");
        person.set_age("-73");


        new utils().POSTOpsWithBodyParams(person).then().
        assertThat().
             statusCode(200).and().
            body("name", equalTo("Truth"))
            .and().body("salary", equalTo("-99"))
            .and().body("age", equalTo("-73"));

    }
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
    @Test 
    public void DummyGetSingle_1_1(){
        Employee_Info person = new Employee_Info();
        person.set_id("2");

        new utils().GETOpsBodyParams(person).then().
        assertThat().
            statusCode(200)
            .and().body(containsString("false"));
    }

}