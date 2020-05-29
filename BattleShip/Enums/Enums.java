package Enums;

import javax.swing.JButton;

public class Enums {
    
    //---Board Enum---//
    public static enum EnumBoxStates {
        
        EMPTY,
        SHIP,
        BOX,
        TOUCHED,
        MISS;

    }

    //---Ship Enum---//
    public static enum EnumFocusShip {
        
        ON,
        OFF;

    }

    //---Ship Enum---//
    public static enum EnumStatusShip {
        
        IN,
        OUT;

    }

    //---Ship Enum---//
    public static enum EnumShipButton {
    
        SHIP1(5, new JButton()), 
        SHIP2(4, new JButton()), 
        SHIP3(3, new JButton()), 
        SHIP4(3, new JButton()),
        SHIP5(2, new JButton());
    
        private int shipLen;
            public int getShipLen() {return (this.shipLen);}

        private JButton btn;
            public JButton getButton() {return (this.btn);}
    
        EnumShipButton(int shipLen, JButton btn){
            
            this.shipLen = shipLen;
            this.btn = btn;
        }

    }

    //---Ship Enum---//
    public static enum EnumShipDirection {
    
        RIGHT(new JButton("RIGHT")),
        LEFT(new JButton("LEFT")),
        TOP(new JButton("TOP")),
        BOTTOM(new JButton("BOTTOM"));

        private JButton btn;
            public JButton getButton() {return (this.btn);}
        
        EnumShipDirection(JButton btn){
    
            this.btn = btn;
        }

    }

    public static enum GamePhases {

        ENEMY_CHOICE,
        BOARD_CONSTRUCTION,
        CHECK_CONTINUE_ANSWER,
        ATTACK_PHASE,
        WINNING_PHASE;

    }

    public static enum Opponent {

        PLAYER1,
        PLAYER2;

    }

    public static enum OpponentType {

        HUMAN,
        COMPUTER;
        
    }
}