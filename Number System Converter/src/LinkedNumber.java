/**
 * Represents a linked list implementation of a number with a given base.
 * 
 * @author adamm
 */
public class LinkedNumber {

	private int base;
	private DLNode<Digit> front;
	private DLNode<Digit> rear;

	/**
	 * Constructs a LinkedNumber from a string representation of a number.
	 *
	 * @param num     The string representation of the number.
	 * @param baseNum The base of the number (e.g., 10 for decimal, 16 for
	 *                hexadecimal).
	 * @throws LinkedNumberException if no digits are given.
	 */
	public LinkedNumber(String num, int baseNum) {
		this.base = baseNum;
		if (num.isEmpty()) {
			throw new LinkedNumberException("no digits given");
		}

		for (int i = 0; i < num.length(); i++) {
			// represents individual digit character
			char digitChar = num.charAt(i);
			int digitVal = 0;
			Digit dig = new Digit(digitChar);
			// if hexadecimal go to digit class to get value
			if (baseNum == 16) {
				digitVal = dig.getValue();
			} else {
				digitVal = Character.digit(digitChar, baseNum);
			}
			DLNode<Digit> node = new DLNode<>(dig);
			if (this.front == null) {
				this.front = node;
				this.rear = node;
			} else {
				rear.setNext(node);
				node.setPrev(rear);
				rear = node;
			}
		}
	}

	/**
	 * Constructs a LinkedNumber from an integer.
	 *
	 * @param num The integer representation of the number.
	 * @throws LinkedNumberException if no digits are given.
	 */
	public LinkedNumber(int num) {
		this.base = 10;
		String strNum = String.valueOf(num);
		if (strNum.isEmpty()) {
			throw new LinkedNumberException("no digits given");
		}

		for (int i = 0; i < strNum.length(); i++) {
			int digitVal = Character.digit(strNum.charAt(i), 10);
			Digit dig = new Digit((char) (digitVal + '0'));
			DLNode<Digit> node = new DLNode<>(dig);
			if (this.front == null) {
				this.front = node;
				this.rear = node;
			} else {
				rear.setNext(node);
				node.setPrev(rear);
				rear = node;
			}
		}
	}

	/**
	 * Checks if the current number is a valid number in its base.
	 *
	 * @return true if the number is valid, false otherwise.
	 */
	public boolean isValidNumber() {
		DLNode<Digit> current = front;
		while (current != null) {
			int digitVal = current.getElement().getValue();
			if (digitVal < 0 || digitVal >= base) {
				return false;
			}
			current = current.getNext();
		}
		return true;
	}

	/**
	 * Gets the base of the number.
	 *
	 * @return The base of the number.
	 */
	public int getBase() {
		return this.base;
	}

	/**
	 * Gets the front node of the linked list.
	 *
	 * @return The front node of the linked list.
	 */
	public DLNode<Digit> getFront() {
		return this.front;
	}

	/**
	 * Gets the rear node of the linked list.
	 *
	 * @return The rear node of the linked list.
	 */
	public DLNode<Digit> getRear() {
		return this.rear;
	}

	/**
	 * Gets the number of digits in the linked list.
	 *
	 * @return The number of digits in the linked list.
	 */
	public int getNumDigits() {
		int count = 0;
		DLNode<Digit> current = this.front;
		while (current != null) {
			count++;
			current = current.getNext();
		}
		return count;
	}

	/**
	 * Returns a string representation of the linked number.
	 *
	 * @return A string representation of the linked number.
	 */
	public String toString() {
		String strDigit = "";
		DLNode<Digit> current = this.front;
		while (current != null) {
			strDigit += current.getElement();
			current = current.getNext();
		}
		return strDigit;
	}

	/**
	 * Checks whether the current LinkedNumber is equal to another LinkedNumber. Two
	 * LinkedNumbers are equal if they have the same base and represent the same
	 * number.
	 *
	 * @param other The other LinkedNumber to compare.
	 * @return true if the LinkedNumbers are equal, false otherwise.
	 */
	public boolean equals(LinkedNumber other) {
		if (this.base != other.base) {
			return false;
		}

		DLNode<Digit> thisCurrent = this.front;
		DLNode<Digit> otherCurrent = other.front;
		while (thisCurrent != null && otherCurrent != null) {
			if (thisCurrent.getElement().getValue() != otherCurrent.getElement().getValue()) {
				return false;
			}
			thisCurrent = thisCurrent.getNext();
			otherCurrent = otherCurrent.getNext();
		}
		// If both current nodes are null, that means all elements were equal
		if (thisCurrent == null && otherCurrent == null) {
			return true;
		}

		// If one is null and the other is not, then they have different sizes
		return false;
	}

	/**
	 * Converts the current number to a new base.
	 *
	 * @param newBase The base to convert the number to.
	 * @return A new LinkedNumber object representing the converted number.
	 * @throws LinkedNumberException if the current number is invalid for
	 *                               conversion.
	 */
	public LinkedNumber convert(int newBase) {
		LinkedNumber newObj;
		String newStr;
		int val;
		if (!isValidNumber()) {
			throw new LinkedNumberException("cannot convert invalid number");
		}
		newStr = "";
		// Case 1: Decimal to Non-Decimal
		if (getBase() == 10 && newBase != 10) {
			val = 0;
			DLNode<Digit> current = this.rear;
			for (int i = 0; i < getNumDigits(); i++) {
				val += current.getElement().getValue() * Math.pow(10, i);
				current = current.getPrev();
			}
			while (val > 0) {
				if (val % newBase < 10) {
					newStr += val % newBase;
				} else {
					newStr += hexConvert(val % newBase);
				}
				val /= newBase;
			}
			// Reverse String
			newStr = reverseOrder(newStr);
			newObj = new LinkedNumber(newStr, newBase);
		}

		// Case 2 Non-Decimal to Decimal
		else if (getBase() != 10 && newBase == 10) {
			val = 0;
			DLNode<Digit> current = this.rear;
			for (int i = 0; i < getNumDigits(); i++) {
				val += current.getElement().getValue() * Math.pow(getBase(), i);
				current = current.getPrev();
			}
			newObj = new LinkedNumber(String.valueOf(val), 10);
		}

		// Case 3 Non-Decimal to Non-Decimal
		else {
			newStr = "";
			val = 0;
			int newVal = 0;
			DLNode<Digit> current = this.rear;
			for (int i = 0; i < getNumDigits(); i++) {
				val += current.getElement().getValue() * Math.pow(getBase(), i);
				current = current.getPrev();
			}
			LinkedNumber temp = new LinkedNumber(String.valueOf(val), 10);
			DLNode<Digit> newCurrent = temp.getRear();
			for (int i = 0; i < temp.getNumDigits(); i++) {
				newVal += newCurrent.getElement().getValue() * Math.pow(10, i);
				newCurrent = newCurrent.getPrev();
			}
			while (newVal > 0) {
				if (newVal % newBase < 10) {
					newStr += newVal % newBase;
				} else {
					newStr += hexConvert(newVal % newBase);
				}
				newVal /= newBase;
			}
			// Reverse String
			newStr = reverseOrder(newStr);
			newObj = new LinkedNumber(newStr, newBase);
		}
		return newObj;
	}

	/**
	 * Adds a digit to the linked number at the specified position.
	 *
	 * @param digit    The digit to add.
	 * @param position The position at which to add the digit.
	 * @throws LinkedNumberException if the position is invalid.
	 */
	public void addDigit(Digit digit, int position) {
		if (position < 0 || position > getNumDigits()) {
			throw new LinkedNumberException("invalid position");
		}

		DLNode<Digit> newNode = new DLNode<>(digit);
		if (position == 0) {
			// Adding at the end
			if (this.front == null) {
				// If the list is empty
				this.front = newNode;
				this.rear = newNode;
			} else {
				// If the list is not empty
				newNode.setPrev(this.rear);
				this.rear.setNext(newNode);
				this.rear = newNode;
			}
		} else if (position == getNumDigits()) {
			// Adding at the front
			if (this.front == null) {
				// If the list is empty
				this.front = newNode;
				this.rear = newNode;
			} else {
				// If the list is not empty
				newNode.setNext(this.front);
				this.front.setPrev(newNode);
				this.front = newNode;
			}
		} else {
			// Adding in the middle
			DLNode<Digit> current = this.rear;
			for (int i = 0; i < position - 1; i++) {
				current = current.getPrev();
			}
			newNode.setNext(current);
			newNode.setPrev(current.getPrev());
			current.getPrev().setNext(newNode);
			current.setPrev(newNode);
		}
	}

	/**
	 * Removes a digit from the linked number at the specified position.
	 *
	 * @param position The position of the digit to remove.
	 * @return The value of the removed digit within the whole number.
	 * @throws LinkedNumberException if the position is invalid.
	 */
	public int removeDigit(int position) {
		if (position < 0 || position >= getNumDigits()) {
			throw new LinkedNumberException("invalid position");
		}

		DLNode<Digit> curr = this.rear;
		for (int i = 0; i < position; i++) {
			curr = curr.getPrev();
		}

		int remVal = curr.getElement().getValue();

		if (curr == this.rear) {
			this.rear = this.rear.getPrev();
			if (this.rear != null) {
				this.rear.setNext(null);
			} else {
				// If there was only one node in the list
				this.front = null;
			}
		} else if (curr == this.front) {
			this.front = this.front.getNext();
			this.front.setPrev(null);
		} else {
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
		}

		return remVal * (int) Math.pow(this.base, position);
	}

	/**
	 * Converts a number from decimal to hexadecimal.
	 *
	 * @param num The number to convert.
	 * @return The hexadecimal representation of the number.
	 */
	private String hexConvert(int num) {
		switch (num) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		}
		return null;
	}

	/**
	 * Reverses the order of characters in a string.
	 *
	 * @param num The input string.
	 * @return The reversed string.
	 */
	private String reverseOrder(String num) {
		String reversedString = "";
		for (int i = num.length() - 1; i > -1; i--) {
			reversedString += num.charAt(i);
		}
		return reversedString;
	}

	public static void main(String[]args) {
		
		
	
		DLNode<Digit> curr = new DLNode<Digit>();
		
	
	}
}