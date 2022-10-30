package rmi;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.Vector;

public class ServerMain implements ClientInterface {
    private static final String driverName = "org.sqlite.JDBC";
    private static final String connectionString = "jdbc:sqlite:goods.db";

    public static Vector DBRequest() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
            return null;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
            return null;
        }

        System.out.println("Connected");

        String sql = "SELECT orderr.id, good.name, status.status " +
                "FROM orderr INNER JOIN good ON orderr.good = good.id " +
                "INNER JOIN status ON orderr.status = status.id;";


        Vector vec = new Vector();

        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                vec.addElement(new Object[] { rs.getString("id"), rs.getString("name"), rs.getString("status") });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
        }

        return vec;
    }

    public Vector getGoods() throws RemoteException {
        System.out.println("Goods request");

        return DBRequest();
    }
}