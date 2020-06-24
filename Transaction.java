import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String RFCUsr;
    private String RFCCom;
    private LocalDateTime operationDate;
    private int operationCode;
    private double operationPrice;
    private int noAc;


    public Transaction() {
    }



    public Transaction(String RFCUsr, String RFCCom, LocalDateTime operationDate, int operationCode, double operationPrice, int noAc) {
        this.RFCUsr = RFCUsr;
        this.RFCCom = RFCCom;
        this.operationDate = operationDate;
        this.operationCode = operationCode;
        this.operationPrice = operationPrice;
        this.noAc = noAc;
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

    public LocalDateTime getOperationDate() {
        return this.operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public int getOperationCode() {
        return this.operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public double getOperationPrice() {
        return this.operationPrice;
    }

    public void setOperationPrice(double operationPrice) {
        this.operationPrice = operationPrice;
    }

    public int getNoAc() {
        return this.noAc;
    }

    public void setNoAc(int noAc) {
        this.noAc = noAc;
    }

  
    @Override
    public String toString() {
        return "{" +
            " RFCUsr='" + getRFCUsr() + "'" +
            ", RFCCom='" + getRFCCom() + "'" +
            ", operationDate='" + getOperationDate() + "'" +
            ", operationCode='" + getOperationCode() + "'" +
            ", operationPrice='" + getOperationPrice() + "'" +
            ", noAc='" + getNoAc() + "'" +
            "}";
    }
    

}