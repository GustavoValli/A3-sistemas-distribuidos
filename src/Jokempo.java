import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Jokempo {
    public static void startGame(PrintStream clientWriter) {
        clientWriter.println("Bem vindo ao jokempo!");

        clientWriter.println("Digite '/play cpu' para jogar contra a CPU e '/play ac' para jogar contra outra pessoa!");
    }

    public static void startGameVSCpu(BufferedReader clientReader, PrintStream clientWriter) throws IOException {
        Random random = new Random();

        int playerPoints = 0;
        int cpuPoints = 0;

        while (playerPoints + cpuPoints < 3) {

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
                                break;
                            case 2:
                                clientWriter.println("\nPedra vs Papel");
                                clientWriter.println("Cpu venceu");
                                cpuPoints++;
                                break;
                            case 3:
                                clientWriter.println("\nPedra vs Tesoura");
                                clientWriter.println("Usuário venceu");
                                playerPoints++;
                                break;
                        }
                        break;
                    case 2:
                        switch (cpu) {
                            case 1:
                                clientWriter.println("\nPapel vs Pedra");
                                clientWriter.println("Usuário venceu");
                                playerPoints++;
                                break;
                            case 2:
                                clientWriter.println("\nPapel vs Papel");
                                clientWriter.println("Empate");
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
                                clientWriter.println("Usuário venceu");
                                playerPoints++;
                                break;
                            case 3:
                                clientWriter.println("\nTesoura vs Tesoura");
                                clientWriter.println("Empate");
                                break;
                        }
                        break;
                    default:
                        throw new GameException("\nInsira um valor válido!\n");
                }

            } catch (Exception e) {
                clientWriter.println(e.getMessage());
            }
        }

        clientWriter.println("\n\nPONTUAÇÃO FINAL");
        clientWriter.println("Player: " + playerPoints);
        clientWriter.println("Cpu: " + cpuPoints);
    }

    public static void startGameVSPlayer(BufferedReader client1Reader, BufferedReader client2Reader, PrintStream client1Writer, PrintStream client2Writer) throws IOException {
        int player1Points = 0;
        int player2Points = 0;

        client1Writer.println("Você é o player1!");
        client2Writer.println("Você é o player2!");

        while (player1Points + player2Points < 3) {

            try {
                client1Writer.println("\nEscolha entre pedra(1) papel(2) e tesoura(3): ");
                client2Writer.println("Espere seu turno!");
                int player1 = Integer.parseInt(client1Reader.readLine());

                client2Writer.println("\nEscolha entre pedra(1) papel(2) e tesoura(3): ");
                client1Writer.println("Espere seu turno!");
                int player2 = Integer.parseInt(client2Reader.readLine());

                switch (player1) {
                    case 1:
                        switch (player2) {
                            case 1:
                                client1Writer.println("\nPedra vs Pedra");
                                client1Writer.println("Empate");
                                client2Writer.println("\nPedra vs Pedra");
                                client2Writer.println("Empate");
                                break;
                            case 2:
                                client1Writer.println("\nPedra vs Papel");
                                client1Writer.println("Player2 venceu");
                                client2Writer.println("\nPedra vs Papel");
                                client2Writer.println("Player2 venceu");
                                player2Points++;
                                break;
                            case 3:
                                client1Writer.println("\nPedra vs Tesoura");
                                client1Writer.println("Player1 venceu");
                                client2Writer.println("\nPedra vs Tesoura");
                                client2Writer.println("Player1 venceu");
                                player1Points++;
                                break;
                        }
                        break;
                    case 2:
                        switch (player2) {
                            case 1:
                                client1Writer.println("\nPapel vs Pedra");
                                client1Writer.println("Player1 venceu");
                                client2Writer.println("\nPapel vs Pedra");
                                client2Writer.println("Player1 venceu");
                                player1Points++;
                                break;
                            case 2:
                                client1Writer.println("\nPapel vs Papel");
                                client1Writer.println("Empate");
                                client2Writer.println("\nPapel vs Papel");
                                client2Writer.println("Empate");
                                break;
                            case 3:
                                client1Writer.println("\nPapel vs Tesoura");
                                client1Writer.println("Player2 venceu");
                                client2Writer.println("\nPapel vs Tesoura");
                                client2Writer.println("Player2 venceu");
                                player2Points++;
                                break;
                        }
                        break;
                    case 3:
                        switch (player2) {
                            case 1:
                                client1Writer.println("\nTesoura vs Pedra");
                                client1Writer.println("Player2 venceu");
                                client2Writer.println("\nTesoura vs Pedra");
                                client2Writer.println("Player2 venceu");
                                player2Points++;
                                break;
                            case 2:
                                client1Writer.println("\nTesoura vs Papel");
                                client1Writer.println("Player1 venceu");
                                client2Writer.println("\nTesoura vs Papel");
                                client2Writer.println("Player1 venceu");
                                player1Points++;
                                break;
                            case 3:
                                client1Writer.println("\nTesoura vs Tesoura");
                                client1Writer.println("Empate");
                                client2Writer.println("\nTesoura vs Tesoura");
                                client2Writer.println("Empate");
                                break;
                        }
                        break;
                    default:
                        throw new GameException("\nInsira um valor válido!\n");
                }

            } catch (Exception e) {
                client1Writer.println(e.getMessage());
                client2Writer.println(e.getMessage());
            }
        }

        client1Writer.println("\n\nPONTUAÇÃO FINAL");
        client1Writer.println("Player1: " + player1Points);
        client1Writer.println("Player2: " + player2Points);

        client2Writer.println("\n\nPONTUAÇÃO FINAL");
        client2Writer.println("Player1: " + player1Points);
        client2Writer.println("Player2: " + player2Points);
    }
}