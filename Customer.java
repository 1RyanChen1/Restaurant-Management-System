import java.io.Serializable;

public class Customer extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private Customer customerLink;
    private int customerID;

    public Customer() {
        super();
        this.customerID = 0;
        this.customerLink = null;
    }

    public Customer(Customer customer, Customer link) {
        super(customer.getAge(), customer.getName());
        this.customerID = customer.getCustomerID();
        this.customerLink = link;
    }

    public Customer(int age, String name, int customerID, Customer customerLink) {
        super(age, name);
        this.customerLink = customerLink;
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomerLink() {
        return this.customerLink;
    }

    public void setCustomerLink(Customer customerLink) {
        this.customerLink = customerLink;
    }
}
