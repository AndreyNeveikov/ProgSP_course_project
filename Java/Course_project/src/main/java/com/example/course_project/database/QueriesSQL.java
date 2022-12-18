package com.example.course_project.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QueriesSQL {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;

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

    public static String getClientScoringData(String credit_order) throws SQLException {

        String[] credit_order_data = credit_order.split("/");

        ResultSet resSetClientPers = statement.executeQuery("SELECT client_id FROM clients_personal_data " +
                "WHERE client_passport_personal_number='" + credit_order_data[0] + "';");

        int client_found_id = 0;
        while(resSetClientPers.next()){
            client_found_id = resSetClientPers.getInt("client_id");
        }
        System.out.println(client_found_id);

        ResultSet resSetClientFin = statement.executeQuery("SELECT client_total_debt, client_monthly_income, " +
                "client_monthly_loan_payment, client_number_of_repaid_loans, " +
                " client_total_number_of_loans, client_value_of_collateral, client_number_of_overdue_payments, " +
                " clients_personal_data.client_id, client_name, client_surname, client_patronymic, client_date_of_birth " +
                "FROM fct_clients_financial_data " +
                "INNER JOIN clients_personal_data " +
                "ON clients_personal_data.client_id = fct_clients_financial_data.client_id " +
                "WHERE clients_personal_data.client_id='" + client_found_id + "'" +
                "ORDER BY client_loaned_date ASC " +
                "LIMIT 1;");


        double client_total_debt;
        double client_monthly_income;
        double client_monthly_loan_payment;
        int client_number_of_repaid_loans;
        int client_total_number_of_loans;
        double client_value_of_collateral;
        int client_number_of_overdue_payments;
        int client_id;
        String client_name;
        String client_surname;
        String client_patronymic;
        String client_date_of_birth;

        ArrayList<ScoringPersonData> scoringDataList = new ArrayList<>();

        while (resSetClientFin.next()) {

            client_total_debt = resSetClientFin.getDouble("client_total_debt");
            client_monthly_income = resSetClientFin.getDouble("client_monthly_income");
            client_monthly_loan_payment = resSetClientFin.getDouble("client_monthly_loan_payment");
            client_number_of_repaid_loans = resSetClientFin.getInt("client_number_of_repaid_loans");
            client_total_number_of_loans = resSetClientFin.getInt("client_total_number_of_loans");
            client_value_of_collateral = resSetClientFin.getDouble("client_value_of_collateral");
            client_number_of_overdue_payments = resSetClientFin.getInt("client_number_of_overdue_payments");
            client_id = resSetClientFin.getInt("clients_personal_data.client_id");
            client_name = resSetClientFin.getString("client_name");
            client_surname = resSetClientFin.getString("client_surname");
            client_patronymic = resSetClientFin.getString("client_patronymic");
            client_date_of_birth = String.valueOf(resSetClientFin.getDate("client_date_of_birth"));

            ScoringPersonData scoringPersonData = new ScoringPersonData(client_id, client_name, client_surname,
                    client_patronymic, client_date_of_birth, client_total_debt, client_monthly_income,
                    client_monthly_loan_payment, client_number_of_repaid_loans, client_total_number_of_loans,
                    client_value_of_collateral, client_number_of_overdue_payments);

            scoringDataList.add(scoringPersonData);
        }
        System.out.println(scoringDataList);
        return String.valueOf(scoringDataList).substring(1, String.valueOf(scoringDataList).length() - 1)
                + "," + credit_order_data[1] + "," + credit_order_data[2] + "," +
                credit_order_data[3] + "," + credit_order_data[4];
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

    public static void addClient(String args) throws SQLException {

        String[] client_data = args.split("/");

        statement.executeUpdate("USE bank_credit_policy");
        statement.executeUpdate("INSERT INTO clients_personal_data (" +
                "client_name, client_surname, client_patronymic, client_date_of_birth, client_passport_personal_number," +
                "client_passport_series, client_passport_number, client_status)" +
                "VALUES ('" + client_data[0] + "', '" + client_data[1] + "', '" + client_data[2] + "', '" + client_data[3] +
                "', '" + client_data[4] + "', '" + client_data[5] + "', '" + client_data[6] + "', '" + client_data[7] + "');");
    }

    public static void deleteClient(int id) throws SQLException {

        statement.executeUpdate("USE bank_credit_policy");
        statement.executeUpdate("DELETE FROM clients_personal_data WHERE client_id='" + id + "';");
    }

    public static ArrayList<Clients> findClient(String passport_id) throws SQLException {

        return queryClientsCommon("SELECT * FROM clients_personal_data " +
                "WHERE client_passport_personal_number='" + passport_id + "';");

    }

    public static void addCredit(String args) throws SQLException {

        String[] credit_data = args.split("/");
        System.out.println(Arrays.toString(credit_data));

        statement.executeUpdate("USE bank_credit_policy");
        statement.executeUpdate("INSERT INTO loan_product (product_minimum_amount, product_maximum_amount, product_minimum_percent," +
                " product_maximum_percent, product_minimum_duration, product_maximum_duration, product_minimum_rating_access," +
                " product_mandatory_goal)" +
                "VALUES ('" + credit_data[0] + "', '" + credit_data[1] + "', '" + credit_data[2] + "', '" + credit_data[3] +
                "', '" + credit_data[4] + "', '" + credit_data[5] + "', '" + credit_data[6] + "', '" + credit_data[7] + "');");
    }

    public static void deleteCredit(int id) throws SQLException {

        statement.executeUpdate("USE bank_credit_policy");
        statement.executeUpdate("DELETE FROM loan_product WHERE product_id='" + id + "';");
    }

    public static ArrayList<LoanProducts> findCredit(String product_id) throws SQLException {

        return queryCreditsCommon("SELECT * FROM loan_product " +
                "WHERE product_id='" + product_id + "';");
    }

    public static ArrayList<LoanProducts> queryCreditsCommon(String query) throws SQLException {
        ArrayList<LoanProducts> productList = new ArrayList<>();

        ResultSet resSet = statement.executeQuery(query);

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

    public static ArrayList<LoanProducts> getLoanProducts() throws SQLException {
        return queryCreditsCommon("SELECT * FROM loan_product;");
    }

    public static ArrayList<BankFinansialFlows> getFinFlows() throws SQLException {
        ArrayList<BankFinansialFlows> finFlow = new ArrayList<>();

        ResultSet resSet = statement.executeQuery("SELECT * FROM bank_financial_flows");

        String fin_date;
        double bank_own_funds;
        double bank_borrowed_funds;
        double bank_reserve_funds;
        double bank_refinancing_rate;
        double central_bank_refinancing_rate;
        double bank_monthly_expected_income;
        double bank_monthly_expected_costs;

        while (resSet.next()) {

            fin_date = resSet.getString("fin_date");
            bank_own_funds = resSet.getDouble("bank_own_funds");
            bank_borrowed_funds = resSet.getDouble("bank_borrowed_funds");
            bank_reserve_funds = resSet.getDouble("bank_reserve_funds");
            bank_refinancing_rate = resSet.getDouble("bank_refinancing_rate");
            central_bank_refinancing_rate = resSet.getDouble("central_bank_refinancing_rate");
            bank_monthly_expected_income = resSet.getDouble("bank_monthly_expected_income");
            bank_monthly_expected_costs = resSet.getDouble("bank_monthly_expected_costs");

            BankFinansialFlows finData = new BankFinansialFlows(fin_date, bank_own_funds, bank_borrowed_funds,
                    bank_reserve_funds, bank_refinancing_rate, central_bank_refinancing_rate,
                    bank_monthly_expected_income, bank_monthly_expected_costs);

            finFlow.add(finData);
        }
        System.out.println(finFlow);

        return finFlow;
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
