package Model;

public class Point {

    private int row;
        public int getRow() {return (this.row);}
        public void setRow(int y) {this.row = y;}

    private int col;
        public int getCol() {return (this.col);}
        public void setCol(int x) {this.col = x;}

    public Point() {

        this.row = 0;
        this.col = 0;

    }

    public Point(int y, int x) {

        this.row = y;
        this.col = x;

    }

    public void setPoint(int y, int x) {

        this.row = y;
        this.col = x;

    }

    public void cpyPoint(Point other) {
        
        this.row = other.row;
        this.col = other.col;
        
    }

    public void printPoint() {System.out.println("(" + this.row + "," + this.col + ")");}
}