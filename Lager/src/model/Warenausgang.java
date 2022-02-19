package model;

public class Warenausgang {
	    private String barCode;
		private String ne_Bezeichnung;
		private String lagerOrt;
		private String eingangsdatum;
		private String ausgangsdatum;
		public Warenausgang() {

		}

		public Warenausgang(String barCode, String ne_Bezeichnung, String lagerOrt, String eingangsdatum, String ausgangsdatum) {
			this.barCode = barCode;
			this.ne_Bezeichnung = ne_Bezeichnung;
			this.lagerOrt = lagerOrt;
			this.eingangsdatum = eingangsdatum;
			this.ausgangsdatum = ausgangsdatum;
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
		
		public String getAusgangsdatum() {
			return ausgangsdatum;
		}

		public void setAusgangsdatum(String ausgangsdatum) {
			this.ausgangsdatum = ausgangsdatum;
		}
		
		@Override
		public String toString() {
			return "Lager [barCode= " + barCode + ", ne_Bezeichnung= " + ne_Bezeichnung 
					+ ", lagerOrt= " + lagerOrt + ", Eingangsdatum= " + eingangsdatum + ", Ausgangsdatum= " + ausgangsdatum + "]";
		}

	}

