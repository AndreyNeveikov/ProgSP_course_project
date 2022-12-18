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

    }

    @FXML
    protected void onHelpButtonClick() {

    }

    @FXML
    protected void onFinDataButtonClick() {

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
