package View;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Control.Control;

abstract public class GridPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;

    protected Control control;
    protected GUIManager GUI;

    protected GridEvent event;

    protected int pnlSize;
    protected int posX, posY;
    protected int rectSize, spaceBtnElm = 4;
    protected int boxRow, boxCol;

    public GridPanel(int posX, int posY, int pnlSize, int rectSize, Control control, GUIManager GUI) {
        
        this.control = control;
        this.GUI = GUI;
        
        this.pnlSize = pnlSize;
        this.posX = posX;
        this.posY = posY;
        this.rectSize = rectSize;

        this.setBounds(posX, posY, pnlSize, pnlSize);
        this.setOpaque(true);
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setLayout(null);

        this.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (int row = 0; row < 10; ++row)
        {
            for (int col = 0; col < 10; ++col)
            {
                g.setColor(setColorForBoxGrid(row, col));

                if (row == 0 || col == 0)
                    g.fillRect(((col * rectSize) + (spaceBtnElm + 3)), ((row * rectSize) + (spaceBtnElm + 3)), rectSize - spaceBtnElm, rectSize - spaceBtnElm);
                else
                    g.fillRect((((col * rectSize) + 3) + spaceBtnElm), (((row * rectSize) + 3) + spaceBtnElm), rectSize - spaceBtnElm, rectSize - spaceBtnElm);
            }
        }
        
    }

    abstract protected Color setColorForBoxGrid(int row, int col);
}