package Model;

import Enums.Enums.EnumBoxStates;
import Enums.Enums.EnumShipDirection;

public class Board {

    private EnumBoxStates[][] boardArr;
        public EnumBoxStates[][] getBoard() {return (this.boardArr);}
        public void setBoard(int row, int col, EnumBoxStates value) {boardArr[row][col] = value;}
    
    private int rowMax;
        public int getRowMax() {return (this.rowMax);}

    private int rowMin;
        public int getRowMin() {return (this.rowMin);}

    private int colMax;
        public int getColMax() {return (this.colMax);}

    private  int colMin;
        public int getColMin() {return (this.colMin);}

    public Board() {

        this.rowMax = 10;
        this.rowMin = 0;
        this.colMax = 10;
        this.colMin = 0;
        this.boardArr = new EnumBoxStates[rowMax][colMax];

    }

//----Setup Board----//

    public void initBoard() {

        for (int row = rowMin; row < rowMax; ++row)
            for (int col = colMin; col < colMax; ++col)
                this.boardArr[row][col] = EnumBoxStates.EMPTY;

    }

//----Setting of the element on the board----//
    //Set Ship on the Board
    public void placeShip(Ship ship) {

        int count = ship.getLen();
        int col = ship.getShipCoord().getCol();
        int row = ship.getShipCoord().getRow();

        if (ship.getDirection().equals(EnumShipDirection.RIGHT))
            for ( ;count > 0; --count)
                this.boardArr[row][col++] = EnumBoxStates.SHIP;
        else if (ship.getDirection().equals(EnumShipDirection.LEFT))
            for ( ;count > 0; --count)
                this.boardArr[row][col--] = EnumBoxStates.SHIP;
        else if (ship.getDirection().equals(EnumShipDirection.BOTTOM))
            for ( ;count > 0; --count)
                this.boardArr[row++][col] = EnumBoxStates.SHIP;
        else if (ship.getDirection().equals(EnumShipDirection.TOP))
            for ( ;count > 0; --count)
                this.boardArr[row--][col] = EnumBoxStates.SHIP;

    }

    //Remove Ship on the Board
    public void removeShip(Ship ship) {

        int count = ship.getLen();
        int col = ship.getShipCoord().getCol();
        int row = ship.getShipCoord().getRow();

        if (ship.getDirectionWhenLocked().equals(EnumShipDirection.RIGHT))
            for ( ;count > 0; --count)
                this.boardArr[row][col++] = EnumBoxStates.EMPTY;
        else if (ship.getDirectionWhenLocked().equals(EnumShipDirection.LEFT))
            for ( ;count > 0; --count)
                this.boardArr[row][col--] = EnumBoxStates.EMPTY;
        else if (ship.getDirectionWhenLocked().equals(EnumShipDirection.BOTTOM))
            for ( ;count > 0; --count)
                this.boardArr[row++][col] = EnumBoxStates.EMPTY;
        else if (ship.getDirectionWhenLocked().equals(EnumShipDirection.TOP))
            for ( ;count > 0; --count)
                this.boardArr[row--][col] = EnumBoxStates.EMPTY;

    }

    //Set the Box surrounding the Ship
    public void placeShipBox(Ship ship) {

        for (int row = ship.getShipBox()[0].getRow(); row <= ship.getShipBox()[1].getRow(); ++row)
            for (int col = ship.getShipBox()[0].getCol(); col <= ship.getShipBox()[1].getCol(); ++col)
                if (boardArr[row][col] == EnumBoxStates.EMPTY)
                    boardArr[row][col] = EnumBoxStates.BOX;

    }

    //Remove the Box surrounding the Ship
    public void removeShipBox(Ship ship) {

        ship.findBoxArea(ship.getDirectionWhenLocked(), ship.getShipCoordWhenLocked());
        for (int row = ship.getShipBox()[0].getRow(); row <= ship.getShipBox()[1].getRow(); ++row)
            for (int col = ship.getShipBox()[0].getCol(); col <= ship.getShipBox()[1].getCol(); ++col)
                if (boardArr[row][col] != EnumBoxStates.EMPTY)
                    boardArr[row][col] = EnumBoxStates.EMPTY;

    }

//----Display----//
    //Print the Board
    public void printBoard() {

        for (int row = rowMin; row < rowMax; ++row)
        {
            for (int col = colMin; col < colMax; ++col)
                System.out.print(" " + boardArr[row][col] + " ");
            System.out.print("\n");
        }
        
    }
}