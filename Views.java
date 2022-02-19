package lager;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Views extends JFrame {

	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;
	CRUD crud = new CRUD();
	
	
	
	
	/* Panel Instanzvariablen */
	private JPanel contentPane;
	private JPanel pWareneingang;
	private JPanel pWarenausgang;

	/* Labels Instanzvariablen */
	private JLabel lbl_BarCode;
	private JLabel lbl_NE_Bezeichnung;
	private JLabel lbl_Gewicht;
	private JLabel lbl_Zustand;
	private JLabel lbl_LagerOrt;
	private JTextField tf_BarCode;
	private JTextField tf_NEBezeichnung;
	private JTextField tf_Gewicht;
	private JTextField tf_Zustand;
	private JTextField tf_LagerOrt;
	private JButton btn_Wareneingang;
	private JLabel lbl_BarCode_Ausgang;
	private JLabel lbl_Menge;
	private JButton btn_Warenausgang;
	private JTextField tf_BarCode_Ausgang;
	private JTextField tf_Menge;
	private JPanel pWareneingangTabelle;
	private JPanel pWarenausgangTabelle;
	private JScrollPane scrollPane;
	public JTable WareneingangTable;

	/**
	 * Launch the application.
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Views view = new Views();
					view.setVisible(true);
					// frame.setTitle("Lager zum NE");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */

	@SuppressWarnings("serial")
	public Views() throws SQLException, ClassNotFoundException {
		//crud.ShowLagerList();
		//new DB_Connection();

		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Lager Netzelement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pWareneingang = new JPanel();
		pWareneingang.setBounds(10, 11, 752, 337);
		pWareneingang.setToolTipText("Wareneingang");
		pWareneingang
				.setBorder(new TitledBorder(null, "Wareneingang", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pWareneingang);
		pWareneingang.setLayout(null);

		lbl_BarCode = new JLabel("BarCode");
		lbl_BarCode.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_BarCode.setBounds(70, 36, 110, 30);
		pWareneingang.add(lbl_BarCode);

		lbl_NE_Bezeichnung = new JLabel("NE Bezeichnung");
		lbl_NE_Bezeichnung.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_NE_Bezeichnung.setBounds(70, 77, 110, 30);
		pWareneingang.add(lbl_NE_Bezeichnung);

		lbl_Gewicht = new JLabel("Gewicht");
		lbl_Gewicht.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_Gewicht.setBounds(70, 118, 110, 30);
		pWareneingang.add(lbl_Gewicht);

		lbl_Zustand = new JLabel("Zustand");
		lbl_Zustand.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_Zustand.setBounds(70, 159, 110, 30);
		pWareneingang.add(lbl_Zustand);

		lbl_LagerOrt = new JLabel("LagerOrt");
		lbl_LagerOrt.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_LagerOrt.setBounds(70, 200, 110, 30);
		pWareneingang.add(lbl_LagerOrt);

		tf_BarCode = new JTextField();
		tf_BarCode.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_BarCode.setBounds(240, 36, 240, 30);
		pWareneingang.add(tf_BarCode);
		tf_BarCode.setColumns(10);

		tf_NEBezeichnung = new JTextField();
		tf_NEBezeichnung.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_NEBezeichnung.setBounds(240, 77, 240, 30);
		pWareneingang.add(tf_NEBezeichnung);
		tf_NEBezeichnung.setColumns(10);

		tf_Gewicht = new JTextField();
		tf_Gewicht.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_Gewicht.setBounds(240, 118, 240, 30);
		pWareneingang.add(tf_Gewicht);
		tf_Gewicht.setColumns(10);

		tf_Zustand = new JTextField();
		tf_Zustand.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_Zustand.setBounds(240, 159, 240, 30);
		pWareneingang.add(tf_Zustand);
		tf_Zustand.setColumns(10);

		tf_LagerOrt = new JTextField();
		tf_LagerOrt.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_LagerOrt.setBounds(240, 200, 240, 30);
		pWareneingang.add(tf_LagerOrt);
		tf_LagerOrt.setColumns(10);

		btn_Wareneingang = new JButton("Wareneingang");
		btn_Wareneingang.setFont(new Font("Arial", Font.BOLD, 12));
		btn_Wareneingang.setBounds(240, 264, 240, 30);
		pWareneingang.add(btn_Wareneingang);

		pWarenausgang = new JPanel();
		pWarenausgang.setBounds(772, 11, 568, 337);
		pWarenausgang.setToolTipText("Warenausgang");
		pWarenausgang
				.setBorder(new TitledBorder(null, "Warenausgang", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pWarenausgang);
		pWarenausgang.setLayout(null);

		lbl_BarCode_Ausgang = new JLabel("BarCode");
		lbl_BarCode_Ausgang.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_BarCode_Ausgang.setBounds(70, 49, 110, 30);
		pWarenausgang.add(lbl_BarCode_Ausgang);

		lbl_Menge = new JLabel("Menge");
		lbl_Menge.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_Menge.setBounds(70, 97, 110, 30);
		pWarenausgang.add(lbl_Menge);

		tf_BarCode_Ausgang = new JTextField();
		tf_BarCode_Ausgang.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_BarCode_Ausgang.setBounds(240, 49, 200, 30);
		pWarenausgang.add(tf_BarCode_Ausgang);
		tf_BarCode_Ausgang.setColumns(10);

		tf_Menge = new JTextField();
		tf_Menge.setFont(new Font("Arial", Font.PLAIN, 12));
		tf_Menge.setBounds(240, 97, 200, 30);
		pWarenausgang.add(tf_Menge);
		tf_Menge.setColumns(10);

		btn_Warenausgang = new JButton("Warenausgang");
		btn_Warenausgang.setFont(new Font("Arial", Font.BOLD, 12));
		btn_Warenausgang.setBounds(240, 175, 200, 30);
		pWarenausgang.add(btn_Warenausgang);

		pWareneingangTabelle = new JPanel();
		pWareneingangTabelle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Wareneingang Tabelle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pWareneingangTabelle.setBounds(10, 359, 752, 359);

		contentPane.add(pWareneingangTabelle);
		pWareneingangTabelle.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 26, 732, 299);
		pWareneingangTabelle.add(scrollPane);

		WareneingangTable = new JTable();
		WareneingangTable.setFont(new Font("Arial", Font.BOLD, 12));
		WareneingangTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "BarCode", "NE Bezeichnung", "Gewicht", "Zustand", "LagerOrt" }));
		scrollPane.setViewportView(WareneingangTable);

		// pWareneingangTabelle.add(WarenEingangsTabelle);

		pWarenausgangTabelle = new JPanel();
		pWarenausgangTabelle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Warenausgang Tabelle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pWarenausgangTabelle.setBounds(772, 359, 568, 359);
		contentPane.add(pWarenausgangTabelle);
		pWarenausgangTabelle.setLayout(null);
	}

	/**
	 * Diese Methode zeigt alle Angestellte in der Angestellte_Table Tabelle
	 * 
	 * @throws SQLException
	 */
	public void ShowLagerList() throws ClassNotFoundException, SQLException {
		new DB_Connection();
		ArrayList<Lager> List = new CRUD().getWarenList();
		DefaultTableModel model = (DefaultTableModel)WareneingangTable.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < List.size(); i++) {
			row[0] = List.get(i).getBarCode();
			row[1] = List.get(i).getNe_Bezeichnung();
			row[2] = List.get(i).getGewicht();
			row[3] = List.get(i).getZustand();
			row[4] = List.get(i).getLagerOrt();

			model.addRow(row);
		}
	}

	
}
