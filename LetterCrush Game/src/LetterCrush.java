/**
 * The LetterCrush class represents a game grid with letters and provides
 * methods for removing lines and applying gravity to stabilize the grid.
 * 
 * @author AdamMuzzo
 */
public class LetterCrush {

	private char[][] grid;
	public static final char EMPTY = ' ';

	/**
	 * Constructs a LetterCrush object with the specified dimensions and initial
	 * configuration.
	 *
	 * @param width   The width of the grid.
	 * @param height  The height of the grid.
	 * @param initial The initial configuration of the grid as a string.
	 */
	public LetterCrush(int width, int height, String initial) {
		this.grid = new char[height][width];

		for (int j = 0; j < height; j++) {
			for (int k = 0; k < width; k++) {
				// tries to fill the grid
				try {
					this.grid[j][k] = initial.charAt((width * j) + k);
				}
				// if characters are less than grid size set equal to EMPTY
				catch (Exception e) {
					this.grid[j][k] = EMPTY;
				}
			}
		}
	}

	/**
	 * Returns a string representation of the LetterCrush grid.
	 *
	 * @return A formatted string displaying the letters in the grid.
	 */
	public String toString() {
		String entries = "LetterCrush\n";
		for (int i = 0; i < this.grid.length; i++) {
			entries += "|";
			for (int j = 0; j < this.grid[0].length; j++) {
				entries += this.grid[i][j];
			}
			entries += "|" + i + "\n";
		}
		entries += "+";
		for (int j = 0; j < this.grid[0].length; j++) {
			entries += j;
		}
		entries += "+";
		return entries;
	}

	/**
	 * Checks if the grid is stable, meaning there are no empty spaces below
	 * non-empty spaces.
	 *
	 * @return True if the grid is stable, false otherwise.
	 */
	public boolean isStable() {
		for (int i = 1; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length - 1; j++) {
				// if grid point is empty and the grid point above is empty
				if (this.grid[i][j] == EMPTY && this.grid[i - 1][j] != EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Applies gravity to stabilize the grid by moving letters down to fill empty
	 * spaces.
	 */
	public void applyGravity() {
		// Continues to apply gravity until stability is achieved
		while (!isStable()) {
			for (int i = this.grid.length - 1; i > 0; i--) {
				for (int j = 0; j < this.grid[0].length; j++) {
					if (this.grid[i - 1][j] != EMPTY && this.grid[i][j] == EMPTY) {
						// replaces grid point with the char above
						this.grid[i][j] = this.grid[i - 1][j];
						// sets char above to empty
						this.grid[i - 1][j] = EMPTY;
					}
				}
			}
		}
	}

	/**
	 * Removes the letters in the specified line from the grid.
	 *
	 * @param theLine The line to be removed.
	 * @return True if the removal is successful, false otherwise.
	 */
	public boolean remove(Line theLine) {
		int[] startArr = theLine.getStart();

		if (theLine.isHorizontal()) {
			// checks all invalid possibilities
			if (startArr[1] + theLine.length() - 1 > this.grid[0].length || startArr[0] > this.grid.length - 1
					|| startArr[0] < 0 || startArr[1] < 0) {
				return false;
			}
		} else {
			// Checks all invalid possibilities
			if (startArr[0] + theLine.length() - 1 > this.grid.length || startArr[1] > this.grid[0].length - 1
					|| startArr[0] < 0 || startArr[1] < 0) {
				return false;
			}
		}
		for (int i = startArr[0]; i < this.grid.length; i++) {
			for (int j = startArr[1]; j < this.grid[0].length; j++) {
				// if point in the line set equal to empty
				if (theLine.inLine(i, j)) {
					grid[i][j] = EMPTY;
				}
			}
		}
		return true;
	}

	/**
	 * Returns a string representation of the LetterCrush grid with the letters in
	 * the specified line displayed in lower case.
	 *
	 * @param theLine The line to be displayed differently.
	 * @return A formatted string with the specified line's letters in lower case.
	 */
	public String toString(Line theLine) {
		String entries = "CrushLine\n";
		for (int i = 0; i < this.grid.length; i++) {
			entries += "|";
			for (int j = 0; j < this.grid[0].length; j++) {
				// if point in line, set to lower case char
				if (theLine.inLine(i, j)) {
					entries += Character.toLowerCase(this.grid[i][j]);
				} else {
					entries += this.grid[i][j];
				}
			}
			entries += "|" + i + "\n";
		}
		entries += "+";
		for (int j = 0; j < this.grid[0].length; j++) {
			entries += j;
		}
		entries += "+";
		return entries;
	}

	/**
	 * Finds and returns the longest contiguous line of identical letters in the
	 * grid.
	 *
	 * @return The longest line found or null if no such line exists.
	 */
	public Line longestLine() {
		int largest = 0;
		Line longLine = new Line(0, 0, true, 1);
		for (int i = this.grid.length - 1; i > 0; i--) {
			char letter = this.grid[i][0];
			int adjacent = 1;
			for (int j = 1; j < this.grid[0].length; j++) {
				if (this.grid[i][j] == letter && letter != EMPTY) {
					adjacent++;
					if (adjacent > largest) {
						largest = adjacent;
						longLine = new Line(i, j - adjacent + 1, true, adjacent);
					}
				} else {
					letter = this.grid[i][j];
					adjacent = 1;
				}
			}
		}
		for (int j = 0; j < this.grid[0].length; j++) {
			char letter = this.grid[this.grid.length - 1][j];
			int adjacent = 1;
			for (int i = this.grid.length - 2; i >= 0; i--) {
				if (this.grid[i][j] == letter && letter != EMPTY) {
					adjacent++;
					if (adjacent > largest) {
						largest = adjacent;
						longLine = new Line(i, j, false, adjacent);
					}
				} else {
					letter = this.grid[i][j];
					adjacent = 1;
				}
			}
		}
		if (longLine.length() > 2) {
			return longLine;
		} else {
			return null;
		}

	}

	/**
	 * Removes the longest lines one by one until the grid becomes stable.
	 */
	public void cascade() {
		// while a longest line exists
		while (longestLine() != null) {
			// remove the longest line
			remove(this.longestLine());
			// while unstable
			while (isStable() == false) {
				// apply gravity
				applyGravity();
			}
		}
	}
}
