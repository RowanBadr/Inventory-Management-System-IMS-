public class Product {
    int productId;
    String productName;
    double price;
    int quantityInStock;

    public Product(int productId, String productName, double price, int quantityInStock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Price: $" + price + ", Stock: "
                + quantityInStock;
    }
}
