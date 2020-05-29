package View;

import java.awt.Color;

import Control.Control;
import Enums.Enums.EnumBoxStates;
import Model.Board;

public class AttackBoard extends GridPanel {

    private static final long serialVersionUID = 1L;

    private Board actBoard;

    public AttackBoard(int posX, int posY, int pnlSize, int rectSize, Control control, GUIManager GUI) {
        
        super(posX, posY, pnlSize, rectSize, control, GUI);

        event = new AttackBoardEvent(control, GUI, rectSize);
        
        this.addMouseListener(event);
        this.addMouseMotionListener(event);

    }

    @Override
    protected Color setColorForBoxGrid(int row, int col) {
        
        actBoard = control.getEnemyPlayer().getBoard();

        if (actBoard.getBoard()[row][col] == EnumBoxStates.TOUCHED)
            return (Color.BLUE);
        else if (actBoard.getBoard()[row][col] == EnumBoxStates.MISS)
            return (Color.GREEN);

        return (Color.GRAY);
    }
    
}