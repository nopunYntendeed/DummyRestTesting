package utils;


import java.util.ArrayList;
import java.util.List;

import io.restassured.response.ValidatableResponse;

public class Employee_Info {
    private Object name;
    private Integer name_int;
    private Boolean name_bool;
    private Object name_obj;
    private ArrayList name_array;
    private String salary;
    private String age;
    private String profile_picture;
    private String rng;
    private String id;

    public void set_name(String employee_name) {
        this.name = employee_name;
    }

    public void set_name_int(int name_int) {
        this.name = String.valueOf(name_int);
    }

    public void set_name_bool(Boolean name_bool) {
        this.name = String.valueOf(name_bool);
    }

    public void set_name_array(List<Object> name_array) {
        this.name = name_array;
    }
    public void set_name_obj(Object set_name) {
        this.name = name_obj;
	}
    
    public void set_salary(String employee_salary) {
        this.salary = employee_salary;
    }
    public void set_salary_bool(Boolean salary_bool){
        this.salary = String.valueOf(salary_bool);
    }
    public void set_salary_int(int salary_int){
        this.salary = String.valueOf(salary_int);
    }
    public void set_age(String employee_age){
        this.age = employee_age;
    }
    public void set_age_int(int age_int){
        this.age = String.valueOf(age_int);
    }
    public void set_age_bool(Boolean age_bool){
        this.age = String.valueOf(age_bool);
    }
    public void set_id(ValidatableResponse validatableResponse){
        this.id =  validatableResponse.toString();
    }
    public void set_picture(String picture){
        this.profile_picture = picture;
    }
    public void set_rng(String rng){
        this.rng = rng;
    }
    public String getName () {
        return (String) name;
    }
    public Integer getName_int () {
        return name_int;    
    }
    public Boolean getName_bool () {
        return name_bool;    
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
    public String getPicture () {
        return profile_picture;    
    }
    public String getRng () {
        return rng;    
    }
	public void set_id(String id) {
        this.id  = id;
	}





}