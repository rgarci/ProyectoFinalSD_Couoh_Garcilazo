import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.CompanyDOException;

import java.sql.Connection;

public class CompanyRespository {
  public static boolean save(Company com) {
    try {
      Connection con = DBManager.getInstance().getConnection();
      String SQL = "INSERT INTO Company (RFCCom, acTot, acDisp, valActAc) values(?,?,?,?)";
      
      PreparedStatement pstmt = con.prepareStatement(SQL);
      pstmt.setString(1, com.getRFCCom());
      pstmt.setInt(2, com.getAcTot());
      pstmt.setInt(3, com.getAcDisp());
      pstmt.setDouble(4, com.getValActAc());
  
      boolean rs = pstmt.execute();
  
      pstmt.close();
      return rs;
    } catch (SQLException se) {
      throw new CompanyDOException("No se pudo crear la compania\n" + se.getMessage());
    }
  }
  
  public static boolean update(Company com) {
    try {
      Connection con = DBManager.getInstance().getConnection();
      String SQL = "UPDATE Company SET acTot=?, acDisp=?, valActAc=? WHERE RFCCom=?";
      PreparedStatement pstmt = con.prepareStatement(SQL);
      pstmt.setInt(1, com.getAcTot());
      pstmt.setInt(2, com.getAcDisp());
      pstmt.setDouble(3, com.getValActAc());
      pstmt.setString(4, com.getRFCCom());

      int rs = pstmt.executeUpdate();
  
      pstmt.close();
      
      return rs>0?true:false;
    } catch (SQLException se) {
        throw new CompanyDOException("No se pudo actualizar la compania\n" + se.getMessage());
    }
  }
  
  public static void delete(Company com) {
    
    try {
      Connection con = DBManager.getInstance().getConnection();
      String SQL = "DELETE FROM Company WHERE RFCCom=?";
      PreparedStatement pstmt = con.prepareStatement(SQL);
      pstmt.setString(1, com.getRFCCom());

      pstmt.executeUpdate();
  
      pstmt.close();
      
    } catch (SQLException se) {
      throw new CompanyDOException("Ocurrió un error al eliminar la compania\n" + se.getMessage());
    }
  }
  
  public static void deleteAll() {
    Connection con = DBManager.getInstance().getConnection();
    try {
      String SQL = "DELETE FROM Company";
      PreparedStatement pstmt = con.prepareStatement(SQL);
  
      pstmt.executeUpdate();
      
    } catch (SQLException se) {
      throw new CompanyDOException("No se pudo eliminar las companias\n" + se.getMessage());
    }
  }
  
  public static ArrayList<Company> findAll() {
    ArrayList<Company> arr = null;
  
    try {
      arr = new ArrayList<Company>();
      String QRY = "SELECT * FROM Company";
      Connection con = DBManager.getInstance().getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(QRY);

      while (rs.next()) {
        Company com = new Company();
        com.setRFCCom(rs.getString("RFCCom"));
        com.setAcTot(rs.getInt("acTot"));
        com.setAcDisp(rs.getInt("acDisp"));
        com.setValActAc(rs.getFloat("valActAc"));
        arr.add(com);
      }
  
    } catch (SQLException se) {
      throw new CompanyDOException("Ocurrió un error al obtener companias\n" + se.getMessage());
    }
    return arr;
  }
  
  public static Company findByRFC(String rfc) {
    Company com = null;
  
    try {
      com = new Company();
      com.setRFCCom(rfc);
      String QRY = "SELECT * FROM Company WHERE RFCCom=?";
      Connection con = DBManager.getInstance().getConnection();
      PreparedStatement pstmt = con.prepareStatement(QRY);
      pstmt.setString(1, rfc);
      ResultSet rs = pstmt.executeQuery();
      rs.next();
      com.setAcTot(rs.getInt("acTot"));
      com.setAcDisp(rs.getInt("acDisp"));
      com.setValActAc(rs.getFloat("valActAc"));
    
    } catch (SQLException se) {
       throw new CompanyDOException("Ocurrió un error al obtener la compania\n" + se.getMessage());
    }
    return com;
  }

}