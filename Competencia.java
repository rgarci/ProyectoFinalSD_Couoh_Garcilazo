import java.util.ArrayList;

public class Competencia extends Thread {
    private static ArrayList<Transaction> ofertas;
    private static Competencia comp;

    private Competencia(){
        ofertas = new ArrayList<Transaction>();
    }

    public static Competencia getInstance(){
        if(comp == null){
            comp = new Competencia();
        }
        return comp;
    }

    public static void hacerOferta(Transaction tr){
        ofertas.add(tr);
    }

    private void competir() {
        System.out.println("Nueva competencia");
        ArrayList<Transaction> ventas = getVentas();

        while(!ventas.isEmpty()){
            ventas = competenciaVentas(ventas);
        }

        ArrayList<Transaction> compras = getCompras();
        while(!compras.isEmpty()){
            for (int i = 0; i < compras.size(); i++) {
                System.out.println(compras.get(i));
            }
            System.out.println("compras");
            compras = competenciaCompras(compras);
        }

        ofertas = new ArrayList<Transaction>();
    }

    private ArrayList<Transaction> competenciaVentas(ArrayList<Transaction> ventas){
        Transaction ganadorVenta = ventas.get(0);

        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getOperationPrice() < ganadorVenta.getOperationPrice() && ventas.get(i).getRFCCom().equals(ganadorVenta.getRFCCom())) {
                ganadorVenta = ventas.get(i);
            }
        }
        TransactionRepository.save(ganadorVenta);
        String rfcToDelete = ganadorVenta.getRFCCom();
        return limpiarLista(ventas, rfcToDelete);
    }

    private ArrayList<Transaction> competenciaCompras(ArrayList<Transaction> compras){
        Transaction ganadorCompra = compras.get(0);
        int tam = compras.size();

        for (int i = 0; i < tam; i++) {
            if (compras.get(i).getOperationPrice() > ganadorCompra.getOperationPrice() && compras.get(i).getRFCCom().equals(ganadorCompra.getRFCCom())) {
                ganadorCompra = compras.get(i);
            }
        }
        System.out.println("ganador: " + ganadorCompra.getRFCUsr() + "com: " + ganadorCompra.getRFCCom());
        TransactionRepository.save(ganadorCompra);
        String rfcToDelete = ganadorCompra.getRFCCom();
        return limpiarLista(compras, rfcToDelete);
    }

    private ArrayList<Transaction> limpiarLista(ArrayList<Transaction> lista, String rfccom){
        ArrayList<Transaction> temp = new ArrayList<Transaction>();

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getRFCCom().equals(rfccom)) {
                Transaction temptr = lista.get(i);
                temp.add(temptr);
            }
        }
        return temp;
    }

    private ArrayList<Transaction> getVentas(){
        ArrayList<Transaction> ventas = new ArrayList();
        for (int i = 0; i < ofertas.size(); i++) {
            String rfcComOferta = ofertas.get(i).getRFCCom();
            Company comp = CompanyRespository.findByRFC(rfcComOferta);
            if(ofertas.get(i).getOperationCode() == -1 && ofertas.get(i).getOperationPrice() < comp.getValActAc()){
                ventas.add(ofertas.get(i));
            }
        }
        return ventas;
    }

    private ArrayList<Transaction> getCompras(){
        ArrayList<Transaction> ventas = new ArrayList();
        
        for (int i = 0; i < ofertas.size(); i++) {
            String rfcComOferta = ofertas.get(i).getRFCCom();
            Company comp = CompanyRespository.findByRFC(rfcComOferta);
            if(ofertas.get(i).getOperationCode() == 1 && ofertas.get(i).getOperationPrice() > comp.getValActAc()){
                ventas.add(ofertas.get(i));
            }
        }
        return ventas;
    }

    @Override
    public void run() {
        try {
            while(true){
                Thread.sleep(120000);
                competir();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}