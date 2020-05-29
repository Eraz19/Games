package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import Control.Control;
import Enums.Enums.EnumShipButton;
import Enums.Enums.EnumShipDirection;

public class GUIManager extends JFrame{

    private static final long serialVersionUID = 1L;

    private EnemySelectorMenu enemySelector; // Enemy Selector
    
    private WhoIsBuildingBoard p1IsBuildingBoard; // Board Building 
    private WhoIsBuildingBoard p2IsBuildingBoard;
    private ShipDirectionPanel shipDirectionPanel;
    private ShipSelectorPanel shipSelectorPanel;
    private GridPanel gridBuildBoard;

    private GridPanel myBoard;

    private AttackBoard attackBoard;
    private GridPanel computerAttackBoard;
    
    private ContinuePanel continuePanel;
    private ContinuePanel playAgain;
    private WinningPanel winningPanel;


    private Control control;

    private final int GUIWidth = 1200;
    private final int GUIHeight = 800;

    private boolean isPanelCovered;
        public boolean getIsPanelCovered() {return (this.isPanelCovered);}
        public void setIsPanelCovered(boolean answer) {this.isPanelCovered = answer;}

    private boolean gridAlreadyClicked;
        public boolean getGridAlreadyClicked() {return (this.gridAlreadyClicked);}
        public void setGridAlreadyClicked(boolean answer) {this.gridAlreadyClicked = answer;}

    public GUIManager (Control control) {

        super("BattleShip");

        this.setPreferredSize(new java.awt.Dimension(GUIWidth, GUIHeight));
        this.setMinimumSize(new Dimension(GUIWidth, GUIHeight));
        this.setMaximumSize(new Dimension(GUIWidth, GUIHeight));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        this.control = control;

        JLayeredPane layerPane = this.getLayeredPane();
        isPanelCovered = false;

        enemySelector = new EnemySelectorMenu(GUIWidth, GUIHeight, control);

        p1IsBuildingBoard = new WhoIsBuildingBoard("PLAYER 1 BUILD YOUR BOARD !!", GUIWidth, GUIHeight);
        p2IsBuildingBoard = new WhoIsBuildingBoard("PLAYER 2 BUILD YOUR BOARD !!", GUIWidth, GUIHeight);
        shipDirectionPanel = new ShipDirectionPanel(430, 250, control, this);
        shipDirectionPanel.buildDirectionButton();
        shipSelectorPanel = new ShipSelectorPanel(430, 420, control, this);
        shipSelectorPanel.buildSelectorButton();
        gridBuildBoard = new GridBoardBuildPanel(30, 30, 700, 69, control, this);
        
        myBoard = new MyBoard(750, 30, 430, 42, control, this);
        attackBoard = new AttackBoard(30, 30, 700, 69, control, this);
        computerAttackBoard = new ComputerAttackGrid(30, 30, 700, 69, control, this);

        continuePanel = new ContinuePanel(control, "DO YOU WAN'T TO SAVE THE BOARD ??", GUIWidth, GUIHeight);
        playAgain = new ContinuePanel(control, "DO YOU WANT TO PLAY A NEW GAME ?", GUIWidth, GUIHeight);

        winningPanel = new WinningPanel(GUIWidth, GUIHeight);
        
        layerPane.add(enemySelector, Integer.valueOf(1));

        layerPane.add(p1IsBuildingBoard, Integer.valueOf(2));
        layerPane.add(p2IsBuildingBoard, Integer.valueOf(2));
        layerPane.add(shipSelectorPanel, Integer.valueOf(1));
        layerPane.add(shipDirectionPanel, Integer.valueOf(1));
        layerPane.add(gridBuildBoard, Integer.valueOf(1));

        layerPane.add(myBoard, Integer.valueOf(1));
        layerPane.add(attackBoard, Integer.valueOf(1));
        layerPane.add(computerAttackBoard, Integer.valueOf(1));

        layerPane.add(continuePanel, Integer.valueOf(2));
        layerPane.add(playAgain, Integer.valueOf(2));

        layerPane.add(winningPanel, Integer.valueOf(1));

        this.setVisible(true);
    }

    public void addEnemySelector() {enemySelector.setVisible(true);}
    public void removeEnemySelector() {enemySelector.setVisible(false);}

    public void addP1IsBuildingBoard() {
        p1IsBuildingBoard.setValidationBoardBuild();
        isPanelCovered = true;
        p1IsBuildingBoard.setVisible(true);
    }
    public void removeP1IsBuildingBoard() {
        p1IsBuildingBoard.setVisible(false);
        isPanelCovered = false;
    }
    public void addP2IsBuildingBoard() {
        p2IsBuildingBoard.setValidationBoardBuild();
        isPanelCovered = true;
        p2IsBuildingBoard.setVisible(true);
    }
    public void removeP2IsBuildingBoard() {
        p2IsBuildingBoard.setVisible(false);
        isPanelCovered = false;
    }
    public void addShipDirectionPanel() {shipDirectionPanel.setVisible(true);}
    public void removeShipDirectionPanel() {shipDirectionPanel.setVisible(false);}
    public void addShipSelectorPanel() {shipSelectorPanel.setVisible(true);}
    public void removeShipSelectorPanel() {shipSelectorPanel.setVisible(false);}
    public void addGridBuildBoard() {gridBuildBoard.setVisible(true);}
    public void removeGridBuildBoard() {gridBuildBoard.setVisible(false);}

    public void addMyBoard() {myBoard.setVisible(true);}
    public void removeMyBoard() {myBoard.setVisible(false);}
    public void addAttackBoard() {attackBoard.setVisible(true);}
    public void removeAttackBoard() {attackBoard.setVisible(false);}
    public void addComputerAttackBoard() {computerAttackBoard.setVisible(true);}
    public void removeComputerAttackBoard() {computerAttackBoard.setVisible(false);}

    public void addWinningPanel() {winningPanel.setVisible(true);}
    public void removeWinningPanel() {winningPanel.setVisible(false);}
    public void updateWinningPanel() {winningPanel.getWinningText().setText("The Winner");}
    public void updateWinnerName() {winningPanel.getWinnerName().setText(control.getWinner().getPlayerName());}

    public void addContinuePanel() {

        continuePanel.setValidationBoardBuild();
        isPanelCovered = true;
        continuePanel.setVisible(true);

    }
    public void removeContinuePanel() {

        continuePanel.setVisible(false);
        isPanelCovered = false;
        
    }

    public void addPlayAgain() {
                
        playAgain.setValidationBoardBuild();
        isPanelCovered = true;
        playAgain.setVisible(true);
    }
    public void removePlayAgain() {

        playAgain.setVisible(false);
        isPanelCovered = false;
        
    }

    public void resetAllDirectionButton() {

        for (EnumShipDirection direction : EnumShipDirection.values()) 
        {
            direction.getButton().setBackground(Color.GRAY);
            direction.getButton().setForeground(Color.WHITE);
        }
    }

    public void resetAllShipSelectorButton() {

        for (EnumShipButton ship : EnumShipButton.values()) 
            ship.getButton().setBackground(Color.LIGHT_GRAY);

    }

    // ---Display Warning Panel---//
    public void warningFrame(String text, int width, int height) {

        JFrame warningPanel = new JFrame();
        JLabel warningText = new JLabel(text, SwingConstants.CENTER);
        warningPanel.setSize(width, height);
        warningPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        warningPanel.getContentPane().setBackground(Color.DARK_GRAY);
        warningPanel.setLocationRelativeTo(null);

        warningText.setFont(new Font("Ubuntu Light", Font.BOLD, 20));
        warningText.setForeground(Color.GRAY);
        warningPanel.add(warningText, BorderLayout.CENTER);

        warningPanel.setVisible(true);

    }
}