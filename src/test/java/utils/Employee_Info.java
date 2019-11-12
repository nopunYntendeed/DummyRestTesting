package utils;

public class Employee_Info {
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