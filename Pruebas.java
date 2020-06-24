import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteTransaction rt = (IRemoteTransaction) registry.lookup("Transaction");

            //Código para hacer ventas concurrentes
            /*Transaction trU1 = new Transaction();
            trU1.setRFCCom("ABCDE12345");
            trU1.setRFCUsr("USER123456");
            trU1.setOperationCode(-1);
            trU1.setNoAc(20);
            trU1.setOperationDate(LocalDateTime.now());
            trU1.setOperationPrice(3500.00);
            rt.ofertar(trU1);

            Transaction trU2 = new Transaction();
            trU2.setRFCCom("ABCDE12345");
            trU2.setRFCUsr("USER678901");
            trU2.setOperationCode(-1);
            trU2.setNoAc(50);
            trU2.setOperationDate(LocalDateTime.now());
            trU2.setOperationPrice(2500.00);
            rt.ofertar(trU2);

            Transaction trU3 = new Transaction();
            trU3.setRFCCom("ABCDE12345");
            trU3.setRFCUsr("USER987654");
            trU3.setOperationCode(-1);
            trU3.setNoAc(100);
            trU3.setOperationDate(LocalDateTime.now());
            trU3.setOperationPrice(3550.00);
            rt.ofertar(trU3);

            //Código para hacer compras concurrentes

            Transaction trU1 = new Transaction();
            trU1.setRFCCom("12345ABCDE");
            trU1.setRFCUsr("USER123456");
            trU1.setOperationCode(1);
            trU1.setNoAc(200);
            trU1.setOperationDate(LocalDateTime.now());
            trU1.setOperationPrice(2500.00);
            rt.ofertar(trU1);

            Transaction trU2 = new Transaction();
            trU2.setRFCCom("12345ABCDE");
            trU2.setRFCUsr("USER678901");
            trU2.setOperationCode(1);
            trU2.setNoAc(350);
            trU2.setOperationDate(LocalDateTime.now());
            trU2.setOperationPrice(2000.00);
            rt.ofertar(trU2);

            Transaction trU3 = new Transaction();
            trU3.setRFCCom("12345ABCDE");
            trU3.setRFCUsr("USER987654");
            trU3.setOperationCode(1);
            trU3.setNoAc(100);
            trU3.setOperationDate(LocalDateTime.now());
            trU3.setOperationPrice(2250.00);
            rt.ofertar(trU3);*/

            Transaction trU1 = new Transaction();
            trU1.setRFCCom("ABCDE12345");
            trU1.setRFCUsr("USER123456");
            trU1.setOperationCode(-1);
            trU1.setNoAc(30);
            trU1.setOperationDate(LocalDateTime.now());
            trU1.setOperationPrice(2450.00);
            rt.ofertar(trU1);

            Transaction trU2 = new Transaction();
            trU2.setRFCCom("ABCDE12345");
            trU2.setRFCUsr("USER678901");
            trU2.setOperationCode(-1);
            trU2.setNoAc(50);
            trU2.setOperationDate(LocalDateTime.now());
            trU2.setOperationPrice(2499.99);
            rt.ofertar(trU2);

            Transaction trU3 = new Transaction();
            trU3.setRFCCom("ABCDE12345");
            trU3.setRFCUsr("USER987654");
            trU3.setOperationCode(1);
            trU3.setNoAc(90);
            trU3.setOperationDate(LocalDateTime.now());
            trU3.setOperationPrice(3000.00);
            rt.ofertar(trU3);

            Transaction trU4 = new Transaction();
            trU4.setRFCCom("ABCDE12345");
            trU4.setRFCUsr("USER444444");
            trU4.setOperationCode(1);
            trU4.setNoAc(200);
            trU4.setOperationDate(LocalDateTime.now());
            trU4.setOperationPrice(3500.00);
            rt.ofertar(trU4);


        } catch (RemoteException re) {
            re.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}