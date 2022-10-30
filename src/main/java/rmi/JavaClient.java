package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.*;

public class JavaClient {
    public static void main (String [] args) {
        MainForm mainForm = new MainForm();
        try {
            final Registry registry = LocateRegistry.getRegistry(8732);

            ClientInterface clientInterface = (ClientInterface) registry.lookup("server.goods");

            Object[] arr = clientInterface.getGoods().toArray();

            mainForm.setRows(arr);

        } catch (Exception exception) {
            System.err.println("JavaClient: " + exception);
            JOptionPane.showMessageDialog(null,
                    "Ошибка соединения с сервером",
                    "Внимание",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}