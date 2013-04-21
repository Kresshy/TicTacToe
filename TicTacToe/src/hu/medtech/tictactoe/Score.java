package hu.medtech.tictactoe;

public class Score {

	private String nev;
	private String ido;
	private String pont;

	public Score(String aNev, String aIdo, String aPont) {
		nev = aNev;
		ido = aIdo;
		pont = aPont;
	}

	public String getNev() {
		return nev;
	}

	public String getIdo() {
		return ido;
	}

	public String getPont() {
		return pont;
	}
}
