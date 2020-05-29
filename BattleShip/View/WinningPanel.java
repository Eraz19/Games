package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinningPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel winningText;
        public JLabel getWinningText() {return (this.winningText);}

    private JLabel winnerName;
        public JLabel getWinnerName() {return (this.winnerName);}
    
    public WinningPanel(int GUIWidth, int GUIHeight) {

        this.setBounds(0, 0, GUIWidth, GUIHeight);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);

        winningText = new JLabel();
        winningText.setHorizontalAlignment(JLabel.CENTER);
        winningText.setBounds(0, GUIHeight / 13, GUIWidth , GUIHeight / 2);
        winningText.setFont(new Font("Ubuntu Light", Font.BOLD, 70));
        winningText.setForeground(Color.WHITE);

        winnerName = new JLabel();
        winnerName.setHorizontalAlignment(JLabel.CENTER);
        winnerName.setBounds(0, GUIHeight / 4, GUIWidth , GUIHeight / 2);
        winnerName.setFont(new Font("Ubuntu Light", Font.BOLD, 150));
        winnerName.setForeground(Color.WHITE);

        this.add(winningText);
        this.add(winnerName);

        setVisible(false);
    }

}