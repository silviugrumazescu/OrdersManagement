package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GenerateTableUtility {

    public static void populateTable(ArrayList<Object> objectArrayList, JTable table, DefaultTableModel model){

        model.setRowCount(0);
        model.setColumnCount(0);
        table.setModel(model);

        for(Field field : objectArrayList.get(0).getClass().getDeclaredFields()){
            field.setAccessible(true);
            model.addColumn(field.getName());
        }
        for(Object o : objectArrayList){
            ArrayList<Object> rowData = new ArrayList<Object>();
            for(Field field : objectArrayList.get(0).getClass().getDeclaredFields()){
                field.setAccessible(true);
                try{
                    Object value = field.get(o);
                    rowData.add(value);
                }
                catch(IllegalAccessException ex){
                    ex.printStackTrace();
                }
            }

            model.addRow(rowData.toArray());
        }
        System.out.println(model.getColumnCount() + "FDFDF" + model.getRowCount());

    }

}
