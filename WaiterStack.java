public class WaiterStack {

    Waiter head;
    Waiter current;
    Waiter tail;

    public WaiterStack() {
        head = null;
        current = null;
    }

    public void push(Waiter newPerson) {
        if (head == null) {
            head = new Waiter(newPerson, head);
            tail = head;
        } else {
            head = new Waiter(newPerson, head);
        }
    }

    public Waiter pop() {
        Waiter current;
        current = head;

        if (current != null)
            head = head.getWaiterLink();

        return current;
    }

    public int getSize() {
        int size = 0;
        Waiter current = head;
        while (current != null) {
            size++;
            current = current.getWaiterLink();
        }
        return size;
    }
}
