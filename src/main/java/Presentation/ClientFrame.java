package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientFrame extends JFrame{

    private JButton backButton, applyAddClientButton, applyEditClientButton, deleteButton, filterButton;
    private JScrollPane clientTableScrollPane;
    private ArrayList<JTextField> clientFields;
    private JTable viewClientsTable;
    private DefaultTableModel viewClientsTableModel;
    private JPanel mainPanel;

    public ClientFrame(){

        this.setPreferredSize(new Dimension(800, 400));
        this.pack();
        mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        backButton = new JButton("Back");
        applyAddClientButton = new JButton(("Add New"));
        applyEditClientButton = new JButton("Edit Selected");
        deleteButton = new JButton("Delete");
        filterButton = new JButton("Filter");

        JLabel clientFormLabel = new JLabel("Client form");
        JLabel viewClientsLabel = new JLabel("View clients");
        JLabel filterClientsLabel = new JLabel("Filter clients");

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
        clientTableScrollPane = generateTablePanel();
        mainPanel.add(clientTableScrollPane, c);
       /* c.gridx = 4; c.gridy = 2;
        c.gridwidth = 2;
        mainPanel.add(generateFilterClientsForm(), c); */
        //buttons

        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 1;
        mainPanel.add(applyAddClientButton, c);
        c.gridx = 1; c.gridy = 3;
        mainPanel.add(applyEditClientButton, c);
        c.gridx = 2; c.gridy = 3;
        c.gridwidth = 2;
        mainPanel.add(deleteButton, c);
      /*  c.gridx = 4; c.gridy = 3;
        mainPanel.add(filterButton, c); */

        this.add(mainPanel);
    }

    public JPanel generateClientForm(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        clientFields = new ArrayList<JTextField>();

        for(int i = 0; i < 4; i++){
            JTextField field = new JTextField();
            field.setPreferredSize(new Dimension(200, 20));
            clientFields.add(field);
        }

        JLabel numeLabel = new JLabel("Nume");
        numeLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(numeLabel);
        panel.add(clientFields.get(0));
        JLabel prenumeLabel = new JLabel("Prenume");
        prenumeLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(prenumeLabel);
        panel.add(clientFields.get(1));
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(emailLabel);
        panel.add(clientFields.get(2));
        JLabel adresaLabel = new JLabel("Adresa");
        adresaLabel.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(adresaLabel);
        panel.add(clientFields.get(3));

        return panel;
    }

    public JScrollPane generateTablePanel(){
        Object data[][] = {};
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
        applyAddClientButton.addActionListener(applyAddClientListener);
        applyEditClientButton.addActionListener(editClientListener);
        deleteButton.addActionListener(deleteClientListener);
        filterButton.addActionListener(filterClientListener);
        viewClientsTable.getSelectionModel().addListSelectionListener(tableSelectionListener);

    }

    public JTable getViewClientsTable(){
        return viewClientsTable;
    }

    public DefaultTableModel getViewClientsTableModel(){
        return viewClientsTableModel;
    }

    public ArrayList<JTextField> getClientFields(){
        return clientFields;
    }

}
