/**
 * Represents a quadrant tree data structure.
 * 
 * @author adamm
 */
public class QuadrantTree {

	private QTreeNode root;

	/**
	 * Constructs a QuadrantTree from the given pixels array.
	 *
	 * @param thePixels The 2D array representing pixel values.
	 */
	public QuadrantTree(int[][] thePixels) {
		this.root = build(thePixels, null, 0, 0, thePixels.length);
	}

	/**
	 * Recursively builds a quadrant tree from the given pixels array.
	 *
	 * @param pixels The 2D array representing pixel values.
	 * @param Parent The parent node of the current node being constructed.
	 * @param x      The x-coordinate of the current node.
	 * @param y      The y-coordinate of the current node.
	 * @param size   The size of the current node.
	 * @return The root node of the constructed quadrant tree.
	 */
	private QTreeNode build(int[][] pixels, QTreeNode Parent, int x, int y, int size) {
		if (size == 1) {
			int color = pixels[x][y];
			QTreeNode r = new QTreeNode(null, x, y, size, color);
			// Initialize parent of r
			r.setParent(Parent);
			return r;
		} else {
			int avgColor = Gui.averageColor(pixels, x, y, size);
			int half = size / 2;
			QTreeNode node = new QTreeNode(new QTreeNode[4], x, y, size, avgColor);
			node.setParent(Parent);
			// recursively call build to build the children of each node.
			node.setChild(build(pixels, node, x, y, half), 0);
			node.setChild(build(pixels, node, x + half, y, half), 1);
			node.setChild(build(pixels, node, x, y + half, half), 2);
			node.setChild(build(pixels, node, x + half, y + half, half), 3);
			return node;
		}
	}

	/**
	 * Gets the root node of this quadrant tree.
	 *
	 * @return The root node.
	 */
	public QTreeNode getRoot() {
		return this.root;
	}

	/**
	 * Retrieves a list of nodes at the specified level in the quadrant tree.
	 *
	 * @param r        The root node of the subtree to search.
	 * @param theLevel The level at which to retrieve nodes.
	 * @return A list of nodes at the specified level.
	 */
	public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
		if (r == null || theLevel == 0 || r.isLeaf()) {
			return new ListNode<>(r);
		}
		ListNode<QTreeNode> list = null;

		// loop through each child
		for (int i = 0; i < 4; i++) {
			// recursively call getPixels to get lists of children for each parent
			ListNode<QTreeNode> children = getPixels(r.getChild(i), theLevel - 1);
			if (list == null) {
				list = children;
			} else {
				// Concatenate the lists
				ListNode<QTreeNode> curr = list;
				while (curr.getNext() != null) {
					curr = curr.getNext();
				}
				curr.setNext(children);
			}
		}
		return list;
	}

	/**
	 * Finds nodes in the quadrant tree with a color similar to the specified color
	 * and at the specified level.
	 *
	 * @param r        The root node of the subtree to search.
	 * @param theColor The color to match.
	 * @param theLevel The level at which to search for nodes.
	 * @return A Duple containing a list of matching nodes and the count of such
	 *         nodes.
	 */
	public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
		ListNode<QTreeNode> obj = new ListNode<>(r);

		if (r.isLeaf() || theLevel == 0) {
			if (Gui.similarColor(r.getColor(), theColor)) {
				return new Duple(obj, 1);
			} else {
				return new Duple();
			}
		}

		Duple matching = new Duple();
		ListNode<QTreeNode> newList = null;
		int totalCount = 0;

		// Similar to getPixels but we decrement theLevel and get all children of parent
		// i.
		for (int i = 0; i < 4; i++) {
			Duple childDuple = findMatching(r.getChild(i), theColor, theLevel - 1);
			if (childDuple.getFront() != null) {
				// if first iteration update list accordingly
				if (newList == null) {
					newList = childDuple.getFront();
				}
				// concatenate the lists as they are entered
				else {
					ListNode<QTreeNode> current = newList;
					// loop to the end of the linked Nodes
					while (current.getNext() != null) {
						current = current.getNext();
					}
					// Then set the final node to the front of the ChildDuple list.
					current.setNext(childDuple.getFront());
				}
				totalCount += childDuple.getCount();
			}
		}

		return new Duple(newList, totalCount);
	}

	/**
	 * Finds a node in the subtree rooted at the specified node and at the specified
	 * level.
	 *
	 * @param r        The root node of the subtree to search.
	 * @param theLevel The level at which to search for the node.
	 * @param x        The x-coordinate of the point.
	 * @param y        The y-coordinate of the point.
	 * @return The node representing the quadrant containing the point, or null if
	 *         not found.
	 */
	public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {

		if (theLevel == 0 && r.contains(x, y)) {
			return r;
		}
		// Use contains method to ensure the pixel exists
		if (r.contains(x, y)) {
			for (int i = 0; i < 4; i++) {
				QTreeNode child = r.getChild(i);
				QTreeNode result = findNode(child, theLevel - 1, x, y);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
}
