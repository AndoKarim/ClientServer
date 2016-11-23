package client;

import data.Idea;
import data.Packet;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import static client.flag.Send.ADD_IDEA;
import static client.flag.Send.GET_LIST_IDEAS;

/**
 * A client which send and get ideas.
 *
 * @author Anthony LOROSCIO
 */
public class ClientV2 {

  private final String host;
  private final int port;
  private Socket socket;
  private ObjectOutputStream output;
  private ObjectInputStream input;

  public ClientV2(String host, int port) throws IOException {
    this.host = host;
    this.port = port;

    socket = new Socket(host, port);
    output = new ObjectOutputStream(socket.getOutputStream());
    input = new ObjectInputStream(socket.getInputStream());

  }

  /**
   * Send the object idea to the server.
   *
   * @param idea     Idea to be send.
   * @return String  Qualifier of the request status
   */
  public String sendIdea(Idea idea) throws IOException {
    Packet packet = new Packet(ADD_IDEA, idea);
    return sendRequest(packet).getKey();
  }

  /**
   * Request to the server to get all the ideas
   *
   * @return Pair  The key of the pair is the status and the value is the List of Idea
   */
  public Pair<String, List<Idea>> getIdeas() throws IOException {
    Packet packet = new Packet(GET_LIST_IDEAS);
    return (Pair) sendRequest(packet);
  }

  public void exit() throws IOException {

    Packet packet = new Packet("EXIT");
    sendRequest(packet);

    socket.close();
  }

  /**
   *
   * Send the object packet to server
   *
   * @param packet   Packet to be sent to the server.
   */
  private Pair<String, Object> sendRequest(Packet packet) throws IOException {



    // Send packet to the server
    output.writeObject(packet);

    // Get the response
    Packet response = null;
    try {
      response = (Packet) input.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }


    // Process the response
    return new Pair<>(response.getQualifier(), response.getValue());
  }
}
