package View;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Control.Control;
import Enums.Enums.EnumShipButton;

public class ShipSelectorPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int pnlWidth;
    private int pnlHeight;

    private ShipSelectorEvent event;
    
    public ShipSelectorPanel(int pnlWidth, int pnlHeight, Control control, GUIManager GUI) {

        this.pnlWidth = pnlWidth;
        this.pnlHeight = pnlHeight;

        this.setBounds(750, 30, pnlWidth, pnlHeight);
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setLayout(null);

        event = new ShipSelectorEvent(control, GUI);
        
        this.setVisible(false);
    }

    // ---shipBoxSelector Builder---//
    public void buildSelectorButton() {

        for (EnumShipButton ship : EnumShipButton.values()) 
        {   
            setShipsButton(ship, pnlWidth, pnlHeight);
            this.add(ship.getButton()); 
        }

    }

    private void setShipsButton(EnumShipButton ship, int pnlWidth, int pnlHeight) {
        
        JButton actualButton = ship.getButton();

        int spaceBtwnElm = 38;
        int space = 60;
        int shipWidth = 40;

        actualButton.setLocation((spaceBtwnElm * (ship.ordinal() + 1)) + (shipWidth * ship.ordinal()), (pnlHeight - ((50 * ship.getShipLen()) + space)));
        actualButton.setSize(shipWidth, (50 * ship.getShipLen()));
        actualButton.setBorderPainted(false);
        actualButton.setFocusPainted(false);
        actualButton.setBackground(Color.LIGHT_GRAY);

        actualButton.addActionListener(event);
        
    }
}