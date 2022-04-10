package Presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderFrame extends JFrame {

    private JButton backButton, createOrderButton;
    private JTextField quantityField;
    private JTable viewClientsTable, viewProductsTable, viewOrdersTable;
    private DefaultTableModel viewClientsTableModel, viewProductsTableModel, viewOrdersTableModel;
    private JPanel mainPanel;
    private JLabel errorLabel;

    public OrderFrame(){

        this.setPreferredSize(new Dimension(700, 600));
        this.pack();

        mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        backButton = new JButton("Back");
        createOrderButton = new JButton(("Create Order"));

        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(200, 20));

        JLabel quantityLabel = new JLabel("Quantity");
        JLabel viewClientsLabel = new JLabel("Clients");
        JLabel viewProductsLabel = new JLabel("Products");
        JLabel viewOrdersLabel = new JLabel("Orders");
        errorLabel = new JLabel("Produse insuficiente in stoc!");

        c.gridx = 0; c.gridy = 0;
        //c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(backButton, c);

        // labels
        c.gridx = 0; c.gridy = 1;
        c.gridheight = 1;
        mainPanel.add(viewClientsLabel, c);
        c.gridx = 0; c.gridy = 3;
        c.gridheight = 1;
        mainPanel.add(viewProductsLabel, c);
        c.gridx = 2; c.gridy = 1;
        mainPanel.add(viewOrdersLabel, c);


        // panels
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        mainPanel.add(generateClientsViewTablePanel(), c);
        c.gridx = 0; c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        mainPanel.add(generateProductsViewTablePanel(), c);
        c.gridx = 2; c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 3;
        mainPanel.add(generateOrdersViewTablePanel(), c);

        // base

        c.gridx = 0; c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(quantityLabel, c);
        c.gridx = 1; c.gridy = 5;
        c.gridheight = 1;
        mainPanel.add(quantityField, c);
        c.gridx = 0; c.gridy = 6;
        mainPanel.add(createOrderButton, c);

        c.gridx = 1; c.gridy = 6;
        mainPanel.add(errorLabel, c);

        this.add(mainPanel);

    }

    public JScrollPane generateClientsViewTablePanel(){
        JPanel tablePanel = new JPanel();
        Object data[][] = { {1 ,"aurel", "fasdf", "fdasf", "fdasfs"} , {2, "aurel", "fasdf", "fdasf", "fdasfs"},};
        String column[] = {"ID", "Nume", "Prenume", "Email", "Adresa"};

        viewClientsTableModel = new DefaultTableModel(column, 0);
        viewClientsTable = new JTable(viewClientsTableModel);
        viewClientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for(int i = 0; i < data.length; i++){
            viewClientsTableModel.addRow(data[i]);
        }

        JScrollPane sp = new JScrollPane(viewClientsTable);
        sp.setPreferredSize(new Dimension(300, 200));

        return sp;
    }
    public JScrollPane generateProductsViewTablePanel(){
        JPanel tablePanel = new JPanel();
        Object data[][] = { {1 ,"produs", "fasdf", "fdasf", "fdasfs"} , {2, "aurel", "fasdf", "fdasf", "fdasfs"},};
        String column[] = {"ID", "Nume", "Pret", "Descriere", "Cantitate"};

        viewProductsTableModel = new DefaultTableModel(column, 0);
        viewProductsTable = new JTable(viewProductsTableModel);
        viewProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for(int i = 0; i < data.length; i++){
            viewProductsTableModel.addRow(data[i]);
        }

        JScrollPane sp = new JScrollPane(viewProductsTable);
        sp.setPreferredSize(new Dimension(300, 150));

        return sp;
    }

    public JScrollPane generateOrdersViewTablePanel(){
        JPanel tablePanel = new JPanel();
        Object data[][] = { {1 ,"order", "fasdf", "fdasf", "fdasfs"} , {2, "aurel", "fasdf", "fdasf", "fdasfs"},};
        String column[] = {"ID", "ClientID", "ProductID", "Quantity"};

        viewOrdersTableModel = new DefaultTableModel(column, 0);
        viewOrdersTable = new JTable(viewOrdersTableModel);
        viewOrdersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for(int i = 0; i < data.length; i++){
            viewOrdersTableModel.addRow(data[i]);
        }

        JScrollPane sp = new JScrollPane(viewOrdersTable);
        sp.setPreferredSize(new Dimension(300, 360));

        return sp;
    }

    public void setErrorText(String text){
        errorLabel.setText(text);
    }

    public void setActionListeners(ActionListener backButtonListener, ActionListener createOrderButtonListener) {

        backButton.addActionListener(backButtonListener);
        createOrderButton.addActionListener(createOrderButtonListener);
    }

    public DefaultTableModel getViewClientsTableModel(){
        return viewClientsTableModel;
    }
    public JTable getViewClientsTable (){
        return viewClientsTable;
    }
    public DefaultTableModel getViewProductsTableModel(){
        return viewProductsTableModel;
    }
    public JTable getViewProductsTable (){ return viewProductsTable; }
    public DefaultTableModel getViewOrdersTableModel(){
        return viewOrdersTableModel;
    }
    public JTable getViewOrdersTable(){ return viewOrdersTable; }
    public JTextField getQuantityField(){return quantityField; }
}
