package com.example.course_project;

import com.example.course_project.database.QueriesSQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class SalerMenuController {

    @FXML
    private Button move_back;

    @FXML
    private Button credit_products;

    @FXML
    private Button help;

    @FXML
    private Button client_editor;

    @FXML
    ListView<String> loan_products = new ListView<String>();
    @FXML
    ListView<String> bank_clients = new ListView<String>();

    int status = 2;


    @FXML
    protected void onExitButtonClick() {

    }

    @FXML
    protected void onStartScoringButtonClick() {

    }

    @FXML
    protected void onClientEditorButtonClick() {
        client_editor.setOnAction(event -> {
            client_editor.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("client_editor.fxml", (Stage) client_editor.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @FXML
    protected void onAddButtonClick() {

    }


    @FXML
    protected void onEditClientButtonClick() {

    }


    @FXML
    protected void onDeleteClientButtonClick() {
        bank_clients.getItems().clear();
        ClientCommonFuctions.clientServerDialog(status, 3,
                "1");

    }

    @FXML
    protected void onRefreshClientButtonClick() {
        bank_clients.getItems().clear();
        String bank_clients_list = ClientCommonFuctions.clientServerDialog(status, 2,
                "");

        bank_clients_list = bank_clients_list.substring(1, bank_clients_list.length() - 1);
        bank_clients_list = bank_clients_list.replaceAll(";, ", ";");
        bank_clients_list = bank_clients_list.replaceAll(",", "\t\t\t\t");
        String[] loan_products_splited = bank_clients_list.split(";");

        String table_header = "ID клиента \t\tИмя \t\t\t\tФамилия \t\t\t Отчество" +
                "\t\t\t\tдата рождения \t\t  идентификационный номер \tсерия паспорта \t\t" +
                "номер паспорта \t\tстатус активности";

        bank_clients.getItems().add(table_header);

        for (int i = 0;  i < loan_products_splited.length; i++){
            bank_clients.getItems().add(loan_products_splited[i]);
        }
    }



    @FXML
    protected void onHelpButtonClick() {
        help.setOnAction(event -> {
            help.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("saler_help.fxml", (Stage) help.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onCreditProductsButtonClick() {
        credit_products.setOnAction(event -> {
            credit_products.getScene().getWindow().hide();

        try {
                ClientCommonFuctions.openNewWindow("view_credit_products.fxml", (Stage) credit_products.getScene().getWindow());
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
                ClientCommonFuctions.openNewWindow("saler_menu.fxml", (Stage) move_back.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onRefreshCreditProductsButtonClick() throws IOException {
        loan_products.getItems().clear();
        String loan_products_list = ClientCommonFuctions.clientServerDialog(status, 1,
                "");

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
}
