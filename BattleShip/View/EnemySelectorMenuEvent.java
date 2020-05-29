package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.Control;
import Enums.Enums.OpponentType;

public class EnemySelectorMenuEvent implements ActionListener {

    private Control control;
    
    public EnemySelectorMenuEvent(Control control) {

        this.control = control;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
         
        if (e.getActionCommand() == "HUMAN")
        {
            control.setEnemyType(OpponentType.HUMAN);
            control.setJumpNextPhase(true);
        }
        else if (e.getActionCommand() == "CPU")
        {
            control.setEnemyType(OpponentType.COMPUTER);
            control.setJumpNextPhase(true);
        }

    }
    
}