package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author Abdelkarim Andolerzak
 */
public class Server implements Runnable {

    private ServerSocket serverSocket;
    private final int port = 8080;
    private boolean isListening;


    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isListening = true;
    }

    /**
     * main method of the thread which is listening the port to accept new clients.
     */
    @Override
    public void run() {
        try {
            //While the server is allowed to listen, it will accept new clients and creates a new thread
            //to treat the client
            while (isListening) {
                Socket socket = serverSocket.accept();
                new Thread(new RequestHandler(socket)).start();
            }
        } catch (IOException e) {
            if (!isListening)
                return;
            e.printStackTrace();
        }
    }

    /**
     * Method in charge of closing the socket and so, shuts down the server.
     * @throws IOException if the closing of the socket fails
     */
    public void stopListening() throws IOException {
        isListening = false;
        serverSocket.close();
    }

}
