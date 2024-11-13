
public class RestaurantStack {
	Restaurant head;
	Restaurant current;
	Restaurant tail;

	public RestaurantStack() {
		head = null;
		current = null;
	}

	public void push(Restaurant newPerson) {
		if (head == null) {
			head = new Restaurant(newPerson, head);
			tail = head;
		} else
			head = new Restaurant(newPerson, head);
	}

	public Restaurant pop() {
		Restaurant current;
		current = head;
		if (current != null)
			head = head.getRestaurantLink();
		return current;
	}

	public int getSize() {
		int size = 0;
		Restaurant current = head;
		while (current != null) {
			size++;
			current = current.getRestaurantLink();
		}
		return size;
	}
}