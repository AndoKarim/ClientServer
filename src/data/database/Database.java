package data.database;

import data.Idea;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Abdelkarim Andolerzak
 *
 * The database which will contains all the ideas submitted
 */
public class Database {

    private Map<String, Idea> ideas;

    private Database() {
        this.ideas = new ConcurrentHashMap<>();
    }

    public boolean addIdea(Idea i) {
        if (!ideas.containsKey(i.getName())) {
            ideas.put(i.getName(), i);
            return true;
        }
        System.out.println("The idea " + i.getName() + " is already on the database");
        return false;
    }

    /**
     *
     * @param id of the idea
     * @return the idea saved in the Database
     */
    public Idea getIdea(String id) {
        return ideas.get(id);
    }

    /**
     *
     * @return all the ideas submitted in the database
     */
    public Collection<Idea> getAll() {
        return ideas.values();
    }

    private static class Holder {
        /**
         * Unique instance non pre-initialized
         */
        private static final Database instance = new Database();
    }

    public static Database getInstance() {
        return Holder.instance;

    }

}
