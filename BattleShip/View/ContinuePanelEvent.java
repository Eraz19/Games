package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.Control;

public class ContinuePanelEvent implements ActionListener {

    private Control control;

    public ContinuePanelEvent(Control control) {

        this.control = control;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Yes")
        {
            control.setContinueRunning(true);
            control.setFinishPhase(true);
        }
        else if (e.getActionCommand() == "No")
        {
            control.setContinueRunning(false);
            control.setFinishPhase(true);
        }
    }
    
}