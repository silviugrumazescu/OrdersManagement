package DAO;

import Model.Client;
import Model.Comanda;
import Model.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import Connection.ConnectionFactory;

public class ProdusDAO extends AbstractDAO<Produs>{

    public ProdusDAO(){
        super(Produs.class);
    }

    /**
     * @return returneaza atributul cantitate din tupla care are atributul id egal cu valoarea primita ca parametru.
     */
    public int getStockOfProduct(int id){
        String getStockQuery = "SELECT cantitate FROM Produs WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(getStockQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            int returnedValue = -1;
            System.out.println("STOCK ID " + id);
            while(resultSet.next()){
                System.out.println("S_ACINTRAT IN WGILE");
                returnedValue = resultSet.getInt("cantitate");
            }

            return returnedValue;

        }catch(SQLException ex){
            LOGGER.log(Level.WARNING, "ComandaDAO:getStockofProduct " + ex.getMessage());
        }
        return -1;
    }

}
