public class MenuStack {
    Menu head;
    Menu current;
    Menu tail;

    public MenuStack() {
        head = null;
        current = null;
    }

    public void push(Menu newMenu) {
        if (head == null) {
            head = newMenu;
            tail = head;
        } else {
            head.setPrev(newMenu);
            head = newMenu;
        }
    }

    public Menu pop() {
        Menu current;
        current = head;

        if (current != null)
            head = head.getNext();

        return current;
    }

    public int getSize() {
        int size = 0;
        Menu current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
}
