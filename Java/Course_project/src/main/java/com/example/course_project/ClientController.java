package com.example.course_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Objects;

public class ClientController {
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnDisconnect;
    @FXML
    private Button btnSend;
    @FXML
    private Button btnShutDown;
    @FXML
    private Button btnSelect;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField fieldIP;
    @FXML
    private TextField fieldPort;
    @FXML
    private TextField fieldM;
    @FXML
    private TextArea textResult;
    @FXML
    private RadioButton rBtnInsert;
    @FXML
    private RadioButton rBtnUpdate;
    @FXML
    private RadioButton rBtnDelete;
    @FXML
    private ToggleGroup sqlQuery;
    @FXML
    ListView<String> elListView = new ListView<String>();

    Socket sock = null;

    @FXML
    protected void onConnectButtonClick() {
        try {
            this.sock = new Socket(InetAddress.getByName(this.fieldIP.getText()), Integer.parseInt(this.fieldPort.getText()));

            this.textResult.setText("Connected");
            this.btnConnect.setDisable(true);
            this.btnSend.setDisable(false);
            this.btnDisconnect.setDisable(false);
            this.btnRefresh.setDisable(false);
            this.btnSelect.setDisable(false);
            this.btnShutDown.setDisable(false);
            this.fieldIP.setDisable(true);
            this.fieldPort.setDisable(true);
            this.fieldM.setDisable(false);
            this.elListView.setDisable(false);
            this.rBtnInsert.setDisable(false);
            this.rBtnUpdate.setDisable(false);
            this.rBtnDelete.setDisable(false);
        } catch (NumberFormatException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onDisconnectButtonClick() {
        try {
            this.fieldM.setText("");
            this.textResult.setText("Disconnected");
            setDefaults();
            if (this.sock == null) {
                return;
            }
            try {
                PrintWriter out = new PrintWriter(this.sock.getOutputStream(), true);
                out.println("Disconnect\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    protected void onShutDownButtonClick() {
        try {
            this.fieldM.setText("");
            this.textResult.setText("Disconnected. Client stoped");
            setDefaults();
            this.btnConnect.setDisable(true);
            this.fieldPort.setDisable(true);
            this.fieldIP.setDisable(true);
            if (this.sock == null) {
                return;
            }
            try {
                PrintWriter out = new PrintWriter(this.sock.getOutputStream(), true);
                out.println("Client stop\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    protected void onSelectButtonClick() {
        String selectedItem = this.elListView.getSelectionModel().getSelectedItem();
        this.fieldM.setText(selectedItem);
    }

    @FXML
    protected void onRefreshButtonClick() {
        if (this.sock == null) {
            setDefaults();
            this.textResult.setText("No connection");
            return;
        }
        try {
            PrintWriter out = new PrintWriter(this.sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.fieldM.setText("");
            out.println("Deafult\n");
            elListView.getItems().clear();
            String line = in.readLine();
            while (!Objects.equals(line, "")) {
                elListView.getItems().add(line);
                line = in.readLine();
            }
            this.textResult.setText("Refreshed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    protected void onSendButtonClick() {
        if (this.sock == null) {
            setDefaults();
            this.textResult.setText("No connection");
            return;
        }

        try {
            PrintWriter out = new PrintWriter(this.sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));

            RadioButton selectedRadioButton = (RadioButton) sqlQuery.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            out.println(toggleGroupValue + "; " + this.fieldM.getText() + "\n");
            elListView.getItems().clear();
            String line = in.readLine();
            while (!Objects.equals(line, "")) {
                elListView.getItems().add(line);
                line = in.readLine();
            }
            if(Objects.equals(toggleGroupValue, "Insert")) {
                this.textResult.setText(toggleGroupValue + "ed");
            } else {
                this.textResult.setText(toggleGroupValue + "d");
            }
            this.fieldM.setText("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setDefaults() {
        elListView.getItems().clear();
        this.btnConnect.setDisable(false);
        this.btnSend.setDisable(true);
        this.btnDisconnect.setDisable(true);
        this.btnRefresh.setDisable(true);
        this.btnSelect.setDisable(true);
        this.btnShutDown.setDisable(true);
        this.fieldIP.setDisable(false);
        this.fieldPort.setDisable(false);
        this.fieldM.setDisable(true);
        this.elListView.setDisable(true);
        this.rBtnInsert.setDisable(true);
        this.rBtnUpdate.setDisable(true);
        this.rBtnDelete.setDisable(true);
    }
}