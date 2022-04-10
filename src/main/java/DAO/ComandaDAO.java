package DAO;

import Model.Client;
import Model.Comanda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import Connection.ConnectionFactory;

public class ComandaDAO extends AbstractDAO<Comanda>{

    public ComandaDAO(){
        super(Comanda.class);
    }

    /**
     * Aceasta metoda acceseaza baza de date si sterge doar tupla care are atributul ClientID egal cu valoarea primita ca parametru
     */
    public void deleteOrderByClientID(int clientID){
        String deleteQuery = "DELETE FROM Comanda WHERE clientID = " + clientID;
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();
        }catch(SQLException ex){
            LOGGER.log(Level.WARNING,  "ComandaDAO:update " + ex.getMessage());
        }
    }

    /**
     * Aceasta metoda acceseaza baza de date si sterge doar tupla care are atributul productID egal cu valoarea primita ca parametru
     */
    public void deleteOrderByProductID(int productID){
        String deleteQuery = "DELETE FROM Comanda WHERE produsID = " + productID;
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();
        }catch(SQLException ex){
            LOGGER.log(Level.WARNING,  "ComandaDAO:update " + ex.getMessage());
        }
    }


}
