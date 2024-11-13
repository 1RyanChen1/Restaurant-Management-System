import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MenuLL {
    private Menu head;
    private Menu tail;
    private Menu undo;

    File file = new File("D:\\Javabin\\Menu.txt");

    public MenuLL() {
        this.head = null;
        this.tail = null;
        this.undo = null;
    }

    public void addMenu(Menu newMenu) {
        if (head == null) {
            head = newMenu;
            tail = newMenu;
        } else {
            tail.setNext(newMenu);
            newMenu.setPrev(tail);
            tail = newMenu;
        }
    }

    public boolean insert(Menu menu, int id) {
        Menu temp = head;
        while (temp.getNext() != null) {
            if (temp.getId() == id) {
                temp.getNext().setPrev(menu);
                menu.setNext(temp.getNext());
                return true;
            }
            temp = temp.getNext();

        }
        return false;
    }

    public void addMenu(String name, double price, boolean avail, String category, String description, int prepTime,
            boolean isSale, int id, double salePercentage) {
        Menu newMenu = new Menu(name, price, avail, category, description, prepTime,
                isSale, id, salePercentage);
        if (head == null) {
            head = newMenu;
            tail = newMenu;
        } else {
            tail.setNext(newMenu);
            newMenu.setPrev(tail);
            tail = newMenu;
        }
    }

    public Menu removeMenu(int id) {
        Menu current = head;

        while (current != null) {
            if (current.getId() == id) {
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }

                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }

                undo = current;
                return undo;
            }
            current = current.getNext();
        }
        return null;
    }

    public void displayMenus() {

        if (head != null) {
            Menu current = head;
            System.out.println("________Menu________");
            while (current != null) {
                System.out.println("--------------------------");
                System.out.println("ID: " + current.getId() + "\n" +
                        "Name: " + current.getName() + "\n" +
                        "Price: " + current.getPrice() + "\n" +
                        "Availability: " + (current.isAvail() ? "Available" : "Not Available") + "\n" +
                        "Category: " + current.getCategory() + "\n" +
                        "Description: " + current.getDescription() + "\n" +
                        "Prep Time: " + current.getPrepTime() + " min\n" +
                        "Sale: " + (current.isSale() ? "Yes" : "No"));
                if (current.isSale())
                    System.out.println(("Sale off: " + (current.getSalePercentage()) * 100 + "%\n"));
                current = current.getNext();
            }
        } else {
            System.out.println("Empty List");
        }
    }

    public void displaySimple() {
        Menu current = head;
        while (current != null) {
            System.out.println("--------------------------");
            System.out.println("ID: " + current.getId() + "\n" +
                    "Name: " + current.getName() + "\n" +
                    "Price: " + current.getPrice() + "\n");
            current = current.getNext();
        }
    }

    public void undoRemove() {
        if (undo != null) {
            addMenu(undo.getName(), undo.getPrice(), undo.isAvail(), undo.getCategory(), undo.getDescription(),
                    undo.getPrepTime(), undo.isSale(), undo.getId(), undo.getSalePercentage());
            undo = null;
        } else {
            System.out.println("No removal to undo.");
        }
    }

    public int length() {
        int count = 0;
        Menu position = head;

        while (position != null) {
            position = position.getNext();
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void SaveInFile() throws IOException {
        try {
            if (head == null)
                System.out.println("Empty List");
            else {
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                Menu temp = head;
                while (temp != null) {
                    out.writeObject(temp);
                    temp = temp.getNext();
                }
                out.close();
                fileOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MenuLL ReadFromFile() throws IOException {
        MenuLL list = new MenuLL();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    Menu newMenu = (Menu) in.readObject();
                    list.addMenu(newMenu);
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean checkIDExsitance(int id) {
        Menu temp = head;
        while (temp.getNext() != null) {
            if (temp.getId() == id)
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    public Menu searchByName(String name) {
        Menu temp = head;
        while (temp.getNext() != null) {
            if (temp.getName().equals(name))
                return temp;
            temp = temp.getNext();
        }
        return null;
    }

    public Menu searchByID(int id) {
        Menu temp = head;
        while (temp.getNext() != null) {
            if (temp.getId() == id)
                return temp;
            temp = temp.getNext();
        }
        return null;
    }

    public void Clear() {
        head = null;
    }

    public double getSubtotal() {
        Menu temp = head;
        double subtotal = 0.0;
        while (temp.getNext() != null) {
            if (temp.isSale())
                subtotal += temp.getPrice() * (1.0 - temp.getSalePercentage());
            else
                subtotal += temp.getPrice();
            temp = temp.getNext();
        }
        return subtotal;
    }

}
