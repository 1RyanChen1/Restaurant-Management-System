import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CustomerLL {
    // instance
    private Customer head;
    private Customer current;
    private Customer previous;
    private Customer tail;
    private Customer undo;

    File file = new File("Customer.txt");

    public CustomerLL() {
        head = null;
        tail = null;
    }

    public void addCustomerEnd(int newAge, String newPerson, int customerID) {
        if (tail == null) {
            tail = new Customer(newAge, newPerson, customerID, null);
            head = tail;
        } else {
            Customer position = new Customer(newAge, newPerson, customerID, null);
            tail.setCustomerLink(position);
            tail = position;
        }
    }

    public void addCustomerEnd(Customer customer) {
        if (tail == null) {
            tail = new Customer(customer, null);
            head = tail;
        } else {
            Customer position = new Customer(customer, null);
            tail.setCustomerLink(position);
            tail = position;
        }
    }

    public void addCustomerStart(int newAge, String newPerson, int customerID) {
        if (head == null) {
            head = new Customer(newAge, newPerson, customerID, head);
            tail = head;
        } else {
            head = new Customer(newAge, newPerson, customerID, head);
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    // length
    public int length() {
        int count = 0;
        Customer position = head;

        while (position != null) {
            position = position.getCustomerLink();
            count++;
        }
        return count;
    }

    // show list
    public void showList() {
        Customer position;
        position = head;
        if (head == null)
            System.out.println("————————————————————\nEmpty List\n————————————————————");
        else {
            System.out.println("------------------------------");
            while (position != null) {
                System.out.println("Customer Name: " + position.getName() + " AGE: " + position.getAge() + " ID: "
                        + position.getCustomerID());
                position = position.getCustomerLink();
            }
            System.out.println("------------------------------");
        }
    }

    public boolean checkIDExsitance(int id) {
        Customer position = head;
        while (position != null) {
            if (position.getCustomerID() == id)
                return true;
            position = position.getCustomerLink();
        }
        return false;
    }

    // show next
    public Customer goToNext(Customer found) {
        if (found.getCustomerLink() == null)
            System.out.println("End of the list.");
        else {
            previous = found;
            found = previous.getCustomerLink();
            previous.setCustomerLink(found);
        }
        return found;
    }

    // show prev
    public Customer goToPrevious(Customer found) {
        if (previous == head)
            System.out.println("End of the list.");
        else {
            current = head;
            while (current.getCustomerLink() != found) {
                current = current.getCustomerLink();
            }
            previous = current;
        }
        return previous;
    }

    public Customer deleteCustomer() {
        if (head != null) {
            undo = head;
            head = head.getCustomerLink();
        } else {
            System.out.println("Empty list!");
        }
        return undo;
    }

    public Customer deleteCustomerById(int id) {
        Customer temp = head;
        while (temp.getCustomerLink() != null) {
            if (temp.getCustomerID() == id)
                return temp;
            temp = temp.getCustomerLink();
        }
        System.out.println("Not Found");
        return null;
    }

    public int deleteCustomerEnd() {
        Customer position;
        position = head;
        if (head == null) {
            head = null;
            return 0;

        } else {
            for (int i = 0; i < length() - 2; i++)
                position = position.getCustomerLink();
            position.setCustomerLink(null);
            return position.getCustomerID();
        }
    }

    public Customer getCustomerByID(int id) {
        Customer position = head;
        while (position != null) {
            if (position.getCustomerID() == id)
                return position;
            position = position.getCustomerLink();
        }
        return null;
    }

    // search Name
    public Customer searchName(String nameTarget) {
        Customer position;
        position = head;
        String nameAtPosition;
        while (position != null) {
            nameAtPosition = position.getName();
            if (nameAtPosition.equals(nameTarget)) {
                return position;
            }
            position = position.getCustomerLink();
        }
        return null;
    }

    public void sortList() {
        Customer position = head;
        Customer next = head;
        Customer temp = new Customer();

        if (position == null) {
            System.out.println("\nThe List is empty");
        } else {
            for (int i = 0; i < length() - 1; i++) {
                for (int j = 0; j < length() - 1; j++) {
                    next = next.getCustomerLink();
                    if (position.getName().compareTo(next.getName()) > 0) {
                        temp.setName(position.getName());
                        temp.setAge(position.getAge());
                        temp.setCustomerID(position.getCustomerID());

                        position.setName(next.getName());
                        position.setAge(next.getAge());
                        position.setCustomerID(next.getCustomerID());

                        next.setName(temp.getName());
                        next.setAge(temp.getAge());
                        next.setCustomerID(temp.getCustomerID());
                    }
                    position = position.getCustomerLink();
                }
                position = head;
                next = head;
            }
            System.out.println("\nSorted");
        }
    }

    public void clear() {
        head = null;
        tail = null;
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
                    Customer temp = head;
                    while (temp != null) {
                        out.writeObject(temp);
                        temp = temp.getCustomerLink();
                    }
                    out.close();
                    fileOut.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerLL ReadFromFile() throws IOException {
        CustomerLL list = new CustomerLL();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    Customer temp = (Customer) in.readObject();
                    list.addCustomerEnd(temp.getAge(), temp.getName(), temp.getCustomerID());
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
