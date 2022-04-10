package Presentation.Controllers;

import BLL.ClientBLL;
import Model.Client;
import Presentation.ClientFrame;
import Presentation.GenerateTableUtility;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Clasa controller pentru fereastra ClientView a interfetei
 */
public class ClientViewController{

    private ClientFrame clientFrame;
    private MainController mainController;
    private ClientBLL clientBLL;

    public ClientViewController(MainController controller){

        this.mainController = controller;

        clientFrame = new ClientFrame();
        clientFrame.setActionListeners(new BackButtonListener(),
                new ApplyAddClientListener(), new EditClientListener(),
                new DeleteClientListener(), new FilterClientListener(),
                new TableSelectionListener());

        clientBLL = new ClientBLL();
    }

    /**
     * Metoda ce afiseaza fereastra ClientFrame si initializeaza datele din tabele
     */
    /*public void initialize(){

        clientFrame.setVisible(true);
        clientFrame.getViewClientsTable().clearSelection();
        clientFrame.getViewClientsTableModel().setRowCount(0);
        ArrayList<Client> clients = clientBLL.getAllClients();
        for(Client c : clients){
            Object[] row = {c.getId(), c.getNume(), c.getPrenume(), c.getEmail(), c.getAdresa()};
            clientFrame.getViewClientsTableModel().addRow(row);
        }
    }
    public void hide(){
        clientFrame.setVisible(false);
    }

    public class BackButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                hide();
                mainController.initialize();;
            }
    } */

    public void initialize(){

        ArrayList<Object> clients = new ArrayList<Object>();
        for(Client c : clientBLL.getAllClients()){
            clients.add((Object)c);
        }
        GenerateTableUtility.populateTable(clients, clientFrame.getViewClientsTable(), clientFrame.getViewClientsTableModel());

        clientFrame.setVisible(true);
    }
    public void hide(){
        clientFrame.setVisible(false);
    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            hide();
            mainController.initialize();;
        }
    }

    /**
     * implementeaza functia actionListener actionata in urma apasarii butonului
     * addClientButton din fereastra ClientFrame. Metoda extrage datele din tabele,
     * le insereaza in baza de date folosindu-se de metoda insertClient din clasa ClientBLL
     */
    public class ApplyAddClientListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            ArrayList<JTextField> fields = clientFrame.getClientFields();
            String nume = fields.get(0).getText();
            String prenume = fields.get(1).getText();
            String email = fields.get(2).getText();
            String adresa = fields.get(3).getText();
            clientBLL.insertClient(new Client(0, nume, prenume, email, adresa));

            initialize();
        }
    }

    /**
     * implementeaza functia actionListener actionata in urma apasarii butonului editClientButton din fereastra ClientFrame.
     * Metoda extrage datele din obiectele JtextField si actualizeaza tupla din baza de date corespunzatoare liniei selectate in tabela din ClientFrame
     */
    public class EditClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = clientFrame.getViewClientsTable().getSelectedRow();
            int id = (int)clientFrame.getViewClientsTable().getValueAt(selectedRowIndex, 0);

            ArrayList<JTextField> fields = clientFrame.getClientFields();
            String nume = fields.get(0).getText();
            String prenume = fields.get(1).getText();
            String email = fields.get(2).getText();
            String adresa = fields.get(3).getText();
            clientBLL.updateClient(new Client(id, nume, prenume, email, adresa));
            initialize();
        }
    }

    /**
     * : implementeaza functia actionListener actionata in urma apasarii butonului deleteButton
     * din fereastra ClientFrame. Metoda sterge tupla din baza de date corespunzatoare elementului selectat in tabela.
     */
    public class DeleteClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = clientFrame.getViewClientsTable().getSelectedRow();
            int id = (int)clientFrame.getViewClientsTable().getValueAt(selectedRowIndex, 0);

            clientBLL.deleteClient(id);
            clientFrame.getViewClientsTable().clearSelection();
            initialize();
        }
    }

    public class FilterClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * : implementeaza functia valueChanged actionata in urma schimbarii selectiei din tabel.
     * Odata ce selectia este schimbata obiectele JtextField vor avea setat ca text informatia corespunzatoare elementului selectat
     */
    public class TableSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            DefaultTableModel model = clientFrame.getViewClientsTableModel();
            int selectedRow = clientFrame.getViewClientsTable().getSelectedRow();

            int index = 1;
            if(selectedRow != -1){
                System.out.println(model.getColumnCount() + "OULA " + model.getRowCount() );
                for(JTextField field : clientFrame.getClientFields()){
                    field.setText((String) model.getValueAt(selectedRow, index));
                    index++;
                }
            }
        }
    }

}
