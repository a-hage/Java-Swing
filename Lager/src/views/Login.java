package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utility.DBConnection;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_Benutzername;
	private JLabel lbl_Passwort;
	public JTextField tf_Benutzername;
	public JPasswordField pf_Passwort;
	private JButton btnLogin;
	private JButton btnClose;

	DBConnection db = new DBConnection();

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Login frame = new Login();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */
	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 389, 253);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl_Benutzername = new JLabel("Benutzername");
		lbl_Benutzername.setBounds(10, 32, 100, 30);
		contentPane.add(lbl_Benutzername);

		lbl_Passwort = new JLabel("Passwort");
		lbl_Passwort.setBounds(10, 90, 100, 30);
		contentPane.add(lbl_Passwort);

		tf_Benutzername = new JTextField();
		tf_Benutzername.setBounds(120, 32, 154, 30);
		contentPane.add(tf_Benutzername);
		tf_Benutzername.setColumns(10);

		pf_Passwort = new JPasswordField();
		pf_Passwort.setBounds(120, 90, 154, 30);
		contentPane.add(pf_Passwort);

		btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btn_LoginPerformedAction(e);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(112, 159, 80, 30);
		contentPane.add(btnLogin);
		btnClose = new JButton("Exit");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setBounds(206, 159, 80, 30);
		contentPane.add(btnClose);
	}

	public void btn_LoginPerformedAction(ActionEvent e) throws ClassNotFoundException, SQLException {

		if (tf_Benutzername.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Ihr Benutzername leer. Bitte Geben sie Ihre Benutzername");
		} else if (pf_Passwort.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Ihr Passwort leer. Bitte Geben sie Ihre Passwort");
		} else {
			String name = tf_Benutzername.getText();
			String pass = String.valueOf(pf_Passwort.getPassword());
			if (login(name, pass)) {
				JOptionPane.showMessageDialog(null, "Login erfolgreich, Sie werden zum LagerProgramm weitergeleitet ");
				this.dispose();
				Views view = new Views();
				disableTextField((view).getTf_Neue_LagerOrt());
				disableButton(view.getBtnLagerOrtHinzufügen(),view.getBtnLagerOrtEntfernen());
				view.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Login fehlgeschlagen, Ihre Daten sind nicht korrekt");
			}
		}
	}

	public boolean login(String username, String password) throws SQLException, ClassNotFoundException {
		String selectQuery = "SELECT username, password FROM users WHERE username =? AND password =?";
		PreparedStatement ps = null;
		try {
			ps = db.getPreparedStatement(selectQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ps != null)
				ps.close();
			db.getConnection().close();
		}
	}

	/***
	 * Diese Methode setzt einen TextFeld auf Bearbeitbar, Falls der Benutzername während
	 * des Login "A.Svoboda" ist und auf nicht Bearbeitbar für die anderen Benutzernamen  
	 * 
	 * @param tf
	 */
	public void disableTextField(JTextField tf) {
		String name = tf_Benutzername.getText();
		//System.out.println("Test  " + name);
		if (name.equals(("a.svoboda").toUpperCase()) || name.equals(("A.Svoboda").toLowerCase())) {
			tf.setEditable(true);
		} else {
			tf.setEditable(false);
		}
	}
	
	public void disableButton(JButton btn1,JButton btn2) {
		String name = tf_Benutzername.getText();
		if (name.equals(("a.svoboda").toUpperCase()) || name.equals(("A.Svoboda").toLowerCase())){
			btn1.setEnabled(true);
			btn2.setEnabled(true);
		} else {
			btn1.setEnabled(false);
			btn2.setEnabled(false);
		}
	}
	

}
