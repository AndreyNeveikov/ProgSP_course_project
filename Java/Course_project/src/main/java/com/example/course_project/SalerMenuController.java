package com.example.course_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


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
    private Button btnEditClient;

    @FXML
    private TextField client_id;

    @FXML
    private TextField client_passport_personal_number;

    @FXML
    private TextField client_passport_series;

    @FXML
    private TextField client_passport_number;

    @FXML
    private TextField client_status;

    @FXML
    private TextField client_date_of_birth;

    @FXML
    private TextField client_patronymic;

    @FXML
    private TextField client_surname;

    @FXML
    private TextField client_name;

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
        if (!client_name.getText().equals("") &&
        !client_surname.getText().equals("") &&
        !client_patronymic.getText().equals("") &&
        !client_date_of_birth.getText().equals("") &&
        !client_passport_personal_number.getText().equals("") &&
        !client_passport_series.getText().equals("") &&
        !client_passport_number.getText().equals("") &&
        !client_status.getText().equals("")) {

            ClientCommonFuctions.clientServerDialog(status, 5,
                    client_name.getText().trim() + "/" + client_surname.getText().trim() + "/" +
                            client_patronymic.getText().trim() + "/" + client_date_of_birth.getText().trim() + "/" +
                            client_passport_personal_number.getText().trim() + "/" + client_passport_series.getText().trim() + "/" +
                            client_passport_number.getText().trim() + "/" + client_status.getText().trim() + "/");
        }
    }


    @FXML
    protected void onEditClientButtonClick() {
        btnEditClient.setOnAction(event -> {
            btnEditClient.getScene().getWindow().hide();

            try {
                ClientCommonFuctions.openNewWindow("lvl2_client_editor.fxml", (Stage) btnEditClient.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @FXML
    protected void onFindClientButtonClick() {
        if (!client_passport_personal_number.getText().trim().equals("")) {
            String client = ClientCommonFuctions.clientServerDialog(status, 4,
                    client_passport_personal_number.getText().trim());

            client = client.substring(1, client.length() - 1);

            String[] splited_info = client.split(",");

            if (splited_info[0].equals("")) {
                client_id.setText("");
                client_name.setText("");
                client_surname.setText("");
                client_patronymic.setText("");
                client_date_of_birth.setText("");
                client_passport_personal_number.setText("");
                client_passport_series.setText("");
                client_passport_number.setText("");
                client_status.setText("");
            }
            else {
                client_id.setText(splited_info[0]);
                client_name.setText(splited_info[1]);
                client_surname.setText(splited_info[2]);
                client_patronymic.setText(splited_info[3]);
                client_date_of_birth.setText(splited_info[4]);
                client_passport_personal_number.setText(splited_info[5]);
                client_passport_series.setText(splited_info[6]);
                client_passport_number.setText(splited_info[7]);
                client_status.setText(splited_info[8]);
            }


        }
    }


    @FXML
    protected void onDeleteClientButtonClick() {
        if (!client_id.getText().trim().equals("")) {
            ClientCommonFuctions.clientServerDialog(status, 3, client_id.getText().trim());
        }
    }

    @FXML
    protected void onRefreshClientButtonClick() {
        bank_clients.getItems().clear();
        String bank_clients_list = ClientCommonFuctions.clientServerDialog(status, 2,
                "0");

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
