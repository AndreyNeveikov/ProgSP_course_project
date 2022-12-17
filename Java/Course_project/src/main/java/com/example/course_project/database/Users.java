package com.example.course_project.database;

public class Users {

    public int employee_id;
    public String employee_login;
    public String employee_password;
    public int employee_access_status;
    public String employee_name;
    public String employee_surname;
    public String employee_patronymic;
    public String employee_job_title;


    public Users(int employee_id, String employee_login, String employee_password, int employee_access_status,
                 String employee_name, String employee_surname, String employee_patronymic, String employee_job_title){
        this.employee_id = employee_id;
        this.employee_login = employee_login;
        this.employee_password = employee_password;
        this.employee_access_status = employee_access_status;
        this.employee_name = employee_name;
        this.employee_surname = employee_surname;
        this.employee_patronymic = employee_patronymic;
        this.employee_job_title = employee_job_title;
    }

    @Override
    public String toString() {
        return "Users{" +
                "employee_id=" + employee_id +
                ", employee_login='" + employee_login + '\'' +
                ", employee_password='" + employee_password + '\'' +
                ", employee_access_status=" + employee_access_status +
                ", employee_name='" + employee_name + '\'' +
                ", employee_surname='" + employee_surname + '\'' +
                ", employee_patronymic='" + employee_patronymic + '\'' +
                ", employee_job_title='" + employee_job_title + '\'' +
                '}';
    }
}
