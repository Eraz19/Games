package Model;

import Enums.Enums.EnumFocusShip;
import Enums.Enums.EnumShipButton;
import Enums.Enums.EnumShipDirection;
import Enums.Enums.EnumStatusShip;

public class Ship {
    
    //Ship name
    private EnumShipButton name;
        public EnumShipButton getNameShip() {return (this.name);}
        public void setNameShip(EnumShipButton newName) {this.name = newName;}

    //Lenght of the Ship
    private int shipLen;
        public int getLen() {return (this.shipLen);}
        public void setLen(int newLen) {this.shipLen = newLen;}

    //If the Ship is selected for making action on it is ON else it is OFF
    private EnumFocusShip focus;
        public EnumFocusShip getFocus() {return (this.focus);}
        public void setFocus(EnumFocusShip newState) {this.focus = newState;}

    //If the Ship is places on the Board the his status is IN else is OUT
    private EnumStatusShip status;
        public EnumStatusShip getStatus() {return (this.status);}
        public void setStatus(EnumStatusShip newStatus) {this.status = newStatus;}

    //Contains the direction of the Ship on the Board
    private EnumShipDirection shipDirection;
        public EnumShipDirection getDirection() {return (this.shipDirection);}
        public void setDirection(EnumShipDirection newDirection) {this.shipDirection = newDirection;}

    //Save the direction of the Ship when locked on the Board, used if want to change Ship direction
    private EnumShipDirection shipDirectionWhenLocked;
        public EnumShipDirection getDirectionWhenLocked() {return (this.shipDirectionWhenLocked);}
        public void setDirectionWhenLocked() {this.shipDirectionWhenLocked = this.shipDirection;}

    //The Coordonate of the first square of the Ship, it's a Point variable which is represented by a row variable and a col variable 
    private Point shipCoord;
        public Point getShipCoord() {return (this.shipCoord);}
        public void setCoordShip(int row, int col) {this.shipCoord.setPoint(row, col);}
        public void setCoordShip(Point newPoint) {this.shipCoord.cpyPoint(newPoint);}

    //Save the Ship Coordonate when it is locked, used if want to change Ship position
    private Point shipCoordWhenLocked;
        public Point getShipCoordWhenLocked() {return (this.shipCoordWhenLocked);}
        public void setCoordShipWhenLocked() {this.shipCoordWhenLocked.cpyPoint(shipCoord);}

    //Contains two Points who help defining the area surrounding the Ship
    //  - shipBoxArea[0] the Point Up Left 
    //  - shipBoxArea[1] the Point Down Right
    private Point[] shipBoxArea;
        public Point[] getShipBox() {return (this.shipBoxArea);}
        public void setShipBox() {findBoxArea(this.getDirection(), this.shipCoord);}

    //Save the two Points that helps defining the area surrounding the Ship, used if want to change Ship position
    private Point[] shipBoxAreaWhenLocked;
        public Point[] getShipBoxWhenLocked() {return (shipBoxArea);}
        public void setShipBoxWhenLocked() {findBoxArea(this.getDirectionWhenLocked(), this.shipCoordWhenLocked);}

    private Board board;

    public Ship(int Len, Board board) {

        this.shipLen = Len;
        this.shipDirection = EnumShipDirection.RIGHT;
        this.shipDirectionWhenLocked = EnumShipDirection.RIGHT;
        this.shipCoord = new Point();
        this.shipBoxArea = new Point[2];
        this.shipBoxArea[0] = new Point();
        this.shipBoxArea[1] = new Point();
        this.shipCoordWhenLocked = new Point();
        this.shipBoxAreaWhenLocked = new Point[2];
        this.shipBoxAreaWhenLocked[0] = new Point();
        this.shipBoxAreaWhenLocked[1] = new Point();
        this.focus = EnumFocusShip.OFF;
        this.status = EnumStatusShip.OUT;

        this.board = board;

    }

//----Setup box surrounding the ship----//
    //Find the coordonates of the Box that surroundes the Ship 
    public void findBoxArea(EnumShipDirection direction, Point coord) {

        if (direction.equals(EnumShipDirection.RIGHT))
        {
            setPointOfShipBoxArea((coord.getRow() - 1), (coord.getCol() - 1), (coord.getRow() + 1), (coord.getCol() + this.shipLen));
            checkShipBoxAreaInBounds(board.getRowMin(), board.getRowMax(), board.getColMin(), board.getColMax());
        }
        else if (direction.equals(EnumShipDirection.LEFT))
        {
            setPointOfShipBoxArea((coord.getRow() - 1), (coord.getCol() - this.shipLen), (coord.getRow() + 1), (coord.getCol() + 1));
            checkShipBoxAreaInBounds(board.getRowMin(), (board.getRowMax()), board.getColMin(), (board.getColMax()));
        }
        else if (direction.equals(EnumShipDirection.BOTTOM))
        {
            setPointOfShipBoxArea((coord.getRow() - 1), (coord.getCol() - 1), (coord.getRow() + this.shipLen), (coord.getCol() + 1));
            checkShipBoxAreaInBounds(board.getRowMin(), board.getRowMax(), board.getColMin(), board.getColMax());
        }
        else if (direction.equals(EnumShipDirection.TOP))
        {
            setPointOfShipBoxArea((coord.getRow() - this.shipLen), (coord.getCol() - 1), (coord.getRow() + 1), (coord.getCol() + 1));
            checkShipBoxAreaInBounds(board.getRowMin(), (board.getRowMax()), board.getColMin(), (board.getColMax()));
        }

    }

    //Set the value (row/col) of the surrounding Ship Box Limit Points
    private void setPointOfShipBoxArea(int valueFirstPointRow, int valueFirstPointCol, int valueSecondPointRow, int valueSecondPointCol) {

        shipBoxArea[0].setCol(valueFirstPointCol);
        shipBoxArea[0].setRow(valueFirstPointRow);
        shipBoxArea[1].setCol(valueSecondPointCol);
        shipBoxArea[1].setRow(valueSecondPointRow);

    }

    //Change coordonate of the surrounding Ship Box Limit Points to stay in the Board bounds
    private void checkShipBoxAreaInBounds(int minRowLimit, int maxRowLimit, int minColLimit, int maxColLimit) {

        if (shipBoxArea[0].getCol() < minColLimit)
            shipBoxArea[0].setCol(minColLimit);
        if (shipBoxArea[0].getRow() < minRowLimit)
            shipBoxArea[0].setRow(minRowLimit);
        if (shipBoxArea[1].getCol() >= maxColLimit)
            shipBoxArea[1].setCol(maxColLimit - 1);
        if (shipBoxArea[1].getRow() >= maxRowLimit)
            shipBoxArea[1].setRow(maxRowLimit - 1);
            
    }
}