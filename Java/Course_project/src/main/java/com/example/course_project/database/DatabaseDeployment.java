package com.example.course_project.database;

import java.sql.*;

public class DatabaseDeployment {
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
        statement.executeUpdate("CREATE DATABASE bank_credit_policy");
        statement.executeUpdate("USE bank_credit_policy");

        statement.executeUpdate("CREATE TABLE clients_personal_data (" +
                "client_id                          int         auto_increment      primary key," +
                "client_name                        varchar(30) not null," +
                "client_surname                     varchar(30) not null," +
                "client_patronymic                  varchar(30) not null," +
                "client_date_of_birth               date        not null," +
                "client_passport_personal_number    varchar(14) not null," +
                "client_passport_series             varchar(2)  not null," +
                "client_passport_number             bigint(7)   not null," +
                "client_status                      varchar(15) not null)");

        statement.executeUpdate("CREATE TABLE clients_financial_data (" +
                "client_id                            int         primary key," +
                "client_loan_id                       varchar(15) not null, " +
                "client_total_debt                    double      not null," +
                "client_monthly_income                double      not null," +
                "client_monthly_loan_payment          double      not null," +
                "client_loan_interest                 double      not null," +
                "client_number_of_repaid_loans        int         not null," +
                "client_total_number_of_loans         int         not null," +
                "client_value_of_collateral           double      not null," +
                "client_number_of_overdue_payments    int         not null," +
                "client_risk_rate                     double      not null)");

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
                "financial_month                  date        primary key," +
                "bank_own_funds                   double      not null," +
                "bank_borrowed_funds              double      not null, " +
                "bank_reserve_funds               double      not null," +
                "bank_refinancing_rate            double      not null," +
                "central_bank_refinancing_rate    double      not null," +
                "bank_monthly_expected_income     double      not null," +
                "bank_monthly_expected_costs      double      not null)");

        DatabaseDeployment.disconnect();
    }
}
