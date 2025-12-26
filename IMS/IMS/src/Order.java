import java.util.ArrayList;
import java.util.List;

class Order {
    private int orderId;
    private String customerName;
    private List<Product> productsOrdered;
    private double totalAmount;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productsOrdered = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        productsOrdered.add(new Product(product.productId, product.productName, product.price, quantity));
        totalAmount += product.price * quantity;
    }
}
