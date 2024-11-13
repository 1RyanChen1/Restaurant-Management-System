public class CustomerStack {
    Customer head;
    Customer current;
    Customer tail;

    public CustomerStack() {
        head = null;
        current = null;
    }

    public void push(Customer newPerson) {
        if (head == null) {
            head = new Customer(newPerson, head);
            tail = head;
        } else {
            head = new Customer(newPerson, head);
        }
    }

    public Customer pop() {
        Customer current;
        current = head;

        if (current != null)
            head = head.getCustomerLink();

        return current;
    }

    public int getSize() {
        int size = 0;
        Customer current = head;
        while (current != null) {
            size++;
            current = current.getCustomerLink();
        }
        return size;
    }
}
