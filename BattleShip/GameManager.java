
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Control.Control;
import Enums.Enums.GamePhases;
import Enums.Enums.Opponent;
import Enums.Enums.OpponentType;
import Model.Player;
import View.GUIManager;

public class GameManager {

    private Timer gameTime;

    private Player p1;
    private Player p2;

    private Control control;
    private GUIManager GUI;

    private int delay;
    private boolean alreadyDisplayed;

    public GameManager() {

        this.delay = 0;
        this.alreadyDisplayed = false;

        p1 = new Player("Player1");
        p2 = new Player("Player2");

        control = new Control(p1, p2);
        p1.setControl(control);
        p2.setControl(control);
        GUI = new GUIManager(control);

        control.setActualPhase(GamePhases.ENEMY_CHOICE);
        control.setActualPlayer(Opponent.PLAYER1);
        
    }

    public void gameRunning(){

        gameTime = new Timer(15, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

                if (control.getActualPhase() == GamePhases.ENEMY_CHOICE)
                {
                    GUI.addEnemySelector();

                    if (control.getJumpNextPhase() == true)
                    {
                        control.setActualPhase(GamePhases.BOARD_CONSTRUCTION);
                        GUI.removeEnemySelector();
                        control.setJumpNextPhase(false);
                    }   
                }
                else if (control.getActualPhase() == GamePhases.BOARD_CONSTRUCTION)
                {
                    if (control.getActualPlayer() == Opponent.PLAYER1)
                    {
                        whosTurnPanel(1);
                        boardConstructionPhase(p1, Opponent.PLAYER2);
                    }
                    else if (control.getActualPlayer() == Opponent.PLAYER2)
                    {
                        if (control.getEnemyType() == OpponentType.HUMAN)
                        {   
                            whosTurnPanel(2);
                            boardConstructionPhase(p2, Opponent.PLAYER1);
                        }
                        else if (control.getEnemyType() == OpponentType.COMPUTER)
                            computerBuildBoard();
                    }

                    if (control.getJumpNextPhase() == true)
                    {
                        control.setActualPhase(GamePhases.ATTACK_PHASE);
                        GUI.removeShipDirectionPanel();
                        GUI.removeShipSelectorPanel();
                        GUI.removeGridBuildBoard();
                        control.setJumpNextPhase(false);
                    }   
                }
                else if (control.getActualPhase() == GamePhases.ATTACK_PHASE)
                {
                    if (control.getFinishPhase() == false)
                    {
                        if (control.getActualPlayer() == Opponent.PLAYER1)
                            attackHumanEnemyPhase(p1);
                        else if (control.getActualPlayer() == Opponent.PLAYER2)
                        {
                            if (control.getEnemyType() == OpponentType.HUMAN)
                                attackHumanEnemyPhase(p2);
                            else if (control.getEnemyType() == OpponentType.COMPUTER)
                            {
                                GUI.removeAttackBoard();
                                p2.computerAttackBoard(p1);
                                GUI.addComputerAttackBoard();
                            }
                        }  
                    }
                    else
                    {
                        delay++;
                        if (delay == 50)
                        {
                            if (control.getActualPlayer() == Opponent.PLAYER1)
                                control.setActualPlayer(Opponent.PLAYER2);
                            else if (control.getActualPlayer() == Opponent.PLAYER2)
                            {
                                control.setActualPlayer(Opponent.PLAYER1);
                                GUI.removeComputerAttackBoard();
                            }

                            control.isThereAWinner();
                            delay = 0;
                            GUI.setGridAlreadyClicked(false);
                            if (control.getWinner() != null)
                            {
                                control.setActualPhase(GamePhases.WINNING_PHASE);
                                GUI.removeAttackBoard();
                                GUI.removeMyBoard();
                            }
                            control.setFinishPhase(false);
                        }
                    }
                }
                else if (control.getActualPhase() == GamePhases.WINNING_PHASE)
                {
                    winningBoardConstructionPhase();

                    if (control.getJumpNextPhase() == true)
                    {
                        control.setActualPhase(GamePhases.ENEMY_CHOICE);
                        control.setActualPlayer(Opponent.PLAYER1);
                        resetAllDataNewGame();
                        GUI.removeWinningPanel();
                        control.setJumpNextPhase(false);
                        control.setFinishPhase(false);
                    }
                }
                GUI.repaint();
            }
		});

        gameTime.start();
    }

    private void boardConstructionPhase(Player player, Opponent nextPlayer) {

        control.setControl(player);

        GUI.addShipDirectionPanel();
        GUI.addShipSelectorPanel();
        GUI.addGridBuildBoard();

        if (control.allShipIn() == true)
        {
            GUI.addContinuePanel();

            if (control.getFinishPhase() == true)
            {
                delay = 0;
                alreadyDisplayed = false;

                GUI.removeContinuePanel();
                if (control.getContinueRunning() == false)
                    control.resetBoardAndShip(control.getBoard(), control.getShipArr());
                else
                {
                    control.setContinueRunning(false);
                    control.setActualPlayer(nextPlayer);

                    if (control.getActualPlayer() == Opponent.PLAYER1)
                        control.setJumpNextPhase(true);
                }
                control.setFinishPhase(false);
                GUI.resetAllDirectionButton();
                GUI.resetAllShipSelectorButton();
            }
        }
    }

    private void computerBuildBoard() {

        p2.setControl(control);

        while (p2.computerBoardBuilding() == false);

        control.setJumpNextPhase(true);
        control.setActualPlayer(Opponent.PLAYER1);

    }

    private void attackHumanEnemyPhase(Player player) {

        control.setControl(player);

        GUI.addMyBoard();
        GUI.addAttackBoard();

    }

    private void winningBoardConstructionPhase() {

        GUI.updateWinningPanel();
        GUI.updateWinnerName();
        GUI.addWinningPanel();

        delay++;
        if (delay == 150)
            GUI.addPlayAgain();

        if (control.getFinishPhase() == true)
        {
            GUI.removePlayAgain();

            if (control.getContinueRunning() == true)
            {
                control.setJumpNextPhase(true);
                control.setContinueRunning(false);
            }
            else 
            {
                gameTime.stop();
                GUI.dispose();
                System.exit(0);
            }

            delay = 0;
        }
        
    }

    private void whosTurnPanel(int index) {

        if (alreadyDisplayed == false)
        {
            if (index == 1)
                GUI.addP1IsBuildingBoard();
            else 
                GUI.addP2IsBuildingBoard();

            delay++;
            if (delay == 100)
            {
                if (index == 1)
                    GUI.removeP1IsBuildingBoard();
                else 
                    GUI.removeP2IsBuildingBoard();

                alreadyDisplayed = true;
            }
        }
    }

    private void resetAllDataNewGame() {
     
        control.setWinner(null);
        
        control.setControl(p1);
        control.resetBoardAndShip(control.getBoard(), control.getShipArr());
        control.setControl(p2);
        control.resetBoardAndShip(control.getBoard(), control.getShipArr());
        p1.setHP(17);
        p2.setHP(17);
    }
}
