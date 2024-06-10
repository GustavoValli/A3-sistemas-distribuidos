import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    private ServerSocket serverSocket = Server.getServerSocket();
    private Socket socket;
    private BufferedReader clientReader;
    private PrintStream clientWriter;

    public ServerThread() {
        socket = Server.getClientsConnected().poll();
        System.out.println("Jogo iniciado!");
        try {
            clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientWriter = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                Jokempo.startGame(clientWriter);
                String message;
                while ((message = clientReader.readLine()) != null) {
                    if (message.equals("/play cpu")) {
                        Jokempo.startGameVSCpu(clientReader, clientWriter);
                    } else if (message.equals("/play ac")) {
                        synchronized (Server.getClientsConnected()) {
                            Server.getClientsConnected().add(socket);
                            clientWriter.println("Esperando outro jogador, aguarde...");
                            if (!Server.getClientsConnected().isEmpty()) {

                                if (Server.getClientsConnected().size() >= 2) {
                                    Server.getClientsConnected().remove(socket);
                                    Socket otherSocket = Server.getClientsConnected().poll();

                                    if (otherSocket != null) {
                                        Server.getClientsConnected().remove(otherSocket);
                                        BufferedReader otherClientReader = new BufferedReader(new InputStreamReader(otherSocket.getInputStream()));
                                        PrintStream otherClientWriter = new PrintStream(otherSocket.getOutputStream());

                                        Jokempo.startGameVSPlayer(clientReader, otherClientReader, clientWriter, otherClientWriter);
                                    }
                                }
                            } else {
                                clientWriter.println("Esperando outro cliente se conectar...");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Server.getClientsConnected().remove(socket);
                System.out.println("Cliente desconectado!");
            }
            break;
        }
    }
}
