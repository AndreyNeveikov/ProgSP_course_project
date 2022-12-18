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

    public static ArrayList<Clients> queryClientsCommon(String query) throws SQLException {
        ArrayList<Clients> clientsList = new ArrayList<>();

        ResultSet resSet = statement.executeQuery(query);

        int client_id;
        String client_name;
        String client_surname;
        String client_patronymic;
        String client_date_of_birth;
        String client_passport_personal_number;
        String client_passport_series;
        String client_passport_number;
        String client_status;


        while (resSet.next()) {

            client_id = resSet.getInt("client_id");
            client_name = resSet.getString("client_name");
            client_surname = resSet.getString("client_surname");
            client_patronymic = resSet.getString("client_patronymic");
            client_date_of_birth = String.valueOf(resSet.getDate("client_date_of_birth"));
            client_passport_personal_number = resSet.getString("client_passport_personal_number");
            client_passport_series = resSet.getString("client_passport_series");
            client_passport_number = resSet.getString("client_passport_number");
            client_status = resSet.getString("client_status");

            Clients clients = new Clients(client_id, client_name, client_surname,
                    client_patronymic, client_date_of_birth, client_passport_personal_number, client_passport_series,
                    client_passport_number, client_status);

            clientsList.add(clients);
        }
        System.out.println(clientsList);

        return clientsList;
    }

    public static ArrayList<Clients> getClients() throws SQLException {
        return queryClientsCommon(SELECT_ALL_CLIENTS_QUERY);
    }

    public static void deleteClient(int id) throws SQLException {

        statement.executeUpdate("USE bank_credit_policy");
        statement.executeUpdate("DELETE FROM clients_personal_data WHERE client_id='" + id + "';");
    }

    public static ArrayList<Clients> findClient(String passport_id) throws SQLException {

        return queryClientsCommon("SELECT * FROM clients_personal_data " +
                "WHERE client_passport_personal_number='" + passport_id + "';");
    }

    public static void addClient(String args) throws SQLException {

        System.out.println(args);
        String[] client_data = args.split("/");
        System.out.println(client_data[2]);
        statement.executeUpdate("USE bank_credit_policy");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('" + client_data[0] + "', '" + client_data[1] + "', '" + client_data[2] + "', '" + client_data[3] +
                "', '" + client_data[4] + "', '" + client_data[5] + "', '" + client_data[6] + "', '" + client_data[7] + "');");
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

    public static String getUsers(String login, String password) throws SQLException {
        String user_status = "0";

        statement.executeUpdate("USE bank_credit_policy");
        ResultSet resSet = statement.executeQuery("SELECT employee_access_status FROM bank_employee_authorization "+
                "WHERE employee_login ='" + login +"' and employee_password = '" + password +"';");

        while(resSet.next()){
            user_status = String.valueOf(resSet.getInt("employee_access_status"));
        }
        return user_status;
    }


}


/*main(String[] args)*/
