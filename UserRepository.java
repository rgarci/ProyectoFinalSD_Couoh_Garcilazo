import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.UserDOException;


public class UserRepository {
    public static boolean save(User usr){
        try{
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO User (RFCUsr, RFCCom, noAc, precioCompra) values(?,?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, usr.getRFCUsr());
            pstmt.setString(2, usr.getRFCCom());
            pstmt.setInt(3, usr.getNoAc());
            pstmt.setDouble(4, usr.getPrecioCompra());

            boolean rs = pstmt.execute();

            pstmt.close();
            return rs;

        }catch(SQLException se){
            throw new UserDOException("Hubo un error al crear el usuario\n" + se.getMessage());
        }
    }

    public static boolean update(User usr){
        try{
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE User SET noAc=?, precioCompra=? WHERE RFCUsr=? AND RFCCom=?";
            
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            pstmt.setInt(1, usr.getNoAc());
            pstmt.setDouble(2, usr.getPrecioCompra());
            pstmt.setString(3, usr.getRFCUsr());
            pstmt.setString(4, usr.getRFCCom());

            int rs = pstmt.executeUpdate();

            pstmt.close();
            return rs>0?true:false;

        }catch(SQLException se){
            throw new UserDOException("Hubo un error al actualizar el usuario\n" + se.getMessage());
        }
    }

    public static void delete(User usr){
        try{
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM User WHERE RFCUsr=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, usr.getRFCCom());

            pstmt.executeUpdate();
        
            pstmt.close();
        }catch(SQLException se){
            throw new UserDOException("Hubo un error al eliminar el usuario\n" + se.getMessage());
        }
    }

    public static void deleteAll() {
        Connection con = DBManager.getInstance().getConnection();
        try {
          String SQL = "DELETE FROM User";
          PreparedStatement pstmt = con.prepareStatement(SQL);
      
          pstmt.executeUpdate();
          pstmt.close();
        } catch (SQLException se) {
            throw new UserDOException("Hubo un error al eliminar los usuarios\n" + se.getMessage());
        }
    }

    public static ArrayList<User> getResgistrosByUser(String RFCUsr){
        ArrayList<User> arr = null;
  
        try {
            arr = new ArrayList<User>();
            String QRY = "SELECT * FROM User WHERE RFCUsr=?";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1, RFCUsr);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User usr = new User();
                usr.setRFCUsr(RFCUsr);
                usr.setRFCCom(rs.getString("RFCCom"));
                usr.setNoAc(rs.getInt("noAc"));
                usr.setPrecioCompra(rs.getDouble("precioCompra"));
                arr.add(usr);
            }

            pstmt.close();
    
        } catch (SQLException se) {
            throw new UserDOException("Hubo un error al obtener los registros\n" + se.getMessage());
        }
        return arr;
    }

    public static User getByCompany(String RFCCom, String RFCUsr){
        User usr = null;
        try {
            usr = new User();
            usr.setRFCUsr(RFCUsr);
            usr.setRFCCom(RFCCom);

            String QRY = "SELECT * FROM User WHERE RFCCom=? AND RFCUsr=?";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1, RFCCom);
            pstmt.setString(2, RFCUsr);

            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                usr.setNoAc(rs.getInt("noAc"));
                usr.setPrecioCompra(rs.getDouble("precioCompra"));

                pstmt.close();
                return usr;
            }else{
                return null;
            }

        } catch (SQLException se) {
            throw new UserDOException("Hubo un error al obtener el usuario\n" + se.getMessage());
        }
    }

}