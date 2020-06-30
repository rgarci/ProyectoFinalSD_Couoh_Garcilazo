import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

public class client4 {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteLogin login = (IRemoteLogin) registry.lookup("Login");
            IRemoteTransaction rt = login.login();

            Transaction trU3 = new Transaction();
            trU3.setRFCCom("BBBBBB2222");
            trU3.setRFCUsr("USER444444");
            trU3.setOperationCode(1);
            trU3.setNoAc(100);
            trU3.setOperationDate(LocalDateTime.now());
            trU3.setOperationPrice(550.00);
            System.out.println(rt.ofertar(trU3)?"ganador":"perdedor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}