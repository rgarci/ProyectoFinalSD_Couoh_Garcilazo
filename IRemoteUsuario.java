import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteUsuario extends Remote{
    
    public ArrayList<User> getResgistrosByUser(String RFCUsr) throws RemoteException;
}