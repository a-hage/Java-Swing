package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/lager";
			String user = "admin";
			String password = "admin";
			//con = (Connection) DriverManager.getConnection(url, user, password);
			con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost/lager","root","");
			// System.out.println("Verbindung Erfolgreich");
		} catch (Exception ex) {
			System.err.print(ex);
		}
		return con;
	}

	public PreparedStatement getPreparedStatement(String sqlQuery) throws ClassNotFoundException, SQLException {

		PreparedStatement ps = null;
		ps = getConnection().prepareStatement(sqlQuery);
		return ps;
	}

}
