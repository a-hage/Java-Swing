package lager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD {
     DB_Connection db = new DB_Connection();
    
	
    /*
	public void InsertProdukt(Lager l) throws ClassNotFoundException, SQLException {
		Connection con = null;
		 ps = null;
		
		try {
			ps = db.getPreparedStatement("INSERT INTO lager values(?, ?, ?, ?, ?)");
			ps.setString(1, l.getBarCode());
			ps.setString(2, l.getNe_Bezeichnung());
			ps.setDouble(3, l.getGewicht());
			ps.setString(4, l.getZustand());
			ps.setString(5, l.getLagerOrt());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} 

	}

	public void UpdateProdukt(Lager l) throws ClassNotFoundException, SQLException {
		Connection con = null;
		ps = null;
		
		try {
			ps = db.getPreparedStatement(
					"UPDATE lager SET (barCode=?, ne_Bezeichnung=?, gewicht=?, zustand=?, lagerOrt=?) WHERE barCode=? ");
			ps.setString(1, l.getBarCode());
			ps.setString(2, l.getNe_Bezeichnung());
			ps.setDouble(3, l.getGewicht());
			ps.setString(4, l.getZustand());
			ps.setString(5, l.getLagerOrt());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void DeleteProdukt(String ne_Bezeichnung) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		    ps = db.getPreparedStatement("DELETE FROM lager WHERE ne_Bezeichnung = ? ");
			ps.setString(2, ne_Bezeichnung);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
*/
	public ArrayList<Lager> getWarenList() throws ClassNotFoundException, SQLException {

		ArrayList<Lager> warenList = new ArrayList<Lager>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			ps= db.getPreparedStatement("Select * From lager");
			rs=ps.executeQuery();
			Lager lg;
			while (rs.next()) {
				lg = new Lager(rs.getString("barCode"), rs.getString("ne_Bezeichnung"), rs.getDouble("gewicht"),
						rs.getString("zustand"), rs.getString("lagerOrt"));
				warenList.add(lg);
			}
			ps.close();
			
		} catch (SQLException ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return warenList;
	}
	
	
	

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		new CRUD().getWarenList();
	}
}
