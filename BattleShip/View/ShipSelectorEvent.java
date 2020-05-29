package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;

import Control.Control;
import Enums.Enums.EnumFocusShip;
import Enums.Enums.EnumShipDirection;
import Enums.Enums.EnumStatusShip;
import Model.Ship;

public class ShipSelectorEvent implements ActionListener {

    private Control control;
    private GUIManager GUI;

    public ShipSelectorEvent(Control control, GUIManager GUI) {

        this.control = control;
        this.GUI = GUI;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Ship actualShip = control.getShipAccordToButton((JButton)event.getSource());
        JButton actualBtn = ((JButton) event.getSource());

        if(GUI.getIsPanelCovered() == false)
        {
            if (((actualBtn.getBackground() == Color.LIGHT_GRAY) || (actualBtn.getBackground() == Color.BLACK)) && (control.allShipFocusOff() == true))
            {
                if (((JButton) event.getSource()).getBackground().equals(Color.BLACK))
                {
                    actualShip.setStatus(EnumStatusShip.OUT);
                    control.getBoard().removeShip(actualShip);
                    control.getBoard().removeShipBox(actualShip);
                }
                actualBtn.setBackground(Color.GRAY);
                actualShip.setFocus(EnumFocusShip.ON);
                resetDirectionButton();
    
                EnumShipDirection.RIGHT.getButton().setBackground(Color.LIGHT_GRAY);
                EnumShipDirection.RIGHT.getButton().setForeground(Color.DARK_GRAY);
            } 
            else if (((JButton) event.getSource()).getBackground().equals(Color.GRAY)) 
            {
                ((JButton) event.getSource()).setBackground(Color.LIGHT_GRAY);
                actualShip.setFocus(EnumFocusShip.OFF);
                resetDirectionButton();
            }
        }

    }

    private void resetDirectionButton() {

        for (EnumShipDirection direction : EnumShipDirection.values()) 
        {
            direction.getButton().setBackground(Color.GRAY);
            direction.getButton().setForeground(Color.WHITE);
        }

    }
}