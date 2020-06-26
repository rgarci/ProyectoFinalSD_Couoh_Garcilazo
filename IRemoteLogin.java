import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteLogin extends Remote
{
    IRemoteTransaction login() throws RemoteException;
}