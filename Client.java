import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanStr = new Scanner(System.in);
            Scanner scanInt = new Scanner(System.in);
            Scanner scanDbl = new Scanner(System.in);
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");

            IRemoteLogin rl = (IRemoteLogin) registry.lookup("Login");
            IRemoteTransaction rt = rl.login();
            IRemoteCompany rc = (IRemoteCompany) registry.lookup("Company");
            IRemoteUsuario ru = (IRemoteUsuario) registry.lookup("User");

            System.out.println("BIENVENIDO A LA BOLSA EN LINEA");
            System.out.println("Por favor ingrese su RFC");
            String RFCUsr = scanStr.nextLine();

            System.out.println("A CONTINUACIÓN SE MUESTRAN SUS ACCIONES:");
            ArrayList<User> acsUser = ru.getResgistrosByUser(RFCUsr);
            for (int i = 0; i < acsUser.size(); i++) {
                System.out.println("RFC Compañía: " + acsUser.get(i).getRFCCom() + "   - No de acciones: " + acsUser.get(i).getNoAc() + "   - Precio de compra: " + acsUser.get(i).getPrecioCompra());
            }
            String opt = "#";
            while(true){
                System.out.println("\n\n\n¿Qué operación desea realizar?\nA) Compra\nB)Venta\nC)Ver mis transacciones");
                opt = scanStr.nextLine();
                switch (opt) {
                    case "A":{
                        System.out.println("Estas son las empresas registradas:");
                        ArrayList<Company> companies = rc.getAll();
                        for (int i = 0; i < companies.size(); i++) {
                            System.out.println("RFC Compañía: " + companies.get(i).getRFCCom() + "   - Acciones totales: " +companies.get(i).getAcTot() + "   - Acciones disponibles: " + companies.get(i).getAcDisp() + "   - Precio actual: " + companies.get(i).getValActAc());
                        }
                        System.out.println("\n\nIngrese el RFC de la compañía donde desea comprar acciones");
                        String RFCCom = scanStr.nextLine();
                        System.out.println("\nIngrese el número de acciones que desea comprar");
                        int noAc = scanInt.nextInt();
                        System.out.println("\nIngrese una oferta monetaria, esta debe ser mayor al precio actual de la acción");
                        double operationPrice = scanDbl.nextDouble();

                        Transaction trans = new Transaction();
                        trans.setRFCUsr(RFCUsr);
                        trans.setRFCCom(RFCCom);
                        trans.setNoAc(noAc);
                        trans.setOperationCode(1);
                        trans.setOperationPrice(operationPrice);
                        trans.setOperationDate(LocalDateTime.now());

                        
                        System.out.println(rt.ofertar(trans)?"Su oferta ha sido exitosa":"Lo sentimos, su oferta ha sido rechazada");

                        break;
                    }
                    case "B":{
                        System.out.println("Estas son las empresas donde tiene acciones:");
                        acsUser = ru.getResgistrosByUser(RFCUsr);
                        for (int i = 0; i < acsUser.size(); i++) {
                            String rfccomp = acsUser.get(i).getRFCCom();
                            Company company = rc.findByRFC(rfccomp);
                            System.out.println("RFC Compañía: " + acsUser.get(i).getRFCCom() + "   - No de acciones: " + acsUser.get(i).getNoAc() + "   - Precio actual: " + company.getValActAc());
                        }

                        System.out.println("\n\nIngrese el RFC de la compañía donde desea vender acciones");
                        String RFCCom = scanStr.nextLine();
                        System.out.println("\nIngrese el número de acciones que desea vender");
                        int noAc = scanInt.nextInt();
                        System.out.println("\nIngrese una oferta monetaria, esta debe ser menor al precio actual de la acción");
                        double operationPrice = scanDbl.nextDouble();

                        Transaction trans = new Transaction();
                        trans.setRFCUsr(RFCUsr);
                        trans.setRFCCom(RFCCom);
                        trans.setNoAc(noAc);
                        trans.setOperationCode(-1);
                        trans.setOperationPrice(operationPrice);
                        trans.setOperationDate(LocalDateTime.now());

                        System.out.println(rt.ofertar(trans)?"Su oferta ha sido exitosa":"Lo sentimos, su oferta ha sido rechazada");

                    }
                    break;
                    case "C":{
                        System.out.println("TRANSACCIONES DEL USUARIO " + RFCUsr);
                        ArrayList<Transaction> transactions = rt.getAllFromUser(RFCUsr);
                        for (int i = 0; i < transactions.size(); i++) {
                            Transaction tr = transactions.get(i);
                            String oper = tr.getOperationCode()==1?"Compra":"Venta";
                            System.out.println("RFCCompañía: " + tr.getRFCCom() + "   - Operación: " + oper + "   - No de acciones: " + tr.getNoAc() + "   - Precio: " + tr.getOperationPrice() + "   - Fecha: " + tr.getOperationDate());
                        }
                    }
                    default:
                        break;
                }
            }


    
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}