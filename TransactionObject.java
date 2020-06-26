import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;

public class TransactionObject extends UnicastRemoteObject implements IRemoteTransaction, Unreferenced {

    private boolean winnerFlag;
    private FactoryCompetencia fc;

    protected TransactionObject(FactoryCompetencia fc) throws RemoteException {
        super();
        winnerFlag = false;
        this.fc = fc;
    }

    private static final long serialVersionUID = 1L;

    public void setWinnerFlag(boolean winnerFlag) {
        this.winnerFlag = winnerFlag;
    }

    @Override
    public boolean ofertar(Transaction tr) {

        Competencia competencia = fc.getCompetencia(tr.getRFCCom());
        competencia.hacerOferta(tr, this);
        try {
            Thread.sleep(125000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return winnerFlag;
    }

    @Override
    public ArrayList<Transaction> getAllFromLastHour() {
        return TransactionRepository.findAllfromLastHour();
    }

    @Override
    public ArrayList<Transaction> getAllFromUser(String RFCUsr) throws RemoteException {
        return TransactionRepository.getAllFromUser(RFCUsr);
    }

    @Override
    public void logout() throws RemoteException {
        try {
            unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unreferenced() {
        try {
            unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
    
}