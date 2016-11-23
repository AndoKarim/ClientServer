package data;

import java.io.Serializable;

/**
 * <p>List of the packet qualifier and values for client :</p>
 * <ul>
 * <li>The client can send a packet with the string "ADD_IDEA" as qualifier and an object Idea as value</li>
 * <li>The client can send a packet with the string "GET_LIST_IDEAS" as qualifier and no object (null) as value</li>
 * <li>The client can send a packet with the string "REGISTER_IN_IDEA" as qualifier and a Pair<Long,String> as value.
 * The pair takes the id of the idea as long and the email as String.</li>
 * <li>The client can send a packet with the string "GET_REGISTERED_IN" as qualifier and a Long for the idea's id as value</li>
 * </ul>
 *
 * <p>List of the packet qualifier and values for server :</p>
 * <ul>
 * <li>For a "ADD_IDEA" request: The server can respond with "OK" or "ERROR" as qualifier and null as value</li>
 * <li>For a "GET_LIST_IDEAS" request: The server can respond with "OK" as qualifier and List of Idea object as value</li>
 * <li>For a "REGISTER_IN_IDEA" request: The server can respond with "OK" or "ERROR" as qualifier and null as value</li>
 * <li>For a "GET_REGISTERED_IN": The server can respond with "OK" or "ERROR" as qualifier and a List of String as value</li>
 * </ul>
 *
 * See ServiceTCP_Friedel_Gerashchenko_Andolerzak_Loroscio.txt for more information.
 *
 * @author Loris Friedel
 * @author Anthony LOROSCIO
 * @author Abdelkarim Andolerzak
 */
public class Packet implements Serializable {

  private final Serializable value;
  private final String qualifier;

  /**
   * Create a packet that can be transmitted to a client or a server.
   *
   * @param qualifier A string qualifier that gives information about the packet (if its a request or an answer for example)
   * @param value     The serializable object that represents the value of the packet.
   */
  public Packet(String qualifier, Serializable value) {
    if(qualifier == null) {
      throw new IllegalArgumentException("Qualifier can't be null");
    }
    this.qualifier = qualifier;
    this.value = value;
  }

  /**
   * Create a packet that can be transmitted to a client or a server.
   *
   * @param qualifier A enum that will be converted into a string (its name).
   *                  Represents a qualifier that gives information about the packet (if its a request or an answer for example)
   * @param value     The serializable object that represents the value of the packet.
   */
  public Packet(Enum<?> qualifier, Serializable value) {
    this(qualifier.name(), value);
  }

  /**
   * Create a packet that can be transmitted to a client or a server.
   *
   * @param qualifier A string qualifier that gives information about the packet (if its a request or an answer for example)
   */
  public Packet(String qualifier) {
    this(qualifier, null);
  }

  /**
   * Create a packet that can be transmitted to a client or a server.
   *
   * @param qualifier A enum that will be converted into a string (its name).
   *                  Represents a qualifier that gives information about the packet (if its a request or an answer for example)
   */
  public Packet(Enum<?> qualifier) {
    this(qualifier.name());
  }

  /**
   * @return The serializable object that represents the value of the packet.
   */
  public Serializable getValue() {
    return value;
  }

  /**
   * @return A string qualifier that gives information about the packet (if its a request or an answer for example)
   */
  public String getQualifier() {
    return qualifier;
  }
}
