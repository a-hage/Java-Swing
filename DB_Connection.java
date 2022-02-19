package lager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DB_Connection {
	

	public Connection getConnection() {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
			}
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lager", "root", "");
			return con;
		} catch (SQLException ex) {
			System.out.println("Database.getConnection() Error -->" + ex.getMessage());
			return null;
		}
	}

	/*public  void closeAll(PreparedStatement pstmt, ResultSet rs) {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
	}
*/
	public PreparedStatement getPreparedStatement(String sqlQuery) throws ClassNotFoundException, SQLException{
		        
		        PreparedStatement ps = null;
		        try{
		        ps = (PreparedStatement) getConnection().prepareStatement(sqlQuery);
		        ps.close();
		        }catch(SQLException ex){
		        	ex.getMessage();
		        }
		        
		        return ps;
	
		    }
}