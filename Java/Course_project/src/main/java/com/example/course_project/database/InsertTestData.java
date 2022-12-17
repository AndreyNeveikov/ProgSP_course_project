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
                "VALUES ('Maximov', 'Maxim', 'Maximovich', '2000-01-13', 'PB59V78CG3CGP9', 'MP', '1616889', 'new');");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('Antonov', 'Anton', 'Antonovich', '2000-09-30', 'PB59V73C9G54Y', 'MP', '4343634', 'active');");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('Semenov', 'Semen', 'Semenovich', '2001-01-01', 'PB461F3D51VDF', 'MP', '6235654', 'new');");

        ///////////////////////////////////////////////loan_product/////////////////////////////////////////////////////

        statement.executeUpdate("INSERT INTO loan_product (" +
                "product_id, product_minimum_amount, product_maximum_amount, product_minimum_percent, product_maximum_percent," +
                "product_minimum_duration, product_maximum_duration, product_minimum_rating_access, product_mandatory_goal)" +
                "VALUES ('1', '500.0', '10000.0', '3.0', '25.0', '1', '5', '25.0', 'car');");
        statement.executeUpdate("INSERT INTO loan_product (" +
                "product_id, product_minimum_amount, product_maximum_amount, product_minimum_percent, product_maximum_percent," +
                "product_minimum_duration, product_maximum_duration, product_minimum_rating_access, product_mandatory_goal)" +
                "VALUES ('2', '15000.0', '500000.0', '7.0', '25.0', '5', '50', '50.0', 'flat');");
        statement.executeUpdate("INSERT INTO loan_product (" +
                "product_id, product_minimum_amount, product_maximum_amount, product_minimum_percent, product_maximum_percent," +
                "product_minimum_duration, product_maximum_duration, product_minimum_rating_access, product_mandatory_goal)" +
                "VALUES ('3', '1500.0', '20000.0', '5.0', '17.0', '1', '10', '10.0', 'unknown');");
        statement.executeUpdate("INSERT INTO loan_product (" +
                "product_id, product_minimum_amount, product_maximum_amount, product_minimum_percent, product_maximum_percent," +
                "product_minimum_duration, product_maximum_duration, product_minimum_rating_access, product_mandatory_goal)" +
                "VALUES ('4', '100.0', '5000.0', '3.5', '12.7', '1', '3', '30.0', 'unknown');");
        statement.executeUpdate("INSERT INTO loan_product (" +
                "product_id, product_minimum_amount, product_maximum_amount, product_minimum_percent, product_maximum_percent," +
                "product_minimum_duration, product_maximum_duration, product_minimum_rating_access, product_mandatory_goal)" +
                "VALUES ('5', '50000.0', '900000.0', '6.0', '16.0', '3', '30', '80.0', 'flat');");
        statement.executeUpdate("INSERT INTO loan_product (" +
                "product_id, product_minimum_amount, product_maximum_amount, product_minimum_percent, product_maximum_percent," +
                "product_minimum_duration, product_maximum_duration, product_minimum_rating_access, product_mandatory_goal)" +
                "VALUES ('6', '3000.0', '60000.0', '7.0', '12.0', '1', '8', '38.0', 'car');");

        //////////////////////////////////////////bank_employee_authorization///////////////////////////////////////////

        statement.executeUpdate("INSERT INTO bank_employee_authorization (" +
                "employee_login, employee_password, employee_access_status, employee_name, employee_surname," +
                "employee_patronymic, employee_job_title)" +
                "VALUES ('admin', 'admin', '1', 'Alexander', 'Alexandrov', 'Alexandrovich', 'Exclusive admin');");
        statement.executeUpdate("INSERT INTO bank_employee_authorization (" +
                "employee_login, employee_password, employee_access_status, employee_name, employee_surname," +
                "employee_patronymic, employee_job_title)" +
                "VALUES ('manager', 'manager', '3', 'Roman', 'Romanov', 'Romanovich', 'Head of managers');");
        statement.executeUpdate("INSERT INTO bank_employee_authorization (" +
                "employee_login, employee_password, employee_access_status, employee_name, employee_surname," +
                "employee_patronymic, employee_job_title)" +
                "VALUES ('saler', 'saler', '2', 'Igor', 'Igorev', 'Igorevich', 'Junior saler');");
        statement.executeUpdate("INSERT INTO bank_employee_authorization (" +
                "employee_login, employee_password, employee_access_status, employee_name, employee_surname," +
                "employee_patronymic, employee_job_title)" +
                "VALUES ('saler2', 'saler2', '2', 'Artem', 'Artemov', 'Artemovich', 'Senior saler');");

        //////////////////////////////////////////bank_financial_flows//////////////////////////////////////////////////

        statement.executeUpdate("INSERT INTO bank_financial_flows (" +
                "fin_date, bank_own_funds, bank_borrowed_funds, bank_reserve_funds, bank_refinancing_rate," +
                "central_bank_refinancing_rate, bank_monthly_expected_income, bank_monthly_expected_costs)" +
                "VALUES ('2022-12-11', '10000000.0', '300000.0', '150000.0', '8.5', '7.0', '0.0', '157000.0');");

        //////////////////////////////////////fct_clients_financial_data////////////////////////////////////////////////

        statement.executeUpdate("INSERT INTO fct_clients_financial_data (" +
                "client_id, client_loan_id, employee_loaned_id, client_loaned_date, client_total_debt," +
                "client_monthly_income, client_monthly_loan_payment, client_loan_interest, client_number_of_repaid_loans," +
                "client_total_number_of_loans, client_value_of_collateral, client_number_of_overdue_payments, client_risk_rate)" +
                "VALUES ('1', '1', '1', '2022-12-11', '4400.00', '1300.00', '100.00', '15.0', '0', '1', '7500.00', '0', '50');");
        statement.executeUpdate("INSERT INTO fct_clients_financial_data (" +
                "client_id, client_loan_id, employee_loaned_id, client_loaned_date, client_total_debt," +
                "client_monthly_income, client_monthly_loan_payment, client_loan_interest, client_number_of_repaid_loans," +
                "client_total_number_of_loans, client_value_of_collateral, client_number_of_overdue_payments, client_risk_rate)" +
                "VALUES ('2', '5', '4', '2022-12-11', '77000.00', '2500.00', '1000.00', '11.0', '0', '1', '45000.00', '0', '90');");

        InsertTestData.disconnect();
    }
}

