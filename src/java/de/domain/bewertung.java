package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class bewertung {
	private int beid ;
	private String textnachricht;
	private Timestamp erstellungsdatum;
	private int rating;
	
	public bewertung(int beid, String textnachricht, Timestamp erstellungsdatum, int rating) {
		this.beid = beid;
		this.textnachricht = textnachricht;
		this.erstellungsdatum = erstellungsdatum;
		this.rating = rating;
	}
	
	public bewertung(String textnachricht, int rating) {
		this.textnachricht = textnachricht;
		this.rating = rating;
	}
	
	public int getBeid() {
		return beid;
	}
	
	public void setBeid(int beid) {
		this.beid = beid;
	}
	
	public String getTextnachricht() {
		return textnachricht;
	}
	
	public void setTextnachricht(String textnachricht) {
		this.textnachricht = textnachricht;
	}
	
	public Timestamp getErstellungsdatum() {
		return erstellungsdatum;
	}
	
	public void setErstellungsdatum(Timestamp erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	

}
