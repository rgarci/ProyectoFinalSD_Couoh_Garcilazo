import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CompanyObject extends UnicastRemoteObject implements IRemoteCompany {

    protected CompanyObject() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public ArrayList<Company> getAll() {
        try {
            System.out.println("Invoke getAllCompanies from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return CompanyRespository.findAll();
    }

    @Override
    public Company findByRFC(String rfc){
        try {
            System.out.println("Invoke findByRFC from " + getClientHost());
          } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
          }
        return CompanyRespository.findByRFC(rfc);
    }
    
}