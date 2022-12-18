package com.example.course_project.database;

import java.sql.SQLException;

import static com.example.course_project.database.QueriesSQL.getFinFlows;
import static com.example.course_project.database.QueriesSQL.getLoanProducts;

public class ScoringPersonData {
    public int client_id;
    public String client_name;
    public String client_surname;
    public String client_patronymic;
    public String client_date_of_birth;

    public double client_total_debt;
    public double client_monthly_income;
    public double client_monthly_loan_payment;
    public int client_number_of_repaid_loans;
    public int client_total_number_of_loans;
    public double client_value_of_collateral;
    public int client_number_of_overdue_payments;

    public ScoringPersonData(int client_id, String client_name, String client_surname, String client_patronymic, String client_date_of_birth, double client_total_debt, double client_monthly_income, double client_monthly_loan_payment, int client_number_of_repaid_loans, int client_total_number_of_loans, double client_value_of_collateral, int client_number_of_overdue_payments) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.client_patronymic = client_patronymic;
        this.client_date_of_birth = client_date_of_birth;
        this.client_total_debt = client_total_debt;
        this.client_monthly_income = client_monthly_income;
        this.client_monthly_loan_payment = client_monthly_loan_payment;
        this.client_number_of_repaid_loans = client_number_of_repaid_loans;
        this.client_total_number_of_loans = client_total_number_of_loans;
        this.client_value_of_collateral = client_value_of_collateral;
        this.client_number_of_overdue_payments = client_number_of_overdue_payments;
    }

    @Override
    public String toString() {
        return client_id +
                "," + client_name +
                "," + client_surname +
                "," + client_patronymic +
                "," + client_date_of_birth +
                "," + client_total_debt +
                "," + client_monthly_income +
                "," + client_monthly_loan_payment +
                "," + client_number_of_repaid_loans +
                "," + client_total_number_of_loans +
                "," + client_value_of_collateral +
                "," + client_number_of_overdue_payments +
                ';';
    }

    public static String getScoring(String credit_order) throws SQLException {
        double total_score = 0.0;

        String client = QueriesSQL.getClientScoringData(credit_order);
        String loan_products = String.valueOf(getLoanProducts());
        String fin_flows = String.valueOf(getFinFlows());

        client = client.replaceAll(";, ", ",");

        String[] client_splited = client.split(",");

        if (Double.parseDouble(client_splited[6]) < Double.parseDouble(client_splited[12])) {
            total_score += 1.5;}
        if (Integer.parseInt(client_splited[8]) > 0) {
            total_score += Integer.parseInt(client_splited[8]) * 1.5;}
        if (Double.parseDouble(client_splited[12]) > 1500.0) {
            total_score += 0.001 * Double.parseDouble(client_splited[12]);}
        if (Double.parseDouble(client_splited[10]) > 0.7 * Double.parseDouble(client_splited[12])) {
            total_score += 0.003 * Double.parseDouble(client_splited[10]);}
        if (Integer.parseInt(client_splited[11].substring(0, client_splited[11].length()-1)) > 2) {
            total_score -= Integer.parseInt(client_splited[11]);}
        if (Double.parseDouble(client_splited[13]) < Double.parseDouble(client_splited[12]) * 12) {
            total_score += Double.parseDouble(client_splited[12]) * 12/Double.parseDouble(client_splited[13]);}
        if (client_splited[15].equals("car") || client_splited[15].equals("flat")) {
            total_score += 20.0;}
        if (Double.parseDouble(client_splited[14]) > 10.0 && Double.parseDouble(client_splited[14]) < 20.0) {
            total_score += 1.5 * (Double.parseDouble(client_splited[14]) - 10);}
        if (Integer.parseInt(client_splited[4].substring(0, 4)) > 2003) {
            total_score += 10.0;}

        loan_products = loan_products.substring(1, loan_products.length() - 1);
        loan_products = loan_products.replaceAll(";, ", ";");
        String[] loan_products_splited = loan_products.split(";");

        String best_product_id = "";
        Double product_persent = 10000.0;

        for (int i = 0;  i < loan_products_splited.length; i++){
            String[] loan_products_args = loan_products_splited[i].split(",");
            if (Double.parseDouble(loan_products_args[1]) < Double.parseDouble(client_splited[13]) &&

                    Double.parseDouble(loan_products_args[2]) > Double.parseDouble(client_splited[13]) &&
                    Double.parseDouble(loan_products_args[3]) < Double.parseDouble(client_splited[14]) &&
                    Double.parseDouble(loan_products_args[7]) < total_score &&

                    (loan_products_args[8].equals(client_splited[15]) || loan_products_args[8].equals("unknow") &&
                            product_persent > Double.parseDouble(loan_products_args[3]))
            ) {
                best_product_id = loan_products_args[0];
                product_persent = ((Double.parseDouble(loan_products_args[4]) - Double.parseDouble(loan_products_args[3]))
                        / (0.05 * total_score)) + Double.parseDouble(loan_products_args[3]);
            }
        }

        fin_flows = fin_flows.substring(1, fin_flows.length() - 1);
        String[] fin_flows_args = fin_flows.split(",");

        String result = "";

        if (!product_persent.equals(10000.0) && total_score > 30.0 && (Double.parseDouble(fin_flows_args[1]) - Double.parseDouble(fin_flows_args[2])
            - Double.parseDouble(fin_flows_args[3])) > Double.parseDouble(client_splited[13])) {
            result = client_splited[2] + " " + client_splited[1] + " " + client_splited[3]
                    + " с номером = " + client_splited[0] + " Рекомендуется к кредитованию кредитом с id = " + best_product_id
                    + " по ставке: " + String.format("%.3f",product_persent) + " На сумму " + client_splited[13];
        }
        else {
            result = client_splited[2] + " " + client_splited[1] + " " + client_splited[3]
                    + " с номером = " + client_splited[0] + " Не рекомендуется к кредитованию";
        }

        return result;
    }
}
