import java.sql.*;
 
/**
 * DBManager: Singleton pattern
 *
 *
 **/
public final class DBManager {
 
  private static DBManager _instance = null;
  private Connection _con = null;
 
  private DBManager() {
  
 	_con = getMySQLConnection();
		
  }
 
  //Thread safe instatiate method
  public static synchronized DBManager getInstance() {
    if (_instance == null) {
      _instance = new DBManager();
    }
    return _instance;
  }
 
  public Connection getConnection() {
    return _con;
  }
 
  /**
   * Connection to MySQL Database
   */
  private static Connection getMySQLConnection() {
    Connection con = null;
 
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      String strCon = "jdbc:mysql://127.0.0.1:3306/sd_bolsa?user=root";
      con = DriverManager.getConnection(strCon);
    } catch (SQLException se) {
      se.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return con;
  }
  
}