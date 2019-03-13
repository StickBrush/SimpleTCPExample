package UI;

import client.TCPClient;
import server.TCPServer;

import java.util.Scanner;

public class SimpleUI {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String ip;
        Integer cPort, sPort;
        System.out.println("¡Bienvenido al ejemplo simple de TCP para probar IDS!");
        System.out.println("1) Ejecutar servidor");
        System.out.println("2) Ejecutar cliente");
        System.out.println("3) Ejecutar ambos en local");
        System.out.println("SELECT YOUR DESTINY");
        Integer option = Integer.parseInt(keyboard.nextLine());
        switch (option) {
            case 1:
                System.out.println("Introduce el puerto");
                sPort = Integer.parseInt(keyboard.nextLine());
                TCPServer server = new TCPServer(sPort);
                System.out.println("Introduce el mensaje a servir");
                String msg = keyboard.nextLine();
                server.serve(msg);
                break;
            case 2:
                System.out.println("Introduce la dirección IP");
                ip = keyboard.nextLine();
                System.out.println("Introduce el puerto");
                cPort = Integer.parseInt(keyboard.nextLine());
                TCPClient client = new TCPClient(ip, cPort);
                client.getMessage();
                break;
            case 3:
                System.out.println("Introduce el puerto");
                sPort = Integer.parseInt(keyboard.nextLine());
                cPort = sPort;
                TCPServer multithreadServer = new TCPServer(sPort);
                Thread serverThread = new Thread(multithreadServer);
                serverThread.start();
                TCPClient multithreadClient = new TCPClient(cPort);
                multithreadClient.getMessage();
                break;
            default:
                throw new RuntimeException("You shouldn't have done that...");
        }
    }
}
