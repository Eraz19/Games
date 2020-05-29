package Control;

import javax.swing.JButton;

import Enums.Enums.EnumBoxStates;
import Enums.Enums.EnumFocusShip;
import Enums.Enums.EnumShipButton;
import Enums.Enums.EnumShipDirection;
import Enums.Enums.EnumStatusShip;
import Enums.Enums.GamePhases;
import Enums.Enums.Opponent;
import Enums.Enums.OpponentType;
import Model.Board;
import Model.Player;
import Model.Point;
import Model.Ship;

public class Control {

    private Player p1;
        public Player getPlayer1() {return (p1);}

    private Player p2;
        public Player getPlayer2() {return (p2);}

    private Opponent actualPlayer;
        public Opponent getActualPlayer() {return (this.actualPlayer);}
        public void setActualPlayer(Opponent answer) {this.actualPlayer = answer;}

    private Ship[] shipArr;
        public Ship[] getShipArr() {return (this.shipArr);}
        public void setShipArr(Ship[] newShipArr) {this.shipArr = newShipArr;}

    private Board board;
        public Board getBoard() {return (this.board);}
        public void setBoard(Board newBoard) {this.board = newBoard;}

    private OpponentType enemyType;
        public OpponentType getEnemyType() {return (this.enemyType);}
        public void setEnemyType(OpponentType newEnemy) {this.enemyType = newEnemy;}

    private boolean continueRunning;
        public boolean getContinueRunning() {return (this.continueRunning);}
        public void setContinueRunning(boolean answer) {this.continueRunning = answer;}

    private boolean finishPhase;
        public boolean getFinishPhase() {return (this.finishPhase);}
        public void setFinishPhase(boolean answer) {this.finishPhase = answer;}

    private boolean jumpNextPhase;
        public boolean getJumpNextPhase() {return (this.jumpNextPhase);}
        public void setJumpNextPhase(boolean answer) {this.jumpNextPhase = answer;} 
        
    private GamePhases actualPhase;
        public GamePhases getActualPhase() {return (this.actualPhase);}
        public void setActualPhase(GamePhases answer) {this.actualPhase = answer;}

    private Player winner;
        public Player getWinner() {return (this.winner);}
        public void setWinner(Player newWinner) {this.winner = newWinner;}

    public Control(Player p1, Player p2) {

        setControl(p1);
       
        this.p1 = p1;
        this.p2 = p2;
        this.finishPhase = false;
    }

    public void setControl(Player player) {

        setShipArr(player.getShipArr());
        setBoard(player.getBoard());

    }

    public Player getEnemyPlayer() {

        if (actualPlayer == Opponent.PLAYER1)
            return (p2);
        
        return (p1);
    }

//---Status of ShipArr---//
    public boolean allShipFocusOff() {

        for (Ship elem : shipArr)
            if (elem.getFocus().equals(EnumFocusShip.ON))
                return (false);

        return (true);

    }

    public boolean allShipIn() {

        for (Ship ship : shipArr)
            if (ship.getStatus() == EnumStatusShip.OUT)
                return (false);

        return (true);

    }

    public Ship shipIsOn() {
        for (Ship elem : shipArr)
            if (elem.getFocus().equals(EnumFocusShip.ON))
                return (elem);

        return (null);

    }

//---Convertor Button to Ship---//
    public Ship getShipAccordToButton(JButton btn) {

        EnumShipButton selectedShip = getValueOfShipButton(btn);

        for (int index = 0; index < 5; ++index)
            if (shipArr[index].getNameShip() == selectedShip)
                return (shipArr[index]);

        return (null);

    }

    public EnumShipButton getValueOfShipButton(JButton btn) {

        for (EnumShipButton actBtn : EnumShipButton.values())
            if (actBtn.getButton() == btn)
                return (actBtn);

        return (null);

    }

//---Convertor Ship to Button---//
    public JButton getButtonAccordToShip(Ship ship) {

        for (EnumShipButton actShip : EnumShipButton.values())
            if (actShip == ship.getNameShip())
                return (actShip.getButton());

        return (null);

    }

//---Convertor Button to Direction---//
    public EnumShipDirection getValueOfDirectionButton(JButton btn) {

        for (EnumShipDirection actBtn : EnumShipDirection.values())
            if (actBtn.getButton() == btn)
                return (actBtn);

        return (null);

    }

//----Check validity placement----//
    // Go from the Point Up/Left to tho Point Down/Right (without exceeding the
    // limits of the box) of the shipBoxArea checked
    public boolean isAlreadyAShipHere(Board bd, Ship ship) {

        ship.findBoxArea(ship.getDirection(), ship.getShipCoord());

        for (int iRow = ship.getShipBox()[0].getRow(); iRow <= ship.getShipBox()[1].getRow(); ++iRow)
            for (int iCol = ship.getShipBox()[0].getCol(); iCol <= ship.getShipBox()[1].getCol(); ++iCol)
                if (bd.getBoard()[iRow][iCol] != EnumBoxStates.EMPTY)
                    return (true);

        return (false);

    }

    // Check if the coordonate with the direction don't make the Ship go out of
    // Board bounds, if yes assign a new value (the closest
    // from the original one) to the coordinate Point
    public Point checkCoordInBounds(Ship actShip, int y, int x) {

        Point checkedPoint = new Point();

        if (x < actShip.getLen() && actShip.getDirection() == EnumShipDirection.LEFT)
            setNewCoordValues(checkedPoint, y, (actShip.getLen() - 1));
        else if (x > (board.getColMax() - (actShip.getLen() + 1)) && actShip.getDirection() == EnumShipDirection.RIGHT)
            setNewCoordValues(checkedPoint, y, (board.getColMax() - (actShip.getLen())));
        else if (y > (board.getRowMax() - (actShip.getLen() + 1)) && actShip.getDirection() == EnumShipDirection.BOTTOM)
            setNewCoordValues(checkedPoint, (board.getRowMax() - (actShip.getLen())), x);
        else if (y < actShip.getLen() && actShip.getDirection() == EnumShipDirection.TOP)
            setNewCoordValues(checkedPoint, (actShip.getLen() - 1), x);
        else
            setNewCoordValues(checkedPoint, y, x);

        return (checkedPoint);

    }

    // Change coordonate to stay in the Board bounds
    private void setNewCoordValues(Point newPoint, int valueForRow, int valueForCol) {

        newPoint.setRow(valueForRow);
        newPoint.setCol(valueForCol);

    }

//---Reset the board and the all the ships---//

    public void resetBoardAndShip(Board board, Ship[] shipArr) {

        board.initBoard();
        
        for (Ship ship : shipArr)
            ship.setStatus(EnumStatusShip.OUT);

        for (Ship ship : shipArr)
            ship.setDirection(EnumShipDirection.RIGHT);
    }

    public void isThereAWinner() {

        if (p1.getHp() == 0)
            winner = p2;
        else if (p2.getHp() == 0)
            winner = p1;
            
    }
}