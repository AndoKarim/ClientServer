import client.ClientV2;
import data.Idea;
import data.Student;
import server.Server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Abdelkarim Andolerzak
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        new Thread(server).start();
        String address = "127.0.0.1";
        int port = 8080;

        ClientV2 client = null;


        Idea idea = new Idea("Idée1", new Student("Loris", "loris@mail.fr"), "Ceci est une description", new ArrayList<>());
        Idea idea2 = new Idea("Idée2", new Student("JCVD", "jcvd@mail.fr"), "Ceci est une deuxieme description", new ArrayList<>());
        try {

            client = new ClientV2(address, port);

            client.sendIdea(idea);
            client.sendIdea(idea2);
            for (Idea idea1 : client.getIdeas().getValue()) {

                System.out.println("==========Idea======");
                System.out.println("Auteur : " + idea1.getAuthor().getName());
                System.out.println("Email : " + idea1.getAuthor().getEmail());
                System.out.println("Description de l'idée : " + idea1.getDescription());
                System.out.println("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            client.exit();
            server.stopListening();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
