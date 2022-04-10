package BLL;

import DAO.ClientDAO;
import DAO.ComandaDAO;
import Model.Client;

import java.util.ArrayList;
import java.util.List;


/**
 * Clasa ce se ocupa cu operatiile pe clienti ale aplicatiei in legatura cu baza de date
 */
public class ClientBLL{

    private ClientDAO clientDAO;
    private ComandaDAO comandaDAO;

    public ClientBLL(){
        clientDAO = new ClientDAO();
        comandaDAO = new ComandaDAO();
    }

    public ArrayList<Client> getAllClients(){
        List<Client> clients = clientDAO.findAll();
        return new ArrayList<Client>(clients);
    }

    public void deleteClient(int id){
        // prima data stergem toate comenzile care pe care le-a avut clientul
        comandaDAO.deleteOrderByClientID(id);
        clientDAO.delete(id);
    }

    /**
     * Se insereaza un client in baza de date.
     * @param c atributul id din obiectul c poate fi orice valoare deoarece id-ul va fi generat oricum automat in urma inserarii
     */
    public void insertClient(Client c){
        clientDAO.insert(c);
    }

    /**
     * Se actualizeaza informatiile unui client in baza de date
     * @param c este necesar ca atributul id din obiectul c sa corespunda unui client din baza de date
     */
    public void updateClient(Client c){
        clientDAO.update(c);
    }

}
