import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserObject extends UnicastRemoteObject implements IRemoteUsuario {

    protected UserObject() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public ArrayList<User> getResgistrosByUser(String RFCUsr) {
        try {
            System.out.println("Invoke getRegistrosByUser from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UserRepository.getResgistrosByUser(RFCUsr);
    }

}