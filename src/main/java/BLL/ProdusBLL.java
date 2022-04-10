package BLL;

import DAO.ClientDAO;
import DAO.ComandaDAO;
import DAO.ProdusDAO;
import Model.Client;
import Model.Produs;

import java.util.ArrayList;
import java.util.List;


/**
 * Clasa ce se ocupa cu operatiile pe produse ale aplicatiei in legatura cu baza de date
 */
public class ProdusBLL{

    private ProdusDAO produsDAO;
    private ComandaDAO comandaDAO;

    public ProdusBLL(){
        produsDAO = new ProdusDAO();
        comandaDAO = new ComandaDAO();
    }

    public ArrayList<Produs> getAllProducts(){
        List<Produs> products = produsDAO.findAll();
        return new ArrayList<Produs>(products);
    }

    /**
     * Metoda ce sterge un produs din baza de date. Inainte de stergerea unui produs se vor sterge si toate comenzile care contineau acel produs
     * @param id
     */
    public void deleteProduct(int id){
        // prima data stergem toate comenzile care pe care le-a avut clientul
        comandaDAO.deleteOrderByProductID(id);
        produsDAO.delete(id);
    }

    public void insertClient(Produs p){
        produsDAO.insert(p);
    }
    public void updateProduct(Produs p){
        produsDAO.update(p);
    }


}
