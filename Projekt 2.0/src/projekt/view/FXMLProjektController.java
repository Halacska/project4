package projekt.view;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import projekt.model.Model;
import projekt.model.Seat;
import projekt.model.Show;
import projekt.model.User;

public class FXMLProjektController implements Initializable {
    
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    GridPane gp = new GridPane();
    @FXML
    Button endgame;
    @FXML
    Button captm;
    @FXML
    Button pets;    
    
    Model model;
    User actual_user = null; 
    Show actual_show = null;    
    HashSet<Seat> actual_seats = new HashSet<>();
    
    public void setModel(Model model) {
        this.model = model;
        //csak a példa kedvéért 3 random film, majd a DB-s résznél erre úgyis kitalálunk valami jobbat (gondolom)
        model.getShows().add(new Show("Endgame", 10, 10));
        model.getShows().add(new Show("Captain Marvel", 10, 8));
        model.getShows().add(new Show("Pet Sematary", 8, 8));        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }    
    
    //regisztráció, bejeltnkezés, kijelentkezés    
    
    @FXML
    public void Registration()
    {
        String uname = username.getText();
        if (uname.isEmpty()) {
            System.out.println("Please enter a username!");
            return;
        }
        String pw = password.getText();
        if (pw.isEmpty()) {
            System.out.println("Please enter a password!");
            return;
        }
        
        if (model.getUser(uname) != null) {
            System.out.println("Username is already exist.");
            username.clear(); //textfieldek ürítése
            password.clear();
            return;
        }      
        
        User tmp = new User(uname, pw);
        model.getUsers().add(tmp);        
        System.out.println("Succesful registration.");
        username.clear();
        password.clear();
    }
    
    
    @FXML
    public void Login()
    {   
        String uname = username.getText();
        if (uname.isEmpty()) {
            System.out.println("Please enter a username!");
            return;
        }
        String pw = password.getText();
        if (pw.isEmpty()) {
            System.out.println("Please enter a password!");
            return;
        }
        
        if (actual_user != null) { //ha az el?z? felhazsnáló még nem jelentkezett ki
            System.out.println("The previous user has to log out!\n");
            username.clear(); //textfield ürítése
            password.clear();
            return;
        }
            
        User tmp = model.getUser(username.getText()); //beírt név alapján megpróbálja megkeresni a usert a Modelben lév? listában
        if (tmp == null) {
            System.out.println("User does not exist.");
            username.clear();
            password.clear();
            return;
        }
        if (!tmp.isCorrectPassword(password.getText())) {
            System.out.println("Incorrect password.");
            username.clear();
            password.clear();
            return;
        }
        actual_user = tmp; //ha helyes a jelszó, bejelentkezik
        System.out.printf("Succesful login. The actual user is %s\n", actual_user.name);
        gp.getChildren().clear(); //"kitakarítja" a néz?teret az el?z? felhasználó után
        username.clear(); //meg a textfieldeket is
        password.clear();
    }
    
    @FXML
    public void Logout() {
        actual_user = null;
        System.out.println("Succesful logout.");        
    }
     
   //a Show tabon lév? 3 film közötti választás
   @FXML
   public void button0() {
       actual_show = (Show) model.getShows().get(0);
       loadMovie();
   }
   
   @FXML
   public void button1() {
       actual_show = (Show) model.getShows().get(1);
       loadMovie();
   }
   
   @FXML
   public void button2() {
       actual_show = (Show) model.getShows().get(2);
       loadMovie();
   }
   
    //innent?l kezdve minden az aktuális bejelentkezett felhazsnálóra és a kiválasztott filmre vonatkozik
   
   public void loadMovie(){
        gp.getChildren().clear();        
        for (int i = 1; i < actual_show.room.length; i++)
            gp.add(new Label(Integer.toString(i)), 0, i);
        for (int i = 1; i < actual_show.room[0].length; i++)
            gp.add(new Label(Integer.toString(i)), i, 0);
        
        for (int i = 1; i < actual_show.room.length; i++){            
            for (int j = 1; j < actual_show.room[i].length; j++){
                Seat s = new Seat(i-1, j-1);
                Button b = new Button();
                if (actual_user.hasSeat(actual_show, s))
                    b.setText("O");
                else if (actual_show.isBooked(s))
                    b.setText("X");
                else
                    b.setText(" ");
                b.setId(Integer.toString(i-1) + "-" + Integer.toString(j-1));
                b.setOnAction((ActionEvent event) -> {
                    actual_seats.add(new Seat(b.getId()));
                    b.setText("o");
                });
                gp.add(b, j, i);
            }
        }        
    }
    
    
    //foglalás gomb megnyomása (lehet felesleges volt külön metódusba szétszedni a foglalástól, szerintem így átláthatóbb)
    public void bookButtonPushed(){
        if (actual_user == null) { //ha nincs bejelentkezett felhasználó
            System.out.println("Please log in!");
            return;
        }
        if (actual_show == null) { //ha nincs kiválasztott film
            System.out.println("Please choose a movie!");
            return;
        }
        if (actual_seats.isEmpty()) { //ha nincs kiválasztott hely
            System.out.println("Please choose a seat!");
            return;
        } 
        //foglalás metódus meghívása
        Booking(actual_seats);
    }
    
    public void deleteButtonPushed(){ //hely törlése
        if (actual_user == null) {
            System.out.println("Please log in!");
            return;
        }
        if (actual_seats.isEmpty()) { //ha nincs kiválasztott hely
            System.out.println("Please choose a seat!");
            return;
        } 
        //film már ígyis-úgyis van kiválasztva        
        deleteBooking(actual_seats);
    }
    
    
    //foglalás
    public void Booking(HashSet<Seat> seats) {
        int success = actual_seats.size();
        for (Seat s : seats) {
            if  (actual_show.isBooked(s)) { //vizsgálja, hogy szabad-e még a hely
                System.out.printf("%s seat is already reserved.\n", s.toString());
                success--;
                continue;            
            }
           actual_show.room[s.row][s.column] = true; //ha igen, most már nem
           actual_user.addBooking(actual_show, s); //hozzáaadja a felhasználó listájához            
        }              
        
        System.out.printf("%d seat(s) %s succesfully booked.\n", success, (success > 1) ? "are" : "is");
        System.out.printf("%s's seats:\n", actual_user.name); //felhasználó foglalásainak kilistázása
        System.out.println(actual_user.printBooking());
        loadMovie();
        actual_seats.clear();
    }
    
    
    //törlés
    public void deleteBooking(HashSet<Seat> seats) {
        int success = actual_seats.size();
        for (Seat s : seats) {
            if (!actual_show.isBooked(s)){ //foglalt-e egyáltalán a hely
                System.out.printf("%s seat is empty.\n", s.toString());
                success--;
                continue;                
            }
            if (!actual_user.hasSeat(actual_show, s)){ //a felhasználóé-e a foglalás, amit törölni akar
                System.out.printf("%s seat is not yours.\n", s.toString());
                success--;
                continue;
            }
            actual_user.deleteBooking(actual_show, s); //törli a foglalást a felhasználó listájáról
            actual_show.room[s.row][s.column] = false; //a hely újra szabad            
        } 
        
        System.out.printf("%d seat(s) %s succesfully deleted.\n", success, (success > 1) ? "are" : "is");
        System.out.printf("%s's seats:\n", actual_user.name); //maradék foglalások kilistázása
        System.out.println(actual_user.printBooking());
        loadMovie();
        actual_seats.clear();        
    }
           
}