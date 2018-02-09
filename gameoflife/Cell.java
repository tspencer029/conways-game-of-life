package gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * 
 * @author Zachary Hildred, William Walkley
 * @version 0.1
 * @since 0.1
 * 
 *        Represents a cell within the game world. A cell remembers its own
 *        state as well as who its neighbors are.
 *
 */
public class Cell extends Rectangle {

	/* Fields */
	private boolean state = false;
	private boolean nextState = false;
	private Cell north = null;
	private Cell south = null;
	private Cell west = null;
	private Cell east = null;

	/* Constructor */
	public Cell(double x, double y, double width, double height,
			boolean newState/* for testing */, Cell tempNorth, Cell tempSouth, Cell tempWest, Cell tempEast) {
		super(x, y, width, height);
		state = newState;
		changeColour();
		north = tempNorth;
		south = tempSouth;
		west = tempWest;
		east = tempEast;
	}

	private void changeColour() {

		if (state) {
			setFill(Color.BLACK);
		} else {
			setFill(Color.LIGHTGREY);
		}
	}

	/**
	 * Counts the number of adjacent living cells and checks whether this cell
	 * should be alive. Returns a boolean indicating whether a change has been
	 * made.
	 */
	public boolean prepareUpdate() {

		int liveNeighbours = 0;

		/* Checks all adjacent cells to check how many neighbours are alive */
		if (north != null && north.getState()) {
			liveNeighbours++;
		}

		if (south != null && south.getState()) {
			liveNeighbours++;
		}

		if (west != null && west.getState()) {
			liveNeighbours++;
		}

		if (east != null && east.getState()) {
			liveNeighbours++;
		}

		if (north != null && north.getWest() != null && north.getWest().getState()) {
			liveNeighbours++;
		}

		if (north != null && north.getEast() != null && north.getEast().getState()) {
			liveNeighbours++;
		}

		if (south != null && south.getWest() != null && south.getWest().getState()) {
			liveNeighbours++;
		}

		if (south != null && south.getEast() != null && south.getEast().getState()) {
			liveNeighbours++;
		}

		/*
		 * Determines whether this cell should be alive or dead at the next
		 * update by checking its current state and number of living neighbors.
		 */
		if (state) {
			if (liveNeighbours == 2 || liveNeighbours == 3) {
				nextState = true;
				return false;
			} else {
				nextState = false;
				return true;
			}
		} else {
			if (liveNeighbours == 3) {
				nextState = true;
				return true;
			} else {
				nextState = false;
				return false;
			}
		}
	}

	/**
	 * Once all cells have had updates prepared this will be run to enact the
	 * updates all at once
	 */
	public void enactUpdate() {
		state = nextState;
		changeColour();
	}

	/* Setters */
	public void setState(boolean newState) {
		state = newState;
		changeColour();
	}

	public void setNorth(Cell newNorth) {
		north = newNorth;
	}

	public void setSouth(Cell newSouth) {
		south = newSouth;
	}

	public void setWest(Cell newWest) {
		west = newWest;
	}

	public void setEast(Cell newEast) {
		east = newEast;
	}

	/* Getters */	
	public boolean getState() {
		return state;
	}

	public boolean getNextState() {
		return nextState;
	}

	public Cell getNorth() {
		return north;
	}

	public Cell getSouth() {
		return south;
	}

	public Cell getWest() {
		return west;
	}

	public Cell getEast() {
		return east;
	}

}
