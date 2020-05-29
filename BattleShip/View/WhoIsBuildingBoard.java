package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WhoIsBuildingBoard extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel validationPanel;
    private JLabel validationText;

    ContinuePanelEvent event;

    private int GUIWidth;
    private int GUIHeight;

    public WhoIsBuildingBoard(String text, int GUIWidth, int GUIHeight) {
        
        this.GUIWidth = GUIWidth;
        this.GUIHeight = GUIHeight;

        validationPanel = new JPanel();
        validationText = new JLabel(text, SwingConstants.CENTER);

    }

    // ---Display Validation Construction Board---//
    public void setValidationBoardBuild() {

        int panelWidth = (GUIWidth / 2);
        int panelHeight = (GUIHeight / 3);

        this.setBounds(0, 0, GUIWidth, GUIHeight);
        this.setOpaque(false);
        this.setLayout(null);

        validationPanel.setBounds((GUIWidth / 4), (GUIHeight / 3), panelWidth, panelHeight);
        validationPanel.setBackground(Color.DARK_GRAY);
        validationPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 5));
        validationPanel.setLayout(null);

        validationText.setBounds(0, 0, panelWidth, panelHeight);
        validationText.setFont(new Font("Ubuntu Light", Font.BOLD, 20));
        validationText.setForeground(Color.GRAY);
        validationText.setVerticalTextPosition(SwingConstants.CENTER);

        validationPanel.add(validationText);

        this.add(validationPanel);

        this.setVisible(false);
    }

}