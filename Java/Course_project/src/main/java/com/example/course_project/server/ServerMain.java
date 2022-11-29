package com.example.course_project.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        ServerSocket server = null;

        System.out.println("Server started");
        try {
            server = new ServerSocket(2229);
            server.setReuseAddress(true);

            while (true) {
                Socket client = server.accept();

                System.out.println("New client connected "
                        + client.getInetAddress().getHostAddress());

                ClientHandler clientSock = new ClientHandler(client);
                clientSock.thread.join();
                if(clientSock.isTurnOffServer()) {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


