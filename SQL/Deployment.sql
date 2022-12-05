CREATE DATABASE bank_credit_policy;
USE bank_credit_policy;

CREATE TABLE clients_personal_data (
                client_id                          int         auto_increment      primary key,
                client_name                        varchar(30) not null,
                client_surname                     varchar(30) not null,
                client_patronymic                  varchar(30) not null,
                client_date_of_birth               date         not null,
                client_passport_personal_number    varchar(14) not null,
                client_passport_series             varchar(2)  not null,
                client_passport_number             bigint(7)   not null,
                client_status                      varchar(15) not null);


CREATE TABLE fct_clients_financial_data (
                client_id                            int    primary key,
                client_loan_id                       int    not null, 
                employee_loaned_id                   int    not null, 
                client_loaned_date                   date    not null,
                client_total_debt                    double not null,
                client_monthly_income                double not null,
                client_monthly_loan_payment          double not null,
                client_loan_interest                 double not null,
                client_number_of_repaid_loans        int    not null,
                client_total_number_of_loans         int    not null,
                client_value_of_collateral           double not null,
                client_number_of_overdue_payments    int    not null,
                client_risk_rate                     double not null);

CREATE TABLE loan_product (
                product_id                       int         primary key,
                product_minimum_amount           double      not null, 
                product_maximum_amount           double      not null,
                product_minimum_percent          double      not null,
                product_maximum_percent          double      not null,
                product_minimum_duration         varchar(15) not null,
                product_maximum_duration         varchar(15) not null,
                product_maximum_rating_access    double      not null,
                product_mandatory_goal           varchar(15) not null);

CREATE TABLE bank_employee_authorization (
                employee_id               int         primary key,
                employee_login            varchar(30) not null, 
                employee_password         varchar(30) not null,
                employee_access_status    int         not null,
                employee_name             varchar(30) not null,
                employee_surname          varchar(30) not null,
                employee_patronymic       varchar(30) not null,
                employee_job_title        varchar(30) not null);

CREATE TABLE bank_financial_flows (
                fin_date                         date   primary key,
                bank_own_funds                   double not null,
                bank_borrowed_funds              double not null,
                bank_reserve_funds               double not null,
                bank_refinancing_rate            double not null,
                central_bank_refinancing_rate    double not null,
                bank_monthly_expected_income     double not null,
                bank_monthly_expected_costs      double not null);


ALTER TABLE fct_clients_financial_data
ADD CONSTRAINT fk_t_fct_clients_fin_data_t_clients_pers_data 
FOREIGN KEY (client_id)
        REFERENCES clients_personal_data (client_id);

ALTER TABLE fct_clients_financial_data
ADD CONSTRAINT fk_t_fct_clients_fin_data_t_loan_product 
FOREIGN KEY (client_loan_id)    
        REFERENCES loan_product (product_id);

ALTER TABLE fct_clients_financial_data
ADD CONSTRAINT fk_t_fct_clients_fin_data_t_bank_empl_auth 
FOREIGN KEY (employee_loaned_id)
        REFERENCES bank_employee_authorization (employee_id);

ALTER TABLE fct_clients_financial_data
ADD CONSTRAINT fk_t_fct_clients_fin_data_t_bank_fin_flows 
FOREIGN KEY (client_loaned_date)
            REFERENCES bank_financial_flows (fin_date);
