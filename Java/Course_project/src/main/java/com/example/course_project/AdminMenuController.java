package com.example.course_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;

public class AdminMenuController {
    @FXML
    private Button help;

    @FXML
    private Button move_back;

    @FXML
    protected void onExitButtonClick() {

    }

    @FXML
    protected void onActiveUsersButtonClick() {

    }

    @FXML
    protected void onUserEditorButtonClick() {

    }
    @FXML
    protected void onHelpButtonClick() {
        help.setOnAction(event -> {
            help.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("admin_help.fxml", (Stage) help.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    protected void onReportsButtonClick() {

    }
    @FXML
    protected void onDisconnectButtonClick() {

    }
    @FXML
    protected void onRestartServerButtonClick() {

    }

    @FXML
    protected void onMoveBackButtonClick() {

        move_back.setOnAction(event -> {
            move_back.getScene().getWindow().hide();
            try {
                ClientCommonFuctions.openNewWindow("admin_menu.fxml", (Stage) move_back.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
