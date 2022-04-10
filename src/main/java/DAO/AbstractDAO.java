package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> entityClass) {
        this.type = entityClass;
         //this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO: finaAll" + e.getMessage());
        }
        finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }

        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Aceasta metoda genereaza un string in care sunt plasate numele field-urile clasei T, intr-un format potrivit pentru generarea interogarilor SQL
     */
    public String getTypesQueryString(){
        String typesForQuery = "(";
        for(Field field : type.getDeclaredFields()){
            if(field.getName() != "id"){
                typesForQuery += field.getName();
                typesForQuery += ",";
            }
        }
        typesForQuery = typesForQuery.substring(0, typesForQuery.length()-1);
        typesForQuery += ")";
        return typesForQuery;
    }

    /**
     * Genereaza un string cu formatul (?,?,…?) numarul semnelor de intrebare fiind numarul de field-urile ale clasei T
     */
    public String getQuestionMarkStringQuery(){
        String valuesForQuery = "(";
        for(Field field : type.getDeclaredFields()){
            if(field.getName() != "id"){
                valuesForQuery += "?,";
            }
        }
        valuesForQuery = valuesForQuery.substring(0, valuesForQuery.length()-1);
        valuesForQuery += ")";
        return valuesForQuery;
    }

    /**
     * Insereaza in tabela corespunzatoare din baza de date un obiect de tip T prin tehnica reflexiei
     * @return returneaza id-ul generat de baza de date in urma inserarii acesteia
     */
    public int insert(T t) {
        String insertQuery = "INSERT INTO " + type.getSimpleName() + " ";
        String typesForQuery = getTypesQueryString();
        String valuesForQuery = getQuestionMarkStringQuery();
        int insertedId = -1;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;

        try{
            insertQuery += typesForQuery + " VALUES " + valuesForQuery;
            insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for(Field field : type.getDeclaredFields()){
                if(field.getName() != "id"){
                    PropertyDescriptor p = new PropertyDescriptor(field.getName(), type);
                    Method getter = p.getReadMethod();
                    Object o = getter.invoke(t);
                    insertStatement.setString(index, o.toString());
                    index++;
                }
            }
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

        }
        catch(IntrospectionException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        catch(IllegalAccessException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        catch(InvocationTargetException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        catch(SQLException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        return insertedId;
    }

    public void insert(ArrayList<T> elements){
        String insertQuery = "INSERT INTO " + type.getSimpleName() + " ";
        String typesForQuery = getTypesQueryString();
        String valuesForQuery = getQuestionMarkStringQuery();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try{
            insertQuery += typesForQuery + " VALUES " + valuesForQuery;
            insertStatement = connection.prepareStatement(insertQuery);
            for(T t : elements) {
                int index = 1;
                for (Field field : type.getDeclaredFields()) {
                    if (field.getName() != "id") {
                        PropertyDescriptor p = new PropertyDescriptor(field.getName(), type);
                        Method getter = p.getReadMethod();
                        Object o = getter.invoke(t);
                        insertStatement.setString(index, o.toString());
                        index++;
                    }
                }
                insertStatement.executeUpdate();
            }

        }
        catch(IntrospectionException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        catch(IllegalAccessException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        catch(InvocationTargetException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
        catch(SQLException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + ex.getMessage());}
    }

    /**
     * Aceasta metoda modifica valorile tuplei corespunzatoare field-ului id din cadrul obiectului t.
     */
    public T update(T t) {
        String updateQuery = "UPDATE " + type.getSimpleName() + " SET ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        Object id = null;
        try{
            for(Field field : type.getDeclaredFields()){
                if(field.getName() != "id"){
                    PropertyDescriptor p = new PropertyDescriptor(field.getName(), type);
                    Method getter = p.getReadMethod();
                    Object o = getter.invoke(t);
                    updateQuery += field.getName() + " = " + "'" +  o.toString() + "'" + ",";
                }
                else {
                    PropertyDescriptor p = new PropertyDescriptor(field.getName(), type);
                    Method getter = p.getReadMethod();
                    id = getter.invoke(t);
                }
            }
            updateQuery = updateQuery.substring(0, updateQuery.length()-1);
            updateQuery += " WHERE id = " + id.toString();
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.executeUpdate();
        }
        catch(IntrospectionException | IllegalAccessException | InvocationTargetException | SQLException ex){LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + ex.getMessage());}
        return t;
    }

    /**
     * Sterge tupla cu id-ul primit ca parametru din tabela corespunzatoare clasei ce mosteneste aceasta metoda.
     * @param id
     */
    public void delete(int id){
        String deleteQuery = "DELETE FROM " + type.getSimpleName() + " WHERE id = " + id;
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();
        }
        catch(SQLException ex){
            LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:update " + ex.getMessage());
        }
    }

}
