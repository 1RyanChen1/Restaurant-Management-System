import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RestaurantLL {
    private Restaurant head;
    private Restaurant current;
    private Restaurant previous;
    private Restaurant tail;
    private Restaurant undo;

    File file = new File("D:\\JavaBin\\Restaurant.txt");

    public RestaurantLL() {
        head = null;
        tail = null;
    }

    public Restaurant getHead() {
        return head;
    }

    public void addRestaurantEnd(Restaurant restaurant) {
        if (tail == null) {
            tail = restaurant;
            head = tail;
        } else {
            Restaurant position = restaurant;
            tail.setRestaurantLink(position);
            tail = position;
        }
    }

    public void addRestaurantEnd(int tableNumber, Waiter Waiter, Customer Customer) {
        if (tail == null) {
            tail = new Restaurant(tableNumber, Waiter, Customer, null);
            head = tail;
        } else {
            Restaurant position = new Restaurant(tableNumber, Waiter, Customer, null);
            tail.setRestaurantLink(position);
            tail = position;
        }
    }

    public void addRestaurantEnd(int tableNumber, Waiter Waiter) {
        if (tail == null) {
            tail = new Restaurant(tableNumber, Waiter, null);
            head = tail;
        } else {
            Restaurant position = new Restaurant(tableNumber, Waiter, null);
            tail.setRestaurantLink(position);
            tail = position;
        }
    }

    public void addInitialize(int tableNumber) {
        if (tail == null) {
            tail = new Restaurant(tableNumber, null);
            head = tail;
        } else {
            Restaurant position = new Restaurant(tableNumber, null);
            tail.setRestaurantLink(position);
            tail = position;
        }
    }

    public void addRestaurantStart(int tableNumber, Waiter Waiter, Customer Customer) {
        if (head == null) {
            head = new Restaurant(tableNumber, Waiter, Customer, head);
            tail = head;
        } else
            head = new Restaurant(tableNumber, Waiter, Customer, head);
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
        Restaurant position = head;
        while (position != null) {
            position = position.getRestaurantLink();
            count++;
        }
        return count;
    }

    public Restaurant goToNext(Restaurant found) {
        if (found.getRestaurantLink() == null)
            System.out.println("End of the list.");
        else {
            previous = found;
            found = previous.getRestaurantLink();
            previous.setRestaurantLink(found);
        }
        return found;
    }

    public Restaurant goToPrevious(Restaurant found) {
        if (previous == head)
            System.out.println("End of the list.");
        else {
            current = head;
            while (current.getRestaurantLink() != found) {
                current = current.getRestaurantLink();
            }
            previous = current;
        }
        return previous;
    }

    public Restaurant deleteRestaurant() {
        if (head != null) {
            undo = head;
            head = head.getRestaurantLink();
        } else {
            System.out.println("Empty list!");
        }
        return undo;
    }

    public Restaurant search(int tableNumber) {
        Restaurant position;
        position = head;
        int target;
        while (position != null) {
            target = position.getTableNumber();
            if (tableNumber == target) {
                return position;
            }
            position = position.getRestaurantLink();
        }
        return null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean checkIDExsitance(int id) {
        Restaurant position = head;
        while (position != null) {
            if (position.getTableNumber() == id)
                return true;
            position = position.getRestaurantLink();
        }
        return false;
    }

    public void showList() {
        Restaurant position;
        position = head;
        if (head == null) {
            System.out.println("————————————————————\nEmpty list\n————————————————————");
        } else {
            while (position != null) {
                System.out.println("------------------------------");
                System.out
                        .println("Table Number: " + position.getTableNumber());
                if (position.getWaiter() == null)
                    System.out.println("No Charged Waiter");

                else {
                    System.out.print("\nCharged Waiter Name: "
                            + position.getWaiter().getName() + " Age: " + position.getWaiter().getAge() + " ID "
                            + position.getWaiter().getWaiterID() + "\n");
                }
                if (position.getCustomer() == null)
                    System.out.println("Guest Customer");
                else
                    System.out.println("Customer Name: " + position.getCustomer().getName() + " Age: "
                            + position.getCustomer().getAge() + " ID: " + position.getCustomer().getCustomerID());
                System.out.println("------------------------------");
                System.out.println("Ordered Food");
                position.getBill().getMenu().displayMenus();
                System.out.println("------------------------------");
                if (position.getBill() == null)
                    System.out.println("Not Bill Added Yet");
                else
                    System.out.print("Bill\nTotal Ordered Food" + position.getBill().getTotalNumOrderedFood()
                            + "\nDiscount " + position.getBill().getDiscount() + "\nSubtotal "
                            + position.getBill().getMenu().getSubtotal() + "\nTotal (13% HST Tax)"
                            + (position.getBill().getMenu().getSubtotal() * position.getBill().gettaxRate()
                                    + position.getBill().getMenu().getSubtotal())
                            + "\n");
                position = position.getRestaurantLink();
            }
        }
    }

    public boolean checkCustomerExistence(Customer customer) {
        Restaurant current = head;
        while (current != null) {
            if (current.getCustomer() == customer) {
                return true;
            }
            current = current.getRestaurantLink();
        }
        return false;
    }

    public void setCustomer(Customer customer, int tableNumber) {
        Restaurant current = head;
        while (current != null) {
            if (current.getTableNumber() == tableNumber) {
                current.setCustomer(customer);
            }
            current = current.getRestaurantLink();
        }
    }

    public void deleteSearch(int tableNumber) {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            Restaurant current = head;
            Restaurant previous = null;
            while (current != null) {
                if (current.getTableNumber() == tableNumber) {
                    if (previous == null) {
                        head = current.getRestaurantLink();
                        if (head == null) {
                            tail = null;
                        }
                    } else {
                        previous.setRestaurantLink(current.getRestaurantLink());
                        if (current.getRestaurantLink() == null) {
                            tail = previous;
                        }
                    }
                    return;
                }
                previous = current;
                current = current.getRestaurantLink();
            }
            System.out.println("Table not found.");
        }
    }

    public void setWaiter(Waiter Waiter, int tableNumber) {
        Restaurant current = head;
        while (current != null) {
            if (current.getTableNumber() == tableNumber) {
                current.setWaiter(Waiter);
            }
            current = current.getRestaurantLink();
        }
    }

    public void deleteCustomer(int customerID) {
        Restaurant current = head;
        while (current != null) {
            if (current.getCustomer().getCustomerID() == customerID) {
                current.setCustomer(null);

                System.out.println("Removed");
                break;
            }
            current = current.getRestaurantLink();
        }
    }

    public void deleteWaiter(int WaiterID) {
        Restaurant current = head;
        while (current != null) {
            if (current.getWaiter().getWaiterID() == WaiterID) {
                current.setWaiter(null);

                System.out.println("Removed");
                break;
            }
            current = current.getRestaurantLink();
        }
    }

    public void sortList() {

        Restaurant position = head;
        Restaurant next = head;
        Restaurant temp = new Restaurant();

        if (position == null) {
            System.out.println("\nThe List is empty");
        } else {
            for (int i = 0; i < length() - 1; i++) {
                for (int j = 0; j < length() - 1; j++) {
                    next = next.getRestaurantLink();
                    if (position.getTableNumber() - next.getTableNumber() > 0) {
                        temp.setWaiter(position.getWaiter());
                        temp.setCustomer(position.getCustomer());
                        temp.setTableNumber(position.getTableNumber());
                        temp.setBill(position.getBill());

                        position.setWaiter(next.getWaiter());
                        position.setCustomer(next.getCustomer());
                        position.setTableNumber(next.getTableNumber());
                        position.setBill(next.getBill());

                        next.setWaiter(temp.getWaiter());
                        next.setCustomer(temp.getCustomer());
                        next.setTableNumber(temp.getTableNumber());
                        next.setBill(temp.getBill());
                    }
                    position = position.getRestaurantLink();
                }
                position = head;
                next = head;
            }
            System.out.println("\nSorted");
        }
    }

    public void setBillByTableNum(Restaurant position, Bill bill) {
        position.setBill(bill);
    }

    public void SaveInFile() throws IOException {
        try {

            if (head == null) {
                System.out.println("Empty List");
            } else {
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                Restaurant temp = head;
                while (temp != null) {
                    out.writeObject(temp);
                    temp = temp.getRestaurantLink();
                }
                out.close();
                fileOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RestaurantLL ReadFromFile() throws IOException {
        RestaurantLL list = new RestaurantLL();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    Restaurant temp = (Restaurant) in.readObject();
                    list.addRestaurantEnd(temp);
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

    public void showListTabNum() {
        Restaurant temp = head;
        while (temp.getRestaurantLink() != null) {
            System.out.println("Table Number: " + temp.getTableNumber());
            temp = temp.getRestaurantLink();
        }
    }

    public Restaurant getRestaurantByTableNum(int tableNum) {
        Restaurant temp = head;
        while (temp.getRestaurantLink() != null) {
            if (temp.getTableNumber() == tableNum)
                return temp;
            temp = temp.getRestaurantLink();
        }
        return null;
    }
}
