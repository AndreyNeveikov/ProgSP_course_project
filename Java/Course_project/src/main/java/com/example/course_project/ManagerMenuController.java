package com.example.course_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


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
    ListView<String> loan_products = new ListView<String>();

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
    protected void onEditCreditProductsButtonClick() {

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

    }

    @FXML
    protected void onRefreshScoringResultsButtonClick() {

    }


    @FXML
    protected void onConfirmScoringResultsButtonClick() {

    }

    @FXML
    protected void onCancelScoringResultsButtonClick() {

    }
}
