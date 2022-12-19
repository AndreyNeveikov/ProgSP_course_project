package com.example.course_project;

import com.example.course_project.database.ActionLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientCommonFuctions {

    static boolean access = true;

    protected static void openNewWindow(String pathToNewWindow, Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ClientCommonFuctions.class.getResource(pathToNewWindow));


        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    protected static String clientServerDialog(int status, int function_identifier, String args_list) {

        String response = "";

        try(Socket socket = new Socket("127.0.0.1", 2222);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            while(!socket.isOutputShutdown() && response.equals("")){

                if(access) {
                    oos.writeUTF(status + ";" + function_identifier + ";" + args_list);
                    oos.flush();

                    response = ois.readUTF();
                    ActionLogger.getActionLogger().addActionLogInfo(response + " got");
                }
            }


        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return response;
    }
}
