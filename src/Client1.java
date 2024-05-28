import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String ip;
            int port;
            Socket socket;

            while (true) {
                try {
                    System.out.println("Digite o IP do servidor: ");
                    ip = reader.readLine();
                    System.out.println("Digite a porta do servidor: ");
                    port = Integer.parseInt(reader.readLine());
                    socket = new Socket(ip, port);
                    if (socket.isConnected()) {
                        System.out.println("Esperando o servidor...\n");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("IP ou porta estão errados!");
                }
            }

            new ClientThread(socket).start();
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            while (socket.isConnected()) {
                printStream.println(reader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
