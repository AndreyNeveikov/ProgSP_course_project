package com.example.course_project.database;

public class FctFinData {

    public int client_id;
    public int client_loan_id;
    public int employee_loaned_id;
    public String client_loaned_date;
    public double client_total_debt;
    public double client_monthly_income;
    public double client_monthly_loan_payment;
    public double client_loan_interest;
    public int client_number_of_repaid_loans;
    public int client_total_number_of_loans;
    public double client_value_of_collateral;
    public int client_number_of_overdue_payments;
    public double client_risk_rate;

    public FctFinData(int client_id, int client_loan_id, int employee_loaned_id, String client_loaned_date, double client_total_debt, double client_monthly_income, double client_monthly_loan_payment, double client_loan_interest, int client_number_of_repaid_loans, int client_total_number_of_loans, double client_value_of_collateral, int client_number_of_overdue_payments, double client_risk_rate) {
        this.client_id = client_id;
        this.client_loan_id = client_loan_id;
        this.employee_loaned_id = employee_loaned_id;
        this.client_loaned_date = client_loaned_date;
        this.client_total_debt = client_total_debt;
        this.client_monthly_income = client_monthly_income;
        this.client_monthly_loan_payment = client_monthly_loan_payment;
        this.client_loan_interest = client_loan_interest;
        this.client_number_of_repaid_loans = client_number_of_repaid_loans;
        this.client_total_number_of_loans = client_total_number_of_loans;
        this.client_value_of_collateral = client_value_of_collateral;
        this.client_number_of_overdue_payments = client_number_of_overdue_payments;
        this.client_risk_rate = client_risk_rate;
    }

    @Override
    public String toString() {
        return client_id +
            "," + client_loan_id +
            "," + employee_loaned_id +
            "," + client_loaned_date +
            "," + client_total_debt +
            "," + client_monthly_income +
            "," + client_monthly_loan_payment +
            "," + client_loan_interest +
            "," + client_number_of_repaid_loans +
            "," + client_total_number_of_loans +
            "," + client_value_of_collateral +
            "," + client_number_of_overdue_payments +
            "," + client_risk_rate +
            ';';
    }
}
