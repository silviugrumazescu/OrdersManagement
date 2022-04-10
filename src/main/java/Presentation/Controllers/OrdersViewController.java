package Presentation.Controllers;

import BLL.ClientBLL;
import BLL.ComandaBLL;
import BLL.ProdusBLL;
import Model.Client;
import Model.Comanda;
import Model.Produs;
import Presentation.OrderFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clasa controller pentru fereastra OrdersView a interfetei
 */
public class OrdersViewController{

    private OrderFrame orderFrame;
    private MainController mainController;
    private ClientBLL clientBLL;
    private ComandaBLL comandaBLL;
    private ProdusBLL produsBLL;

    public OrdersViewController(MainController controller){

        this.mainController = controller;

        orderFrame = new OrderFrame();
        orderFrame.setActionListeners(new BackButtonListener(),
                new CreateOrderButtonListener());

        clientBLL = new ClientBLL();
        comandaBLL = new ComandaBLL();
        produsBLL = new ProdusBLL();
    }

    /**
     * Metoda ce actualizeaza valorile din cele trei tabele ale interfetei iar apoi face fereastra ordersFrame vizibila
     */
    public void initialize(){
        updateClientsTable();
        updateProductsTable();
        updateOrdersTable();

        orderFrame.setVisible(true);

        orderFrame.setErrorText("");

    }

    private void updateOrdersTable(){
        orderFrame.getViewOrdersTableModel().setRowCount(0);
        ArrayList<Comanda> orders = comandaBLL.getAllOrders();
        for(Comanda c : orders){
            Object[] row = {c.getId(), c.getClientID(), c.getProdusID(), c.getCantitate()};
            orderFrame.getViewOrdersTableModel().addRow(row);
        }
    }

    private void updateClientsTable(){
        orderFrame.getViewClientsTableModel().setRowCount(0);
        ArrayList<Client> clients = clientBLL.getAllClients();
        for(Client c : clients){
            Object[] row = {c.getId(), c.getNume(), c.getPrenume(), c.getEmail(), c.getAdresa()};
            orderFrame.getViewClientsTableModel().addRow(row);
        }
    }

    private void updateProductsTable(){
        orderFrame.getViewProductsTableModel().setRowCount(0);
        ArrayList<Produs> products = produsBLL.getAllProducts();
        for(Produs p : products){
            Object[] row = {p.getId(), p.getNume(), p.getPret(), p.getDescriere(), p.getCantitate()};
            orderFrame.getViewProductsTableModel().addRow(row);
        }
    }
    public void hide(){
        orderFrame.setVisible(false);
    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            hide();
            mainController.initialize();;
        }
    }

    /**
     * implementeaza functia actionListener actionata in urma apasarii butonului  createOrderButton din fereastra OrderFrame.
     * Metoda determina id-ul produsului si al clientului selectat in tabelele din OrderFrame iar apoi insereaza rezultatul folosind clasa ComandaBLL.
     * Daca rezultatul returnat de metoda insertComanda este -1 atunci se afiseaza un mesaj de eroare. In caz contrar se reactualizeaza informatiile din tabele.
     */
    public class CreateOrderButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            int selectedClientRow = orderFrame.getViewClientsTable().getSelectedRow();
            int selectedClientID = (int)orderFrame.getViewClientsTableModel().getValueAt(selectedClientRow, 0);

            int selectedProductRow = orderFrame.getViewProductsTable().getSelectedRow();
            int selectedProductID = (int)orderFrame.getViewProductsTableModel().getValueAt(selectedProductRow, 0);

            int quantity = Integer.parseInt(orderFrame.getQuantityField().getText());
            int success = comandaBLL.insertComanda(new Comanda(0, selectedClientID, selectedProductID, quantity));
            if(success == -1){
                orderFrame.setErrorText("Produse insuficiente in depozit!");
            }
            else {
                initialize();
            }


        }
    }



}
