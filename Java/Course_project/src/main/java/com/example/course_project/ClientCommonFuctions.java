package com.example.course_project;

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
        stage.setTitle("Другая форма");
        stage.setScene(new Scene(root));
        stage.show();
    }

    protected static String clientServerDialog(int status, int function_identifier, String args_list) {

        String response = "";

        try(Socket socket = new Socket("127.0.0.1", 2222);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            System.out.println("Client connected to socket.");

            while(!socket.isOutputShutdown() && response.equals("")){

                if(access) {

                    System.out.println(status + ";" + function_identifier + ";" + args_list);

                    oos.writeUTF(status + ";" + function_identifier + ";" + args_list);
                    oos.flush();

                    response = ois.readUTF();
                    System.out.println("Server response " + response);
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
