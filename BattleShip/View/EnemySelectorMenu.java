package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Control.Control;

public class EnemySelectorMenu extends JPanel {
    
    private static final long serialVersionUID = 1L;

    private JButton human;
    private JButton computer;
    private JLabel text;

    private final int buttonWidth = 200;
    private final int buttonHeight = 100;

    private EnemySelectorMenuEvent event;

    public EnemySelectorMenu(int GUIWidth, int GUIHeight, Control control) {

        this.setBounds(0, 0, GUIWidth, GUIHeight);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);

        event = new EnemySelectorMenuEvent(control);

        human = new JButton("HUMAN");
        setButton(human, 350, 450);

        computer = new JButton("CPU");
        setButton(computer, ((GUIWidth - 350) - buttonWidth), 450);

        text = new JLabel("CHOOSE YOUR ENNEMY", SwingConstants.CENTER);
        text.setBounds(0, 150, GUIWidth, 300);
        text.setFont(new Font("Arial", Font.BOLD, 48));
        text.setForeground(Color.GRAY);

        add(human);
        add(computer);
        add(text);

        this.setVisible(false);
        
    }

    private void setButton(JButton actualBtn, int posX, int posY) {

        actualBtn.setBounds(posX, posY, buttonWidth, buttonHeight);
        actualBtn.setBackground(Color.GRAY);
        actualBtn.setBorderPainted(false);
        actualBtn.setFocusable(false);
        
        actualBtn.addActionListener(event);

    }
}