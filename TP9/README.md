<h1 align="center">TP9</h1>

---

## Content
---

[Exercice 1](#exercice1)

[Exercice 2](#exercice2)


---

# <a id="exercice1"></a>Exercise 1: JAVA Game.
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

***

# <a id="exercice2"></a>Exercise 2 :
`Overview:`In this exercise we are in the main focus of Sockets,
We practice it in a different approach, we want to read files inside the Server, and we want to configure the server to handle every client's wants
a file from it and also see the content of these files.

## Server Class
---

 In this class, we want to implement the functionality to let the server up for every client, 
 That's why as you can see in the code I use `ExecutorService` to provide multiple connection of clients.
 

```java
package Exercise2;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class FileServer {
    private static final int PORT = 7585;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("File Server started...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}


```

## ClientHandler class
---
In this class where I handle the file given by the user, and show his content in the terminal output.

```java

package Exercise2;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            String fileName = in.readLine();
            File file = new File(fileName);

            if (file.exists() && !file.isDirectory()) {
                out.write("OK\n");
                out.flush();

                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        out.write(line + "\n");
                    }
                }
            } else {
                out.write("File not found\n");
            }
            out.flush();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}

```


## Client Class
---

Without overwhelming this is the class of clients, Where we can choose file by providing the End User a NextLine to inter
specific name.

```java

package Exercise2;

import java.io.*;
import java.net.*;

public class FileClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 7585;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("Enter the file name: ");
            String fileName = consoleInput.readLine();
            out.write(fileName + "\n");
            out.flush();

            String serverResponse = in.readLine();
            if ("OK".equals(serverResponse)) {
                System.out.println("File content:");
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } else {
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}


```

**Testing is everything.**

# Scenario 
---

My scenario here is to create two files, one called abdo.txt and the second.txt, I write some lines inside them.


![image](https://github.com/user-attachments/assets/f2b2b3a4-9fc8-4013-91ff-d6d73cf37d12)

![image](https://github.com/user-attachments/assets/0d0d2f47-2676-4482-9c2a-e64b8ce64f88)

![image](https://github.com/user-attachments/assets/f0d71f75-1d25-4e14-bea9-4bdcbd92442e)



# Summary
---

This is a very practical activity where we see how to work with sockets,
in the first exercise we use it to build connection between the Server and Client,
and in the second one, we see how can we implement them in real scenario.


