<h1 align="center">TP9</h1>

---

## Content
---

[Exercice 1](#exercice1)

[Exercice 2](#exercice2)


---

# Exercise 1: JAVA Game.
---

`Overview:` In this first exercise we point the attention to the sockets in JAVA, using a Game Guess to build a basic
code of Sockets and try it, the core concept is to use a Server and client, and a magic number inside the server, and the clients,
or the players must guess the magic number.

**Tasks:**
- Build a class Server
- Build a class Client
- Made Connection between them
- Lunch and test the magic number inside the Server

### Class Client.
---

```java


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 7585;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server!");

            boolean gameRunning = true;

            while (gameRunning) {
                System.out.print("Guess the number between 0 and 100: ");
                int guess = scanner.nextInt();

                outputStream.write(guess); // Send guess to the server
                int response = inputStream.read(); // Read server's response

                switch (response) {
                    case 0:
                        System.out.println("Congratulations, you guessed the number!");
                        gameRunning = false; // Exit the game
                        break;
                    case 1:
                        System.out.println("Go Higher!");
                        break;
                    case -1:
                        System.out.println("Go Lower!");
                        break;
                    default:
                        System.out.println("Unexpected response from server. Exiting.");
                        gameRunning = false;
                        break;
                }
            }

            System.out.println("Exiting the game. Goodbye!");

        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        }
    }
}


```

**Explication**

The code above is the client class where I write a `try` inside the code to make the basic configuration for sending and receiving the data with the Server,
also as you can see inside the `TRY` the `switch` for testing different cases, The case of exact Numbers, and the cases of invalid numbers, 
(If the user is up to the M.Number we tell him to go lower if not you can see the other case).


---

## Server Class
---

```java

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


```

**Explication**

In this Class, I write the configurations of establishing the connection between the Server and the client, 
as well I mentioned a while where I repeated until we find the Magic number,
I use the `localhost` and the `port 7585`.

---

# Scenario.
---
I will choose the number magic as 40 and play the game as a User. 

**Note: Testing is Everything !**

This is the part of the server where I compile and I go this.(All the configuration work cause we see they are connected ).

![image](https://github.com/user-attachments/assets/f98ad654-1f5a-4319-b345-2839e70b98fe)

Then, I will test the cases on the User side (exactly the number, higher, Slower).


![image](https://github.com/user-attachments/assets/5bf2e673-faf3-446e-9eee-1d7326224367)


As you can see in the above illustration we can see different tests and the final results.



