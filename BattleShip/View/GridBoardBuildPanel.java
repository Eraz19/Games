package View;

import java.awt.Color;

import Control.Control;
import Enums.Enums.EnumBoxStates;
import Enums.Enums.EnumShipDirection;
import Model.Board;
import Model.Point;
import Model.Ship;

public class GridBoardBuildPanel extends GridPanel {

    private static final long serialVersionUID = 1L;

    private Board actBoard;

    public GridBoardBuildPanel(int posX, int posY, int pnlSize, int rectSize, Control control, GUIManager GUI) {
        
        super(posX, posY, pnlSize, rectSize, control, GUI);

        event = new GridBoardBuildEvent(control, rectSize, GUI);
        this.addMouseListener(event);
        this.addMouseMotionListener(event);

    }

    @Override
    protected Color setColorForBoxGrid(int row, int col) {

        actBoard = control.getBoard();
        
        if (actBoard.getBoard()[row][col] == EnumBoxStates.SHIP)
            return (Color.RED);
        else if (actBoard.getBoard()[row][col] == EnumBoxStates.BOX)
            return (Color.PINK);
        else if (shipDrawInGrid(rectSize, row, col))
            return (Color.RED);
            
        return (Color.GRAY);

    }

    private boolean shipDrawInGrid(int rctSize, int row, int col) {
        
        Ship actualShip = control.shipIsOn();
        int boxRow = (event.getMouseY() / rctSize);
        int boxCol = (event.getMouseX() / rctSize);
        Point firstLimit = new Point();
        Point secondLimit = new Point();

        if (actualShip == null || event.getMouseIn() == false || boxRow == control.getBoard().getRowMax() || boxCol == control.getBoard().getColMax())
            return (false);   
        else if (actualShip.getDirection() == EnumShipDirection.RIGHT)
        {
            firstLimit.cpyPoint(control.checkCoordInBounds(actualShip, boxRow, boxCol));
            secondLimit.setPoint(boxRow, (boxCol + (actualShip.getLen() - 1)) );   
        }
        else if (actualShip.getDirection() == EnumShipDirection.LEFT)
        {
            secondLimit.cpyPoint(control.checkCoordInBounds(actualShip, boxRow, boxCol));
            firstLimit.setPoint(boxRow, (boxCol - (actualShip.getLen() - 1)) );  
        }
        else if (actualShip.getDirection() == EnumShipDirection.TOP)
        {
            secondLimit.cpyPoint(control.checkCoordInBounds(actualShip, boxRow, boxCol)); 
            firstLimit.setPoint(boxRow - (actualShip.getLen() - 1), boxCol);
        }
        else if (actualShip.getDirection() == EnumShipDirection.BOTTOM)
        {
            firstLimit.cpyPoint(control.checkCoordInBounds(actualShip, boxRow, boxCol));
            secondLimit.setPoint(boxRow + (actualShip.getLen() - 1), boxCol); 
        }

        if (row >= firstLimit.getRow() && row <= secondLimit.getRow() && col >= firstLimit.getCol() && col <= secondLimit.getCol())
            return (true);

        return (false);
        
    }
}