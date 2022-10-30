package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ClientInterface extends Remote {
    public Vector getGoods() throws RemoteException;
}
