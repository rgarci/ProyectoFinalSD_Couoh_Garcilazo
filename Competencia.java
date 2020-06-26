import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Competencia extends Thread {
    private  ArrayList<Transaction> ofertas;
    private  HashMap<Transaction, TransactionObject> offers;
    private FactoryCompetencia factory;
    private String RFCComp;

    public Competencia(String RFCComp) {
        this.RFCComp = RFCComp;
        factory = FactoryCompetencia.getInstance();
        ofertas = new ArrayList<Transaction>();
        offers = new HashMap<Transaction, TransactionObject>();
    }

    public void hacerOferta(Transaction tr, TransactionObject tro) {
        ofertas.add(tr);
        offers.put(tr, tro);
        System.out.println("recibir oferta de: " + tr.getRFCUsr());
    }

    private void competir() {
        System.out.println("Nueva competencia");
        ArrayList<Transaction> ventas = getVentas();
        if(!ventas.isEmpty()){
            competenciaVentas(ventas);
        }

        ArrayList<Transaction> compras = getCompras();
        if(!compras.isEmpty()){
            competenciaCompras(compras);
        }

    }

    private void competenciaVentas(ArrayList<Transaction> ventas) {
        Transaction ganadorVenta = ventas.get(0);

        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getOperationPrice() < ganadorVenta.getOperationPrice()) {
                ganadorVenta = ventas.get(i);
            }
        }

        TransactionRepository.save(ganadorVenta);
        TransactionObject tro = offers.get(ganadorVenta);
        tro.setWinnerFlag(true);
    }

    private void competenciaCompras(ArrayList<Transaction> compras) {
        Transaction ganadorCompra = compras.get(0);

        for (int i = 0; i < compras.size(); i++) {
            if (compras.get(i).getOperationPrice() > ganadorCompra.getOperationPrice()) {
                ganadorCompra = compras.get(i);
            }
        }
        TransactionRepository.save(ganadorCompra);
        TransactionObject tro = offers.get(ganadorCompra);
        tro.setWinnerFlag(true);
    }

    private ArrayList<Transaction> getVentas() {
        ArrayList<Transaction> ventas = new ArrayList();
        for (int i = 0; i < ofertas.size(); i++) {
            String rfcComOferta = ofertas.get(i).getRFCCom();
            Company comp = CompanyRespository.findByRFC(rfcComOferta);
            if (ofertas.get(i).getOperationCode() == -1 && ofertas.get(i).getOperationPrice() < comp.getValActAc()) {
                ventas.add(ofertas.get(i));
            }
        }
        return ventas;
    }

    private ArrayList<Transaction> getCompras() {
        ArrayList<Transaction> ventas = new ArrayList();

        for (int i = 0; i < ofertas.size(); i++) {
            String rfcComOferta = ofertas.get(i).getRFCCom();
            Company comp = CompanyRespository.findByRFC(rfcComOferta);
            if (ofertas.get(i).getOperationCode() == 1 && ofertas.get(i).getOperationPrice() > comp.getValActAc()) {
                ventas.add(ofertas.get(i));
            }
        }
        return ventas;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(120000);
            competir();
            factory.eliminarCompetencia(RFCComp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}