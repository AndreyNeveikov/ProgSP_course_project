package com.example.course_project.database;

public class Clients {
    public int client_id;
    public String client_name;
    public String client_surname;
    public String client_patronymic;
    public String client_date_of_birth;
    public String client_passport_personal_number;
    public String client_passport_series;
    public String client_passport_number;
    public String client_status;

    public Clients(int client_id, String client_name, String client_surname, String client_patronymic,
                   String client_date_of_birth, String client_passport_personal_number,
                        String client_passport_series, String client_passport_number, String client_status){
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.client_patronymic = client_patronymic;
        this.client_date_of_birth = client_date_of_birth;
        this.client_passport_personal_number = client_passport_personal_number;
        this.client_passport_series = client_passport_series;
        this.client_passport_number = client_passport_number;
        this.client_status = client_status;
    }

    @Override
    public String toString() {
        return client_id +
                "," + client_name +
                "," + client_surname +
                "," + client_patronymic +
                "," + client_date_of_birth +
                "," + client_passport_personal_number +
                "," + client_passport_series +
                "," + client_passport_number +
                "," + client_status +
                ';';
    }
}
