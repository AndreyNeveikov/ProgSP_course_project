package com.example.course_project;

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
import java.util.Objects;

public class SalerMenuController {

    @FXML
    private Button move_back;

    @FXML
    private Button credit_products;

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
        credit_products.setOnAction(event -> {credit_products.getScene().getWindow().hide();});

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view_credit_products.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    protected void onMoveBackButtonClick() {

        move_back.setOnAction(event -> {move_back.getScene().getWindow().hide();});

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("saler_menu.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    protected void onRefreshButtonClick() {

    }
}
