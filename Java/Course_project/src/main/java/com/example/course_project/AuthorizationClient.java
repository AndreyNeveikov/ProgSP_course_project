package com.example.course_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationClient {
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    @FXML
    private Button enter_the_application;

    @FXML
    protected void onEnterButtonClick() throws IOException {
        int status = 0;
        String response = ClientCommonFuctions.clientServerDialog(status, 0,
                login.getText().trim() + "/" + password.getText().trim());

        System.out.println("Client connected to socket.");


        enter_the_application.getScene().getWindow().hide();
        switch (response) {

            case "1":
                ClientCommonFuctions.openNewWindow("admin_menu.fxml",
                        (Stage) enter_the_application.getScene().getWindow());
                break;
            case "2":
                ClientCommonFuctions.openNewWindow("saler_menu.fxml",
                        (Stage) enter_the_application.getScene().getWindow());
                break;
            case "3":
                ClientCommonFuctions.openNewWindow("manager_menu.fxml",
                        (Stage) enter_the_application.getScene().getWindow());
                break;
            case "0":
                ClientCommonFuctions.openNewWindow("authorization.fxml",
                        (Stage) enter_the_application.getScene().getWindow());
                break;
        }

    }
}