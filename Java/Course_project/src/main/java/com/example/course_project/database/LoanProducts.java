package com.example.course_project.database;

public class LoanProducts {
    public int product_id;
    public double product_minimum_amount;
    public double product_maximum_amount;
    public double product_minimum_percent;
    public double product_maximum_percent;
    public String product_minimum_duration;
    public String product_maximum_duration;
    public double product_minimum_rating_access;
    public String product_mandatory_goal;

    public LoanProducts(int product_id, double product_minimum_amount, double product_maximum_amount,
                        double product_minimum_percent, double product_maximum_percent, String product_minimum_duration,
                        String product_maximum_duration, double product_minimum_rating_access, String product_mandatory_goal){
        this.product_id = product_id;
        this.product_minimum_amount = product_minimum_amount;
        this.product_maximum_amount = product_maximum_amount;
        this.product_minimum_percent = product_minimum_percent;
        this.product_maximum_percent = product_maximum_percent;
        this.product_minimum_duration = product_minimum_duration;
        this.product_maximum_duration = product_maximum_duration;
        this.product_minimum_rating_access = product_minimum_rating_access;
        this.product_mandatory_goal = product_mandatory_goal;
    }

    @Override
    public String toString() {
        return "LoanProducts{" +
                "product_id=" + product_id +
                ", product_minimum_amount=" + product_minimum_amount +
                ", product_maximum_amount=" + product_maximum_amount +
                ", product_minimum_percent=" + product_minimum_percent +
                ", product_maximum_percent=" + product_maximum_percent +
                ", product_minimum_duration='" + product_minimum_duration + '\'' +
                ", product_maximum_duration='" + product_maximum_duration + '\'' +
                ", product_minimum_rating_access=" + product_minimum_rating_access +
                ", product_mandatory_goal='" + product_mandatory_goal + '\'' +
                '}';
    }
}
