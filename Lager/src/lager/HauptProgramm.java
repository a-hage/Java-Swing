package lager;

import java.awt.EventQueue;

import utility.CRUD;
import views.Login;

public class HauptProgramm {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					CRUD crud=new CRUD();
					//System.out.println(crud.searchNeBezeichung("CXU-10").toMenge());
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
