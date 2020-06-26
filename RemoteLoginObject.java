import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteLoginObject extends UnicastRemoteObject implements IRemoteLogin {

    private FactoryCompetencia fc;
    protected RemoteLoginObject(FactoryCompetencia fc) throws RemoteException {
        super();
        this.fc = fc;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public IRemoteTransaction login() throws RemoteException {
        TransactionObject ntro = new TransactionObject(fc);
        return ntro;
    }
    
}