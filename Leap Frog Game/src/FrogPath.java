/**
 * Represents a frog navigating a pond to find the best path to reach the other
 * frog
 * 
 * @author adamm
 */
public class FrogPath {

	private Pond pond;

	/**
	 * Constructs a FrogPath object with the specified pond file.
	 * 
	 * @param filename The filename of the pond configuration
	 */
	public FrogPath(String filename) {

		try {
			this.pond = new Pond(filename);
		} catch (Exception e) {
			System.out.println("Error occured initializing pond: " + e);
		}
	}

	/**
	 * Finds the best neighboring cell for the frog to move from the current cell
	 * 
	 * @param currCell The current cell of the frog
	 * @return a Hexagon Object begin the best cell to move to or null if no valid
	 *         cells
	 */
	public Hexagon findBest(Hexagon currCell) {
		ArrayUniquePriorityQueue<Hexagon> cells = new ArrayUniquePriorityQueue<>();

		// If lilypad cell or if the start cell
		if (currCell.isLilyPadCell() || currCell.isStart()) {
			for (int i = 0; i < 6; i++) { // Loop through each cell
				Hexagon nextCell = currCell.getNeighbour(i);
				double prio = 0.0;
				if (isValid(nextCell)) { // Check cell validity
					// fly cases
					if (nextCell instanceof FoodHexagon) { // If contains flies
						if (((FoodHexagon) nextCell).getNumFlies() == 3) {
							prio = 0.0;
						} else if (((FoodHexagon) nextCell).getNumFlies() == 2) {
							prio = 1.0;
						} else if (((FoodHexagon) nextCell).getNumFlies() == 1) {
							prio = 2.0;
						}
					}
					// End (Franny)
					else if (nextCell.isEnd()) {
						prio = 3.0;
					}
					// LilyPad
					else if (nextCell.isLilyPadCell()) {
						prio = 4.0;
					}
					// Reeds & Reeds near Alligator
					else if (nextCell.isReedsCell()) {
						prio = 5.0;
						if (isNearAlligator(nextCell)) {
							prio = 10.0;
						}
					}
					// Water
					else if (nextCell.isWaterCell()) {
						prio = 6.0;
					}
					cells.add(nextCell, prio);
				}
			}
			for (int i = 0; i < 6; i++) { // Loops again for neighboring cells
				Hexagon nextCellAdj = currCell.getNeighbour(i);
				if (nextCellAdj != null) { // Checks first for nullity
					for (int j = 0; j < 6; j++) {
						Hexagon nextCell = nextCellAdj.getNeighbour(j);
						double prio = 0;
						if (isValid(nextCell)) { // Then checks for validity
							// fly cases
							if (nextCell instanceof FoodHexagon) {
								if (((FoodHexagon) nextCell).getNumFlies() == 3) {
									prio = 0.0;
								} else if (((FoodHexagon) nextCell).getNumFlies() == 2) {
									prio = 1.0;
								} else if (((FoodHexagon) nextCell).getNumFlies() == 1) {
									prio = 2.0;
								}
							}
							// End (Franny)
							else if (nextCell.isEnd()) {
								prio = 3.0;
							}
							// LilyPad
							else if (nextCell.isLilyPadCell()) {
								prio = 4.0;
							}
							// Reeds & Reeds near Alligator
							else if (nextCell.isReedsCell()) {
								prio = 5.0;
								if (isNearAlligator(nextCell)) {
									prio = 10.0;
								}
							}
							// Water
							else if (nextCell.isWaterCell()) {
								prio = 6.0;
							}
							if (i == j) {
								prio += 0.5;
							} else {
								prio += 1.0;
							}
							cells.add(nextCell, prio);
						}
					}
				}
			}
		}
		// Not LilyPad
		// Works the same except no double cell jump
		else {
			for (int i = 0; i < 6; i++) {
				Hexagon nextCell = currCell.getNeighbour(i);
				double prio = 0.0;
				if (isValid(nextCell)) {
					// fly cases
					if (nextCell instanceof FoodHexagon) {
						if (((FoodHexagon) nextCell).getNumFlies() == 3) {
							prio = 0.0;
						} else if (((FoodHexagon) nextCell).getNumFlies() == 2) {
							prio = 1.0;
						} else if (((FoodHexagon) nextCell).getNumFlies() == 1) {
							prio = 2.0;
						}
					}
					// End (Franny)
					else if (nextCell.isEnd()) {
						prio = 3.0;
					}
					// LilyPad
					else if (nextCell.isLilyPadCell()) {
						prio = 4.0;
					}
					// Reeds & Reeds near Alligator
					else if (nextCell.isReedsCell()) {
						prio = 5.0;
						if (isNearAlligator(nextCell)) {
							prio = 10.0;
						}
					}
					// Water
					else if (nextCell.isWaterCell()) {
						prio = 6.0;
					}
					cells.add(nextCell, prio);
				}
			}
		}
		// Check marked cells
		while (cells.size() != 0) {
			Hexagon bestHex = cells.peek();
			if (bestHex.isMarked()) {
				cells.removeMin();
			} else {
				return bestHex;
			}
		}

		return null;
	}

	/**
	 * Finds the path for the frog to navigate through the pond
	 * 
	 * @return A string representing the path taken by the frog and the number of
	 *         flies eaten
	 */
	public String findPath() {
		ArrayStack<Hexagon> S = new ArrayStack<Hexagon>();
		S.push(pond.getStart());
		pond.getStart().markInStack();
		int fliesEaten = 0;

		String str = "";
		while (!S.isEmpty()) {
			Hexagon curr = S.peek();

			str += curr.getID() + " ";
			if (curr.isEnd()) {
				break;
			}
			if (curr instanceof FoodHexagon) {
				fliesEaten += ((FoodHexagon) curr).getNumFlies();
				((FoodHexagon) curr).removeFlies();
			}
			Hexagon next = findBest(curr);
			if (next == null) {
				S.pop();
				curr.markOutStack();
			} else {
				S.push(next);
				next.markInStack();
			}
		}
		if (S.isEmpty()) {
			str = "No solution";
		} else {
			str += "ate " + fliesEaten + " flies";
		}
		return str;

	}

	/**
	 * Checks if a cell is valid for the frog to move to.
	 * 
	 * @param nextCell The cell to check.
	 * @return true if the cell is valid, false otherwise.
	 */
	private boolean isValid(Hexagon nextCell) {
		if (nextCell != null) {
			if (nextCell.isMudCell() || nextCell.isAlligator()
					|| (isNearAlligator(nextCell) && !nextCell.isReedsCell())) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a cell is near an alligator.
	 * 
	 * @param nextCell The cell to check.
	 * @return true if the cell is near an alligator, false otherwise.
	 */
	private boolean isNearAlligator(Hexagon nextCell) {

		for (int i = 0; i < 6; i++) {
			Hexagon neighbour = nextCell.getNeighbour(i);
			if (neighbour != null) {
				if (neighbour.isAlligator()) {
					return true;
				}
			}
		}
		return false;
	}
}