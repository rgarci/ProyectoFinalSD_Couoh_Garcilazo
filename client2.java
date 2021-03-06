import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

public class client2 {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteLogin login = (IRemoteLogin) registry.lookup("Login");
            IRemoteTransaction rt = login.login();

            Transaction trU2 = new Transaction();
            trU2.setRFCCom("BBBBBB2222");
            trU2.setRFCUsr("USER222222");
            trU2.setOperationCode(-1);
            trU2.setNoAc(50);
            trU2.setOperationDate(LocalDateTime.now());
            trU2.setOperationPrice(500.00);
            System.out.println(rt.ofertar(trU2)?"ganador": "perdedor");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}