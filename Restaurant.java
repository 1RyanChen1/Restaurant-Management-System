import java.io.Serializable;

public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;
    private int tableNumber;
    private Restaurant link;
    private Customer Customer;
    private Waiter Waiter;
    private Bill bill = null;

    public Restaurant() {
        this.tableNumber = 0;
        Customer = null;
        Waiter = null;
        this.link = null;
    }

    public Restaurant(Restaurant restaurant, Restaurant link) {
        this.bill = restaurant.getBill();
        this.tableNumber = restaurant.getTableNumber();
        Customer = restaurant.getCustomer();
        Waiter = restaurant.getWaiter();
        this.link = link;
    }

    public Restaurant(int tableNumber, Waiter Waiter, Customer Customer, Restaurant link) {
        this.tableNumber = tableNumber;
        this.Waiter = Waiter;
        this.Customer = Customer;
        this.link = link;
    }

    public Restaurant(int tableNumber, Customer Customer, Restaurant link) {
        this.tableNumber = tableNumber;
        this.Customer = Customer;
        this.link = link;
    }

    public Restaurant(int tableNumber, Waiter Waiter, Restaurant link) {
        this.tableNumber = tableNumber;
        this.Waiter = Waiter;
        this.link = link;
    }

    public Restaurant(int tableNumber, Restaurant link) {
        this.tableNumber = tableNumber;
        this.Waiter = null;
        this.Customer = null;
        this.bill = null;
        this.link = link;
    }

    public Restaurant(int tableNumber, Waiter Waiter, Customer Customer, Restaurant link, Bill bill) {
        this.tableNumber = tableNumber;
        this.Waiter = Waiter;
        this.Customer = Customer;
        this.bill = bill;
        this.link = link;
    }

    public void setWaiter(Waiter Waiter) {
        this.Waiter = Waiter;
    }

    public Waiter getWaiter() {
        return this.Waiter;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public Customer getCustomer() {
        return this.Customer;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setRestaurantLink(Restaurant link) {
        this.link = link;
    }

    public Restaurant getRestaurantLink() {
        return this.link;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public Bill getBill() {
        return this.bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
