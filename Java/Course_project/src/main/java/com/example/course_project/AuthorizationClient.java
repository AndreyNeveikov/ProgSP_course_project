package com.example.course_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.*;

public class AuthorizationClient {

    Socket sock = null;


    @FXML
    protected void onEnterButtonClick() {
        try {
            this.sock = new Socket(InetAddress.getByName("127.0.0.1"), Integer.parseInt("2222"));

        } catch (NumberFormatException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
