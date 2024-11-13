import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WaiterLL {
    private Waiter head;
    private Waiter current;
    private Waiter previous;
    private Waiter tail;
    private Waiter undo;
    File file = new File("Waiter.txt");

    public WaiterLL() {
        head = null;
        tail = null;
    }

    public void addWaiterEnd(int newAge, String newPerson, int WaiterID) {
        if (tail == null) {
            tail = new Waiter(newAge, newPerson, WaiterID, null);
            head = tail;
        } else {
            Waiter position = new Waiter(newAge, newPerson, WaiterID, null);
            tail.setWaiterLink(position);
            tail = position;
        }
    }

    public boolean checkIDExsitance(int id) {
        Waiter position = head;
        while (position != null) {
            if (position.getWaiterID() == id)
                return true;
            position = position.getWaiterLink();
        }
        return false;
    }

    public void deleteSearch(int id) {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            Waiter current = head;
            Waiter previous = null;
            while (current != null) {
                if (current.getWaiterID() == id) {
                    if (previous == null) {
                        head = current.getWaiterLink();
                        if (head == null) {
                            tail = null;
                        }
                    } else {
                        previous.setWaiterLink(current.getWaiterLink());
                        if (current.getWaiterLink() == null) {
                            tail = previous;
                        }
                    }
                    return;
                }
                previous = current;
                current = current.getWaiterLink();
            }
            System.out.println("Account not found.");
        }
    }

    public void addWaiterStart(int newAge, String newPerson, int WaiterID) {
        if (head == null) {
            head = new Waiter(newAge, newPerson, WaiterID, head);
            tail = head;
        } else {
            head = new Waiter(newAge, newPerson, WaiterID, head);
        }

    }

    // length
    public int length() {
        int count = 0;
        Waiter position = head;

        while (position != null) {
            position = position.getWaiterLink();
            count++;
        }
        return count;
    }

    public Waiter deleteWaiter() {
        if (head != null) {
            undo = head;
            head = head.getWaiterLink();
        } else {
            System.out.println("Empty list!");
        }
        return undo;
    }

    // show list
    public void showList() {
        Waiter position;
        position = head;
        if (head == null)
            System.out.println("————————————————————\nEmpty List\n————————————————————");
        else {
            System.out.println("------------------------------");
            while (position != null) {
                System.out.println("Waiter Name: " + position.getName() + "  AGE: " + position.getAge() + "    ID: "
                        + position.getWaiterID());
                position = position.getWaiterLink();
            }
            System.out.println("------------------------------");
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    // show next
    public Waiter goToNext(Waiter found) {
        if (found.getWaiterLink() == null)
            System.out.println("End of the list.");
        else {
            previous = found;
            found = previous.getWaiterLink();
            previous.setWaiterLink(found);
        }
        return found;
    }

    // show prev
    public Waiter goToPrevious(Waiter found) {
        if (previous == head)
            System.out.println("End of the list.");
        else {
            current = head;
            while (current.getWaiterLink() != found) {
                current = current.getWaiterLink();
            }
            previous = current;
        }
        return previous;
    }

    // delete PersonEnd
    public int deleteWaiterEnd() {
        Waiter position;
        position = head;
        if (head == null) {

            head = null;
            return 0;

        } else {
            for (int i = 0; i < length() - 2; i++)
                position = position.getWaiterLink();
            position.setWaiterLink(null);
            return position.getWaiterID();
        }

    }

    // search Name
    public Waiter searchName(String nameTarget) {
        Waiter position;
        position = head;
        String nameAtPosition;
        while (position != null) {
            nameAtPosition = position.getName();
            if (nameAtPosition.equals(nameTarget)) {
                return position;
            }
            position = position.getWaiterLink();
        }
        return null;
    }

    // clear
    public void clear() {
        head = null;
        tail = null;
    }

    public Waiter getWaiterByID(int id) {
        Waiter position = head;
        while (position != null) {
            if (position.getWaiterID() == id)
                return position;
            position = position.getWaiterLink();
        }
        return null;
    }

    public void sortList() {
        Waiter position = head;
        Waiter next = head;
        Waiter temp = new Waiter();

        if (position == null) {
            System.out.println("\nThe List is empty");
        } else {
            for (int i = 0; i < length() - 1; i++) {
                for (int j = 0; j < length() - 1; j++) {
                    next = next.getWaiterLink();
                    if (position.getName().compareTo(next.getName()) > 0) {
                        temp.setName(position.getName());
                        temp.setAge(position.getAge());
                        temp.setWaiterID(position.getWaiterID());

                        position.setName(next.getName());
                        position.setAge(next.getAge());
                        position.setWaiterID(next.getWaiterID());

                        next.setName(temp.getName());
                        next.setAge(temp.getAge());
                        next.setWaiterID(temp.getWaiterID());
                    }
                    position = position.getWaiterLink();
                }
                position = head;
                next = head;
            }
            System.out.println("\nSorted");
        }
    }

    public void SaveInFile() throws IOException {
        try {
            if (head == null)
                System.out.println("Empty List");
            else {
                if (head == null) {
                    System.out.println("Empty List");
                } else {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    Waiter temp = head;
                    while (temp != null) {
                        out.writeObject(temp);
                        temp = temp.getWaiterLink();
                    }
                    out.close();
                    fileOut.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WaiterLL ReadFromFile() throws IOException {
        WaiterLL list = new WaiterLL();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    Waiter temp = (Waiter) in.readObject();
                    list.addWaiterEnd(temp.getAge(), temp.getName(), temp.getWaiterID());
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
