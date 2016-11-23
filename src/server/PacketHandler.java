package server;

import client.flag.Receive;
import data.Idea;
import data.Packet;
import data.database.Database;

import java.util.ArrayList;

/**
 * This class will retrieve the packet and work with it to send it to the database.
 * @author Abdelkarim Andolerzak
 */
public class PacketHandler {
    private Packet responsePacket;

    public PacketHandler(String qualifier, Idea i) {
        ideaTreatment(qualifier, i);
    }

    /**
     *
     * @param qualifier in the packet to tell what action we do with the database
     * @param i the idea to work with
     */
    public void ideaTreatment(String qualifier, Idea i) {
        Database db = Database.getInstance();
        switch (qualifier) {
            case "ADD_IDEA":
                boolean result = db.addIdea(i);
                Receive answer = result ? Receive.OK : Receive.ERROR;
                responsePacket = new Packet(answer);
                break;
            case "GET_LIST_IDEAS":
                ArrayList<Idea> listIdeas = new ArrayList<>(db.getAll());
                responsePacket = new Packet(Receive.OK, listIdeas);
                break;
            case "REGISTER_IN_IDEA":
                break;
            case "GET_REGISTERED_IN":
                break;

            default:
                break;
        }
    }

    public Packet getResponsePacket() {
        return responsePacket;
    }
}
