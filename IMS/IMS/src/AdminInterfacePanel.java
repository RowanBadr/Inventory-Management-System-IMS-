import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class AdminInterfacePanel {
    private JPanel adminPanel;
    private JButton generateLowStockReportButton;
    private JButton generateSalesReportButton;
    private JButton addNewProductButton;
    private JButton increaseProductQuantityButton;
    private JButton mainMenuButton; // New button for Main Menu

    private InventoryManagementGUI mainGUI;

    public AdminInterfacePanel(InventoryManagementGUI mainGUI) {
        this.mainGUI = mainGUI;
        initialize();
        addListeners();
    }

    private void initialize() {
        adminPanel = new JPanel(new GridLayout(5, 1));

        generateLowStockReportButton = new JButton("Generate Low Stock Report");
        generateSalesReportButton = new JButton("Generate Sales Report");
        addNewProductButton = new JButton("Add New Product");
        increaseProductQuantityButton = new JButton("Increase Product Quantity");
        mainMenuButton = new JButton("Main Menu"); // New button for Main Menu

        adminPanel.add(generateLowStockReportButton);
        adminPanel.add(generateSalesReportButton);
        adminPanel.add(addNewProductButton);
        adminPanel.add(increaseProductQuantityButton);
        adminPanel.add(mainMenuButton); // Added button for Main Menu
    }

    private void addListeners() {
        generateLowStockReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLowStock();
            }
        });

        generateSalesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSales();
            }
        });

        addNewProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddNewProductDialog();
            }
        });

        increaseProductQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIncreaseQuantityDialog();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.showMainMenu();
            }
        });
    }
    private void showSales() {
        JTextArea productsTextArea = new JTextArea();
        productsTextArea.setEditable(false);

        List<List<Product>> cartHistory = CustomerInterfacePanel.getCart_History();

        for(int i = 0; i<cartHistory.size(); i++){
            productsTextArea.append("Sale " + i+1);
            for(int j = 0; j<cartHistory.get(i).size(); i++){
                productsTextArea.append(cartHistory.get(i).get(j).toString() + "\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(productsTextArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Available Products", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAddNewProductDialog() {
        JTextField productIdField = new JTextField();
        JTextField productNameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Product Name:"));
        panel.add(productNameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int productId = Integer.parseInt(productIdField.getText());
                String productName = productNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Product newProduct = new Product(productId, productName, price, quantity);
                mainGUI.getInventory().addProduct(newProduct);
                JOptionPane.showMessageDialog(null, "New product added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input. Please enter numeric values for price and quantity.");
            }
        }
    }

    private void showLowStock() {
        JTextArea productsTextArea = new JTextArea();
        productsTextArea.setEditable(false);

        List<Product> ListProd = mainGUI.getInventory().sortProductByStock();
        for (int i = 0; i < ListProd.size(); i++) {
            if(ListProd.get(i).quantityInStock > 10) {
                productsTextArea.append(ListProd.get(i).toString() + "\n");
            }
            else {
                productsTextArea.append(ListProd.get(i).toString() + "LOW STOCK!!\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(productsTextArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Available Products", JOptionPane.PLAIN_MESSAGE);
    }
    private void showIncreaseQuantityDialog() {
        JTextField productIdField = new JTextField();
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Quantity to Increase:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Increase Product Quantity",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int productId = Integer.parseInt(productIdField.getText());
                int quantityToAdd = Integer.parseInt(quantityField.getText());

                Product existingProduct = mainGUI.getInventory().getProductInfo(productId);
                if (existingProduct != null) {
                    existingProduct.quantityInStock += quantityToAdd;
                    JOptionPane.showMessageDialog(null, "Product quantity increased successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found with ID: " + productId);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input. Please enter numeric values for product ID and quantity.");
            }
        }
    }

    public JPanel getPanel() {
        return adminPanel;
    }
}
