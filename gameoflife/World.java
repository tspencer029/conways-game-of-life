package gameoflife;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * 
 * @author Zac Hildred, William Walkley
 * @version 0.1
 * @since 0.1
 *
 *        Contains a graph of all cells within this game, along with methods to
 *        update every cell at the same time.
 */
public class World {

	/*
	 * Note that because these are the default java LinkedLists the cells
	 * themselves are not the nodes of the list, rather the node contain cells.
	 * Because of this if the linkedlists are being traversed via the nodes it
	 * must be traversed horizontally before vertically.
	 */
	LinkedList<LinkedList<Cell>> map = new LinkedList<LinkedList<Cell>>();

	/* Constructs a world with a default size. */
    public World(int guiWidth, int guiHeight) {
        int displayWidth = 64;
        int displayHeight = 48;
        
        // default world used for testing
        for (int i = 0; i < displayWidth; i++) {
            LinkedList<Cell> column = new LinkedList<Cell>();
            map.add(column);
            for (int j = 0; j < displayHeight; j++) {
                /*
                 * Creates a set of three live cells that should switch back and
                 * forth between a horizontal and a rectangular line
                 */
                if ((i == 5 && (j == 4 || j == 5 || j == 6 || j == 10 || j == 11 || j == 12))) {
                    Cell cell = createCell(true, i, j, guiHeight/46, i*guiWidth/displayWidth, j*guiHeight/displayHeight);
                    map.get(i).add(cell);
                } else {
                    Cell cell = createCell(false, i, j, guiHeight/46, i*guiWidth/displayWidth, j*guiHeight/displayHeight);
                    map.get(i).add(cell);
                }
            }
        }
    }

	public Cell createCell(boolean on, int x, int y, int size, int guiX, int guiY) {

		Cell north = null;
		Cell south = null;
		Cell west = null;
		Cell east = null;

		// Checks if the new cell will have a neighbour in each direction.
		if (y > 0) {
			north = map.get(x).get(y - 1);
		}
		if (y < map.get(x).size()) {
			south = map.get(x).get(y + 1);
		}
		if (x > 0) {
			west = map.get(x - 1).get(y);
		}
		if (x < map.size() - 1) {
			east = map.get(x + 1).get(y);
		}

		// Constructs the cell
		Cell cell = new Cell(guiX, guiY, size, size, on, north, south, west, east);

		// Goes to any neighbour the new cell has and sets the new cell as its
		// neighbour.
		if (north != null) {
			north.setSouth(cell);
		}
		if (south != null) {
			south.setNorth(cell);
		}
		if (west != null) {
			west.setEast(cell);
		}
		if (east != null) {
			east.setWest(cell);
		}

		return cell;
	}

	/*
	 * Need methods to extend the world whenever a cell missing any neighbours
	 * comes alive
	 */
	private void addColumn() {
	} // useful to keep seperate?

	private void addRow() {
	} // useful to keep seperate?

	/* this method will update all cells */
	public void updateWorld() {

		// A list is constructed to track which cells have been changed
		ArrayList<Cell> changedCells = new ArrayList<Cell>();

		for (int i = 0; i < map.size(); i++) {

			for (int j = 0; j < map.get(i).size(); j++) {

				if (map.get(i).get(j).prepareUpdate()) {

					changedCells.add(map.get(i).get(j));
				}
			}
		}

		for (int i = 0; i < changedCells.size(); i++) {

			changedCells.get(i).enactUpdate();
		}
	}

	/* Getter */
	public LinkedList<LinkedList<Cell>> getMap() {
		return map;
	}

	/* A very unprofessional unit test */
	public static void main(String args[]) {

		World test = new World(10, 10);

		System.out.println(test.getMap().size());
		System.out.println(test.getMap().get(0).size());

		for (int i = 0; i < test.getMap().size(); i++) {

			System.out.println();

			for (int j = 0; j < test.getMap().get(i).size(); j++) {

				if (test.getMap().get(i).get(j).getState()) {
					System.out.print("0");
				} else {
					System.out.print("X");
				}

			}
		}

		// for (int i = 0; i < test.getMap().size(); i++) {

		// for (int j = 0; j < test.getMap().get(i).size(); j++) {

		// test.getMap().get(i).get(j).hasNeighbours(); } }

		for (int x = 0; x < 100; x++) {

			System.out.println();
			test.updateWorld();

			for (int i = 0; i < test.getMap().size(); i++) {

				System.out.println();

				for (int j = 0; j < test.getMap().get(i).size(); j++) {

					if (test.getMap().get(i).get(j).getState()) {
						System.out.print("0");
					} else {
						System.out.print("X");
					}

				}
			}
		}
	}
}
