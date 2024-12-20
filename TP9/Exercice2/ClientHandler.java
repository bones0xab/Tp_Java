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