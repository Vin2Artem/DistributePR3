package rmi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class JavaServer {
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to start server...");

            final ServerMain server = new ServerMain();

            final Registry registry = LocateRegistry.createRegistry(8732);

            Remote stub = UnicastRemoteObject.exportObject(server, 0);
            registry.bind("server.goods", stub);

            System.out.println("Started successfully.");
            System.out.println("Accepting requests. (Halt program to stop.)");

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception exception){
            System.err.println("ServerMain: " + exception);
        }
    }
}
