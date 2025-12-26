import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class CustomerInterfacePanel {
    private JPanel customerPanel;
    private JButton displayProductsButton;
    private JButton addToCartButton;
    private JButton placeOrderButton;
    private JButton mainMenuButton; // New button for Main Menu

    private InventoryManagementGUI mainGUI;
    private List<Product> cart;

    private static List<List<Product>> Cart_History;

    public CustomerInterfacePanel(InventoryManagementGUI mainGUI) {
        this.mainGUI = mainGUI;
        initialize();
        addListeners();
        cart = new ArrayList<>();
    }

    public static List<List<Product>> getCart_History() {
        return Cart_History;
    }



    private void initialize() {
        customerPanel = new JPanel(new GridLayout(4, 1));

        displayProductsButton = new JButton("Display Available Products");
        addToCartButton = new JButton("Add to Cart");
        placeOrderButton = new JButton("Place Order");
        mainMenuButton = new JButton("Main Menu"); // New button for Main Menu

        customerPanel.add(displayProductsButton);
        customerPanel.add(addToCartButton);
        customerPanel.add(placeOrderButton);
        customerPanel.add(mainMenuButton); // Added button for Main Menu
    }

    private void addListeners() {
        displayProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableProducts();
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddToCartDialog();
            }
        });

        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.showMainMenu();
            }
        });
    }

    private void showAvailableProducts() {
        JTextArea productsTextArea = new JTextArea();
        productsTextArea.setEditable(false);

        for (Product product : mainGUI.getInventory().getProducts()) {
            productsTextArea.append(product.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(productsTextArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Available Products", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAddToCartDialog() {
        JTextField productIdField = new JTextField();
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add to Cart", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int productId = Integer.parseInt(productIdField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Product product = mainGUI.getInventory().getProductInfo(productId);
                if (product != null && product.quantityInStock >= quantity) {
                    cart.add(new Product(product.productId, product.productName, product.price, quantity));
                    JOptionPane.showMessageDialog(null, "Added to cart successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Product not available or insufficient stock.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input. Please enter numeric values for product ID and quantity.");
            }
        }
    }

    private void placeOrder() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty. Please add items before placing an order.");
            return;
        }

        String customerName = JOptionPane.showInputDialog("Enter Your Name:");
        if (customerName != null && !customerName.isEmpty()) {
            int orderId = mainGUI.getOrderQueue().getQueueSize() + 1;
            Order order = new Order(orderId, customerName);

            for (Product product : cart) {
                order.addProduct(product, product.quantityInStock);
                product.quantityInStock -= product.quantityInStock; // Deduct stock from inventory
            }

            mainGUI.getOrderQueue().enqueueOrder(order);
            Cart_History.add(cart);
            cart.clear(); // Clear the cart after placing the order
            JOptionPane.showMessageDialog(null, "Order placed successfully. Order ID: " + orderId);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid customer name. Please enter a valid name.");
        }
    }

    public JPanel getPanel() {
        return customerPanel;
    }
}
