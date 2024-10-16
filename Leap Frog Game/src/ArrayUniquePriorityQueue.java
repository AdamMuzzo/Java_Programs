/**
 * Represents a priority queue implementation using an array with unique
 * elements and priorities.
 * 
 * @param <T> The type of elements stored in the priority queue.
 * @author adamm
 */
public class ArrayUniquePriorityQueue<T> implements UniquePriorityQueueADT<T> {

	private T[] queue;
	private double[] priority;
	private int count;

	/**
	 * Constructs an ArrayUniquePriorityQueue with an initial capacity of 10.
	 */
	public ArrayUniquePriorityQueue() {
		this.queue = (T[]) new Object[10];
		this.priority = new double[10];
		this.count = 0;
	}

	/**
	 * Adds a new element with the given priority to the priority queue, if it's not
	 * already present.
	 * 
	 * @param data The element to add.
	 * @param prio The priority of the element.
	 */
	@Override
	public void add(T data, double prio) {
		if (this.contains(data)) {
			return;
		}

		if (checkForFullCapacity()) {
			expandCapacities();
		}
		// Sorts accordingly
		int i = count - 1;
		while (i >= 0 && prio < priority[i]) {
			queue[i + 1] = queue[i];
			priority[i + 1] = priority[i];
			i--;
		}

		// Find the correct position if new item has the same priority as an existing
		// item
		if (i >= 0 && prio == priority[i]) {
			i++;
			while (i < count && prio == priority[i]) {
				i++;
			}
		} else {
			i++;
		}

		queue[i] = data;
		priority[i] = prio;
		count++;
	}

	/**
	 * Checks if the priority queue contains the specified element.
	 * 
	 * @param data The element to check.
	 * @return true if the element is present, false otherwise.
	 */
	@Override
	public boolean contains(T data) {
		for (int i = 0; i < count; i++) {
			if (data.equals(queue[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieves, but does not remove, the highest priority element in the priority
	 * queue.
	 * 
	 * @return The highest priority element.
	 * @throws CollectionException if the priority queue is empty.
	 */
	@Override
	public T peek() throws CollectionException {
		if (this.isEmpty()) {
			throw new CollectionException("PQ is empty");
		}
		return this.queue[0];
	}

	/**
	 * Removes and returns the highest priority element from the priority queue.
	 * 
	 * @return The highest priority element.
	 * @throws CollectionException if the priority queue is empty.
	 */
	@Override
	public T removeMin() throws CollectionException {
		if (this.isEmpty()) {
			throw new CollectionException("PQ is empty");
		}
		// Sets data and priority to default value
		T minItem = queue[0];
		this.priority[0] = 0.0;
		this.queue[0] = null;
		// Shifts the items to the left
		for (int i = 0; i < count - 1; i++) {
			priority[i] = priority[i + 1];
			queue[i] = queue[i + 1];
		}
		count--;
		return minItem;
	}

	/**
	 * Updates the priority of the specified element in the priority queue.
	 * 
	 * @param data    The element whose priority is to be updated.
	 * @param newPrio The new priority of the element.
	 * @throws CollectionException if the specified element is not found in the
	 *                             priority queue.
	 */
	@Override
	public void updatePriority(T data, double newPrio) throws CollectionException {
		if (!this.contains(data)) {
			throw new CollectionException("Item not found in PQ");
		}

		// find index of data item
		int index = 0;
		for (int i = 0; i < count; i++) {
			if (queue[i] == data) {
				index = i;
				break;
			}
		}

		// remove data item
		for (int i = index; i < count - 1; i++) {
			queue[i] = queue[i + 1];
			priority[i] = priority[i + 1];
		}
		count--;

		// add data item
		add(data, newPrio);
	}

	/**
	 * Checks if the priority queue is empty.
	 * 
	 * @return true if the priority queue is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (this.count == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of elements in the priority queue.
	 * 
	 * @return The number of elements.
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Returns the length of the internal array used to store the priority queue.
	 * 
	 * @return The length of the internal array.
	 */
	public int getLength() {
		return priority.length;
	}

	/**
	 * Returns a string representation of the priority queue.
	 * 
	 * @return A string representation of the priority queue.
	 */
	public String toString() {
		if (this.isEmpty()) {
			return "The PQ is empty";
		}
		String str = "";
		for (int i = 0; i < count; i++) {
			// if last
			if (i == count - 1) {
				str += queue[i] + " [" + priority[i] + "]";
			} else {
				str += queue[i] + " [" + priority[i] + "], ";
			}
		}
		return str;
	}

	/**
	 * Checks if the internal array has reached its full capacity.
	 * 
	 * @return true if the array is full, false otherwise.
	 */
	private boolean checkForFullCapacity() {
		if (this.count == this.getLength()) {
			return true;
		}
		return false;
	}

	/**
	 * Expands the capacities of the internal arrays by increasing their lengths.
	 */
	private void expandCapacities() {
		// Creates new queue and priority
		T[] newQueue = (T[]) new Object[this.getLength() + 5];
		double[] newPriority = new double[this.getLength() + 5];

		// fills the new queue and new priority
		for (int i = 0; i < this.getLength(); i++) {
			newQueue[i] = this.queue[i];
			newPriority[i] = this.priority[i];
		}
		// updates this queue and priority
		this.queue = newQueue;
		this.priority = newPriority;

	}
}
