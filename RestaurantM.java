import java.util.*;
import java.io.*;

public class RestaurantM {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\Javabin\\Restaurant.txt");
        Scanner fileS = new Scanner(file);
        Scanner fileScanner = new Scanner(file);
        Scanner inputSearchMenu = new Scanner(System.in);
        try {
            file.delete();
            file.createNewFile();
            Restaurant dRestaurant;
            Waiter dWaiter;
            Customer dCustomer;
            RestaurantStack rStack = new RestaurantStack();
            WaiterStack WaiterStack = new WaiterStack();
            CustomerStack customerStack = new CustomerStack();
            RestaurantLL list = new RestaurantLL();
            WaiterLL calist = new WaiterLL();
            CustomerLL culist = new CustomerLL();
            Menu delete = new Menu();
            MenuLL menulist = new MenuLL();
            MenuStack menuStack = new MenuStack();
            int choice, choice1, choice2, choice3;
            Scanner input = new Scanner(System.in);
            do {
                System.out
                        .print("________________Welcome to Restaurant Management System________________\n\n[1] Waiter\n[2] Order \n[3] Menu\n[4] Customer\n[5] Exit\nEnter choice: ");
                choice = input.nextInt();
                if (choice == 4) {
                    do {
                        System.out.print(
                                "=====Customer=====\n\n[1] ShowList\n[2] Delete\n[3] Search\n[4] Sort\n[5] Clear\n[6] ADD\n[7] Undo\n[8] Read From File\n[9] Save In File\n[10] Exit\nEnter choice: ");
                        choice1 = input.nextInt();
                        if (choice1 == 1) {
                            culist.showList();
                        } else if (choice1 == 2) {
                            dCustomer = culist.deleteCustomer();
                            customerStack.push(dCustomer);
                            list.deleteCustomer(dCustomer.getCustomerID());
                        } else if (choice1 == 3) {
                            System.out.print("Enter name to search: ");
                            String name = input.next();
                            while (name.length() == 1) {
                                System.out.println("Name Invalid\nPlease Type Again: ");
                                name = input.next();
                            }
                            Customer found = culist.searchName(name);
                            if (found == null)
                                System.out.println("Not in the list.");
                            else {
                                System.out.println(found.getName() + " " + found.getAge());
                                int searchMenu;
                                do {
                                    System.out.print(
                                            "MENU SEARCH\n====\n[1] NEXT\n[2] PREVIOUS\n[3] BACK\nEnter your choice: ");
                                    searchMenu = inputSearchMenu.nextInt();
                                    if (searchMenu == 1) {
                                        found = culist.goToNext(found);
                                        System.out.println(found.getName() + " " + found.getAge());
                                    } else if (searchMenu == 2) {
                                        found = culist.goToPrevious(found);
                                        System.out.println(found.getName() + " " + found.getAge());
                                    }
                                    if (searchMenu == 3) {
                                    }
                                } while (searchMenu != 3);
                            }
                        } else if (choice1 == 4) {
                            culist.sortList();
                        } else if (choice1 == 5) {
                            culist.clear();
                        } else if (choice1 == 6) {
                            System.out.print("\nName: ");
                            String name = input.next();
                            System.out.print("\nAge: ");
                            int age = input.nextInt();
                            System.out.print("\nId: ");
                            int id = input.nextInt();
                            while (culist.checkIDExsitance(id)) {
                                System.out.print("\nId Already Exist\nInput Again: ");
                                id = input.nextInt();
                            }
                            culist.addCustomerStart(age, name, id);
                        } else if (choice1 == 7) {
                            Customer a = customerStack.pop();
                            if (a == null)
                                System.out.println("Nothing to undo.");
                            else
                                culist.addCustomerStart(a.getAge(), a.getName(), a.getCustomerID());
                        } else if (choice1 == 8) {
                            culist = culist.ReadFromFile();
                        } else if (choice1 == 9) {
                            calist.SaveInFile();
                        } else if (choice1 == 10) {
                        } else {
                            System.out.println("Invalid Input");
                        }
                    } while (choice1 != 10);
                } else if (choice == 1) {
                    do {
                        System.out.print(
                                "=====Waiter=====\n[1] ShowList\n[2] Delete\n[3] Search\n[4] Sort\n[5] Clear\n[6] Undo\n[7] ADD\n[8] Read From File\n[9] Save In File\n[10] Exit\nEnter choice: ");
                        choice2 = input.nextInt();
                        if (choice2 == 1) {
                            calist.showList();
                        } else if (choice2 == 2) {
                            dWaiter = calist.deleteWaiter();
                            WaiterStack.push(dWaiter);
                            list.deleteWaiter(dWaiter.getWaiterID());
                        } else if (choice2 == 3) {
                            System.out.print("Enter name to search: ");
                            String name = input.next();
                            while (name.length() == 1) {
                                System.out.println("Name Invalid\nPlease Type Again: ");
                                name = input.next();
                            }
                            Waiter found = calist.searchName(name);
                            if (found == null)
                                System.out.println("Not in the list.");
                            else {
                                System.out.println(found.getName() + " " + found.getAge());
                                int searchMenu;
                                do {
                                    System.out.print(
                                            "MENU SEARCH\n====\n[1] NEXT\n[2] PREVIOUS\n[3] BACK\nEnter your choice: ");
                                    searchMenu = inputSearchMenu.nextInt();
                                    if (searchMenu == 1) {
                                        found = calist.goToNext(found);
                                        System.out.println(found.getName() + " " + found.getAge());
                                    } else if (searchMenu == 2) {
                                        found = calist.goToPrevious(found);
                                        System.out.println(found.getName() + " " + found.getAge());
                                    }
                                    if (searchMenu == 3) {

                                    }
                                } while (searchMenu != 3);
                            }
                        } else if (choice2 == 4) {
                            calist.sortList();
                        } else if (choice2 == 5) {
                            calist.clear();
                        } else if (choice2 == 6) {
                            Waiter a = WaiterStack.pop();
                            if (a == null)
                                System.out.println("Nothing to undo.");
                            else
                                calist.addWaiterStart(a.getAge(), a.getName(), a.getWaiterID());
                        } else if (choice2 == 7) {
                            System.out.print("\nName: ");
                            String name = input.next();
                            System.out.print("\nAge: ");
                            int age = input.nextInt();
                            System.out.print("\nId: ");
                            int id = input.nextInt();
                            while (calist.checkIDExsitance(id)) {
                                System.out.print("\nId Already Exist\nInput Again: ");
                                id = input.nextInt();
                            }
                            calist.addWaiterStart(age, name, id);
                        } else if (choice2 == 8) {
                            calist = calist.ReadFromFile();
                        } else if (choice2 == 9) {
                            calist.SaveInFile();
                        } else if (choice2 == 10) {
                        } else {
                            System.out.println("Invalid Input");
                        }
                    } while (choice2 != 10);
                } else if (choice == 7777777) {
                    System.out.println("Cristiano Ronaldo Siuuuuuuuuuuuuuuuuuuuuu");
                } else if (choice == 2) {
                    do {
                        System.out.print(
                                "Restaurant\n[1] Show Current List\n[2] Edit\n[3] ADD\n[4] Delete\n[5] Undo\n[6] Save in File\n[7] Read from File\n[8] Exit\nEnter choice: ");
                        choice3 = input.nextInt();
                        if (choice3 == 1) {
                            list.showList();
                        } else if (choice3 == 2) {// Havent Done Yet
                            list.showListTabNum();
                            System.out.print("Enter Table number: ");
                            int tableNum = input.nextInt();
                            while (!list.checkIDExsitance(tableNum)) {
                                System.out.print("ID does not Exist\nPlease try again: ");
                                tableNum = input.nextInt();
                            }
                            int choice32;
                            do {
                                System.out.print(
                                        "-----Editing-----\n[1]Customer Login\n[2] Set Waiter\n[3] Order\n[4] Edit Bill\n[5] Exit");
                                choice32 = input.nextInt();
                                if (choice32 == 1) {
                                    System.out.print("\nCustomer Id: ");
                                    int id = input.nextInt();
                                    culist.showList();
                                    if (culist.checkIDExsitance(id)) {
                                        if (list.checkCustomerExistence(culist.getCustomerByID(id))) {
                                            System.out.println("ID already logged in");
                                        } else {
                                            list.setCustomer(culist.getCustomerByID(id), tableNum);
                                            System.out.println("Succeeded");
                                        }
                                    } else {
                                        System.out.print("New Customer Creating\nName: ");
                                        String name = input.next();
                                        while (name.length() == 1) {
                                            System.out.println("Name Invalid\nPlease Type Again: ");
                                            name = input.next();
                                        }
                                        System.out.print("\nAge: ");
                                        int age = input.nextInt();
                                        Customer newCustomer = new Customer(age, name, id, null);
                                        culist.addCustomerEnd(newCustomer);

                                    }
                                } else if (choice32 == 2) {
                                    if (calist.isEmpty())
                                        System.out.println("No Waiter Added Yet");
                                    else {
                                        System.out.print("Enter Waiter ID: ");
                                        int WaiterID = input.nextInt();
                                        if (calist.checkIDExsitance(WaiterID))
                                            list.setWaiter(calist.getWaiterByID(WaiterID), tableNum);
                                        else
                                            System.out.println("ID not found");
                                    }
                                } else if (choice32 == 3) {
                                    Order(list.getRestaurantByTableNum(tableNum), menulist);
                                } else if (choice32 == 4) {
                                    int choice244;
                                    do {
                                        System.out.println(
                                                "-----Editing-----\n[1]Apply Discount \n[2] Set Date\n[3] Exit\nEnter Choice:");
                                        choice244 = input.nextInt();
                                        switch (choice244) {
                                            case 1:
                                                System.out.print("Enter Discount Percentage: ");
                                                double percentage = input.nextDouble() / 100.0;
                                                list.search(tableNum).getBill().setDiscount(percentage);
                                                break;
                                            case 2:
                                                System.out.print("Enter Date: ");
                                                String date = input.next();
                                                list.search(tableNum).getBill().setDate(date);
                                                break;
                                            default:
                                                break;
                                        }
                                    } while (choice244 != 2);
                                } else if (choice32 == 5) {
                                } else
                                    System.out.println("Invalid Input");
                            } while (choice32 != 5);
                        } else if (choice3 == 3) {
                            if (calist.isEmpty()) {
                                System.out.print("No Waiter Yet\nAdd Waiter\n");
                                System.out.print("\nName: ");
                                String name = input.next();
                                while (name.length() == 1) {
                                    System.out.println("Name Invalid\nPlease Type Again: ");
                                    name = input.next();
                                }
                                System.out.print("\nAge: ");
                                int age = input.nextInt();
                                System.out.print("\nId: ");
                                int id = input.nextInt();
                                calist.addWaiterStart(age, name, id);
                                System.out.println("Waiter Added");
                            } else {
                                calist.showList();
                                System.out.print("Enter charged Waiter id: ");
                                int cid = input.nextInt();
                                if (calist.checkIDExsitance(cid)) {
                                    System.out.print("Customer Login [Y/N]: ");
                                    char clogin = input.next().charAt(0);
                                    if (clogin == 'Y' || clogin == 'y') {
                                        System.out.print("\nCustomer Id: ");
                                        int id = input.nextInt();
                                        culist.showList();
                                        if (culist.checkIDExsitance(id)) {
                                            if (list.checkCustomerExistence(culist.getCustomerByID(id))) {
                                                System.out.println("ID already logged in");
                                            } else {
                                                System.out.print("\nEnter the table number");
                                                int tableNumber = input.nextInt();
                                                while (list.checkIDExsitance(tableNumber)) {
                                                    System.out.println("Table Number already exist.");
                                                    System.out.print("Input new Table Number: ");
                                                    tableNumber = input.nextInt();
                                                }
                                                list.addRestaurantEnd(tableNumber, calist.getWaiterByID(cid),
                                                        culist.getCustomerByID(id));
                                            }
                                        } else {
                                            System.out.print("New Customer Creating\nName: ");
                                            String name = input.next();
                                            while (name.length() == 1) {
                                                System.out.println("Name Invalid\nPlease Type Again: ");
                                                name = input.next();
                                            }
                                            System.out.print("\nAge: ");
                                            int age = input.nextInt();
                                            culist.addCustomerStart(age, name, id);
                                            System.out.print("Input the table number: ");
                                            int tableNumber = input.nextInt();
                                            while (list.checkIDExsitance(tableNumber)) {
                                                System.out.println("Table Number already exist.");
                                                System.out.print("Input new Table Number: ");
                                                tableNumber = input.nextInt();
                                            }
                                            list.addRestaurantEnd(tableNumber, calist.getWaiterByID(cid),
                                                    culist.getCustomerByID(id));
                                        }
                                    } else if (clogin == 'N' || clogin == 'n') {
                                        System.out.print("\nEnter the table number");
                                        int tableNumber = input.nextInt();
                                        while (list.checkIDExsitance(tableNumber)) {
                                            System.out.println("Table Number already exist.");
                                            System.out.print("Input new Table Number: ");
                                            tableNumber = input.nextInt();
                                        }
                                        list.addRestaurantEnd(tableNumber, calist.getWaiterByID(cid));
                                    } else {
                                        do {
                                            System.out.println("Invalid Input\nPlease try again: ");
                                            clogin = input.next().charAt(0);
                                        } while (clogin == 'Y' || clogin == 'y' || clogin == 'N' || clogin == 'n');
                                    }
                                } else {
                                    calist.showList();
                                    System.out.println("ID does not exist.");
                                }
                            }
                        } else if (choice3 == 4) {
                            dRestaurant = list.deleteRestaurant();
                            rStack.push(dRestaurant);
                        } else if (choice3 == 5) {
                            Restaurant a = rStack.pop();
                            if (a == null)
                                System.out.println("Nothing to undo");
                            else
                                list.addRestaurantStart(a.getTableNumber(), a.getWaiter(), a.getCustomer());
                        } else if (choice3 == 6) {
                            list.SaveInFile();
                        } else if (choice3 == 7) {
                            list = list.ReadFromFile();
                        } else if (choice3 == 8) {

                        } else
                            System.out.println("Invalid Input");
                    } while (choice3 != 8);

                } else if (choice == 3) {
                    int menuChoice;
                    do {
                        System.out.print(
                                "Menu Setting\n[1] Display\n[2] Add Items\n[3] Delete Items\n[4] Undo\n[5] Search\n[6] Save in File\n[7] Read From File\n[8] Exit\nEnter your choice: ");
                        menuChoice = input.nextInt();
                        if (menuChoice == 1) {
                            menulist.displayMenus();
                        } else if (menuChoice == 2) {
                            System.out.print("Enter Name: ");
                            String name = input.next();
                            System.out.print("\nEnter Price: ");
                            double price = input.nextDouble();
                            System.out.print("\nIs it available? (true/false): ");
                            boolean avail = input.nextBoolean();
                            System.out.print("\nEnter Category: ");
                            String category = input.next();
                            System.out.print("\nEnter Description: ");
                            String description = input.next();
                            System.out.print("\nEnter Preparation Time (in minutes): ");
                            int prepTime = input.nextInt();
                            System.out.print("\nIs it on sale? (true/false): ");
                            boolean isSale = input.nextBoolean();
                            System.out.print("\nEnter Sale Percentage (1 if none 0 if free): ");
                            double salePercentage = input.nextDouble();
                            System.out.print("\nEnter ID: ");
                            int id = input.nextInt();
                            if (menulist.checkIDExsitance(id)) {
                                System.out.print("ID already occupied: you may assigned a new ID to it: ");
                                id = input.nextInt();
                                while (menulist.checkIDExsitance(id)) {
                                    System.out.print("ID already occupied: you may assigned a new ID to it: ");
                                    id = input.nextInt();
                                }
                            }
                            menulist.addMenu(name, price, avail, category, description, prepTime, isSale, id,
                                    salePercentage);
                        } else if (menuChoice == 3) {
                            System.out.print("Enter ID: ");
                            int dMenuID = input.nextInt();
                            delete = menulist.removeMenu(dMenuID);
                            menuStack.push(delete);
                        } else if (menuChoice == 4) {
                            if (menuStack.getSize() == 0)
                                System.out.println("Nothing to UNDO");
                            else {
                                Menu undo = menuStack.pop();
                                if (menulist.checkIDExsitance(undo.getId())) {
                                    System.out.print("ID already occupied: you may assigned a new ID to it: ");
                                    int newId = input.nextInt();
                                    while (menulist.checkIDExsitance(newId)) {
                                        System.out.print("ID already occupied: you may assigned a new ID to it: ");
                                        newId = input.nextInt();
                                    }
                                    undo.setId(newId);
                                    menulist.addMenu(undo);
                                } else
                                    menulist.addMenu(undo);
                            }
                        } else if (menuChoice == 5) {
                            int searchChoice;
                            Menu temp;
                            System.out.print("====Search====\n[1] By Name\n [2] By ID [3] Exit\n Enter your choice: ");
                            searchChoice = input.nextInt();
                            do {
                                switch (searchChoice) {
                                    case 1:
                                        System.out.print("Enter Name: ");
                                        String name = input.next();
                                        temp = menulist.searchByName(name);
                                        if (temp == null) {
                                            System.out.println("Item Not Found");
                                        } else {
                                            int searchChoice1;

                                            do {
                                                System.out.print(
                                                        "Item Found\n[1] Set Name\n[2] Set Price\n[3] Set Availability\n[4] Set Category\n"
                                                                +
                                                                "[5] Set Description\n[6] Set Preparation Time\n[7] Set Sale Status\n[8] Set ID\n"
                                                                +
                                                                "[9] Set Sale Percentage\n[10] Exit\nChoose an option: ");
                                                searchChoice1 = input.nextInt();

                                                switch (searchChoice1) {
                                                    case 1:
                                                        System.out.print("\nEnter new name: ");
                                                        name = input.next();
                                                        temp.setName(name);
                                                        break;
                                                    case 2:
                                                        System.out.print("\nEnter new price: ");
                                                        double price = input.nextDouble();
                                                        temp.setPrice(price);
                                                        break;
                                                    case 3:
                                                        System.out.print("\nIs it available? (true/false): ");
                                                        boolean avail = input.nextBoolean();
                                                        temp.setAvail(avail);
                                                        break;
                                                    case 4:
                                                        System.out.print("\nEnter new category: ");
                                                        String category = input.next();
                                                        temp.setCategory(category);
                                                        break;
                                                    case 5:
                                                        System.out.print("\nEnter new description: ");
                                                        String description = input.next();
                                                        temp.setDescription(description);
                                                        break;
                                                    case 6:
                                                        System.out.print("\nEnter new preparation time: ");
                                                        int prepTime = input.nextInt();
                                                        temp.setPrepTime(prepTime);
                                                        break;
                                                    case 7:
                                                        System.out.print("\nIs it on sale? (true/false): ");
                                                        boolean isSale = input.nextBoolean();
                                                        temp.setSale(isSale);
                                                        break;
                                                    case 8:
                                                        System.out.print("\nEnter new ID: ");
                                                        int id = input.nextInt();
                                                        if (menulist.checkIDExsitance(id)) {
                                                            System.out.print(
                                                                    "ID already occupied: you may assigned a new ID to it: ");
                                                            id = input.nextInt();
                                                            while (menulist.checkIDExsitance(id)) {
                                                                System.out.print(
                                                                        "ID already occupied: you may assigned a new ID to it: ");
                                                                id = input.nextInt();
                                                            }
                                                        }
                                                        temp.setId(id);
                                                        break;
                                                    case 9:
                                                        System.out.print("\nEnter new sale percentage: ");
                                                        double salePercentage = input.nextDouble();
                                                        if (salePercentage >= 0)
                                                            temp.setSalePercentage(salePercentage);
                                                        else
                                                            System.out.println("Invalid Input");
                                                        break;
                                                    case 10:
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid option. Try again.");
                                                }
                                                input.nextLine();
                                            } while (searchChoice1 != 10);
                                        }
                                        break;
                                    case 2:
                                        System.out.print("Enter ID: ");
                                        int id = input.nextInt();
                                        temp = menulist.searchByID(id);
                                        if (temp == null) {
                                            System.out.println("Item Not Found");
                                        } else {
                                            int searchChoice1;

                                            do {
                                                System.out.print(
                                                        "Item Found\n[1] Set Name\n[2] Set Price\n[3] Set Availability\n[4] Set Category\n"
                                                                +
                                                                "[5] Set Description\n[6] Set Preparation Time\n[7] Set Sale Status\n[8] Set ID\n"
                                                                +
                                                                "[9] Set Sale Percentage\n[0] Exit\nChoose an option: ");
                                                searchChoice1 = input.nextInt();

                                                switch (searchChoice1) {
                                                    case 1:
                                                        System.out.print("\nEnter new name: ");
                                                        name = input.next();
                                                        temp.setName(name);
                                                        break;
                                                    case 2:
                                                        System.out.print("\nEnter new price: ");
                                                        double price = input.nextDouble();
                                                        temp.setPrice(price);
                                                        break;
                                                    case 3:
                                                        System.out.print("\nIs it available? (true/false): ");
                                                        boolean avail = input.nextBoolean();
                                                        temp.setAvail(avail);
                                                        break;
                                                    case 4:
                                                        System.out.print("\nEnter new category: ");
                                                        String category = input.next();
                                                        temp.setCategory(category);
                                                        break;
                                                    case 5:
                                                        System.out.print("\nEnter new description: ");
                                                        String description = input.next();
                                                        temp.setDescription(description);
                                                        break;
                                                    case 6:
                                                        System.out.print("\nEnter new preparation time: ");
                                                        int prepTime = input.nextInt();
                                                        temp.setPrepTime(prepTime);
                                                        break;
                                                    case 7:
                                                        System.out.print("\nIs it on sale? (true/false): ");
                                                        boolean isSale = input.nextBoolean();
                                                        temp.setSale(isSale);
                                                        break;
                                                    case 8:
                                                        System.out.print("\nEnter new ID: ");
                                                        id = input.nextInt();
                                                        if (menulist.checkIDExsitance(id)) {
                                                            System.out.print(
                                                                    "ID already occupied: you may assigned a new ID to it: ");
                                                            id = input.nextInt();
                                                            while (menulist.checkIDExsitance(id)) {
                                                                System.out.print(
                                                                        "ID already occupied: you may assigned a new ID to it: ");
                                                                id = input.nextInt();
                                                            }
                                                        }
                                                        temp.setId(id);
                                                        break;
                                                    case 9:
                                                        System.out.print("\nEnter new sale percentage: ");
                                                        double salePercentage = input.nextDouble();
                                                        temp.setSalePercentage(salePercentage);
                                                        break;
                                                    case 10:
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid option. Try again.");
                                                }
                                            } while (searchChoice1 != 10);
                                        }
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Invalid Input");
                                        ;
                                }
                            } while (searchChoice != 3);
                        } else if (menuChoice == 6) {
                            menulist.SaveInFile();
                        } else if (menuChoice == 7) {
                            menulist = menulist.ReadFromFile();
                        } else if (menuChoice == 8) {

                        } else
                            System.out.print("Invalid Input");
                    } while (menuChoice != 8);
                } else if (choice == 5) {
                } else {
                    System.out.println("Invalid Input");
                }
            } while (choice != 5);
            input.close();
            fileS.close();
            fileScanner.close();
            inputSearchMenu.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Order(Restaurant restaurant, MenuLL menulist) {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.print("------Ordering------\n[1] Add\n[2] Remove\n[3] Exit\nEnter your choice: ");
            choice = input.nextInt();
            if (choice == 1) {
                System.out.print("Enter the Item ID: ");
                menulist.displayMenus();
                int id = input.nextInt();
                restaurant.getBill().getMenu().addMenu(restaurant.getBill().getMenu().searchByID(id));
                restaurant.getBill()
                        .setSubtotal(restaurant.getBill().getSubtotal() + menulist.searchByID(id).getPrice());
                restaurant.getBill().setTotalNumOrderedFood(restaurant.getBill().getTotalNumOrderedFood() + 1);
            } else if (choice == 2) {
                menulist.displayMenus();
                System.out.print("Enter the Item ID: ");
                int id = input.nextInt();
                if (restaurant.getBill().getMenu().checkIDExsitance(id)) {
                    {
                        restaurant.getBill().getMenu().removeMenu(id);
                        restaurant.getBill()
                                .setSubtotal(restaurant.getBill().getSubtotal() - menulist.searchByID(id).getPrice());
                        restaurant.getBill().setTotalNumOrderedFood(restaurant.getBill().getTotalNumOrderedFood() - 1);
                    }
                } else
                    System.out.println("Item not found");
            } else if (choice == 3) {
            } else
                System.out.println("Invalid Input");
        } while (choice != 3);
        input.close();
    }
}