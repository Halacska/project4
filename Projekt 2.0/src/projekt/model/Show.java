package projekt.model;

public class Show {
    public String name;
    public boolean[][] room; //a n�z?t�r (foglalt helyek: true, szabad helyek: false)

    public Show(String name, int row, int column) {
        this.name = name;
        this.room = new boolean[row][column];
        for (boolean[] r : room) {
            for (boolean c : r)
                c = false;
        }   
    }
    
    //visszaadja, hogy foglalt-e egy hely
    public boolean isBooked(Seat s)
    {
        return room[s.row][s.column];
    }

    public boolean[][] getRoom() {
        return room;
    }

    //kirajzolja a n�z?teret (foglalt: + szabad: 0)
    public String printRoom() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] r : room) {
            for (boolean c : r) {
                if (c == false)
                    sb.append("0");
                else
                    sb.append("+");
            }
            sb.append("\n");                
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {        
        return name + "\n" + printRoom();
    }

}
