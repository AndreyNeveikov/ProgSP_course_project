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
import java.util.Objects;

public class SalerMenuController {

    @FXML
    private Button move_back;

    @FXML
    private Button credit_products;

    int status = 2;

    @FXML
    ListView<String> loan_products = new ListView<String>();

    @FXML
    protected void onExitButtonClick() {

    }

    @FXML
    protected void onStartScoringButtonClick() {

    }

    @FXML
    protected void onClientEditorButtonClick() {

    }

    @FXML
    protected void onHelpButtonClick() {

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
                ClientCommonFuctions.openNewWindow("admin_help.fxml", (Stage) move_back.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onRefreshButtonClick() {
        String loan_products_list = ClientCommonFuctions.clientServerDialog(status, 1,
                "");
        System.out.println(loan_products_list);
    }
}
