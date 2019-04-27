package projekt.model;

import java.util.ArrayList;

public class Model {
    private ArrayList<User> users; //a regisztr�lt felhazsn�l�k t�rol�sa
    private ArrayList<Show> shows; //az el?ad�sok t�rol�sa

    public Model() {
        this.users = new ArrayList<>();
        this.shows = new ArrayList<>();
    }

    public ArrayList getUsers() {
        return users;
    }

    public ArrayList getShows() {
        return shows;
    }
    
    //megkeres �s visszaad egy adott el?ad�st a neve alapj�n
    public Show getShow(String name) {        
        for (Show sh : shows)
        {
            if (name.equals(sh.name))
                return sh;
        }
        return null;
    }
    
    //megkeres �s visszaad egy adott felhaszn�l�t a neve alapj�n
    public User getUser(String name) {        
        for (User us : users)
        {
            if (name.equals(us.name))
                return us;
        }
        return null;
    }
    
}
