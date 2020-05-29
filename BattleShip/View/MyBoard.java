package View;

import java.awt.Color;

import Control.Control;
import Enums.Enums.EnumBoxStates;
import Model.Board;

public class MyBoard extends GridPanel {

    private static final long serialVersionUID = 1L;

    private Board actBoard;

    public MyBoard(int posX, int posY, int pnlSize, int rectSize, Control control, GUIManager GUI) {
        
        super(posX, posY, pnlSize, rectSize, control, GUI);
        
    }

    @Override
    protected Color setColorForBoxGrid(int row, int col) {
        
        actBoard = control.getBoard();
        if (actBoard.getBoard()[row][col] == EnumBoxStates.SHIP)
            return (Color.RED);
        else if (actBoard.getBoard()[row][col] == EnumBoxStates.BOX)
            return (Color.PINK);
        else if (actBoard.getBoard()[row][col] == EnumBoxStates.TOUCHED)
            return (Color.BLUE);
        else if (actBoard.getBoard()[row][col] == EnumBoxStates.MISS)
            return (Color.GREEN);

        return (Color.GRAY);
    }
}