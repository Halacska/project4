package projekt.view;

import java.io.InputStream;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import projekt.model.Model;
import projekt.model.Seat;
import projekt.model.Show;
import projekt.model.User;

public class FXMLProjektController implements Initializable {
    
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML GridPane gp = new GridPane();    
    @FXML Label username_label;
    @FXML Label message_label;
    @FXML Label seats_label;    
    @FXML Button logout_btn;
    @FXML ImageView logout_img;    
    @FXML TabPane tp;
    @FXML Tab booking_tab;
    @FXML ImageView image;
    @FXML ImageView ngyk;
    @FXML ImageView kcsk;
    Model model;
    User actual_user = null; 
    Show actual_show = null;    
    HashSet<Seat> actual_seats;
    
    
    //ha kész a DB, itt majd ki lehet olvasni az adatokat a Modelbe
    public void startController(){
        logout_btn.setVisible(false); //logout button elrejtése, amíg nincs bejelentkezett felhasználó
        logout_img.setVisible(false);
        ngyk.setVisible(false);
        kcsk.setVisible(false);
        booking_tab.setDisable(true); //booking tab nem elérhet? filmválasztásig 
        actual_seats = new HashSet<Seat>(); //azért szükséges, hogy egyszerre több helyet lehessen foglalni
    }
    
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
    
    //regisztráció  
    @FXML
    public void Registration()
    {
        String uname = username.getText();
        if (uname.isEmpty()) { //ha nem írt be nevet            
            alert("Please enter a username!");
            return;
        }
        String pw = password.getText();
        if (pw.isEmpty()) { //ha nem írt be jelszót            
            alert("Please enter a password!");
            return;
        }
        
        if (model.getUser(uname) != null) { //ha már van ilyen nev? felhasználó            
            alert("Username is already exist.");
            username.clear(); //textfieldek ürítése
            password.clear();
            return;
        }      
        
        User tmp = new User(uname, pw); //user létrehozása...
        model.getUsers().add(tmp); //...és hozzáadása a modelben lév? listához        
        message_label.setText("Succesful registration.");        
        username.clear();
        password.clear();
    }
    
    //bejelentkezés
    @FXML
    public void Login()
    {   
        String uname = username.getText();
        if (uname.isEmpty()) { //ha nem írt be nevet            
            alert("Please enter a username!");
            return;
        }
        String pw = password.getText();
        if (pw.isEmpty()) { //ha nem ír be jelszót            
            alert("Please enter a password!");
            return;
        }
        
        if (actual_user != null) { //ha az el?z? felhasználó még nem jelentkezett ki            
            alert("The previous user has to log out!");
            username.clear(); //textfield ürítése
            password.clear();
            return;
        }
            
        User tmp = model.getUser(username.getText()); //beírt név alapján megpróbálja megkeresni a usert a Modelben lév? listában
        if (tmp == null) { //ha nincs a listában (tehát nincs ilyen nev? regisztrált felhasználó)
            alert("User does not exist.");
            username.clear();
            password.clear();
            return;
        }
        if (!tmp.isCorrectPassword(password.getText())) { //helytelen jelszó
            alert("Incorrect password.");
            password.clear();            
            return;
        }
        actual_user = tmp; //ha helyes a jelszó, bejelentkezik
        System.out.printf("The actual user is %s\n", actual_user.name);
        gp.getChildren().clear(); //"kitakarítja" a néz?teret az el?z? felhasználó után
        username.clear(); //meg a textfieldeket is
        password.clear();
        username_label.setText(uname);
        message_label.setText("Succesful login.");
        logout_btn.setVisible(true); //logout button láthatóvá tétele
        logout_img.setVisible(true);
        tp.getSelectionModel().selectNext(); //ugrás a "movies" tabra
    }
    
    //kijelentkezés
    @FXML
    public void Logout() {
        actual_user = null;        
        message_label.setText("Succesful logout.");
        actual_seats.clear(); //set ürítése
        message_label.setText(""); //label-ek ürítése
        username_label.setText("");
        seats_label.setText("");
        logout_btn.setVisible(false); //logout button elrejtése
        logout_img.setVisible(false);        
        tp.getSelectionModel().selectFirst(); //visszaugrás a login tabra
        booking_tab.setDisable(true); //booking tab nem elérhet?
        image.setVisible(false);
    }
     
   //a Show tabon lév? 3 film közötti választás
   @FXML
   public void button0() {
       actual_show = (Show) model.getShows().get(0); //film lekérése a modelben lév? listából             
       image.setImage(new Image(getClass().getResourceAsStream("endgame.jpg"))); //jobb oldali imageview-ba az akutális film képének betöltése
       image.setVisible(false); //egyel?re láthatatlan, ha átdob a booking-ra, láthatóvá válik (akkor számít, ha a felhasználó bejelentkezés nélkül akar filmet választani)
       loadMovie(); //a néz?tér (és a helyek állapotának) betöltése
   }
   
   @FXML
   public void button1() {
       actual_show = (Show) model.getShows().get(1);       
       image.setImage(new Image(getClass().getResourceAsStream("marvel.jpg")));
       image.setVisible(false);
       loadMovie();
   }
   
   @FXML
   public void button2() {
       actual_show = (Show) model.getShows().get(2);
       image.setImage(new Image(getClass().getResourceAsStream("pet.jpg")));
       image.setVisible(false);
       loadMovie();
   }
   
   //return button
   @FXML
   public void ret(){
       tp.getSelectionModel().selectPrevious(); //ugrás a "movies" tabra
       image.setVisible(false); //aktuális film képének eltüntetése
   }
   
    //innent?l kezdve minden az aktuális bejelentkezett felhasználóra és a kiválasztott filmre vonatkozik
   
   public void loadMovie(){
       if (actual_user == null) { //ha nincs bejelentkezett felhasználó
           alert("Please log in!");
           return;
       }
        
        
        image.setVisible(true);
        gp.getChildren().clear(); //néz?tér nullázása az el?z? betöltött film után        
        for (int i = 1; i <= actual_show.room.length; i++)
            gp.add(new Label(Integer.toString(i)), 0, i); //sorszámozás
        for (int i = 1; i <= actual_show.room[0].length; i++)
            gp.add(new Label(Integer.toString(i)), i, 0); //oszlopszámozás
        int oszlop=actual_show.room[0].length;
        if(oszlop==10)
        {
           ngyk.setVisible(true);
           kcsk.setVisible(false);
        }
        else
        {
           ngyk.setVisible(false);
           kcsk.setVisible(true);
        }
        for (int i = 0; i < actual_show.room.length; i++){            
            for (int j = 0; j < actual_show.room[i].length; j++){
                Seat s = new Seat(i, j); //adott sor- és oszlopszámú hely
                Button b = new Button(); 
                if (actual_user.hasSeat(actual_show, s))
                {                    
                    b.setStyle("-fx-background-color:GREEN;"); //CSS
                }
                else if (actual_show.isBooked(s))
                {                   
                    b.setStyle("-fx-background-color:Red;"); //CSS
                }
                else {
                    b.setStyle("-fx-background-color:rgb(130,130,130)");
                }                    
                b.setId(Integer.toString(i) + "-" + Integer.toString(j)); //button id beállítása ("sor-oszlop" formában)
                b.setOnAction((ActionEvent event) -> { //gombra kattintáskor lefutó rész
                    Seat newseat = new Seat(b.getId());
                    if (actual_seats.contains(newseat)) {
                        actual_seats.remove(newseat);
                        b.setStyle("-fx-background-color:rgb(130,130,130)"); //CSS
                    }
                    else {
                        actual_seats.add(newseat);
                        b.setStyle("-fx-background-color:yellow;"); //CSS
                    }
                                       
                });
                gp.add(b, j+1, i+1); //button hozzáadása a gridpane-hez
            }
        }
        booking_tab.setDisable(false); //"booking" tab láthatóvá tétele        
        tp.getSelectionModel().selectNext(); //ugrás a "booking" tabra 
        
    }
    
    
    //foglalás gomb megnyomása (lehet felesleges volt külön metódusba szétszedni a foglalástól, szerintem így átláthatóbb)
    public void bookButtonPushed(){
        if (actual_user == null) { //ha nincs bejelentkezett felhasználó
            alert("Please log in!");
            return;
        }
        if (actual_show == null) { //ha nincs kiválasztott film
            alert("Please choose a movie!");
            return;
        }
        if (actual_seats.isEmpty()) { //ha nincs kiválasztott hely
            alert("Please choose a seat!");
            return;
        } 
        //foglalás metódus meghívása
        Booking(actual_seats);        
    }
    
    public void deleteButtonPushed(){ //hely törlése
        String str;
        if (actual_user == null) {
            alert("Please log in!");
            return;
        }
        if (actual_seats.isEmpty()) { //ha nincs kiválasztott hely
            alert("Please choose a seat!");
            return;
        } 
        //film már ígyis-úgyis van kiválasztva 
        
        //törés metódus meghívása
        deleteBooking(actual_seats);        
    }
    
    
    //foglalás - ha egy helyet nem is sikerül, a többit lefoglalja
    public void Booking(HashSet<Seat> seats) {
        int success = actual_seats.size(); //számolja a sikeresen lefoglalt helyeket
        String str;
        for (Seat s : seats) {
            if  (actual_show.isBooked(s)) { //vizsgálja, hogy szabad-e még a hely
                success--;
                str = String.format("%s seat is already reserved.\n", s.toString());
                alert(str);
                continue;            
            }
           actual_show.room[s.row][s.column] = true; //ha igen, most már nem
           actual_user.addBooking(actual_show, s); //hozzáaadja a felhasználó listájához            
        }              
        
        str = String.format("%d seat(s) %s succesfully booked.\n", success, (success > 1) ? "are" : "is");
        message_label.setText(str);
        seats_label.setText(actual_user.printBooking());
        loadMovie(); //a néz?tér frissítése
        actual_seats.clear(); //set ürítése
    }
    
    
    //törlés - a foglalás mintájára
    public void deleteBooking(HashSet<Seat> seats) {
        int success = actual_seats.size();
        String str;
        for (Seat s : seats) {
            if (!actual_show.isBooked(s)){ //foglalt-e egyáltalán a hely
                str = String.format("%s seat is empty.\n", s.toString());
                alert(str);
                success--;
                continue;                
            }
            if (!actual_user.hasSeat(actual_show, s)){ //a felhasználóé-e a foglalás, amit törölni akar
                str = String.format("%s seat is not yours.\n", s.toString());
                alert(str);
                success--;
                continue;
            }
            actual_user.deleteBooking(actual_show, s); //törli a foglalást a felhasználó listájáról
            actual_show.room[s.row][s.column] = false; //a hely újra szabad            
        } 
        
        str = String.format("%d seat(s) %s succesfully deleted.\n", success, (success > 1) ? "are" : "is");
        message_label.setText(str);
        seats_label.setText(actual_user.printBooking());
        loadMovie();
        actual_seats.clear();        
    }     
    
    //a felugró ablakokat kezel? függvény
    public void alert(String s){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(s);
        alert.showAndWait();
    }
}