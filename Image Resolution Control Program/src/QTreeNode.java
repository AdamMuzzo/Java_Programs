/**
 * Represents a node in a quadrant tree.
 * 
 * @author adamm
 */
public class QTreeNode {

	private int x, y, size, color;
	private QTreeNode parent;
	private QTreeNode[] children;

	/**
	 * Constructs a QTreeNode with default values
	 */
	public QTreeNode() {
		this.parent = null;
		children = new QTreeNode[4];
		this.x = 0;
		this.y = 0;
		this.size = 0;
		this.color = 0;
	}

	/**
	 * Constructs a QTreeNode with specified properties
	 * 
	 * @param theChildren
	 * @param xcoord
	 * @param ycoord
	 * @param theSize
	 * @param theColor
	 */
	public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
		this.parent = null;
		this.children = theChildren;
		this.x = xcoord;
		this.y = ycoord;
		this.size = theSize;
		this.color = theColor;
	}

	/**
	 * Checks if the given coordinates are within the bounds of this node.
	 * 
	 * @param xcoord
	 * @param ycoord
	 * @return
	 */
	public boolean contains(int xcoord, int ycoord) {
		if (xcoord >= this.x && xcoord <= (this.x + this.size - 1) && ycoord >= this.y
				&& ycoord <= (this.y + this.size - 1)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the x-coordinate of this node
	 * 
	 * @return The x-coordinate
	 */
	public int getx() {
		return this.x;
	}

	/**
	 * Gets the y-coordinate of this node.
	 *
	 * @return The y-coordinate.
	 */
	public int gety() {
		return this.y;
	}

	/**
	 * Gets the size of this node.
	 *
	 * @return The size.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Gets the color of this node.
	 *
	 * @return The color.
	 */
	public int getColor() {
		return this.color;
	}

	/**
	 * Gets the parent node of this node.
	 *
	 * @return The parent node.
	 */
	public QTreeNode getParent() {
		return this.parent;
	}

	/**
	 * Gets the child node at the specified index.
	 *
	 * @param index The index of the child node to retrieve.
	 * @return The child node at the specified index.
	 * @throws QTreeException if the index is out of bounds or the children array is
	 *                        null.
	 */
	public QTreeNode getChild(int index) throws QTreeException {
		if (this.children == null || index < 0 || index > 3) {
			throw new QTreeException("Invalid1");
		}
		return this.children[index];
	}

	/**
	 * Sets the x-coordinate of this node.
	 *
	 * @param newx The new x-coordinate.
	 */
	public void setx(int newx) {
		this.x = newx;
	}

	/**
	 * Sets the y-coordinate of this node.
	 *
	 * @param newy The new y-coordinate.
	 */
	public void sety(int newy) {
		this.y = newy;
	}

	/**
	 * Sets the size of this node.
	 *
	 * @param newSize The new size.
	 */
	public void setSize(int newSize) {
		this.size = newSize;
	}

	/**
	 * Sets the color of this node.
	 *
	 * @param newColor The new color.
	 */
	public void setColor(int newColor) {
		this.color = newColor;
	}

	/**
	 * Sets the parent node of this node.
	 *
	 * @param newParent The new parent node.
	 */
	public void setParent(QTreeNode newParent) {
		this.parent = newParent;
	}

	/**
	 * Sets the child node at the specified index.
	 *
	 * @param newChild The new child node.
	 * @param index    The index where the child node should be set.
	 * @throws QTreeException if the index is out of bounds or the children array is
	 *                        null.
	 */
	public void setChild(QTreeNode newChild, int index) throws QTreeException {
		if (this.children == null || index < 0 || index > 3) {
			throw new QTreeException("Invalid2");
		}
		this.children[index] = newChild;
	}

	/**
	 * Checks if this node is a leaf node (i.e., has no children).
	 *
	 * @return True if this node is a leaf node, otherwise false.
	 */
	public boolean isLeaf() {
		if (this.children == null
				|| (children[0] == null && children[1] == null && children[2] == null && children[3] == null)) {
			return true;
		}
		return false;
	}
}
