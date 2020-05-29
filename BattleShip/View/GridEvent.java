package View;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import Control.Control;

abstract public class GridEvent extends MouseInputAdapter{

    protected int mouseX;
        public int getMouseX() {return (this.mouseX);}
        public void setMouseX(int newMouseX) {this.mouseX = newMouseX;}

    protected int mouseY;
        public int getMouseY() {return (this.mouseY);}
        public void setMouseY(int newMouseY) {this.mouseY = newMouseY;}

    protected boolean mouseIn;
        public boolean getMouseIn() {return (this.mouseIn);}

    protected int rectSize;

    protected Control control;

    public GridEvent(Control control, int rectSize) {

        this.control = control;
        this.rectSize = rectSize;

    }

    @Override
    public void mouseMoved(MouseEvent event) {

        setMouseX(event.getX());
        setMouseY(event.getY());

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        super.mouseEntered(e);

        this.mouseIn = true;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        super.mouseExited(e);

        this.mouseIn = false;
        
    }

    @Override
    abstract public void mouseClicked(MouseEvent event);
}