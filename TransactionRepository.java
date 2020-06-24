import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import Exceptions.TransactionDOException;

public class TransactionRepository {
    public static boolean save(Transaction tr){
        try {
            
            if(tr.getOperationCode() == 1){
                Company comp = CompanyRespository.findByRFC(tr.getRFCCom());
                comp.setAcDisp(comp.getAcDisp() - tr.getNoAc());
                comp.setValActAc(tr.getOperationPrice());
                CompanyRespository.update(comp);

                User usr = UserRepository.getByCompany(tr.getRFCCom(), tr.getRFCUsr());
                if(usr==null){
                    usr = new User();
                    usr.setRFCUsr(tr.getRFCUsr());
                    usr.setRFCCom(tr.getRFCCom());
                    usr.setNoAc(tr.getNoAc());
                    usr.setPrecioCompra(tr.getOperationPrice());
                    UserRepository.save(usr);
                }else{
                    usr.setNoAc(usr.getNoAc() + tr.getNoAc());
                    usr.setPrecioCompra(tr.getOperationPrice());
                    UserRepository.update(usr);
                }
            }
            if (tr.getOperationCode() == -1) {
                Company comp = CompanyRespository.findByRFC(tr.getRFCCom());
                comp.setAcDisp(comp.getAcDisp() + tr.getNoAc());
                comp.setValActAc(tr.getOperationPrice());
                CompanyRespository.update(comp);

                User usr = UserRepository.getByCompany(tr.getRFCCom(), tr.getRFCUsr());
                usr.setNoAc(usr.getNoAc() - tr.getNoAc());
                UserRepository.update(usr);
            }
            
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO Transaction (RFCUsr, RFCCom, operationDate, operationCode, operationPrice, noAc) values(?,?,?,?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, tr.getRFCUsr());
            pstmt.setString(2, tr.getRFCCom());
            pstmt.setString(3, String.valueOf(tr.getOperationDate()));
            pstmt.setInt(4, tr.getOperationCode());
            pstmt.setDouble(5, tr.getOperationPrice());
            pstmt.setInt(6, tr.getNoAc());
            
            boolean rs = pstmt.execute();
        
            pstmt.close();

            return rs;
        } catch (SQLException se) {
            //throw new TransactionDOException("Hubo un error al crear la transacción\n" + se.getMessage());
            se.printStackTrace();
        }
        return false;
    }

    public static boolean update(Transaction tr){
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE Transaction SET operationCode=?, operationPrice=?, noAc=? WHERE RFCUsr=? AND RFCCom=? AND operationDate=?";
            
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, tr.getOperationCode());
            pstmt.setDouble(2, tr.getOperationPrice());
            pstmt.setInt(3, tr.getNoAc());
            pstmt.setString(4, tr.getRFCUsr());
            pstmt.setString(5, tr.getRFCCom());
            pstmt.setString(6, String.valueOf(tr.getOperationDate()));
            
            boolean rs = pstmt.execute();
        
            pstmt.close();

            return rs;
        } catch (SQLException se) {
            throw new TransactionDOException("Hubo un error al actualizar la transacción\n" + se.getMessage());
        }
    }

    public static void delete(Transaction tr) {
    
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM Transaction WHERE RFCUsr=? AND RFCCom=? AND operationDate=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, tr.getRFCUsr());
            pstmt.setString(2, tr.getRFCCom());
            pstmt.setString(3, String.valueOf(tr.getOperationDate()));
        
            pstmt.executeUpdate();
        
            pstmt.close();

        } catch (SQLException se) {
            throw new TransactionDOException("Hubo un error al eliminar la transacción\n" + se.getMessage());
        }
    }

    public static ArrayList<Transaction> findAll(){
        ArrayList<Transaction> arr = null;
  
        try {
            arr = new ArrayList<Transaction>();
            String QRY = "SELECT * FROM Transaction";
            Connection con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QRY);

            while (rs.next()) {
                
                
                Transaction tr = new Transaction();
                tr.setRFCUsr(rs.getString("RFCUsr"));
                tr.setRFCCom(rs.getString("RFCCom"));
                String od = rs.getString("operationDate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                tr.setOperationDate(LocalDateTime.parse(od.substring(0, od.length()-2), formatter));
                tr.setOperationCode(rs.getInt("operationCode"));
                tr.setOperationPrice(rs.getDouble("operationPrice"));
                tr.setNoAc(rs.getInt("noAc"));

                arr.add(tr);
            }

            stmt.close();
    
        } catch (SQLException se) {
            throw new TransactionDOException("Hubo un error al obtener las transacciones\n" + se.getMessage());
        }
        return arr;
    }

    public static ArrayList<Transaction> findAllfromLastHour(){
        ArrayList<Transaction> arr = null;
  
        try {
            arr = new ArrayList<Transaction>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt = LocalDateTime.now();
            LocalDateTime prev = ldt.minus(1, ChronoUnit.HOURS);
            String formattedDateTime = "'" + prev.format(formatter) + "'";
 
            String QRY = "SELECT * FROM Transaction WHERE operationDate >=" + formattedDateTime;
            Connection con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QRY);

            while (rs.next()) {
                Transaction tr = new Transaction();
                tr.setRFCUsr(rs.getString("RFCUsr"));
                tr.setRFCCom(rs.getString("RFCCom"));
                String od = rs.getString("operationDate");
                tr.setOperationDate(LocalDateTime.parse(od.substring(0, od.length()-2), formatter));
                tr.setOperationCode(rs.getInt("operationCode"));
                tr.setOperationPrice(rs.getDouble("operationPrice"));
                tr.setNoAc(rs.getInt("noAc"));
                arr.add(tr);
            }
    
            stmt.close();
        } catch (SQLException se) {
            throw new TransactionDOException("Hubo un error al obtener las transacciones\n" + se.getMessage());
        }
        return arr;
    }

    public static ArrayList<Transaction> getAllFromUser(String RFCUsr){
        ArrayList<Transaction> trans = null;
        try {
            trans = new ArrayList<Transaction>();
            String QRY = "SELECT * FROM Transaction WHERE RFCUsr=?";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1, RFCUsr);

            ResultSet rs = pstmt.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while(rs.next()){
                Transaction tr = new Transaction();
                tr.setRFCUsr(RFCUsr);
                tr.setRFCCom(rs.getString("RFCCom"));
                tr.setNoAc(rs.getInt("noAc"));
                tr.setOperationCode(rs.getInt("operationCode"));
                String od = rs.getString("operationDate");
                tr.setOperationDate(LocalDateTime.parse(od.substring(0, od.length()-2), formatter));
                tr.setOperationPrice(rs.getDouble("operationPrice"));

                trans.add(tr);
            }
        } catch (SQLException se) {
            throw new TransactionDOException("Hubo un error al obtener las transacciones\n" + se.getMessage());
        }
        return trans;
    }

}