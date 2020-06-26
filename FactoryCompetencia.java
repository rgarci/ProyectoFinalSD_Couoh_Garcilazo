import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class FactoryCompetencia {

    private static HashMap<String, Competencia> competencias;
    private static FactoryCompetencia factoryCompetencia;

    private FactoryCompetencia() {
        competencias = new HashMap<String, Competencia>();
    }

    public static FactoryCompetencia getInstance() {
        if (factoryCompetencia == null) {
            factoryCompetencia = new FactoryCompetencia();
        }
        return factoryCompetencia;
    }

    public Competencia getCompetencia(String RFCComp){
        Competencia compe = competencias.get(RFCComp);
        if(compe == null){
            System.out.println("nueva");
            compe = new Competencia(RFCComp);
            competencias.put(RFCComp, compe);
            compe.start();
        }else{
            System.out.println("existe");
        }
        return compe; 
    }

    public void eliminarCompetencia(String RFCComp) {
        System.out.println("eliminar");
        competencias.remove(RFCComp);
    }

}