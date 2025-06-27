import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ExpenseTrackerGUI {
    private JFrame frame;
    private JTextField dayInput;
    private JComboBox<String> typeDropdown;
    private JTextField moneyInput;
    private JTextField categoryInput;
    private JTextArea outputBox;
    private ArrayList<Display> info = new ArrayList<>();

    public ExpenseTrackerGUI() {
        frame = new JFrame("Expense Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel inputArea = new JPanel(new GridLayout(6, 2));
        inputArea.setBackground(new Color(16, 43, 83));
        inputArea.setForeground(Color.white);

        JLabel dayLabel = new JLabel("Date (MM-DD-YYYY): ");
        dayLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        dayLabel.setForeground(new Color(255, 249, 240));
        inputArea.add(dayLabel);
        dayInput = new JTextField();
        dayInput.setFont(new Font("Courier New", Font.PLAIN, 14));
        dayInput.setBackground(new Color(125,159,192));
        dayInput.setForeground(new Color(16,43,83));
        inputArea.add(dayInput);

        String[] typeChoices = {"Income", "Expense" };
        typeDropdown = new JComboBox<>(typeChoices);
        typeDropdown.setFont(new Font("Courier New", Font.PLAIN, 14));
        typeDropdown.setBackground(new Color(125,159,192));
        typeDropdown.setForeground(new Color(16,43,83));
        JLabel typeLabel = new JLabel("Type (income/expense): ");
        typeLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        typeLabel.setForeground(new Color(255, 249, 240));
        inputArea.add(typeLabel);
        inputArea.add(typeDropdown);

        JLabel moneyLabel = new JLabel("Amount ($): ");
        moneyLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        moneyLabel.setForeground(new Color(255, 249, 240));
        moneyInput = new JTextField();
        moneyInput.setFont(new Font("Courier New", Font.PLAIN, 14));
        moneyInput.setBackground(new Color(125,159,192));
        moneyInput.setForeground(new Color(16,43,83));
        inputArea.add(moneyLabel);
        inputArea.add(moneyInput);

        JLabel categoryLabel = new JLabel("Category (ex. food, clothing): ");
        categoryLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        categoryLabel.setForeground(new Color(255, 249, 240));
        categoryInput = new JTextField();
        categoryInput.setFont(new Font("Courier New", Font.PLAIN, 14));
        categoryInput.setBackground(new Color(125,159,192));
        categoryInput.setForeground(new Color(16,43,83));
        inputArea.add(categoryLabel);
        inputArea.add(categoryInput);

        JButton addButton = new JButton("Add Transaction");
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        addButton.setBackground(new Color(206,181,212));
        addButton.setForeground(new Color(77, 47, 81));
        inputArea.add(addButton);

        JButton amountButton = new JButton("Show account balance");
        amountButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        amountButton.setBackground(new Color(206,181,212));
        amountButton.setForeground(new Color(77, 47, 81));
        inputArea.add(amountButton);

        JButton transactionsButton = new JButton("View Transactions");
        transactionsButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        transactionsButton.setBackground(new Color(206,181,212));
        transactionsButton.setForeground(new Color(77, 47, 81));
        inputArea.add(transactionsButton);

        frame.add(inputArea, BorderLayout.NORTH);

        outputBox = new JTextArea();
        outputBox.setFont(new Font("Courier New", Font.PLAIN, 14));
        outputBox.setBackground(new Color(197, 223, 255));
        outputBox.setForeground(new Color(77, 47, 81));
        outputBox.setEditable(false);
        frame.add(new JScrollPane(outputBox), BorderLayout.CENTER);

        addButton.addActionListener(e -> addTransaction());
        amountButton.addActionListener(e -> outputMoney());
        transactionsButton.addActionListener(e -> outputTransactions());

        frame.setVisible(true);
    }

    private void addTransaction() {
        outputBox.setText("");
        String day = dayInput.getText().trim();
        String type = (String) typeDropdown.getSelectedItem();
        double money;
        try{
            money = Double.parseDouble(moneyInput.getText().trim());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(frame, "Enter valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String category = categoryInput.getText().trim();
        Display d = new Display(type, category, money, day);
        info.add(d);
        outputBox.append("Transaction added \n");
        dayInput.setText("");
        moneyInput.setText("");
        categoryInput.setText("");
    }

    private void outputMoney() {
        double money = 0.0;
        for (Display d : info) {
            if (d.getType().equalsIgnoreCase("Income")) {
                money += d.getMoney();
            } else if (d.getType().equalsIgnoreCase("Expense")) {
                money -= d.getMoney();
            }
        }
        outputBox.setText("Current Balance: $" + String.format("%.2f", money) + "\n");
    }

    private void outputTransactions() {
        outputBox.setText("");
        if (info.isEmpty()) {
            outputBox.setText("No transactions to display \n");
        } else {
            for (Display d : info) {
                outputBox.append(d.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new ExpenseTrackerGUI();
    }
}
