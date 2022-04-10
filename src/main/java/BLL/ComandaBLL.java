package BLL;

import DAO.ClientDAO;
import DAO.ComandaDAO;
import DAO.ProdusDAO;
import Model.Client;
import Model.Comanda;
import Model.Produs;

import Bill.BillGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * Clasa ce se ocupa cu operatiile pe comenzi ale aplicatiei in legatura cu baza de date
 */
public class  ComandaBLL{

    private ComandaDAO comandaDAO;
    private ProdusDAO produsDAO;
    private ClientDAO clientDAO;

    public ComandaBLL(){
        comandaDAO = new ComandaDAO();
        produsDAO = new ProdusDAO();
        clientDAO = new ClientDAO();
    }

    public ArrayList<Comanda> getAllOrders(){
        List<Comanda> orders = comandaDAO.findAll();
        return new ArrayList<Comanda>(orders);
    }

    public void deleteComanda(int id){
        comandaDAO.delete(id);
    }

    /**
     * Metoda ce insereaza o comanda in baza de date. Inainte de inserare se verifica daca sunt suficiente elemente in stoc
     * @return returneaza -1 in cazul in care nu sunt suficiente elemente in stoc si 1 in caz contrar. Daca nu sunt suficiente elemente in stoc operatia de insertie nu se va realiza
     */
    public int insertComanda(Comanda c){
        int productsInStock = produsDAO.getStockOfProduct(c.getProdusID());

        if(productsInStock < c.getCantitate()){
            return -1;
        }
        else
        {
            int generatedId = comandaDAO.insert(c);
            c.setId(generatedId);
            Produs produs = produsDAO.findById(c.getProdusID());
            Client client = clientDAO.findById(c.getClientID());
            produs.setCantitate(produs.getCantitate()-c.getCantitate());
            produsDAO.update(produs);

            BillGenerator.generateBill(client, c, produs);
            return 1;
        }

    }
    public void updateOrder(Comanda c){
        comandaDAO.update(c);
    }

}
