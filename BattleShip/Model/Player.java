package Model;

import java.util.ArrayList;
import java.util.Random;

import Control.Control;
import Enums.Enums.EnumBoxStates;
import Enums.Enums.EnumShipButton;
import Enums.Enums.EnumShipDirection;
import Enums.Enums.EnumStatusShip;

public class Player {

    private Ship[] shipArr;
        public Ship[] getShipArr() {return (this.shipArr);}

    private Board board;
        public Board getBoard() {return (this.board);}

    private int HP = 17;
        public int getHp() {return(this.HP);}
        public void setHP(int newHP) {this.HP = newHP;}

    private String playerName;
        public String getPlayerName() {return(this.playerName);}
        public void setPlayerName(String newName) {this.playerName = newName;}

    private Random randomizer;

    private Control control;
        public void setControl(Control newControl) {this.control = newControl;}

    private boolean shipTouch;
    private Point firstShipTouchPoint;
    private Point checkedPoint;
    private boolean firstLimitReached;
    private EnumShipDirection directionToDestroyShip;
    private ArrayList<EnumShipDirection> directionAlreadyCheckedList;

    public Player(String name) {

        this.playerName = name;
        
        this.shipTouch = false;
        firstShipTouchPoint = new Point();
        checkedPoint = new Point();
        firstLimitReached = false;
        this.directionToDestroyShip = null;
        directionAlreadyCheckedList = new ArrayList<EnumShipDirection>();

        board = new Board();
        board.initBoard(); 

        shipArr = new Ship[5];
        for (EnumShipButton shipList : EnumShipButton.values())
        {
            shipArr[shipList.ordinal()] = new Ship(shipList.getShipLen(), board);
            shipArr[shipList.ordinal()].setNameShip(shipList);
        }

        randomizer = new Random();
    }

    public void resetShipArr() {

        for (Ship ship : shipArr)
        ship.setStatus(EnumStatusShip.OUT);
        
    }

//---Computer Create Board---//
    public boolean computerBoardBuilding() {

        Ship actShip;
        int countTestPlaceShip = 0;
        int numTestPlaceShipMax = 30;
        int countTestBuildingBoard = 0;
        int numTestBuildingBoardMax = 10;

        for (int elem = 0; elem < 5; ++elem)
        {
            actShip = shipArr[elem];

            while (countTestPlaceShip < numTestPlaceShipMax)
            {
                if (findPlaceForShip(actShip) == true) 
                {
                    board.placeShip(actShip);
                    board.placeShipBox(actShip);
                    break;
                }
                else 
                    ++countTestPlaceShip;
            }

            if (countTestPlaceShip == numTestPlaceShipMax)
            {
                elem = -1;
                control.resetBoardAndShip(board, shipArr);
                ++countTestBuildingBoard;
                if (countTestBuildingBoard == numTestBuildingBoardMax)
                    return (false);
            }
        }

        return (true);
    }

    private boolean findPlaceForShip(Ship actShip) {

        int row, col, indexDirection;
        EnumShipDirection direction, firstDirection;

        row = randomizer.nextInt(10);
        col = randomizer.nextInt(10);
        indexDirection = randomizer.nextInt(4);

        direction = EnumShipDirection.values()[indexDirection];
        firstDirection = direction;

        actShip.setDirection(direction);
        actShip.setCoordShip(control.checkCoordInBounds(actShip, row, col));

        while (control.isAlreadyAShipHere(board, actShip) == true)
        {
            indexDirection = ((indexDirection + 1) % 4);
            direction = EnumShipDirection.values()[indexDirection];

            if (direction == firstDirection)
                return (false);
        }

        return (true);

    }

//---Computer Attack Board---//
    public void computerAttackBoard(Player enemy) {
     
        Board actBoard = enemy.getBoard();
        
        if (shipTouch == false)
            touchShipForFirstTime(actBoard, enemy);
        else
            touchRestOfShip(actBoard, enemy);

        control.setFinishPhase(true);
    }

    private void touchShipForFirstTime(Board actBoard, Player enemy) {

        int attackRow, attackCol;

        attackRow = randomizer.nextInt(10);
        attackCol = randomizer.nextInt(10);

        while (actBoard.getBoard()[attackRow][attackCol] == EnumBoxStates.MISS || actBoard.getBoard()[attackRow][attackCol] == EnumBoxStates.TOUCHED)
        {
            attackRow = randomizer.nextInt(10);
            attackCol = randomizer.nextInt(10);
        }

        if (actBoard.getBoard()[attackRow][attackCol] == EnumBoxStates.SHIP)
        {
            actBoard.getBoard()[attackRow][attackCol] = EnumBoxStates.TOUCHED;
            this.firstShipTouchPoint.setPoint(attackRow, attackCol);
            this.checkedPoint.cpyPoint(firstShipTouchPoint);
            this.shipTouch = true;
            enemy.setHP(enemy.getHp() - 1);
        }
        else 
            actBoard.getBoard()[attackRow][attackCol] = EnumBoxStates.MISS;
    }

    private void touchRestOfShip(Board actBoard, Player enemy) {

        if (directionToDestroyShip == null)
            findDirectionToDestroy(actBoard, enemy);
        else
        {
            if (isTouched(directionToDestroyShip, actBoard, enemy) == false)
            {
                if (firstLimitReached == false)
                    setWhenFirstLimitReached(actBoard);
                else if (firstLimitReached = true)
                    resetAllAttackData();
            }
        }
    }

    private void findDirectionToDestroy(Board actBoard, Player enemy) {

        int firstDirectionToDestroyShipIndex;
        EnumShipDirection direction;

        firstDirectionToDestroyShipIndex = randomizer.nextInt(4);
        direction = EnumShipDirection.values()[firstDirectionToDestroyShipIndex];

        while (directionAlreadyChecked(direction) == true || outOffBounds(direction) || alreadyTouched(direction, actBoard))
        {
            firstDirectionToDestroyShipIndex = randomizer.nextInt(4);
            direction = EnumShipDirection.values()[firstDirectionToDestroyShipIndex];
        }

        directionToDestroyShip = direction;
        if (isTouched(direction, actBoard, enemy))
            directionAlreadyCheckedList.clear();
        else 
        {
            directionToDestroyShip = null;
            directionAlreadyCheckedList.add(direction);
        }

    }

    private boolean isTouched(EnumShipDirection direction, Board actBoard, Player enemy) {

        Point coordToCheck = getTargetedPoint(checkedPoint, direction);

        if (actBoard.getBoard()[coordToCheck.getRow()][coordToCheck.getCol()] == EnumBoxStates.SHIP)
        {
            actBoard.getBoard()[coordToCheck.getRow()][coordToCheck.getCol()] = EnumBoxStates.TOUCHED;
            checkedPoint.cpyPoint(coordToCheck);
            enemy.setHP(enemy.getHp() - 1);
            
            if (outOffBounds(directionToDestroyShip) || alreadyTouched(directionToDestroyShip, actBoard))
            {
                if (firstLimitReached == false)
                    setWhenFirstLimitReached(actBoard);
                else if (firstLimitReached = true)
                    resetAllAttackData();
            }
            
            return (true);
        }
        else 
        {
            actBoard.getBoard()[coordToCheck.getRow()][coordToCheck.getCol()] = EnumBoxStates.MISS;
            return (false);
        }
    }


    private boolean alreadyTouched(EnumShipDirection direction, Board actBoard) {

        Point coordToCheck = getTargetedPoint(checkedPoint, direction);
        int row = coordToCheck.getRow();
        int col = coordToCheck.getCol();

        if (actBoard.getBoard()[row][col] == EnumBoxStates.TOUCHED || actBoard.getBoard()[row][col] == EnumBoxStates.MISS)
            return (true);
        
        return (false);
    }

    private boolean outOffBounds(EnumShipDirection direction) {

        Point coordToCheck = getTargetedPoint(checkedPoint, direction);

        if (coordToCheck.getRow() < 0 || coordToCheck.getRow() > 9 || coordToCheck.getCol() < 0 || coordToCheck.getCol() > 9)
            return (true);

        return (false);
    }

    private Point getTargetedPoint(Point p, EnumShipDirection direction) {

        Point coordToCheck = new Point(p.getRow(), p.getCol());

        if (direction == EnumShipDirection.RIGHT)
            coordToCheck.setCol(coordToCheck.getCol() + 1);
        else if (direction == EnumShipDirection.LEFT)
            coordToCheck.setCol(coordToCheck.getCol() - 1);
        else if (direction == EnumShipDirection.TOP)
            coordToCheck.setRow(coordToCheck.getRow() - 1);
        else if (direction == EnumShipDirection.BOTTOM)
            coordToCheck.setRow(coordToCheck.getRow() + 1);

        return (coordToCheck);
        
    }

    private boolean directionAlreadyChecked(EnumShipDirection direction) {

        for (int i = 0; i < directionAlreadyCheckedList.size(); ++i)
            if (direction == directionAlreadyCheckedList.get(i))
                return (true);
        
        return (false);
    }

    private EnumShipDirection findOppositeDirection(EnumShipDirection firstDirection) {

        if (firstDirection == EnumShipDirection.RIGHT)
            return (EnumShipDirection.LEFT);
        else if (firstDirection == EnumShipDirection.LEFT)
            return (EnumShipDirection.RIGHT);
        else if (firstDirection == EnumShipDirection.TOP)
            return (EnumShipDirection.BOTTOM);
        else
            return (EnumShipDirection.TOP);
    }

    private void setWhenFirstLimitReached(Board actBoard){

        firstLimitReached = true;
        directionToDestroyShip = findOppositeDirection(directionToDestroyShip);
        checkedPoint.cpyPoint(firstShipTouchPoint);

        if (outOffBounds(directionToDestroyShip) || alreadyTouched(directionToDestroyShip, actBoard))
        {
            if (firstLimitReached = true)
                resetAllAttackData();
        }

    }

    private void resetAllAttackData() {

        shipTouch = false;
        firstLimitReached = false;
        directionToDestroyShip = null;
    }
}