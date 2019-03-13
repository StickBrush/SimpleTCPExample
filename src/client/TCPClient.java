package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    private Socket server;
    private boolean ready;
    private static final Integer DEFAULT_PORT = 8000;

    public TCPClient(String ip, Integer port) {
        try {
            server = new Socket(InetAddress.getByName(ip), port);
            System.out.println("Iniciada conexión con servidor: " + server.getInetAddress().getHostName());
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
            ready = false;
        }
    }

    public TCPClient(Integer port) {
        try {
            server = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("Iniciada conexión con servidor: " + server.getInetAddress().getHostName());
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
            ready = false;
        }
    }

    public TCPClient(String ip) {
        try {
            server = new Socket(InetAddress.getByName(ip), DEFAULT_PORT);
            System.out.println("Iniciada conexión con servidor: " + server.getInetAddress().getHostName());
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
            ready = false;
        }
    }

    public TCPClient() {
        try {
            server = new Socket(InetAddress.getLocalHost(), DEFAULT_PORT);
            System.out.println("Iniciada conexión con servidor: " + server.getInetAddress().getHostName());
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
            ready = false;
        }
    }

    public void getMessage() {
        if (ready) {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String message = input.readLine();
                System.out.println("Mensaje recibido: " + message);
                server.close();
                System.out.println("Conexión con servidor cerrada");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ready = false;
            }
        }
    }

}
