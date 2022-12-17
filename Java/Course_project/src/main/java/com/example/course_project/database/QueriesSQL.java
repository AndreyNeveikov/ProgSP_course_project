package com.example.course_project.database;

import java.sql.*;
import java.util.ArrayList;

public class QueriesSQL {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;

    public static final String SELECT_ALL_LOAN_PRODUCTS_QUERY = "SELECT * FROM loan_product";

    public static final String SELECT_ALL_CLIENTS_QUERY = "SELECT * FROM clients_personal_data";


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
    public static ArrayList<LoanProducts> getLoanProducts() throws SQLException {
        ArrayList<LoanProducts> productList = new ArrayList<>();

        ResultSet resSet = statement.executeQuery(SELECT_ALL_LOAN_PRODUCTS_QUERY);

        int product_id;
        double product_minimum_amount;
        double product_maximum_amount;
        double product_minimum_percent;
        double product_maximum_percent;
        String product_minimum_duration;
        String product_maximum_duration;
        double product_minimum_rating_access;
        String product_mandatory_goal;

        while (resSet.next()) {

            product_id = resSet.getInt("product_id");
            product_minimum_amount = resSet.getDouble("product_minimum_amount");
            product_maximum_amount = resSet.getDouble("product_maximum_amount");
            product_minimum_percent = resSet.getDouble("product_minimum_percent");
            product_maximum_percent = resSet.getDouble("product_maximum_percent");
            product_minimum_duration = resSet.getString("product_minimum_duration");
            product_maximum_duration = resSet.getString("product_maximum_duration");
            product_minimum_rating_access = resSet.getDouble("product_minimum_rating_access");
            product_mandatory_goal = resSet.getString("product_mandatory_goal");

            LoanProducts products = new LoanProducts(product_id, product_minimum_amount, product_maximum_amount,
                    product_minimum_percent, product_maximum_percent, product_minimum_duration, product_maximum_duration,
                    product_minimum_rating_access, product_mandatory_goal);

            productList.add(products);
        }
        System.out.println(productList);

        return productList;
    }

    public static int getUsers(String login, String password) throws SQLException {
        int user_status = 0;

        System.out.println("1");

        statement.executeUpdate("USE bank_credit_policy");
        ResultSet resSet = statement.executeQuery("SELECT employee_access_status FROM bank_employee_authorization "+
                "WHERE employee_login ='" + login +"' and employee_password = '" + password +"';");
        System.out.println("2");

        while(resSet.next()){
            user_status = resSet.getInt("employee_access_status");
        }
        return user_status;
    }
}


/*main(String[] args)*/
