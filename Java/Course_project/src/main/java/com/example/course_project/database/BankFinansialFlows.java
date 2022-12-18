package com.example.course_project.database;

public class BankFinansialFlows {

    public String fin_date;
    public double bank_own_funds;
    public double bank_borrowed_funds;
    public double bank_reserve_funds;
    public double bank_refinancing_rate;
    public double central_bank_refinancing_rate;
    public double bank_monthly_expected_income;
    public double product_minimum_rating_access;
    public double bank_monthly_expected_costs;

    public BankFinansialFlows(String fin_date, double bank_own_funds, double bank_borrowed_funds,
                        double bank_reserve_funds, double bank_refinancing_rate, double central_bank_refinancing_rate,
                              double bank_monthly_expected_income, double product_minimum_rating_access, double bank_monthly_expected_costs){
        this.fin_date = fin_date;
        this.bank_own_funds = bank_own_funds;
        this.bank_borrowed_funds = bank_borrowed_funds;
        this.bank_reserve_funds = bank_reserve_funds;
        this.bank_refinancing_rate = bank_refinancing_rate;
        this.central_bank_refinancing_rate = central_bank_refinancing_rate;
        this.bank_monthly_expected_income = bank_monthly_expected_income;
        this.product_minimum_rating_access = product_minimum_rating_access;
        this.bank_monthly_expected_costs = bank_monthly_expected_costs;
    }

    @Override
    public String toString() {
        return fin_date +
                "," + bank_own_funds +
                "," + bank_borrowed_funds +
                "," + bank_reserve_funds +
                "," + bank_refinancing_rate +
                "," + central_bank_refinancing_rate +
                "," + bank_monthly_expected_income +
                "," + product_minimum_rating_access +
                "," + bank_monthly_expected_costs +
                ';';
    }
}
