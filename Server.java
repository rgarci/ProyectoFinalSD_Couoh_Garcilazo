import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();

            CompanyObject co = new CompanyObject();
            registry.rebind("Company", co);

            UserObject uo = new UserObject();
            registry.rebind("User", uo);

            FactoryCompetencia fc = FactoryCompetencia.getInstance();

            RemoteLoginObject rlo = new RemoteLoginObject(fc);
            registry.rebind("Login", rlo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}