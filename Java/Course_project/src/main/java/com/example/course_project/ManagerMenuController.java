package com.example.course_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;


public class ManagerMenuController {
    @FXML
    private Button exit;

    @FXML
    private Button scoring_data;

    @FXML
    private Button move_back;

    @FXML
    private Button fin_data;

    @FXML
    private Button help;

    @FXML
    private Button credit_product_editor;

    @FXML
    private Button btnEditCredit;

    @FXML
    private Button infographics;

    @FXML
    private TextField bank_own_funds;

    @FXML
    private TextField bank_monthly_expected_income;

    @FXML
    private TextField bank_monthly_expected_costs;

    @FXML
    private TextField central_bank_refinancing_rate;

    @FXML
    private TextField bank_refinancing_rate;

    @FXML
    private TextField bank_reserve_funds;

    @FXML
    private TextField bank_borrowed_funds;

    @FXML
    private TextField product_id;

    @FXML
    private TextField product_minimum_duration;

    @FXML
    private TextField product_maximum_duration;

    @FXML
    private TextField product_minimum_rating_access;

    @FXML
    private TextField product_mandatory_goal;

    @FXML
    private TextField product_maximum_percent;

    @FXML
    private TextField product_minimum_percent;

    @FXML
    private TextField product_maximum_amount;

    @FXML
    private TextField product_minimum_amount;

    @FXML
    private TextField result_id_input;

    @FXML
    ListView<String> loan_products = new ListView<String>();

    @FXML
    ListView<String> scoring_results = new ListView<String>();

    int status = 3;

    @FXML
    protected void onExitButtonClick() {
        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();
            System.exit(0);
        });
    }

    @FXML
    protected void onScoringDataButtonClick() {
        scoring_data.setOnAction(event -> {
            scoring_data.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("scoring_data_viewer.fxml", (Stage) scoring_data.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onMoveBackButtonClick() {
        move_back.setOnAction(event -> {
            move_back.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("manager_menu.fxml", (Stage) move_back.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onCreditEditorButtonClick() {
        credit_product_editor.setOnAction(event -> {
            credit_product_editor.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("credit_editor.fxml", (Stage) credit_product_editor.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onDeleteCreditButtonClick() {
        if (!product_id.getText().trim().equals("")) {
            ClientCommonFuctions.clientServerDialog(status, 4, product_id.getText().trim());
        }
    }

    @FXML
    protected void onFindCreditButtonClick() {
        if (!product_id.getText().trim().equals("")) {
            String product = ClientCommonFuctions.clientServerDialog(status, 5,
                    product_id.getText().trim());

            product = product.substring(1, product.length() - 1);

            String[] splited_info = product.split(",");

            if (splited_info[0].equals("")) {
                product_id.setText("");
                product_minimum_amount.setText("");
                product_maximum_amount.setText("");
                product_minimum_percent.setText("");
                product_maximum_percent.setText("");
                product_minimum_duration.setText("");
                product_maximum_duration.setText("");
                product_minimum_rating_access.setText("");
                product_mandatory_goal.setText("");
            }
            else {
                product_id.setText(splited_info[0]);
                product_minimum_amount.setText(splited_info[1]);
                product_maximum_amount.setText(splited_info[2]);
                product_minimum_percent.setText(splited_info[3]);
                product_maximum_percent.setText(splited_info[4]);
                product_minimum_duration.setText(splited_info[5]);
                product_maximum_duration.setText(splited_info[6]);
                product_minimum_rating_access.setText(splited_info[7]);
                product_mandatory_goal.setText(splited_info[8]);
            }


        }

    }

    @FXML
    protected void onEditCreditProductsButtonClick() {
        btnEditCredit.setOnAction(event -> {
            btnEditCredit.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("lvl2_credit_editor.fxml", (Stage) btnEditCredit.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @FXML
    protected void onAddButtonClick() {
        if (!product_minimum_amount.getText().equals("") &&
                !product_maximum_amount.getText().equals("") &&
                !product_minimum_percent.getText().equals("") &&
                !product_maximum_percent.getText().equals("") &&
                !product_minimum_duration.getText().equals("") &&
                !product_maximum_duration.getText().equals("") &&
                !product_minimum_rating_access.getText().equals("") &&
                !product_mandatory_goal.getText().equals("")) {

            ClientCommonFuctions.clientServerDialog(status, 3,
                    product_minimum_amount.getText().trim() + "/" + product_maximum_amount.getText().trim() + "/" +
                            product_minimum_percent.getText().trim() + "/" + product_maximum_percent.getText().trim() + "/" +
                            product_minimum_duration.getText().trim() + "/" + product_maximum_duration.getText().trim() + "/" +
                            product_minimum_rating_access.getText().trim() + "/" + product_mandatory_goal.getText().trim() + "/");
        }
    }

    @FXML
    protected void onRefreshCreditProductsButtonClick() {
        loan_products.getItems().clear();
        String loan_products_list = ClientCommonFuctions.clientServerDialog(status, 2,
                "0");

        loan_products_list = loan_products_list.substring(1, loan_products_list.length() - 1);
        loan_products_list = loan_products_list.replaceAll(";, ", ";");
        loan_products_list = loan_products_list.replaceAll(",", "\t\t\t\t");
        String[] loan_products_splited = loan_products_list.split(";");

        String table_header = "ID продукта \t\t мин. сумма \t\t\t макс. сумма \t\t\t мин. %" +
                "\t\t\t макс. % \t\tмин. период \t\tмакс. период \t\t" +
                "мин. рейтинг \t\tцель кредитовния" ;
        loan_products.getItems().add(table_header);

        for (int i = 0;  i < loan_products_splited.length; i++){
            loan_products.getItems().add(loan_products_splited[i]);
        }
    }

    @FXML
    protected void onHelpButtonClick() {
        help.setOnAction(event -> {
            help.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("manager_help.fxml", (Stage) help.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onFinDataButtonClick() {
        fin_data.setOnAction(event -> {
            fin_data.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("bank_fin_data.fxml", (Stage) fin_data.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onRefreshFinDataButtonClick() {

        String fin_flow_data = ClientCommonFuctions.clientServerDialog(status, 1, "0");
        fin_flow_data = fin_flow_data.substring(1, fin_flow_data.length() - 1);
        String[] splited_info = fin_flow_data.split(",");

        bank_own_funds.setText(splited_info[1]);
        bank_borrowed_funds.setText(splited_info[2]);
        bank_reserve_funds.setText(splited_info[3]);
        bank_refinancing_rate.setText(splited_info[4]);
        central_bank_refinancing_rate.setText(splited_info[5]);
        bank_monthly_expected_income.setText(splited_info[6]);
        bank_monthly_expected_costs.setText(splited_info[7]);

    }

    @FXML
    protected void onInfographicsButtonClick() {
        infographics.setOnAction(event -> {
            infographics.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("manager_inforgaphics.fxml", (Stage) infographics.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onRefreshChartsButtonClick() {
        PieChartSample pieChartSample = new PieChartSample();
        pieChartSample.start(new Stage());
    }

    @FXML
    protected void onRefreshScoringResultsButtonClick() {
        scoring_results.getItems().clear();
        String scoring_data = ClientCommonFuctions.clientServerDialog(status, 6, "0");
        scoring_data = scoring_data.substring(1, scoring_data.length() - 1);

        String[] scoring_data_splited = scoring_data.split(", ");

        for (int i = 0;  i < scoring_data_splited.length - 1; i++){
            scoring_results.getItems().add(scoring_data_splited[i]);
        }

    }


    @FXML
    protected void onConfirmScoringResultsButtonClick() {
        if (!result_id_input.getText().trim().equals("")) {
            ClientCommonFuctions.clientServerDialog(status, 8, result_id_input.getText());
        }
    }

    @FXML
    protected void onCancelScoringResultsButtonClick() {
        if (!result_id_input.getText().trim().equals("")) {
            ClientCommonFuctions.clientServerDialog(status, 7, result_id_input.getText());
        }
    }
}
