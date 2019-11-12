package utils;

import io.restassured.response.ValidatableResponse;

public class Employee_Info {
    private String name;
    private Integer name_int;
    private String salary;
    private String age;
    private String picture;
    private String rng;
    private String id;

    public void set_name(String employee_name){
        this.name = employee_name;
    }
    public void set_name_int(int name_int) {
        this.name = String.valueOf(name_int);
	}
    public void set_salary(String employee_salary){
        this.salary = employee_salary;
    }
    public void set_age(String employee_age){
        this.age = employee_age;
    }
    public void set_id(ValidatableResponse validatableResponse){
        this.id =  validatableResponse.toString();
    }
    public void set_picture(String picture){
        this.picture = picture;
    }
    public void set_rng(String rng){
        this.rng = rng;
    }
    public String getName () {
        return name;    
    }
    public Integer getName_int () {
        return name_int;    
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
        return picture;    
    }
    public String getRng () {
        return rng;    
    }
	public void set_id(String id) {
        this.id  = id;
	}

}