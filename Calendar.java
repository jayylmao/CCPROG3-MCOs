import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Calendar creates a visual calendar as a panel.
 */
public class Calendar extends JPanel {
	private ArrayList<JPanel> calendarTiles;
	
	public Calendar() {
		calendarTiles = new ArrayList<JPanel>();
		setLayout(new FlowLayout());
		setBackground(UI.BG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);

		for (int i = 0; i < 30; i++) {
			displayTile(i + 1);
		}
	}

	/**
	 * Displays a tile in the calendar.
	 */
	public void updateTileInfo(int index, int date, double price) {
		JLabel tileLabel = (JLabel) calendarTiles.get(index - 1).getComponent(0);
		tileLabel.setText(String.valueOf(date) + " - " + String.valueOf(price));
	}

	/**
	 * Displays a tile in the calendar.
	 */
	private void displayTile(int date) {
		JPanel tile = new JPanel();
		Text label = new Text(String.valueOf(date));
		tile.setAlignmentX(Component.LEFT_ALIGNMENT);
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
		calendarTiles.get(tileIndex - 1).setBackground(UI.ERROR);
	}

	/**
	 * Mark a tile as free. (gray)
	 * @param tileIndex Tile to mark as free.
	 */
	public void markTileFree(int tileIndex) {
		calendarTiles.get(tileIndex - 1).setBackground(UI.BG_SECONDARY);
	}
}
