import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Calendar extends JPanel {
	ArrayList<JPanel> calendarTiles;
	
	public Calendar() {
		calendarTiles = new ArrayList<JPanel>();
		setLayout(new FlowLayout());
		setBackground(UI.BG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);

		for (int i = 0; i < 31; i++) {
			displayTile(i + 1);
		}
	}

	/**
	 * Displays a tile in the calendar.
	 */
	private void displayTile(int date) {
		JPanel tile = new JPanel();
		Text label = new Text(String.valueOf(date));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		tile.setBackground(UI.BG_SECONDARY);
		
		tile.add(label);
		calendarTiles.add(tile);
		this.add(tile);
	}

	/**
	 * Mark a tile as occupied. (red)
	 * @param tileIndex Tile to mark as occupied.
	 */
	public void markTileOccupied(int tileIndex) {
		calendarTiles.get(tileIndex).setBackground(UI.ERROR);
	}

	/**
	 * Mark a tile as free. (gray)
	 * @param tileIndex Tile to mark as free.
	 */
	public void markTileFree(int tileIndex) {
		calendarTiles.get(tileIndex).setBackground(UI.BG_SECONDARY);
	}
}
