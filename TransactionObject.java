import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class TransactionObject extends UnicastRemoteObject implements IRemoteTransaction {

    protected TransactionObject() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void ofertar(Transaction tr) {
        Competencia.hacerOferta(tr);
    }

    @Override
    public ArrayList<Transaction> getAllFromLastHour() {
        return TransactionRepository.findAllfromLastHour();
    }

    @Override
    public ArrayList<Transaction> getAllFromUser(String RFCUsr) throws RemoteException {
        return TransactionRepository.getAllFromUser(RFCUsr);
    }
    
}