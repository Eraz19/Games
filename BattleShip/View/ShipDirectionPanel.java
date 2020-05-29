package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import Control.Control;
import Enums.Enums.EnumShipDirection;

public class ShipDirectionPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int pnlWidth;
    private int pnlHeight;

    private ShipDirectionEvent event;
    
    public ShipDirectionPanel(int pnlWidth, int pnlHeight, Control control, GUIManager GUI) {

        this.pnlWidth = pnlWidth;
        this.pnlHeight = pnlHeight;

        this.setBounds(750, 470, pnlWidth, pnlHeight);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);

        event = new ShipDirectionEvent(control, GUI);
        
        this.setVisible(false);
    }

    // ---DirectionButton Builder---//
    public void buildDirectionButton() {

        int btnWidth = pnlWidth / 3;
        int btnHeight = pnlHeight / 4;

        for (EnumShipDirection direction : EnumShipDirection.values()) 
        {
            if (direction == EnumShipDirection.RIGHT)
                setDirectionButton(direction, (pnlWidth - btnWidth), ((pnlHeight / 2) - (btnHeight / 2)), btnWidth, btnHeight);
            else if (direction == EnumShipDirection.LEFT)
                setDirectionButton(direction, 0, ((pnlHeight / 2) - (btnHeight / 2)), btnWidth, btnHeight);
            else if (direction == EnumShipDirection.TOP)
                setDirectionButton(direction, ((pnlWidth / 2) - (btnWidth / 2)), 0, btnWidth, btnHeight);
            else if (direction == EnumShipDirection.BOTTOM)
                setDirectionButton(direction, ((pnlWidth / 2) - (btnWidth / 2)), (pnlHeight - btnHeight), btnWidth, btnHeight);

            this.add(direction.getButton());
        }

    }

    private void setDirectionButton(EnumShipDirection direction, int posX, int posY, int btnWidth, int btnHeight) {

        JButton actualBtn = direction.getButton();

        actualBtn.setBounds(posX, posY, btnWidth, btnHeight);
        actualBtn.setBorderPainted(false);
        actualBtn.setFocusPainted(false);
        actualBtn.setBackground(Color.GRAY);
        actualBtn.setForeground(Color.WHITE);

        actualBtn.addActionListener(event);
        
    }
}