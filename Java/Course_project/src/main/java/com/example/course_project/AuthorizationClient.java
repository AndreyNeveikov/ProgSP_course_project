package com.example.course_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class AuthorizationClient {
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    @FXML
    private Button enter_the_application;

    @FXML
    protected void onEnterButtonClick() {

            String response = "";
            int status = 0;

            try(Socket socket = new Socket("127.0.0.1", 2222);
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream()); )
            {

                System.out.println("Client connected to socket.");

                while(!socket.isOutputShutdown() && response.equals("")){

                    if(true) {

                        System.out.println("Client start writing in channel...");
                        String loginText = login.getText().trim();
                        String passwordText = password.getText().trim();

                        System.out.println(status + ";" + loginText + ";" + passwordText);

                        oos.writeUTF(status + ";" + loginText + ";" + passwordText);
                        oos.flush();

                        response = String.valueOf(ois.read());
                        System.out.println("Server response " + response);
                    }

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
                        default: response = "Непредвиденная ошибка";
                            break;
                    }

                }


            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }