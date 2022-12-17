package com.example.course_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ClientCommonFuctions {


    protected static void openNewWindow(String pathToNewWindow, Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ClientCommonFuctions.class.getResource(pathToNewWindow));


        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Другая форма");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
