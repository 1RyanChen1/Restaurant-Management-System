public class Bill {
    private MenuLL Menu;
    private double discount = 0;
    private int totalNumOrderedFood = 0;
    private double subtotal = 0.0;
    private double taxRate = 0.13;
    private double totalAmount = 0;
    private String date;

    public Bill(MenuLL Menu, double discount, int totalNumOrderedFood, double subtotal, double taxRate,
            double totalAmount, String date) {
        this.Menu = Menu;
        this.discount = discount;
        this.totalNumOrderedFood = totalNumOrderedFood;
        this.subtotal = subtotal;
        this.taxRate = taxRate;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Bill() {
        this.discount = 0.0;
        this.totalNumOrderedFood = 0;
        this.subtotal = 0.0;
        this.taxRate = 0.0;
        this.totalAmount = 0.0;
        this.date = "";
    }

    public MenuLL getMenu() {
        return Menu;
    }

    public void setMenu(MenuLL Menu) {
        this.Menu = Menu;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getTotalNumOrderedFood() {
        return totalNumOrderedFood;
    }

    public void setTotalNumOrderedFood(int totalNumOrderedFood) {
        this.totalNumOrderedFood = totalNumOrderedFood;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double gettaxRate() {
        return taxRate;
    }

    public void settaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void calculateTotalAmount() {
        double discountedSubtotal = subtotal - discount;
        this.totalAmount = discountedSubtotal + discountedSubtotal * taxRate;
    }
}
