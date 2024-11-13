import java.io.Serializable;

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double price;
    private boolean avail;
    private String category;
    private String description;
    private int prepTime;
    private boolean isSale;
    private int id;
    // 1.0 means 100% charge
    private double salePercentage = 1.0;
    private Menu next;
    private Menu prev;

    public Menu() {
        this.name = null;
        this.price = 0.0;
        this.avail = true;
        this.category = null;
        this.description = null;
        this.prepTime = 0;
        this.isSale = false;
        this.id = 0;
        this.salePercentage = 1.0;
        this.prev = null;
        this.next = null;
    }

    public Menu(String name, double price, boolean avail, String category, String description, int prepTime,
            boolean isSale, int id, double salePercentage) {
        this.name = name;
        this.price = price;
        this.avail = avail;
        this.category = category;
        this.description = description;
        this.prepTime = prepTime;
        this.isSale = isSale;
        this.id = id;
        this.salePercentage = salePercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price * salePercentage;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvail() {
        return avail;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean isSale) {
        this.isSale = isSale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(double num) {
        this.salePercentage = num;
    }

    public Menu getNext() {
        return next;
    }

    public void setNext(Menu next) {
        this.next = next;
    }

    public Menu getPrev() {
        return prev;
    }

    public void setPrev(Menu prev) {
        this.prev = prev;
    }
}
