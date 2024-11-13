import java.io.Serializable;

public class Waiter extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int WaiterID;
    private Waiter Waiterlink;

    public Waiter() {
        super();
        this.WaiterID = 0;
        this.Waiterlink = null;
    }

    public Waiter(int age, String name, int WaiterID, Waiter link) {
        super(age, name);
        this.WaiterID = WaiterID;
        this.Waiterlink = link;
    }

    public Waiter(Waiter Waiter, Waiter link) {
        super(Waiter.getAge(), Waiter.getName());
        this.WaiterID = Waiter.getWaiterID();
        this.Waiterlink = link;
    }

    public int getWaiterID() {
        return this.WaiterID;
    }

    public void setWaiterID(int WaiterID) {
        this.WaiterID = WaiterID;
    }

    public Waiter getWaiterLink() {
        return this.Waiterlink;
    }

    public void setWaiterLink(Waiter link) {
        this.Waiterlink = link;
    }
}
