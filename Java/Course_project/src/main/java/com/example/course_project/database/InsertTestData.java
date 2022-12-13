package com.example.course_project.database;

import java.sql.*;

public class InsertTestData {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        statement.executeUpdate("USE bank_credit_policy");

        //////////////////////////////////////////clients_personal_data/////////////////////////////////////////////////
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('Andrey', 'Neveykov', 'Sergeevich', '2002-06-12', 'PB59V78D81CG3C', 'MP', '1234567', 'active');");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('Petrov', 'Petr', 'Petrovich', '2000-01-13', 'PB59V78CG3CGP9', 'MP', '1616889', 'new');");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('Antonov', 'Anton', 'Antonovich', '2000-09-30', 'PB59V73C9G54Y', 'MP', '4343634', 'active');");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('Semenov', 'Semen', 'Semenovich', '2001-01-01', 'PB461F3D51VDF', 'MP', '6235654', 'new');");


        //////////////////////////////////////fct_clients_financial_data////////////////////////////////////////////////

        statement.executeUpdate("INSERT INTO fct_clients_financial_data (" +
                "client_id, client_loan_id, employee_loaned_id, client_loaned_date, client_total_debt," +
                "client_monthly_income, client_monthly_loan_payment, client_loan_interest, client_number_of_repaid_loans," +
                "client_total_number_of_loans, client_value_of_collateral, client_number_of_overdue_payments, client_risk_rate)" +
                "VALUES ('1', '1', '1', '2020-02-20', '5400.00', '1300.00', '100.00', '15.0', '0', '1', '7500.00', '0', '50');");
/*

        statement.executeUpdate("CREATE TABLE fct_clients_financial_data (" +
                "client_id                            int    primary key," +
                "client_loan_id                       int    not null, " +
                "employee_loaned_id                   int    not null, " +
                "client_loaned_date                   date   not null, " +
                "client_total_debt                    double not null," +
                "client_monthly_income                double not null," +
                "client_monthly_loan_payment          double not null," +
                "client_loan_interest                 double not null," +
                "client_number_of_repaid_loans        int    not null," +
                "client_total_number_of_loans         int    not null," +
                "client_value_of_collateral           double not null," +
                "client_number_of_overdue_payments    int    not null," +
                "client_risk_rate                     double not null)");

        statement.executeUpdate("CREATE TABLE loan_product (" +
                "product_id                       int         primary key," +
                "product_minimum_amount           double      not null, " +
                "product_maximum_amount           double      not null," +
                "product_minimum_percent          double      not null," +
                "product_maximum_percent          double      not null," +
                "product_minimum_duration         varchar(15) not null," +
                "product_maximum_duration         varchar(15) not null," +
                "product_maximum_rating_access    double      not null," +
                "product_mandatory_goal           varchar(15) not null)");

        statement.executeUpdate("CREATE TABLE bank_employee_authorization (" +
                "employee_id               int         primary key," +
                "employee_login            varchar(30) not null, " +
                "employee_password         varchar(30) not null," +
                "employee_access_status    int         not null," +
                "employee_name             varchar(30) not null," +
                "employee_surname          varchar(30) not null," +
                "employee_patronymic       varchar(30) not null," +
                "employee_job_title        varchar(30) not null)");

        statement.executeUpdate("CREATE TABLE bank_financial_flows (" +
                "fin_date                         date        primary key," +
                "bank_own_funds                   double      not null," +
                "bank_borrowed_funds              double      not null, " +
                "bank_reserve_funds               double      not null," +
                "bank_refinancing_rate            double      not null," +
                "central_bank_refinancing_rate    double      not null," +
                "bank_monthly_expected_income     double      not null," +
                "bank_monthly_expected_costs      double      not null)");

*/
        InsertTestData.disconnect();
    }
}

