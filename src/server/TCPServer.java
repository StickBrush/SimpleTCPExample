package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
    private ServerSocket listener;
    private boolean ready;
    private static final Integer DEFAULT_PORT = 8000;
    private static final String DEFAULT_MESSAGE = "Servidor para pruebas de IDS";

    public TCPServer() {
        try {
            listener = new ServerSocket(DEFAULT_PORT);
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
            ready = false;
        }
    }

    public TCPServer(int port) {
        try {
            listener = new ServerSocket(port);
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
            ready = false;
        }
    }

    public void serve() {
        serve(DEFAULT_MESSAGE);
    }

    public void serve(String message) {
        while (ready) {
            try {
                Socket currentClient = listener.accept();
                System.out.println("Sirviendo a cliente: " + currentClient.getInetAddress().getHostName());
                System.out.println("Sirviendo desde el puerto: " + currentClient.getLocalPort());
                message = message + "\n";
                currentClient.getOutputStream().write(message.getBytes());
            } catch (IOException e) {
                ready = false;
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        serve();
    }
}
