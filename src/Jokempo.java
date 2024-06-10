import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class Jokempo {
    public static void startGame(PrintStream clientWriter) {
        clientWriter.println("Bem vindo ao jokempo!");

        clientWriter.println("Digite '/play cpu' para jogar contra a CPU e '/play ac' para jogar contra outra pessoa!");
    }

    public static void startGameVSCpu(BufferedReader clientReader, PrintStream clientWriter) throws IOException {
        Random random = new Random();

        int draws = 0;
        int playerPoints = 0;
        int cpuPoints = 0;

        while (playerPoints + cpuPoints < 2 || (playerPoints < 2 && cpuPoints < 2)) {

            try {
                clientWriter.println("\nEscolha entre pedra(1) papel(2) e tesoura(3): ");
                int player = Integer.parseInt(clientReader.readLine());
                int cpu = random.nextInt(3) + 1;

                switch (player) {
                    case 1:
                        switch (cpu) {
                            case 1:
                                clientWriter.println("\nPedra vs Pedra");
                                clientWriter.println("Empate");
                                draws++;
                                break;
                            case 2:
                                clientWriter.println("\nPedra vs Papel");
                                clientWriter.println("Cpu venceu");
                                cpuPoints++;
                                break;
                            case 3:
                                clientWriter.println("\nPedra vs Tesoura");
                                clientWriter.println("Usuario venceu");
                                playerPoints++;
                                break;
                        }
                        break;
                    case 2:
                        switch (cpu) {
                            case 1:
                                clientWriter.println("\nPapel vs Pedra");
                                clientWriter.println("Usuario venceu");
                                playerPoints++;
                                break;
                            case 2:
                                clientWriter.println("\nPapel vs Papel");
                                clientWriter.println("Empate");
                                draws++;
                                break;
                            case 3:
                                clientWriter.println("\nPapel vs Tesoura");
                                clientWriter.println("Cpu venceu");
                                cpuPoints++;
                                break;
                        }
                        break;
                    case 3:
                        switch (cpu) {
                            case 1:
                                clientWriter.println("\nTesoura vs Pedra");
                                clientWriter.println("Cpu venceu");
                                cpuPoints++;
                                break;
                            case 2:
                                clientWriter.println("\nTesoura vs Papel");
                                clientWriter.println("Usuario venceu");
                                playerPoints++;
                                break;
                            case 3:
                                clientWriter.println("\nTesoura vs Tesoura");
                                clientWriter.println("Empate");
                                draws++;
                                break;
                        }
                        break;
                    default:
                        throw new GameException("\nInsira um valor valido!\n");
                }

            } catch (Exception e) {
                clientWriter.println(e.getMessage());
            }
        }

        clientWriter.println("\n\n----------------------------------");
        if (cpuPoints > playerPoints) {
            clientWriter.println("Voce perdeu!");
        } else {
            clientWriter.println("Voce ganhou!");
        }
        clientWriter.println("----------------------------------");

        clientWriter.println("\n\nPONTUACAO FINAL:");
        clientWriter.println("Player: " + playerPoints);
        clientWriter.println("Cpu: " + cpuPoints);
        clientWriter.println("Empates: " + draws);

        clientWriter.println("\nDigite '/play cpu' para jogar contra a CPU e '/play ac' para jogar contra outra pessoa!");
    }

    public static void startGameVSPlayer(Socket client1, Socket client2,
                                         BufferedReader client1Reader, BufferedReader client2Reader,
                                         PrintStream client1Writer, PrintStream client2Writer) throws IOException {

        int draws = 0;
        int player1Points = 0;
        int player2Points = 0;

        int player1;
        int player2;

        while (player1Points + player2Points < 2 || (player1Points < 2 && player2Points < 2)) {

            try {
                if (client1.isConnected() && client2.isConnected()) {
                    client1Writer.println("\nEscolha entre pedra(1) papel(2) e tesoura(3): ");
                    client2Writer.println("\nEspere seu turno!");
                    player1 = Integer.parseInt(client1Reader.readLine());
                } else {
                    break;
                }

                if (client1.isConnected() && client2.isConnected()) {
                    client2Writer.println("\nEscolha entre pedra(1) papel(2) e tesoura(3): ");
                    client1Writer.println("\nEspere seu turno!");
                    player2 = Integer.parseInt(client2Reader.readLine());
                } else {
                    break;
                }

                switch (player1) {
                    case 1:
                        switch (player2) {
                            case 1:
                                client1Writer.println("\nPedra vs Pedra");
                                client1Writer.println("Empate");
                                client2Writer.println("\nPedra vs Pedra");
                                client2Writer.println("Empate");
                                draws++;
                                break;
                            case 2:
                                client1Writer.println("\nPedra vs Papel");
                                client1Writer.println("Voce perdeu!");
                                client2Writer.println("\nPapel vs Pedra");
                                client2Writer.println("Voce venceu!");
                                player2Points++;
                                break;
                            case 3:
                                client1Writer.println("\nPedra vs Tesoura");
                                client1Writer.println("Voce venceu!");
                                client2Writer.println("\nTesoura vs Pedra");
                                client2Writer.println("Voce perdeu!");
                                player1Points++;
                                break;
                        }
                        break;
                    case 2:
                        switch (player2) {
                            case 1:
                                client1Writer.println("\nPapel vs Pedra");
                                client1Writer.println("Voce venceu!");
                                client2Writer.println("\nPedra vs Papel");
                                client2Writer.println("Voce perdeu!");
                                player1Points++;
                                break;
                            case 2:
                                client1Writer.println("\nPapel vs Papel");
                                client1Writer.println("Empate");
                                client2Writer.println("\nPapel vs Papel");
                                client2Writer.println("Empate");
                                draws++;
                                break;
                            case 3:
                                client1Writer.println("\nPapel vs Tesoura");
                                client1Writer.println("Voce perdeu!");
                                client2Writer.println("\nTesoura vs Papel");
                                client2Writer.println("Voce venceu!");
                                player2Points++;
                                break;
                        }
                        break;
                    case 3:
                        switch (player2) {
                            case 1:
                                client1Writer.println("\nTesoura vs Pedra");
                                client1Writer.println("Voce perdeu!");
                                client2Writer.println("\nPedra vs Tesoura");
                                client2Writer.println("Voce venceu!");
                                player2Points++;
                                break;
                            case 2:
                                client1Writer.println("\nTesoura vs Papel");
                                client1Writer.println("Voce venceu!");
                                client2Writer.println("\nPapel vs Tesoura");
                                client2Writer.println("Voce perdeu!");
                                player1Points++;
                                break;
                            case 3:
                                client1Writer.println("\nTesoura vs Tesoura");
                                client1Writer.println("Empate");
                                client2Writer.println("\nTesoura vs Tesoura");
                                client2Writer.println("Empate");
                                draws++;
                                break;
                        }
                        break;
                    default:
                        throw new GameException("\nInsira um valor valido!\n");
                }

            } catch (Exception e) {
                client1Writer.println(e.getMessage());
                client2Writer.println(e.getMessage());
            }

            if (client1.isClosed() || !client2.isClosed()) {
                break;
            }
        }

        client1Writer.println("\n\n----------------------------------");
        client2Writer.println("\n\n----------------------------------");
        if (player1Points > player2Points) {
            client1Writer.println("Voce ganhou!");
            client2Writer.println("Voce perdeu!");
        } else {
            client1Writer.println("Voce perdeu!");
            client2Writer.println("Voce ganhou!");
        }
        client1Writer.println("----------------------------------");
        client2Writer.println("----------------------------------");

        client1Writer.println("\n\nPONTUACAO FINAL");
        client1Writer.println("Voce: " + player1Points);
        client1Writer.println("Adversario: " + player2Points);
        client1Writer.println("Empates: " + draws);

        client2Writer.println("\n\nPONTUACAO FINAL");
        client2Writer.println("Voce: " + player2Points);
        client2Writer.println("Adversario: " + player1Points);
        client2Writer.println("Empates: " + draws);

        client1Writer.println("\nDigite '/play cpu' para jogar contra a CPU e '/play ac' para jogar contra outra pessoa!");
        client2Writer.println("\nDigite '/play cpu' para jogar contra a CPU e '/play ac' para jogar contra outra pessoa!");
    }
}