package View;

import java.awt.event.MouseEvent;

import Control.Control;
import Enums.Enums.EnumBoxStates;
import Enums.Enums.Opponent;
import Model.Board;

public class AttackBoardEvent extends GridEvent {

    private Board actBoard;

    private Control control;
    private GUIManager GUI;
    
    public AttackBoardEvent(Control control, GUIManager GUI, int rectSize) {
        
        super(control, rectSize);
        
        this.control = control;
        this.GUI = GUI;

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        
        int boxRow = mouseY / rectSize;
        int boxCol = mouseX / rectSize;
        
        actBoard = control.getEnemyPlayer().getBoard();
        if (GUI.getGridAlreadyClicked() == false)
        {
            if (boxRow >= 0 && boxRow < 10 && boxCol >= 0 && boxCol < 10)
            {
                if (actBoard.getBoard()[boxRow][boxCol] != EnumBoxStates.TOUCHED && actBoard.getBoard()[boxRow][boxCol] != EnumBoxStates.MISS)
                {
                    if (actBoard.getBoard()[boxRow][boxCol] == EnumBoxStates.SHIP)
                    {
                        actBoard.getBoard()[boxRow][boxCol] = EnumBoxStates.TOUCHED;
                        if (control.getActualPlayer() == Opponent.PLAYER1)
                            control.getPlayer2().setHP(control.getPlayer2().getHp() - 1);
                        else
                            control.getPlayer1().setHP(control.getPlayer1().getHp() - 1);
                    }
                    else
                        actBoard.getBoard()[boxRow][boxCol] = EnumBoxStates.MISS;
                    
                    GUI.setGridAlreadyClicked(true);
                    control.setFinishPhase(true);
                }
            }
        }

    }
    
}