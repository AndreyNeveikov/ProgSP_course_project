package com.example.course_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminMenuController {
    @FXML
    private Button help;

    @FXML
    private Button log_report;

    @FXML
    private Button worker_editor;

    @FXML
    private Button exit;

    @FXML
    private Button move_back;

    @FXML
    private Button user_editor;

    @FXML
    private Button btnEditWorker;

    @FXML
    private TextField employee_id;

    @FXML
    ListView<String> log_results = new ListView<String>();

    @FXML
    ListView<String> bank_clients = new ListView<String>();

    int status = 1;


    @FXML
    protected void onExitButtonClick() {
        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();
            System.exit(0);
        });
    }

    @FXML
    protected void onActiveUsersButtonClick() {

    }

    @FXML
    protected void onTruncateDBButtonClick() {
        ClientCommonFuctions.clientServerDialog(status, 2, "0");
    }

    @FXML
    protected void onUserEditorButtonClick() {
        user_editor.setOnAction(event -> {
            user_editor.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("worker_editor.fxml", (Stage) user_editor.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onRefreshClientButtonClick() {
        bank_clients.getItems().clear();
        String workers_list = ClientCommonFuctions.clientServerDialog(status, 3,
                "0");

        workers_list = workers_list.substring(1, workers_list.length() - 1);
        workers_list = workers_list.replaceAll(";, ", ";");
        workers_list = workers_list.replaceAll(",", "\t\t\t\t\t\t");
        String[] workers_splited = workers_list.split(";");

        String table_header = "ID \t логин \t\t\t\t\t пароль \t\t\t\t\t\t доступ" +
                "\t\t\t\t имя \t\t\t\t\t\tфамилия \t\t\t\t\t\t\tотчество \t\t\t\t\t\t\t" +
                "должность" ;
        bank_clients.getItems().add(table_header);

        for (int i = 0;  i < workers_splited.length; i++){
            bank_clients.getItems().add(workers_splited[i]);
        }
    }

    @FXML
    protected void onEditWorkerButtonClick() {
        btnEditWorker.setOnAction(event -> {
            btnEditWorker.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("lvl2_worker_editor.fxml", (Stage) btnEditWorker.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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
        log_report.setOnAction(event -> {
            log_report.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("admin_log_view.fxml", (Stage) log_report.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void onDisconnectButtonClick() {
        ClientCommonFuctions.clientServerDialog(status, 3, "4");
    }
    @FXML
    protected void onRefreshLogButtonClick() {
        log_results.getItems().clear();
        String action_data = ClientCommonFuctions.clientServerDialog(status, 1, "0");
        action_data = action_data.substring(status, action_data.length() - 1);

        String[] scoring_data_splited = action_data.split(", ");

        for (int i = 0;  i < scoring_data_splited.length - 1; i++){
            log_results.getItems().add(scoring_data_splited[i]);
        }
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

    @FXML
    protected void onWorkerEditorButtonClick() {
        worker_editor.setOnAction(event -> {
            worker_editor.getScene().getWindow().hide();
            try {
                ClientCommonFuctions.openNewWindow("worker_editor.fxml", (Stage) worker_editor.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @FXML
    protected void onDeleteWorkerButtonClick() {
        if (!employee_id.getText().trim().equals("")) {
            ClientCommonFuctions.clientServerDialog(status, 4, employee_id.getText().trim());
        }
    }

    @FXML
    protected void onFindWorkerButtonClick() {

    }

    @FXML
    protected void onAddButtonClick() {

    }

}
