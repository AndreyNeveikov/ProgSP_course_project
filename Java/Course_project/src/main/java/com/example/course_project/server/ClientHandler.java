package com.example.course_project.server;


import com.example.course_project.database.QueriesSQL;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {

    private static Socket clientDialog;

    public ClientHandler(Socket client) {
        ClientHandler.clientDialog = client;
    }

    @Override
    public void run() {

        String[] switch_params;
        try {

            while (!clientDialog.isClosed()) {
                DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
                DataInputStream in = new DataInputStream(clientDialog.getInputStream());


                String entry = in.readUTF();
                System.out.println("READ from clientDialog message - " + entry);

                switch_params = entry.split(";");

                switch (switch_params[0]) {

                    case "0":
                        String[] login_password;
                        login_password = switch_params[2].split("/");
                        out.write(QueriesSQL.getUsers(login_password[0], login_password[1]));
                        break;
                    case "1":

                        break;
                    case "2":
                        switch (switch_params[1]) {
                            case "1":
                                String loan_products_list = String.valueOf(QueriesSQL.getLoanProducts());
                                out.write(loan_products_list.getBytes());
                                break;
                            case "2":

                                break;
                        }
                        break;
                    case "3":
                        QueriesSQL.disconnect();
                        break;

                }

                System.out.println("Server Wrote message to clientDialog.");

                out.flush();

                in.close();
                out.close();

            }

            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");


            clientDialog.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}