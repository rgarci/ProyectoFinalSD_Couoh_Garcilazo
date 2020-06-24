import java.io.Serializable;

public class Company implements Serializable{
    private static final long serialVersionUID = 1L;
    private String RFCCom;
    private int acTot;
    private int acDisp;
    private double valActAc;


    public Company() {
    }
   

    public Company(String RFCCom, int acTot, int acDisp, double valActAc) {
        this.RFCCom = RFCCom;
        this.acTot = acTot;
        this.acDisp = acDisp;
        this.valActAc = valActAc;
    }

    public String getRFCCom() {
        return this.RFCCom;
    }

    public void setRFCCom(String RFCCom) {
        this.RFCCom = RFCCom;
    }

    public int getAcTot() {
        return this.acTot;
    }

    public void setAcTot(int acTot) {
        this.acTot = acTot;
    }

    public int getAcDisp() {
        return this.acDisp;
    }

    public void setAcDisp(int acDisp) {
        this.acDisp = acDisp;
    }

    public double getValActAc() {
        return this.valActAc;
    }

    public void setValActAc(double valActAc) {
        this.valActAc = valActAc;
    }

    @Override
    public String toString() {
        return "{" +
            " RFCCom='" + getRFCCom() + "'" +
            ", acTot='" + getAcTot() + "'" +
            ", acDisp='" + getAcDisp() + "'" +
            ", valActAc='" + getValActAc() + "'" +
            "}";
    }

}