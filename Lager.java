package lager;

public class Lager {
	private String barCode;
	private String ne_Bezeichnung;
	private double gewicht;
	private String zustand;
	private String lagerOrt;
	
	public Lager(String barCode, String ne_Bezeichnung, double gewicht, String zustand, String lagerOrt) {
		this.barCode = barCode;
		this.ne_Bezeichnung = ne_Bezeichnung;
		this.gewicht = gewicht;
		this.zustand = zustand;
		this.lagerOrt = lagerOrt;
	}

	public String getBarCode() {
		return barCode;
	}
	
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	public String getNe_Bezeichnung() {
		return ne_Bezeichnung;
	}
	
	public void setNe_Bezeichnung(String ne_Bezeichnung) {
		this.ne_Bezeichnung = ne_Bezeichnung;
	}
	
	public double getGewicht() {
		return gewicht;
	}
	
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
	
	public String getZustand() {
		return zustand;
	}
	
	public void setZustand(String zustand) {
		this.zustand = zustand;
	}
	
	public String getLagerOrt() {
		return lagerOrt;
	}
	
	public void setLagerOrt(String lagerOrt) {
		this.lagerOrt = lagerOrt;
	}
	
	@Override
	public String toString() {
		return "Lager [barCode= " + barCode + ", ne_Bezeichnung= " + ne_Bezeichnung + 
		       ", gewicht= " + gewicht + ", zustand= " + zustand + ", lagerOrt= " + lagerOrt + "]";
	}
	

}
