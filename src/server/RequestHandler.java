package server;

import data.Idea;
import data.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class is in charge of retrieving the packet from the socket, close the socket.
 *
 * @author Abdelkarim Andolerzak
 */
public class RequestHandler implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private boolean isClosed = false;

    public RequestHandler(Socket s) {
        socket = s;
        try {
            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!isClosed) {
            try {

                //Retrieving the packet from the outputStream of the socket
                Packet p = (Packet) ois.readObject();
                //Retrieving and casting the serializable object of the packet.
                Idea i = (Idea) p.getValue();

                if (!p.getQualifier().equals("EXIT")) {
                    //Giving the idea and the qualifier of the packet to the class in charge of

                    PacketHandler pHandler = new PacketHandler(p.getQualifier(), i);
                    Packet responsePacket = pHandler.getResponsePacket();

                    //Sending the response of the manipulation of the idea
                    oos.writeObject(responsePacket);
                } else {
                    oos.writeObject(new Packet("EXIT"));
                    socket.close();
                    isClosed = true;
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
