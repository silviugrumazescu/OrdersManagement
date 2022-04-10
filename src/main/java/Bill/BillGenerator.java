package Bill;

import Model.Client;
import Model.Comanda;
import Model.Produs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * O clasa utilitara ce are rolul de a genera o factura in urma plasarii unei comenzi
 */
public class BillGenerator {


    /**
     * Se genereaza un fisier cu formatul "order(comandaID).txt" in
     * care sunt introduse datele clientului, datele despre produs
     * si totalul de plata.
     */
    public static void generateBill(Client client, Comanda comanda, Produs produs){

        File file = new File("order" + comanda.getId() + ".txt");
        try{
            FileWriter writer = new FileWriter(file, false);
            writer.write("FACTURA COMANDA NR. " + comanda.getId() + "\n");
            writer.write("--------------------------\n");
            writer.write("Client: \n");
            writer.write("\tNume: " + client.getNume() + "\n");
            writer.write("\tPrenume: " + client.getPrenume() + "\n");
            writer.write("\tEmail: " + client.getEmail() + "\n");
            writer.write("Adresa livrare: " + client.getAdresa() +"\n");
            writer.write("--------------------------\n");
            writer.write("Produse: " + produs.getNume() + " x " + comanda.getCantitate() + "\n");
            writer.write("Total: " + comanda.getCantitate() + " x " + produs.getPret() + " lei = " + (comanda.getCantitate() * produs.getPret()));


            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }


    }

}
