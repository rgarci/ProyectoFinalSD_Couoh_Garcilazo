import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

public class client3 {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteLogin login = (IRemoteLogin) registry.lookup("Login");
            IRemoteTransaction rt = login.login();

            Transaction trU3 = new Transaction();
            trU3.setRFCCom("BBBBBB2222");
            trU3.setRFCUsr("USER666666");
            trU3.setOperationCode(1);
            trU3.setNoAc(200);
            trU3.setOperationDate(LocalDateTime.now());
            trU3.setOperationPrice(3250.00);
            System.out.println(rt.ofertar(trU3)?"ganador":"perdedor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}