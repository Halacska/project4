package hu.unideb.inf.szekfoglalo_maven_fx.model;

/*
    Egy adott helyet reprezent�l� oszt�ly.
    Mivel a controller �gyis csak egy adott el?ad�ssal dolgozik egyszerre,
    csak a hely sor- �s oszlopsz�m mez?je van.  
*/


public class Seat {
        public int row;
        public int column;

        public Seat(int row, int column) { //konstruktor inttel megadott form�hoz
            this.row = row;
            this.column = column;
        }
        
        public Seat(String s) { //konstruktor a button id-k szerinti form�hoz
            String[] tmp = s.split("-");
            this.row = Integer.parseInt(tmp[0]);
            this.column = Integer.parseInt(tmp[1]);
        }
        
        
        @Override
        public String toString() {
            return (row+1) + "-" + (column+1); //+1 a null�t�l val� indexel�s miatt
        } 

    
    /*public boolean equals(Object obj) {
        final Seat other = (Seat) obj;
        if (this.row != this.row || this.column != other.column)
            return false;
        return true;
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.row;
        hash = 61 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seat other = (Seat) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
    
    }
