import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {
    private static ConcurrentLinkedQueue<Socket> clientsConnected = new ConcurrentLinkedQueue<>();
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(4000);

            while (!serverSocket.isClosed()) {
                try {
                    Socket socket = serverSocket.accept();
                    clientsConnected.add(socket);
                    new ServerThread().start();
                    System.out.println("Cliente conectado!");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Queue<Socket> getClientsConnected() {
        return clientsConnected;
    }

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }
}
