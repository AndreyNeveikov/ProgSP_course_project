package com.example.course_project.server;


import com.example.course_project.database.QueriesSQL;
import com.example.course_project.database.ScoringPersonData;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import static com.example.course_project.database.QueriesSQL.*;

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

                System.out.println(switch_params[2]);


                switch (switch_params[0]) {

                    case "0":
                        String[] login_password;
                        login_password = switch_params[2].split("/");
                        out.writeUTF(QueriesSQL.getUsers(login_password[0], login_password[1]));
                        break;
                    case "1":

                        break;
                    case "2":
                        switch (switch_params[1]) {
                            case "1":
                                String loan_products_list = String.valueOf(getLoanProducts());
                                out.writeUTF(loan_products_list);
                                break;
                            case "2":
                                String bank_clients_list = String.valueOf(getClients());
                                out.writeUTF(bank_clients_list);
                                break;
                            case "3":
                                QueriesSQL.deleteClient(Integer.parseInt(switch_params[2]));
                                out.writeUTF("Deleted");
                                break;
                            case "4":
                                String client = String.valueOf(findClient((switch_params[2])));
                                out.writeUTF(client);
                                break;
                            case "5":
                                QueriesSQL.addClient(switch_params[2]);
                                out.writeUTF("Added");
                            case "6":
                                out.writeUTF(ScoringPersonData.getScoring(switch_params[2]));
                        }
                        break;
                    case "3":
                        switch (switch_params[1]) {
                            case "1":
                                String fin_flows = String.valueOf(getFinFlows());
                                out.writeUTF(fin_flows);
                                break;
                            case "2":
                                String loan_products_list = String.valueOf(getLoanProducts());
                                out.writeUTF(loan_products_list);
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                            case "5":
                                break;
                        }
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