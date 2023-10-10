package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class fahrerlaubnis {
	private int fahrer;
	private int nummer;
	private Timestamp ablaufdatum;
	
	public fahrerlaubnis (int fahrer, int nummer, Timestamp ablaufdatum) {
		this.fahrer = fahrer;
		this.nummer = nummer;
		this.ablaufdatum = ablaufdatum;
	}
	
	public int getFahrer() {
		return fahrer;
	}
	
	public void setFahrer(int fahrer) {
		this.fahrer = fahrer;
	}
	
	public int getNummer() {
		return nummer;
	}
	
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public Timestamp getAblaufdatum() {
		return ablaufdatum;
	}
	
	public void setAblaufdatum(Timestamp ablaufdatum) {
		this.ablaufdatum= ablaufdatum;
	}

}
