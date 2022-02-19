package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Warenausgang;
import model.Wareneingang;
import utility.CRUD;
import utility.DBConnection;
import javax.swing.JRadioButton;

public class Views extends JFrame {

	private static final long serialVersionUID = 1L;
	// Login login=new Login();
	CRUD crud = new CRUD();
	DBConnection db = new DBConnection();

	/* Panel Instanzvariablen */
	private JPanel contentPane;
	private JPanel pWareneingang;
	private JPanel pWarenausgang;
	private JPanel pWareneingangTabelle;
	private JPanel pWarenausgangTabelle;

	/* Labels Instanzvariablen */
	private JLabel lbl_BarCode;
	private JLabel lbl_NE_Bezeichnung;
	private JLabel lbl_LagerOrt;
	private JLabel lbl_BarCode_Ausgang;
	private JLabel lbl_Search;
	private JLabel lbl_Neue_LagerOrt;

	/* TextFelder Instanzvariablen */
	private JTextField tf_BarCode;
	private JTextField tf_NEBezeichnung;
	private JTextField tf_Neue_LagerOrt;
	private JTextField tf_BarCode_Ausgang;
	private JTextField tf_Search;

	/* Combox Instanzvariablen */
	private JComboBox<Object> comboBox;

	/* Separator, ScollPane, Table Instanzvariablen */
	private JSeparator separator;
	private JScrollPane scrollPane_AusgangsTable;
	private JScrollPane scrollPane_EingangsTable;
	private JScrollPane scrollPane_SearchBarCode;
	private JScrollPane scrollPane_SearchNEBezeichnung;

	public JTable WareneingangTable;
	private JTable SearchBarCodeTable;
	private JTable WarenausgangTable;

	/* Buttons Instanzvariablen */
	private JButton btn_Search;
	private JButton btn_Speichern;
	private JButton btn_Warenausgang;
	private JButton btn_Bearbeiten;
	private JButton btn_AusPrint;
	private JButton btn_SuchePrint;
	private JButton btnAktualisieren;
	private JButton btnLagerOrtHinzufügen;
	private JButton btnLagerOrtEntfernen;

	public String[] items = { "", "1.A.Oben", "1.A.Mitte", "1.A.Unten", "2.A.Oben", "2.A.Mitte", "2.A.Unten",
			"3.A.Oben", "3.A.Mitte", "3.A.Unten" };
	private JTable SearchNEBezeichnungTable;
	private JRadioButton rdbtnBarCode;
	private JRadioButton rdbtnNeBezeichnung;

	/**
	 * Launch the application.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	/*
	 * public static void main(String[] args) throws ClassNotFoundException,
	 * SQLException { EventQueue.invokeLater(new Runnable() { public void run()
	 * { try { Views view = new Views(); view.setVisible(true); //
	 * frame.setTitle("Lager zum NE"); view.ShowWarenEingangsList();
	 * view.ShowWarenAusgangsList(); } catch (Exception e) {
	 * e.printStackTrace(); } } });
	 * 
	 * }
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */

	public Views() throws SQLException, ClassNotFoundException {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Lager Netzelement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pWareneingang = new JPanel();
		pWareneingang.setBounds(10, 11, 680, 246);
		pWareneingang.setToolTipText("Wareneingang");
		pWareneingang
				.setBorder(new TitledBorder(null, "Wareneingang", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pWareneingang);
		pWareneingang.setLayout(null);

		lbl_BarCode = new JLabel("BarCode");
		lbl_BarCode.setBounds(20, 28, 110, 30);
		lbl_BarCode.setFont(new Font("Arial", Font.BOLD, 12));
		pWareneingang.add(lbl_BarCode);

		lbl_NE_Bezeichnung = new JLabel("NE Bezeichnung");
		lbl_NE_Bezeichnung.setBounds(20, 73, 110, 30);
		lbl_NE_Bezeichnung.setFont(new Font("Arial", Font.BOLD, 12));
		pWareneingang.add(lbl_NE_Bezeichnung);

		lbl_LagerOrt = new JLabel("LagerOrt");
		lbl_LagerOrt.setBounds(20, 114, 110, 30);
		lbl_LagerOrt.setFont(new Font("Arial", Font.BOLD, 12));
		pWareneingang.add(lbl_LagerOrt);

		tf_BarCode = new JTextField();
		tf_BarCode.setBounds(150, 28, 210, 30);
		tf_BarCode.setFont(new Font("Arial", Font.PLAIN, 12));
		pWareneingang.add(tf_BarCode);
		tf_BarCode.setColumns(10);

		tf_NEBezeichnung = new JTextField();
		tf_NEBezeichnung.setBounds(150, 73, 210, 30);
		tf_NEBezeichnung.setFont(new Font("Arial", Font.PLAIN, 12));
		pWareneingang.add(tf_NEBezeichnung);
		tf_NEBezeichnung.setColumns(10);

		tf_Neue_LagerOrt = new JTextField();
		tf_Neue_LagerOrt.setBounds(520, 93, 150, 30);
		tf_Neue_LagerOrt.setFont(new Font("Arial", Font.PLAIN, 12));
		pWareneingang.add(tf_Neue_LagerOrt);
		tf_Neue_LagerOrt.setColumns(10);

		comboBox = new JComboBox<Object>(items);
		comboBox.setBounds(150, 114, 125, 30);
		// comboBox.setEditable(true);
		pWareneingang.add(comboBox);

		btn_Speichern = new JButton("Speichern");
		btn_Speichern.setBounds(150, 173, 100, 30);
		btn_Speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tf_Checken(tf_BarCode)) {
						JOptionPane.showMessageDialog(tf_BarCode, "Geben Sie ihren BarCode ein. ");
					} else if (tf_Checken(tf_NEBezeichnung)) {
						JOptionPane.showMessageDialog(tf_NEBezeichnung, "Geben Sie ihren NE Bezeichnung ein. ");
					} 
					else if(comboBox.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(comboBox, "Geben Sie ihren Lagerort ein. ");
					}else{
					wareneingang_actionPerformed(e);
					//clearTable(WareneingangTable);
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_Speichern.setFont(new Font("Arial", Font.BOLD, 12));
		pWareneingang.add(btn_Speichern);

		lbl_Neue_LagerOrt = new JLabel("Dynamischer LagerOrt");
		lbl_Neue_LagerOrt.setBounds(520, 52, 150, 30);
		lbl_Neue_LagerOrt.setFont(new Font("Arial", Font.BOLD, 12));
		pWareneingang.add(lbl_Neue_LagerOrt);

		btn_Bearbeiten = new JButton("Bearbeiten");
		btn_Bearbeiten.setBounds(260, 174, 100, 30);
		btn_Bearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateWaren(tf_BarCode.getText());
					DefaultTableModel model = (DefaultTableModel) WareneingangTable.getModel();
					model.setRowCount(0);
					ShowWarenEingangsList();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		pWareneingang.add(btn_Bearbeiten);

		btnLagerOrtHinzufügen = new JButton("LagerOrt hinzuf\u00FCgen");
		btnLagerOrtHinzufügen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_Neue_LagerOrt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(tf_Neue_LagerOrt, "Geben Sie ihren LagerOrt ein. ");
				} else {
					LagerOrtHinzufuegen();
					tfClear(tf_Neue_LagerOrt);
				}
			}
		});
		btnLagerOrtHinzufügen.setBounds(520, 134, 150, 30);
		pWareneingang.add(btnLagerOrtHinzufügen);

		btnLagerOrtEntfernen = new JButton("LagerOrt entfernen");
		btnLagerOrtEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_Neue_LagerOrt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(tf_Neue_LagerOrt, "Geben Sie ihren LagerOrt ein. ");
				} else {
					LagerOrtEntfernen();
					tfClear(tf_Neue_LagerOrt);
				}
			}
		});
		btnLagerOrtEntfernen.setBounds(520, 174, 150, 30);
		pWareneingang.add(btnLagerOrtEntfernen);

		pWarenausgang = new JPanel();
		pWarenausgang.setBounds(700, 11, 640, 359);
		pWarenausgang.setToolTipText("Warenausgang");
		pWarenausgang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "WarenSuche",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pWarenausgang);
		pWarenausgang.setLayout(null);

		lbl_BarCode_Ausgang = new JLabel("BarCode");
		lbl_BarCode_Ausgang.setBounds(22, 276, 110, 30);
		lbl_BarCode_Ausgang.setFont(new Font("Arial", Font.BOLD, 12));
		pWarenausgang.add(lbl_BarCode_Ausgang);

		tf_BarCode_Ausgang = new JTextField();
		tf_BarCode_Ausgang.setBounds(211, 277, 200, 30);
		tf_BarCode_Ausgang.setFont(new Font("Arial", Font.PLAIN, 12));
		pWarenausgang.add(tf_BarCode_Ausgang);
		tf_BarCode_Ausgang.setColumns(10);

		btn_Warenausgang = new JButton("Warenausgang");
		btn_Warenausgang.setBounds(211, 318, 200, 30);
		btn_Warenausgang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					warenausgang_actionPerformed(e);
					//clearTable(WarenausgangTable);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_Warenausgang.setFont(new Font("Arial", Font.BOLD, 12));
		pWarenausgang.add(btn_Warenausgang);

		lbl_Search = new JLabel("Suchen sie den BarCode oder die NE Bezeichnung");
		lbl_Search.setBounds(10, 57, 300, 30);
		pWarenausgang.add(lbl_Search);

		separator = new JSeparator();
		separator.setBounds(10, 263, 620, 2);
		pWarenausgang.add(separator);

		tf_Search = new JTextField();
		tf_Search.setBounds(346, 57, 185, 30);
		pWarenausgang.add(tf_Search);
		tf_Search.setColumns(10);

		btn_Search = new JButton("Suchen");
		btn_Search.setBounds(541, 57, 89, 30);
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((tf_Checken(tf_Search))) {
						JOptionPane.showMessageDialog(tf_Search,
								"Sie haben nichts eingegeben. Ihre Eingabe im TextFeld ist Null ");
					} else if (crud.suchVerfuegbarkeitWareneingang(tf_Search.getText()) && rdbtnBarCode.isSelected()) {
						searched_actionPerformed(e);
						tfClear(tf_Search);
					} else if (crud.suchVerfuegbarkeitWareneingang(tf_Search.getText())
							&& rdbtnNeBezeichnung.isSelected()) {
						searched_actionPerformed(e);
						tfClear(tf_Search);
					} else {
						JOptionPane.showMessageDialog(pWarenausgang, "Der BarCode oder NE Bezeichnung existiert nicht",
								"BarCode, NE Bezeichnung", 2);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		pWarenausgang.add(btn_Search);

		scrollPane_SearchBarCode = new JScrollPane();
		scrollPane_SearchBarCode.setBounds(10, 100, 620, 127);
		scrollPane_SearchBarCode.setVisible(false);
		pWarenausgang.add(scrollPane_SearchBarCode);

		SearchBarCodeTable = new JTable();
		SearchBarCodeTable.setFont(new Font("Arial", Font.BOLD, 12));
		SearchBarCodeTable.setRowHeight(25);
		SearchBarCodeTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "BarCode", "NE Bezeichnung", "LagerOrt", "Eingangsdatum" }));
		scrollPane_SearchBarCode.setViewportView(SearchBarCodeTable);

		scrollPane_SearchNEBezeichnung = new JScrollPane();
		scrollPane_SearchNEBezeichnung.setBounds(10, 100, 620, 127);
		scrollPane_SearchNEBezeichnung.setVisible(false);
		pWarenausgang.add(scrollPane_SearchNEBezeichnung);

		SearchNEBezeichnungTable = new JTable();
		SearchNEBezeichnungTable.setFont(new Font("Arial", Font.BOLD, 12));
		SearchNEBezeichnungTable.setRowHeight(25);
		SearchNEBezeichnungTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "NE Bezeichnung", "LagerOrt", "Menge" }));
		scrollPane_SearchNEBezeichnung.setViewportView(SearchNEBezeichnungTable);

		btn_SuchePrint = new JButton("Print");
		btn_SuchePrint.setBounds(541, 229, 89, 30);
		btn_SuchePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rdbtnBarCode.isSelected()) {
						SearchBarCodeTable.print();
					}
					if (rdbtnNeBezeichnung.isSelected()) {
						SearchNEBezeichnungTable.print();
					}
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		pWarenausgang.add(btn_SuchePrint);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(447, 229, 89, 30);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnBarCode.isSelected()) {
					DefaultTableModel model = (DefaultTableModel) SearchBarCodeTable.getModel();
					model.setRowCount(0);
				}
				if (rdbtnNeBezeichnung.isSelected()) {
					DefaultTableModel model = (DefaultTableModel) SearchNEBezeichnungTable.getModel();
					model.setRowCount(0);
				}
			}
		});
		pWarenausgang.add(btnClear);
		ButtonGroup bG = new ButtonGroup();
		rdbtnBarCode = new JRadioButton("BarCode");
		rdbtnBarCode.setBounds(6, 20, 80, 30);
		rdbtnBarCode.setSelected(true);
		bG.add(rdbtnBarCode);
		pWarenausgang.add(rdbtnBarCode);

		rdbtnNeBezeichnung = new JRadioButton("NE Bezeichnung");
		rdbtnNeBezeichnung.setBounds(112, 20, 130, 30);
		rdbtnBarCode.setSelected(false);
		bG.add(rdbtnNeBezeichnung);
		pWarenausgang.add(rdbtnNeBezeichnung);

		pWareneingangTabelle = new JPanel();
		pWareneingangTabelle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Wareneingang Tabelle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pWareneingangTabelle.setBounds(10, 268, 680, 450);
		contentPane.add(pWareneingangTabelle);
		pWareneingangTabelle.setLayout(null);

		scrollPane_EingangsTable = new JScrollPane();
		scrollPane_EingangsTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_EingangsTable.setBounds(10, 48, 660, 391);
		pWareneingangTabelle.add(scrollPane_EingangsTable);

		WareneingangTable = new JTable();
		WareneingangTable.setFont(new Font("Arial", Font.BOLD, 12));
		WareneingangTable.setRowHeight(25);
		WareneingangTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "BarCode", "NE Bezeichnung", "LagerOrt", "Eingangsdatum" }));
		scrollPane_EingangsTable.setViewportView(WareneingangTable);

		btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crud.aktualisireWarenEingang(WareneingangTable);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAktualisieren.setBounds(555, 11, 115, 30);
		pWareneingangTabelle.add(btnAktualisieren);
		// pWareneingangTabelle.add(WarenEingangsTabelle);
		pWarenausgangTabelle = new JPanel();
		pWarenausgangTabelle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Warenausgang Tabelle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pWarenausgangTabelle.setBounds(700, 381, 640, 337);
		contentPane.add(pWarenausgangTabelle);
		pWarenausgangTabelle.setLayout(null);

		scrollPane_AusgangsTable = new JScrollPane();
		scrollPane_AusgangsTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_AusgangsTable.setBounds(10, 44, 620, 282);
		pWarenausgangTabelle.add(scrollPane_AusgangsTable);

		WarenausgangTable = new JTable();
		WarenausgangTable.setFont(new Font("Arial", Font.BOLD, 12));
		WarenausgangTable.setRowHeight(25);
		WarenausgangTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "BarCode", "NE Bezeichnung", "LagerOrt", "Eingangsdatum", "Ausgangsdatum" }));
		scrollPane_AusgangsTable.setViewportView(WarenausgangTable);

		btn_AusPrint = new JButton("Print");
		btn_AusPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					WarenausgangTable.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_AusPrint.setBounds(541, 11, 89, 30);
		pWarenausgangTabelle.add(btn_AusPrint);
		
		JButton btnAusgangAktualisieren = new JButton("Aktualisieren");
		btnAusgangAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crud.aktualisireWarenAusgang(WarenausgangTable);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAusgangAktualisieren.setBounds(422, 11, 115, 30);
		pWarenausgangTabelle.add(btnAusgangAktualisieren);
		DatenJTableToTextField(WareneingangTable);
		// doppelteDatensatzpruefen(tf_Search.getText());
	}

	/** ------------------------------------------------- **/

	public JTextField getTf_Neue_LagerOrt() {
		return tf_Neue_LagerOrt;
	}

	public JButton getBtnLagerOrtHinzufügen() {
		return btnLagerOrtHinzufügen;
	}

	public JButton getBtnLagerOrtEntfernen() {
		return btnLagerOrtEntfernen;
	}

	/** ------------------------------------------------- **/
	/**
	 * Diese Methode zeigt alle WarenEingangsListe in der WareneingangTable
	 * Tabelle
	 * 
	 * @throws ClassNotFoundException
	 * 
	 * @throws SQLException
	 */

	public void ShowWarenEingangsList() throws ClassNotFoundException, SQLException {
		ArrayList<Wareneingang> List = crud.getWareneingang();
		DefaultTableModel model = (DefaultTableModel) WareneingangTable.getModel();
		Object[] row = new Object[4];
		for (int i = 0; i < List.size(); i++) {
			row[0] = List.get(i).getBarCode();
			row[1] = List.get(i).getNe_Bezeichnung();
			row[2] = List.get(i).getLagerOrt();
			row[3] = List.get(i).getEingangsdatum();
			model.addRow(row);
		}
		
		
	}

	/** ------------------------------------------------- **/
	/**
	 * Diese Methode zeigt alle WarenAusgangsListe in der WarenausgangTable
	 * Tabelle
	 * 
	 * @throws ClassNotFoundException
	 * 
	 * @throws SQLException
	 */

	public void ShowWarenAusgangsList() throws ClassNotFoundException, SQLException {
		ArrayList<Warenausgang> List = crud.getWarenausgang();
		DefaultTableModel model = (DefaultTableModel) WarenausgangTable.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < List.size(); i++) {
			row[0] = List.get(i).getBarCode();
			row[1] = List.get(i).getNe_Bezeichnung();
			row[2] = List.get(i).getLagerOrt();
			row[3] = List.get(i).getEingangsdatum();
			row[4] = List.get(i).getAusgangsdatum();
			model.addRow(row);
		}
	}

	/** ------------------------------------------------- **/
	public void searchBarCode(String barcode) throws ClassNotFoundException, SQLException {
		Wareneingang search = crud.dbSearch(barcode);
		DefaultTableModel model = (DefaultTableModel) SearchBarCodeTable.getModel();
		Object[] row = new Object[4];
		row[0] = search.getBarCode();
		row[1] = search.getNe_Bezeichnung();
		row[2] = search.getLagerOrt();
		row[3] = search.getEingangsdatum();
		if (doppelteDatensatzpruefen(barcode) == false) {
			model.addRow(row);
		} else {
			JOptionPane.showMessageDialog(SearchBarCodeTable, "Der Datensatz ist schon vorhanden. ");
		}

	}

	/** ------------------------------------------------- **/
	public void searchNe_Bezeichnung(String nebezeichnung) throws ClassNotFoundException, SQLException {
		Wareneingang search = crud.searchNeBezeichung(nebezeichnung);
		DefaultTableModel model = (DefaultTableModel) SearchNEBezeichnungTable.getModel();
		Object[] row = new Object[4];
		row[0] = search.getNe_Bezeichnung();
		row[1] = search.getLagerOrt();
		row[2] = search.getMenge();
		if (doppelteDatensatzpruefen(nebezeichnung) == false) {
			model.addRow(row);
		} else {
			JOptionPane.showMessageDialog(SearchNEBezeichnungTable, "Der Datensatz ist schon vorhanden. ");
		}

	}

	/** ------------------------------------------------- **/
	public void wareneingang_actionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
		InsertEingangsWaren();
		DefaultTableModel model = (DefaultTableModel) WareneingangTable.getModel();
		model.setRowCount(0);
		ShowWarenEingangsList();
		
		//crud.aktualisireWarenEingang(WareneingangTable);
	}

	/** ------------------------------------------------- **/
	public void searched_actionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {

		if (rdbtnBarCode.isSelected()) {
			pWarenausgang.setVisible(true);
			rdbtnNeBezeichnung.setSelected(false);
			scrollPane_SearchBarCode.setVisible(true);
			scrollPane_SearchNEBezeichnung.setVisible(false);
			SearchBarCodeTable.setVisible(true);
			searchBarCode(tf_Search.getText());
		} else {
			pWarenausgang.setVisible(true);
			rdbtnBarCode.setSelected(false);
			scrollPane_SearchBarCode.setVisible(false);
			scrollPane_SearchNEBezeichnung.setVisible(true);
			SearchBarCodeTable.setVisible(false);
			SearchNEBezeichnungTable.setVisible(true);
			searchNe_Bezeichnung(tf_Search.getText());
		}

	}

	/** ------------------------------------------------- **/
	public void InsertEingangsWaren() throws ClassNotFoundException, SQLException {
		String insertQuery = "INSERT INTO wareneingang(barCode,ne_Bezeichnung, lagerOrt, eingangsdatum) values(?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = db.getPreparedStatement(insertQuery);
			ps.setString(1, tf_BarCode.getText());
			ps.setString(2, tf_NEBezeichnung.getText());
			ps.setString(3, (String) comboBox.getSelectedItem());
			ps.setString(4, crud.dateFormat());
			ps.executeUpdate();
			ps.close();
			db.getConnection().close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(tf_BarCode, "Der BarCode ist schon vergeben", "BarCode", 0);
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/** ------------------------------------------------- **/
	public void tfClear(JTextField tf) {
		tf.setText("");
	}

	/** ------------------------------------------------- **/
	public boolean tf_Checken(JTextField tf) {
		boolean checken;
		if (tf.getText().equals("")) {
			checken = true;
		} else {
			checken = false;
		}
		return checken;
	}

	/** ------------------------------------------------- **/
	public void clearTable(JTable table) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < model.getRowCount() - 1; i++) {
			model.removeRow(i);
		}
		table.setModel(model);
	}

	/** ------------------------------------------------- **/
	public void InsertAusgangsWaren(String barCode) throws ClassNotFoundException, SQLException {
		Statement st1 = null,st2 = null;
		ResultSet rs = null;

		String selectQuery = " SELECT barCode, ne_Bezeichnung, LagerOrt,Eingangsdatum FROM wareneingang WHERE barCode='"
				+ barCode + "'";
		try {
			st1 = (Statement) db.getConnection().createStatement();
			rs = st1.executeQuery(selectQuery);
			while (rs.next()) {
				String code = rs.getString("barCode");
				String bezeichnung = rs.getString("ne_Bezeichnung");
				String lagerOrt = rs.getString("LagerOrt");
				String eingangsdatum = rs.getString("Eingangsdatum");
				String insertQuery = "INSERT INTO warenausgang VALUES('" + code + "','" + bezeichnung + "', '"
						+ lagerOrt + "','" + eingangsdatum + "','" + crud.dateFormat() + "')";
				st2 = (Statement) db.getConnection().createStatement();
				st2.executeUpdate(insertQuery);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(tf_BarCode_Ausgang, "Der BarCode ist schon vergeben", "BarCode_Ausgang", 0);
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			rs.close();
			st1.close();
			st2.close();
			db.getConnection().close();
		}
	}

	/** ------------------------------------------------- **/
	public void warenausgang_actionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {

		if (tf_Checken(tf_BarCode_Ausgang)) {
			JOptionPane.showMessageDialog(tf_BarCode_Ausgang, "Geben Sie ihren BarCode ein. ");
		} else {
			InsertAusgangsWaren(tf_BarCode_Ausgang.getText());
			crud.deleteWaren(tf_BarCode_Ausgang.getText());
			DefaultTableModel model = (DefaultTableModel) WarenausgangTable.getModel();
			model.setRowCount(0);
			ShowWarenAusgangsList();
			tfClear(tf_BarCode_Ausgang);
		}
	}

	/** ------------------------------------------------- **/
	/**
	 * Diese Methode liest die zeile aus der Table und fügt in der TextFelde, um
	 * die Daten wiederzubearbeiten
	 * 
	 * @param table
	 */
	public void DatenJTableToTextField(JTable table) {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					int rowindex = table.getSelectedRow();
					Object[] daten = new Object[4];
					for (int i = 0; i < 4; i++) {
						daten[i] = table.getValueAt(rowindex, i);
					}
					tf_BarCode.setText(daten[0].toString());
					tf_NEBezeichnung.setText(daten[1].toString());
					comboBox.setSelectedItem(daten[2].toString());
				}
			}
		});
	}

	/** ------------------------------------------------- **/
	public void updateWaren(String barCode) throws ClassNotFoundException, SQLException {
		try {
			String updateQuery = "UPDATE wareneingang SET barCode=?, ne_Bezeichnung=?, LagerOrt=? WHERE barCode ='"
					+ barCode + "';";
			PreparedStatement pstmt = db.getPreparedStatement(updateQuery);
			pstmt.setString(1, tf_BarCode.getText());
			pstmt.setString(2, tf_NEBezeichnung.getText());
			pstmt.setString(3, (String) comboBox.getSelectedItem());
			pstmt.executeUpdate();
			pstmt.close();
			db.getConnection().close();
			
		} catch (Exception ex) {
			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/** ------------------------------------------------- **/
	public void LagerOrtHinzufuegen() {
		if (comboBox.getSelectedItem().equals(tf_Neue_LagerOrt.getText()) == false) {
			comboBox.addItem(tf_Neue_LagerOrt.getText());
		} else {
			JOptionPane.showMessageDialog(tf_Neue_LagerOrt, "Das LagerOrt ist schon vorhanden. ");
		}
	}

	/** ------------------------------------------------- **/
	public void LagerOrtEntfernen() {
		comboBox.removeItem(tf_Neue_LagerOrt.getText());
	}

	/** ------------------------------------------------- **/

	/**
	 * @param datensatz
	 */
	public boolean doppelteDatensatzpruefen(String datensatz) {

		String gesuchteDatensatz;
		boolean gefunden = false;
		if (rdbtnBarCode.isSelected()) {
			for (int i = 0; i < SearchBarCodeTable.getRowCount(); i++) {

				gesuchteDatensatz = SearchBarCodeTable.getValueAt(i, 0).toString();
				if ((gesuchteDatensatz.equals(datensatz)) && datensatz != "") {
					gefunden = true;
					// System.out.println(gesuchteDatensatz);
				} else {
					gefunden = false;
				}
			}
		}else if (rdbtnNeBezeichnung.isSelected()) {
			for (int i = 0; i < SearchNEBezeichnungTable.getRowCount(); i++) {

				gesuchteDatensatz = SearchNEBezeichnungTable.getValueAt(i, 0).toString();
				if ((gesuchteDatensatz.equals(datensatz)) && datensatz != "") {
					gefunden = true;
					// System.out.println(gesuchteDatensatz);
				} else {
					gefunden = false;
				}
			}
		}else{
			for (int i = 0; i <WarenausgangTable.getRowCount(); i++) {

				gesuchteDatensatz = WarenausgangTable.getValueAt(i, 0).toString();
				if ((gesuchteDatensatz.equals(datensatz)) && datensatz != "") {
					gefunden = true;
					// System.out.println(gesuchteDatensatz);
				} else {
					gefunden = false;
				}
			}
			
		}

		return gefunden;
	}
	
	

	
	
	
}