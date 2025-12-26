import java.util.ArrayList;
import java.util.List;

class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
        // Initialize with some sample products
        products.add(new Product(1, "Product A", 10.0, 50));
        products.add(new Product(2, "Product B", 15.0, 30));
        products.add(new Product(3, "Product C", 20.0, 20));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductInfo(int productId) {
        for (Product product : products) {
            if (product.productId == productId) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> sortProductByStock() {
        List<Product> sortedList = products;
        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = 1; j < sortedList.size(); j++) {
                if (sortedList.get(i).quantityInStock > sortedList.get(j).quantityInStock) {
                    Product temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }
}
