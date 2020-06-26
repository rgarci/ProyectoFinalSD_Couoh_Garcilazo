import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

public  class client1 {
    public static void main(String[] args) {
        try {
        
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteLogin login = (IRemoteLogin) registry.lookup("Login");
            IRemoteTransaction rt = login.login();

            Transaction trU1 = new Transaction();
            trU1.setRFCCom("98765ABCDE");
            trU1.setRFCUsr("USER123456");
            trU1.setOperationCode(1);
            trU1.setNoAc(200);
            trU1.setOperationDate(LocalDateTime.now());
            trU1.setOperationPrice(2500.00);
            System.out.println(rt.ofertar(trU1)?"ganador": "perdedor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}