package projekt.model;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public final String name;
    private final String password;
    private HashMap<String, ArrayList<Seat>> booking; //a foglalt helyeket tárolja az el?adások nevei szerint
    
    //visszaadja, hogy az adott felhasználónak van-e foglalása az adott helyre    
    public boolean hasSeat(Show show, Seat s){
        String sname = show.name;
        if (booking.containsKey(sname)){
            if (booking.get(sname).contains(s))
                return true;
            }
        return false;
    }
    
    //foglalás hozzáadása
    public void addBooking(Show show, Seat s){
        String sname = show.name;
        if (!booking.containsKey(sname))
            booking.put(sname, new ArrayList<Seat>());
        booking.get(sname).add(s);        
    }
    
    //foglalás törlése
    public void deleteBooking(Show show, Seat s){
        String sname = show.name;
        booking.get(sname).remove(s);
        if (booking.get(sname).isEmpty())
            booking.remove(sname);
    }

    //a bejelntkezéskor beírt jelszó helyes-e
    public boolean isCorrectPassword(String other) {
        return password.equals(other);
    }
       
    //kiírja a felhasználó foglalásait
    public String printBooking() {
        StringBuilder sb = new StringBuilder();
        if (booking.isEmpty())
            sb.append("There is no booking yet.");
        booking.entrySet().forEach((item) -> {
            sb.append(item.getKey()).append(": ");
            for(Seat s : item.getValue())
                sb.append(s).append(" ");
            sb.append("\n");
        });
        return sb.toString();
    }
    
    
    //konstruktor, tostring, getterek
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.booking = new HashMap<>();
    }

    @Override
    public String toString() {        
        return "Username: " + name + ", password: " + password + "\n" + printBooking();
    }
    
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    public HashMap<String, ArrayList<Seat>> getBooking() {
        return booking;
    }
}
