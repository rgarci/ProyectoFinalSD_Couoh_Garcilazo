import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            TransactionObject to = new TransactionObject();
            registry.rebind("Transaction", to);

            CompanyObject co = new CompanyObject();
            registry.rebind("Company", co);

            UserObject uo = new UserObject();
            registry.rebind("User", uo);

            Competencia competencia = Competencia.getInstance();
            competencia.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}