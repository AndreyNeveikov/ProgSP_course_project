package com.example.course_project.server;

import com.example.course_project.database.DatabaseDeployment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    Thread thread;
    private final Socket clientSocket;
    private boolean turnOffServer = false;
    public ClientHandler(Socket socket)
    {
        this.thread = new Thread(this, "Client thread");
        this.thread.start();
        this.clientSocket = socket;
    }
    public boolean isTurnOffServer() {return this.turnOffServer;}

    public void run()
    {
        PrintWriter out;
        BufferedReader in;
        boolean flag = true;
        while (flag) {
            try {
                out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
                DatabaseDeployment mysqlQuery = new DatabaseDeployment();

                String line = in.readLine();
                System.out.printf(" Sent from the client: %s\n", line);

                switch (line) {
                    case "ShutDown":
                        this.turnOffServer = true;
                        DatabaseDeployment.disconnect();
                    case "Disconnect":
                        try {
                            out.close();
                            in.close();
                            this.clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        flag = false;
                        break;
                    default:
                        String[] args = line.split("; ");

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
