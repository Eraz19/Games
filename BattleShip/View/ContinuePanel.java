package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Control.Control;

public class ContinuePanel extends JPanel {
    
    private static final long serialVersionUID = 1L;

    private JPanel validationPanel;
    private JLabel validationText;
    private JButton answerYes;
    private JButton answerNo;

    ContinuePanelEvent event;

    private int GUIWidth;
    private int GUIHeight;

    public ContinuePanel(Control control, String text, int GUIWidth, int GUIHeight) {
        
        this.GUIWidth = GUIWidth;
        this.GUIHeight = GUIHeight;

        validationPanel = new JPanel();
        validationText = new JLabel(text, SwingConstants.CENTER);
        answerYes = new JButton("Yes");
        answerNo = new JButton("No");

        event = new ContinuePanelEvent(control);

    }

    // ---Display Validation Construction Board---//
    public void setValidationBoardBuild() {

        int panelWidth = (GUIWidth / 2);
        int panelHeight = (GUIHeight / 3);
        int btnWidth = (panelWidth / 4);
        int btnHeight = (panelHeight / 4);
        int space = (panelWidth / 5);

        this.setBounds(0, 0, GUIWidth, GUIHeight);
        this.setOpaque(false);
        this.setLayout(null);

        validationPanel.setBounds((GUIWidth / 4), (GUIHeight / 3), panelWidth, panelHeight);
        validationPanel.setBackground(Color.DARK_GRAY);
        validationPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 5));
        validationPanel.setLayout(null);

        setButtonAnswer(answerYes, space, ((panelHeight / 4) * 2), btnWidth, btnHeight);
        setButtonAnswer(answerNo, (panelWidth - (space + btnWidth)), ((panelHeight / 4) * 2), btnWidth, btnHeight);

        validationText.setBounds(0, 0, panelWidth, (panelHeight / 2));
        validationText.setFont(new Font("Ubuntu Light", Font.BOLD, 20));
        validationText.setForeground(Color.GRAY);

        validationPanel.add(answerYes);
        validationPanel.add(answerNo);
        validationPanel.add(validationText);

        this.add(validationPanel);

        this.setVisible(false);
    }

    private void setButtonAnswer(JButton btn, int posX, int posY, int btnWidth, int btnHeight) {

        btn.setBounds(posX, posY , btnWidth, btnHeight);
        btn.setBackground(Color.GRAY);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.addActionListener(event);

    }
}