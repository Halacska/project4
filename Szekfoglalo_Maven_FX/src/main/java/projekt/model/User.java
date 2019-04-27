package projekt.model;
import java.util.ArrayList;
import java.util.HashMap;

/*
    A felhaszn�l�t reprezent�l� oszt�ly.
    A foglalt helyek list�j�t az el?ad�sok nevei szerint t�rolja.
*/

public class User {
    public final String name;
    private final String password;
    private HashMap<String, ArrayList<Seat>> booking;
    
    //visszaadja, hogy az adott felhaszn�l�nak van-e foglal�sa az adott helyre    
    public boolean hasSeat(Show show, Seat s){
        String sname = show.name; //az el?ad�sok n�v szerinti t�rol�sa miatt
        if (booking.containsKey(sname)){
            if (booking.get(sname).contains(s))
                return true;
            }
        return false;
    }
    
    //foglal�s hozz�ad�sa
    public void addBooking(Show show, Seat s){
        String sname = show.name; //az el?ad�sok n�v szerinti t�rol�sa miatt
        if (!booking.containsKey(sname))
            booking.put(sname, new ArrayList<Seat>()); //ha nincs m�g adott el?ad�s, �j lista
        booking.get(sname).add(s); //adott hely hozz�ad�sa a list�hoz
    }
    
    //foglal�s t�rl�se
    public void deleteBooking(Show show, Seat s){
        String sname = show.name;
        booking.get(sname).remove(s); //hely t�rl�se a list�r�l
        if (booking.get(sname).isEmpty())
            booking.remove(sname); //ha a lista �ress� v�lt, a lista t�rl�se
    }

    //a bejelentkez�skor be�rt jelsz� helyes-e
    public boolean isCorrectPassword(String other) {
        return password.equals(other);
    }
       
    //ki�rja a felhaszn�l� foglal�sait
    public String printBooking() {
        StringBuilder sb = new StringBuilder();
        if (booking.isEmpty()) //ha nincs foglal�s
            sb.append("There is no booking.");
        booking.entrySet().forEach((item) -> { //map entry-k bej�r�sa
            sb.append(item.getKey()).append(": "); //el?ad�s neve
            for(Seat s : item.getValue()) //lista bej�r�sa
                sb.append(s).append(" "); //hely sz�ma
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
