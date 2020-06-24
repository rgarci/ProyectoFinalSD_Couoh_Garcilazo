import java.io.*;
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String RFCUsr;
    private String RFCCom;
    private int noAc;
    private double precioCompra;


    public User(){
    }


    public User(String RFCUsr, String RFCCom, int noAc, double precioCompra) {
        this.RFCUsr = RFCUsr;
        this.RFCCom = RFCCom;
        this.noAc = noAc;
        this.precioCompra = precioCompra;
    }

    public String getRFCUsr() {
        return this.RFCUsr;
    }

    public void setRFCUsr(String RFCUsr) {
        this.RFCUsr = RFCUsr;
    }

    public String getRFCCom() {
        return this.RFCCom;
    }

    public void setRFCCom(String RFCCom) {
        this.RFCCom = RFCCom;
    }

    public int getNoAc() {
        return this.noAc;
    }

    public void setNoAc(int noAc) {
        this.noAc = noAc;
    }

    public double getPrecioCompra() {
        return this.precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    @Override
    public String toString() {
        return "{" +
            " RFCUsr='" + getRFCUsr() + "'" +
            ", RFCCom='" + getRFCCom() + "'" +
            ", noAc='" + getNoAc() + "'" +
            ", precioCompra='" + getPrecioCompra() + "'" +
            "}";
    }
    
}