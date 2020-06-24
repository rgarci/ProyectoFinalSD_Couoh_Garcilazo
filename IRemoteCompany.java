import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteCompany extends Remote {
   
    public ArrayList<Company> getAll() throws RemoteException;
    public Company findByRFC(String rfc) throws RemoteException;
}