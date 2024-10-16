/**
 * The Line class represents a line on a 2D grid defined by its start and end
 * points. It provides methods to get the start point, calculate the length,
 * check if the line is horizontal, and determine if a given point is on the
 * line.
 * 
 * @author AdamMuzzo
 */
public class Line {

	private int[] start = new int[2];
	private int[] end = new int[2];

	/**
	 * Constructs a Line object with the specified parameters.
	 *
	 * @param row        The row coordinate of the starting point.
	 * @param col        The column coordinate of the starting point.
	 * @param horizontal A boolean indicating whether the line is horizontal or
	 *                   vertical.
	 * @param length     The length of the line.
	 */
	public Line(int row, int col, boolean horizontal, int length) {
		this.start[0] = row;
		this.start[1] = col;

		if (horizontal == true) {
			this.end[0] = row;
			this.end[1] = col + length - 1;
		} else {
			this.end[0] = row + length - 1;
			this.end[1] = col;
		}

	}

	/**
	 * Gets the start point of the line.
	 *
	 * @return An array representing the row and column coordinates of the start
	 *         point.
	 */
	public int[] getStart() {
		int[] newArr = new int[2];
		newArr[0] = this.start[0];
		newArr[1] = this.start[1];

		return newArr;
	}

	/**
	 * Calculates the length of the line.
	 *
	 * @return The length of the line.
	 */
	public int length() {
		if (this.isHorizontal()) {
			return end[1] - start[1] + 1;
		} else {
			return end[0] - start[0] + 1;
		}
	}

	/**
	 * Checks if the line is horizontal.
	 *
	 * @return True if the line is horizontal, false otherwise.
	 */
	public boolean isHorizontal() {
		if (start[0] == end[0]) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if a given point is on the line.
	 *
	 * @param row The row coordinate of the point.
	 * @param col The column coordinate of the point.
	 * @return True if the point is on the line, false otherwise.
	 */
	public boolean inLine(int row, int col) {
		if (this.isHorizontal()) {
			// if point is inside the line horizontally
			if (row == start[0] && col >= start[1] && col <= end[1]) {
				return true;
			} else {
				return false;
			}
		} else {
			// if point is inside the line vertically
			if (col == start[1] && row >= start[0] && row <= end[0]) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns a string representation of the Line object.
	 *
	 * @return A formatted string representing the start and end points of the line.
	 */
	public String toString() {
		return String.format("Line:[%d,%d]->[%d,%d]", start[0], start[1], end[0], end[1]);
	}
}
