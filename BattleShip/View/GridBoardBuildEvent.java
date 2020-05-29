package View;

import java.awt.Color;
import java.awt.event.MouseEvent;

import Control.Control;
import Enums.Enums.EnumFocusShip;
import Enums.Enums.EnumStatusShip;
import Model.Board;
import Model.Ship;

public class GridBoardBuildEvent extends GridEvent{

	private GUIManager GUI; 

    public GridBoardBuildEvent(Control control, int rctSize, GUIManager GUI) {

		super(control, rctSize);
		this.GUI = GUI;

    }
    
	@Override
	public void mouseClicked(MouseEvent event) {
		
		Ship actualShip = control.shipIsOn();
		Board actualBoard = control.getBoard();
		int boxRow = mouseY / rectSize;
		int boxCol = mouseX / rectSize;
		
		if (GUI.getIsPanelCovered() == false)
		{
			if (control.shipIsOn() == null)
				GUI.warningFrame("WARNING : NO SHIP SELECTED!!!", 450, 170);
			else 
			{
				if (actualShip.getStatus() == EnumStatusShip.IN)
				{
					actualBoard.removeShip(actualShip);
					actualBoard.removeShipBox(actualShip);
				}
			
				if (boxRow < actualBoard.getRowMax() && boxCol < actualBoard.getColMax())
				{
					actualShip.setCoordShip(control.checkCoordInBounds(actualShip, boxRow, boxCol));

					if (control.isAlreadyAShipHere(actualBoard, actualShip) == false)
					{
						actualShip.setDirectionWhenLocked();
						actualShip.setCoordShipWhenLocked();
						actualBoard.placeShip(actualShip);
						actualBoard.placeShipBox(actualShip);
						actualShip.setStatus(EnumStatusShip.IN);

						control.getButtonAccordToShip(actualShip).setBackground(Color.BLACK);
						actualShip.setFocus(EnumFocusShip.OFF);
					}
				}
			}
		}

	}
	
}