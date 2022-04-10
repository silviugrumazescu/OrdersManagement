package Presentation.Controllers;

import BLL.ProdusBLL;
import Model.Client;
import Model.Produs;
import Presentation.GenerateTableUtility;
import Presentation.ProductFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clasa controller pentru fereastra ProductView a interfetei
 */
public class ProductViewController {

    private ProductFrame productFrame;
    private MainController mainController;

    private ProdusBLL produsBLL;

    public ProductViewController(MainController controller){

        this.mainController = controller;

        productFrame = new ProductFrame();
        productFrame.setActionListeners(new BackButtonListener(),
                new ApplyAddProductListener(), new EditProductListener(),
                new DeleteProductListener(), new FilterProductListener(),
                new TableSelectionListener());

        produsBLL = new ProdusBLL();
    }

    /*public void initialize(){
        productFrame.setVisible(true);
        productFrame.getViewProductsTable().clearSelection();
        productFrame.getViewProductsTableModel().setRowCount(0);
        ArrayList<Produs> products = produsBLL.getAllProducts();
        for(Produs p : products){
            Object[] row = {p.getId(), p.getNume(), p.getPret(), p.getDescriere(), p.getCantitate()};
            productFrame.getViewProductsTableModel().addRow(row);
        }
    }*/

    public void initialize(){
        ArrayList<Object> products = new ArrayList<Object>();
        for(Produs p : produsBLL.getAllProducts()){
            products.add((Object)p);
        }
        GenerateTableUtility.populateTable(products, productFrame.getViewProductsTable(), productFrame.getViewProductsTableModel());

        productFrame.setVisible(true);
    }
    public void hide(){
        productFrame.setVisible(false);
    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            hide();
            mainController.initialize();;
        }
    }

    /**
     * : implementeaza functia actionListener actionata in urma apasarii butonului  addProductButton din fereastra ProductFrame.
     * Metoda extrage datele din tabele, le insereaza in baza de date folosindu-se de metoda insertProduct din clasa ProductBLL
     */
    public class ApplyAddProductListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            ArrayList<JTextField> fields = productFrame.getProductFields();
            String nume = fields.get(0).getText();
            int pret = Integer.parseInt(fields.get(1).getText());
            String descriere = fields.get(2).getText();
            int cantitate = Integer.parseInt(fields.get(3).getText());
            produsBLL.insertClient(new Produs(0, nume, pret, descriere, cantitate));

            initialize();
        }
    }

    /**
     * : implementeaza functia actionListener actionata in urma apasarii butonului editProductButton din fereastra ProductFrame.
     * Metoda extrage datele din obiectele JtextField si actualizeaza tupla din baza de date corespunzatoare liniei selectate in tabela din ProductFrame
     */
    public class EditProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = productFrame.getViewProductsTable().getSelectedRow();
            int id = (int)productFrame.getViewProductsTable().getValueAt(selectedRowIndex, 0);

            ArrayList<JTextField> fields = productFrame.getProductFields();
            String nume = fields.get(0).getText();
            int pret = Integer.parseInt(fields.get(1).getText());
            String descriere = fields.get(2).getText();
            int cantitate = Integer.parseInt(fields.get(3).getText());

            produsBLL.updateProduct(new Produs(id, nume, pret, descriere, cantitate));

            initialize();
        }
    }

    /**
     * : implementeaza functia actionListener actionata in urma apasarii butonului deleteButton din fereastra ProductFrame.
     * Metoda sterge tupla din baza de date corespunzatoare elementului selectat in tabela.
     */
    public class DeleteProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = productFrame.getViewProductsTable().getSelectedRow();
            int id = (int)productFrame.getViewProductsTable().getValueAt(selectedRowIndex, 0);

            produsBLL.deleteProduct(id);
            productFrame.getViewProductsTable().clearSelection();
            initialize();
        }
    }

    public class FilterProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * implementeaza functia valueChanged actionata in urma schimbarii selectiei din tabel.
     * Odata ce selectia este schimbata obiectele JtextField vor avea setat ca text informatia corespunzatoare elementului selectat
     */
    public class TableSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            DefaultTableModel model = productFrame.getViewProductsTableModel();
            int selectedRow = productFrame.getViewProductsTable().getSelectedRow();

            int index = 1;
            if(selectedRow != -1)
            {
                for(JTextField field : productFrame.getProductFields()){
                    field.setText(model.getValueAt(selectedRow, index).toString());
                    index++;
                }
            }
        }
    }

}
