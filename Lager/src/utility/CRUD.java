package utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import model.Warenausgang;
import model.Wareneingang;

public class CRUD {
	DBConnection db = new DBConnection();

	/*
	 * 
	 */
	public ArrayList<Wareneingang> getWareneingang() throws ClassNotFoundException {
		ArrayList<Wareneingang> WarenEingangsListe = new ArrayList<Wareneingang>();
		Wareneingang we = null;
		try {
			ResultSet rs = db
					.getPreparedStatement(
							"Select barCode, ne_Bezeichnung, Lagerort, Eingangsdatum From wareneingang ORDER BY barCode")
					.executeQuery();
			while (rs.next()) {
				we = new Wareneingang(
						rs.getString("barCode"), 
						rs.getString("ne_Bezeichnung"),
						rs.getString("LagerOrt"), 
						rs.getString("Eingangsdatum")
						);
				WarenEingangsListe.add(we);
				// rs.getRow();
			}
			rs.close();
			db.getConnection().close();
		} catch (Exception ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return WarenEingangsListe;
	}
  
	/*
	 * 
	 * 
	 */
	public Wareneingang dbSearch(String barCode) throws ClassNotFoundException {
		Wareneingang we = null;
		try {
			String selectQuery = "Select barCode, ne_Bezeichnung, LagerOrt, Eingangsdatum From wareneingang WHERE barCode= '"
					+ barCode + "'";
			ResultSet rs = db.getPreparedStatement(selectQuery).executeQuery();
			while (rs.next()) {
				we = new Wareneingang();
				we.setBarCode(rs.getString("barCode"));
				we.setNe_Bezeichnung(rs.getString("ne_Bezeichnung"));
				we.setLagerOrt(rs.getString("LagerOrt"));
				we.setEingangsdatum(rs.getString("Eingangsdatum"));
			}
			rs.close();
			db.getConnection().close();
		} catch (Exception ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return we;
	}
	
	/*
	 * 
	 * 
	 */
	public Wareneingang searchNeBezeichung(String bezeichnung) throws ClassNotFoundException {
		Wareneingang we = null;
		try {
			String selectQuery = "Select ne_Bezeichnung, LagerOrt, Eingangsdatum, Count(ne_Bezeichnung)AS \"Menge\" From wareneingang WHERE ne_Bezeichnung= '"
					+ bezeichnung + "'"+" GROUP BY ne_Bezeichnung ORDER BY ne_Bezeichnung";
			ResultSet rs = db.getPreparedStatement(selectQuery).executeQuery();
			while (rs.next()) {
				we = new Wareneingang();
				we.setNe_Bezeichnung(rs.getString("ne_Bezeichnung"));
				we.setLagerOrt(rs.getString("LagerOrt"));
				we.setMenge(rs.getInt("Menge"));
			}
			rs.close();
			db.getConnection().close();
		} catch (Exception ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return we;
	}
  
	/*
	 * 
	 * 
	 */
	public boolean suchVerfuegbarkeitWareneingang(String searched) throws ClassNotFoundException, SQLException {
		ResultSet rs1, rs2= null;
		boolean search;
		String selectBarCodeQuery = "SELECT barCode FROM wareneingang " + "WHERE (barCode ='" + searched + "')";
		String selectNeBezeichnungQuery= "SELECT ne_Bezeichnung FROM wareneingang " + "WHERE (ne_Bezeichnung ='" + searched + "')";
		rs1 = db.getPreparedStatement(selectBarCodeQuery).executeQuery();
		rs2 = db.getPreparedStatement(selectNeBezeichnungQuery).executeQuery();
		if (rs1.next() || rs2.next()) {
			search = true;
		} else {
			search = false;
		}
		rs1.close();
		rs2.close();
		return search;
	}
  
	/*
	 * 
	 * 
	 */
	public String dateFormat() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter df;
		df = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		return date.format(df);
	}
    
	/*
	 * 
	 * 
	 */
	public boolean suchVerfuegbarkeitWarenausgang(String barCode) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		boolean search;
		String selectQuery = "SELECT barCode FROM warenausgang " + "WHERE (barCode ='" + barCode + "')";
		rs = db.getPreparedStatement(selectQuery).executeQuery();
		if (rs.next()) {
			// System.out.println(rs.next());
			search = true;
		} else {
			search = false;
		}
		return search;
	}
    
	/*
	 * 
	 * 
	 */
	public ArrayList<Warenausgang> getWarenausgang() throws ClassNotFoundException, SQLException {
		ArrayList<Warenausgang> WarenAusgangsListe = new ArrayList<Warenausgang>();
		Warenausgang wa = null;
		try {
			ResultSet rs = db
					.getPreparedStatement(
							"SELECT barCode, ne_Bezeichnung, LagerOrt, Eingangsdatum, Ausgangsdatum FROM warenausgang ORDER BY barCode")
					.executeQuery();
			while (rs.next()) {
				wa = new Warenausgang(
						rs.getString("barCode"), 
						rs.getString("ne_Bezeichnung"),
						rs.getString("LagerOrt"), 
						rs.getString("Eingangsdatum"), 
						rs.getString("Ausgangsdatum")
						);
				WarenAusgangsListe.add(wa);
			}
			rs.close();
			db.getConnection().close();
		} catch (Exception ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return WarenAusgangsListe;
	}
	
/*
 * 
 * 
 */
	public void deleteWaren(String barCode) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = (Statement) db.getConnection().createStatement();
			String deleteQuery = "DELETE FROM wareneingang WHERE barCode ='" + barCode + "';";
			stmt.executeUpdate(deleteQuery);
			stmt.close();
			db.getConnection().close();
		} catch (Exception ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/*
	 * 
	 * 
	 */
	public void aktualisireWarenEingang(JTable table) throws SQLException, ClassNotFoundException {
		int counter;
		Statement st = (Statement) db.getConnection().createStatement();
		Wareneingang[] result;
		String query = "SELECT COUNT(barCode) FROM wareneingang";
		ResultSet rs = st.executeQuery(query);
		rs.next();
		String count = rs.getString("COUNT(barCode)");
		int k = Integer.parseInt(count);
		Wareneingang[] rows = new Wareneingang[k];
		counter = k;
		String query1 = "SELECT * FROM Wareneingang";
		ResultSet rs1 = st.executeQuery(query1);
		int i = 0;
		while (rs1.next()) {
			rows[i] = new Wareneingang(rs1.getString("barCode"), rs1.getString("ne_Bezeichnung"),
					rs1.getString("LagerOrt"), rs1.getString("Eingangsdatum"));
			i++;
		}
		result = rows;
		Object[][] daten = new Object[counter][4];
		for (int j = 0; j < counter; j++) {
			daten[j][0] = result[j].getBarCode();
			daten[j][1] = result[j].getNe_Bezeichnung();
			daten[j][2] = result[j].getLagerOrt();
			daten[j][3] = result[j].getEingangsdatum();
		}
		table.setModel(new DefaultTableModel(daten,
				new String[] { "BarCode", "NE Bezeichnung", "LagerOrt", "Eingangsdatum" }));
		table.repaint(); 
	}
	
	/*
	 * 
	 * 
	 */

	public void aktualisireWarenAusgang(JTable table) throws SQLException, ClassNotFoundException {
		int counter;
		Statement st = (Statement) db.getConnection().createStatement();
		Warenausgang[] result;
		String query = "SELECT COUNT(barCode) FROM warenausgang";
		ResultSet rs = st.executeQuery(query);
		rs.next();
		String count = rs.getString("COUNT(barCode)");
		int k = Integer.parseInt(count);
		Warenausgang[] rows = new Warenausgang[k];
		counter = k;
		String query1 = "SELECT * FROM Warenausgang";
		ResultSet rs1 = st.executeQuery(query1);
		int i = 0;
		while (rs1.next()) {
			rows[i] = new Warenausgang(rs1.getString("barCode"), rs1.getString("ne_Bezeichnung"),
					rs1.getString("LagerOrt"), rs1.getString("Eingangsdatum"), rs1.getString("Ausgangsdatum"));
			i++;
		}
		result = rows;
		Object[][] daten = new Object[counter][5];
		for (int j = 0; j < counter; j++) {
			daten[j][0] = result[j].getBarCode();
			daten[j][1] = result[j].getNe_Bezeichnung();
			daten[j][2] = result[j].getLagerOrt();
			daten[j][3] = result[j].getEingangsdatum();
			daten[j][4] = result[j].getAusgangsdatum();
		}
		table.setModel(new DefaultTableModel(daten,
				new String[] { "BarCode", "NE Bezeichnung", "LagerOrt", "Eingangsdatum", "Ausgangsdatum"}));
		table.repaint(); 
	}

	
}

