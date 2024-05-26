import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);

            Socket client1 = serverSocket.accept();
            System.out.println("Esperando o segundo cliente se conectar!");
            Socket client2 = serverSocket.accept();
            System.out.println("Todos os clientes conectados!");

            BufferedReader client1Reader = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintStream client1Writer = new PrintStream(client1.getOutputStream());
            BufferedReader client2Reader = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            PrintStream client2Writer = new PrintStream(client2.getOutputStream());

            Jokempo.startGame(client1Writer);

            String message;
            while ((message = client1Reader.readLine()) != null) {
                if (message.equals("/play cpu")) {
                    Jokempo.startGameVSCpu(client1Reader, client1Writer);
                } else if (message.equals("/play ac")) {
                    Jokempo.startGameVSPlayer(client1Reader, client2Reader, client1Writer, client2Writer);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
