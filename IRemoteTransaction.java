import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteTransaction extends Remote {
    public boolean ofertar(Transaction tr) throws RemoteException;
    public ArrayList<Transaction> getAllFromLastHour() throws RemoteException;
    public ArrayList<Transaction> getAllFromUser(String RFCUsr) throws RemoteException;
    public void logout() throws RemoteException;
}