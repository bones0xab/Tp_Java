import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 7585;
        final int MAGIC_NUMBER = (int) (Math.random() * 101); // Random number [0-100]
        System.out.println("Server is running on port " + PORT);
        System.out.println("The magic number is: " + MAGIC_NUMBER); // For debugging or testing

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {

                boolean gameRunning = true;

                while (gameRunning) {
                    int guess = inputStream.read();
                    if (guess == -1) {
                        System.out.println("Client disconnected.");
                        break;
                    }

                    System.out.println("Player guessed: " + guess);

                    if (guess == MAGIC_NUMBER) {
                        outputStream.write(0); // Signal correct guess
                        System.out.println("Client guessed correctly! Game over.");
                        gameRunning = false;
                    } else if (guess < MAGIC_NUMBER) {
                        outputStream.write(1); // Hint: Go higher
                        System.out.println("Hint sent: Go Higher!");
                    } else {
                        outputStream.write(-1); // Hint: Go lower
                        System.out.println("Hint sent: Go Lower!");
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
