package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductFrame extends JFrame {

    private JButton backButton, applyAddProductButton, applyEditProductButton, deleteButton, filterButton;
    private ArrayList<JTextField> productFields;
    private JTable viewProductsTable;
    private DefaultTableModel viewProductsTableModel;
    private JPanel mainPanel;

    public ProductFrame(){

        this.setPreferredSize(new Dimension(800, 400));
        this.pack();

        mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        backButton = new JButton("Back");
        applyAddProductButton = new JButton(("Add New"));
        applyEditProductButton = new JButton("Edit Selected");
        deleteButton = new JButton("Delete");
        filterButton = new JButton("Filter");

        JLabel clientFormLabel = new JLabel("Product form");
        JLabel viewClientsLabel = new JLabel("View products");
        JLabel filterClientsLabel = new JLabel("Filter products");

        c.gridx = 0; c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(backButton, c);

        // labels
        c.gridx = 0; c.gridy = 1;
        mainPanel.add(clientFormLabel,c);
        c.gridx = 2; c.gridy = 1;
        mainPanel.add(viewClientsLabel,c);
      /*  c.gridx = 4; c.gridy = 1;
        mainPanel.add(filterClientsLabel, c); */

        // panels
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2;
        mainPanel.add(generateClientForm(), c);
        c.gridx = 2; c.gridy = 2;
        mainPanel.add(generateTablePanel(), c);
      /*  c.gridx = 4; c.gridy = 2;
        c.gridwidth = 2;
        mainPanel.add(generateFilterClientsForm(), c); */
        //buttons

        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 1;
        mainPanel.add(applyAddProductButton, c);
        c.gridx = 1; c.gridy = 3;
        mainPanel.add(applyEditProductButton, c);
        c.gridx = 2; c.gridy = 3;
        c.gridwidth = 2;
        mainPanel.add(deleteButton, c);
    /*    c.gridx = 4; c.gridy = 3;
        mainPanel.add(filterButton, c); */

        this.add(mainPanel);

    }

    public JPanel generateClientForm(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        productFields = new ArrayList<JTextField>();

        for(int i = 0; i < 4; i++){
            JTextField field = new JTextField();
            field.setPreferredSize(new Dimension(200, 20));
            productFields.add(field);
        }
        JLabel numeLabel = new JLabel("Nume");
        numeLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(numeLabel);
        panel.add(productFields.get(0));
        JLabel prenumeLabel = new JLabel("Pret");
        prenumeLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(prenumeLabel);
        panel.add(productFields.get(1));
        JLabel emailLabel = new JLabel("Descriere");
        emailLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(emailLabel);
        panel.add(productFields.get(2));
        JLabel adresaLabel = new JLabel("Cantitate");
        adresaLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(adresaLabel);
        panel.add(productFields.get(3));

        return panel;
    }

    public JScrollPane generateTablePanel(){
        JPanel tablePanel = new JPanel();
        Object data[][] = { {1, "aurel", "fasdf", "fdasf", "fdasfs"} , {2, "aurel", "fasdf", "fdasf", "fdasfs"},};
        String column[] = {"ID" ,"Nume", "Pret", "Descriere", "Cantitate"};

        viewProductsTableModel = new DefaultTableModel(column, 0);
        viewProductsTable = new JTable(viewProductsTableModel);
        viewProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for(int i = 0; i < data.length; i++){
            viewProductsTableModel.addRow(data[i]);
        }

        JScrollPane sp = new JScrollPane(viewProductsTable);
        sp.setPreferredSize(new Dimension(300, 200));

        return sp;
    }

   /* public JPanel generateFilterClientsForm(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ArrayList<JTextField> fields = new ArrayList<JTextField>();

        for(int i = 0; i < 4; i++){
            JTextField field = new JTextField();
            field.setPreferredSize(new Dimension(200, 20));
            fields.add(field);
        }

        panel.add(new JLabel("Nume"));
        panel.add(fields.get(0));
        panel.add(new JLabel("Prenume"));
        panel.add(fields.get(1));
        panel.add(new JLabel("Email"));
        panel.add(fields.get(2));
        panel.add(new JLabel("Adresa"));
        panel.add(fields.get(3));

        return panel;
    } */

    public void setActionListeners(ActionListener backButtonListener, ActionListener applyAddClientListener,
                                  ActionListener editClientListener, ActionListener deleteClientListener,
                                  ActionListener filterClientListener, ListSelectionListener tableSelectionListener) {

        backButton.addActionListener(backButtonListener);
        applyAddProductButton.addActionListener(applyAddClientListener);
        applyEditProductButton.addActionListener(editClientListener);
        deleteButton.addActionListener(deleteClientListener);
        filterButton.addActionListener(filterClientListener);
        viewProductsTable.getSelectionModel().addListSelectionListener(tableSelectionListener);

    }

    public DefaultTableModel getViewProductsTableModel(){
        return viewProductsTableModel;
    }

    public JTable getViewProductsTable(){
        return viewProductsTable;
    }

    public ArrayList<JTextField> getProductFields(){
        return productFields;
    }

}
