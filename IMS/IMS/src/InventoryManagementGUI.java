import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InventoryManagementGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton adminButton;
    private JButton customerButton;
    private JButton exitButton;

    private Inventory inventory;
    private OrderQueue orderQueue;

    public InventoryManagementGUI() {
        initialize();
        inventory = new Inventory();
        orderQueue = new OrderQueue();
    }

    private void initialize() {
        frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);

        mainPanel = new JPanel(new GridLayout(3, 1));

        adminButton = new JButton("Admin");
        customerButton = new JButton("Customer");
        exitButton = new JButton("Exit");

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminInterface();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomerInterface();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(adminButton);
        mainPanel.add(customerButton);
        mainPanel.add(exitButton);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showAdminInterface() {
        frame.getContentPane().removeAll();
        frame.repaint();

        AdminInterfacePanel adminPanel = new AdminInterfacePanel(this);
        frame.getContentPane().add(adminPanel.getPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showCustomerInterface() {
        frame.getContentPane().removeAll();
        frame.repaint();

        CustomerInterfacePanel customerPanel = new CustomerInterfacePanel(this);
        frame.getContentPane().add(customerPanel.getPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void showMainMenu() {
        frame.getContentPane().removeAll();
        frame.repaint();

        mainPanel = new JPanel(new GridLayout(3, 1));

        adminButton = new JButton("Admin");
        customerButton = new JButton("Cuackomer");
        exitButton = new JButton("Exit");

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminInterface();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomerInterface();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(adminButton);
        mainPanel.add(customerButton);
        mainPanel.add(exitButton);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryManagementGUI());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public OrderQueue getOrderQueue() {
        return orderQueue;
    }
}
