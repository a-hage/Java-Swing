package model;

public class Wareneingang {
	private String barCode;
	private String ne_Bezeichnung;
	private String lagerOrt;
	private String eingangsdatum;
	private int menge;

	public Wareneingang() {

	}

	public Wareneingang(String barCode, String ne_Bezeichnung, String lagerOrt, String eingangsdatum) {
		this.barCode = barCode;
		this.ne_Bezeichnung = ne_Bezeichnung;
		this.lagerOrt = lagerOrt;
		this.eingangsdatum = eingangsdatum;
		
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

	public String getLagerOrt() {
		return lagerOrt;
	}

	public void setLagerOrt(String lagerOrt) {
		this.lagerOrt = lagerOrt;
	}

	public String getEingangsdatum() {
		return eingangsdatum;
	}

	public void setEingangsdatum(String eingangsdatum) {
		this.eingangsdatum = eingangsdatum;
	}
	
	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	@Override
	public String toString() {
		return "Lager [barCode= " + barCode + ", ne_Bezeichnung= " + ne_Bezeichnung 
				+ ", lagerOrt= " + lagerOrt + ", Eingangsdatum= " + eingangsdatum + "]";
	}
	
	public String toMenge() {
		return "Lager [ne_Bezeichnung= " + ne_Bezeichnung + ", lagerOrt= " + lagerOrt + ", Menge= " + menge + "]";
	}
	
}
