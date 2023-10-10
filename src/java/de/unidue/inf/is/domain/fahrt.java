package de.unidue.inf.is.domain;

import java.sql.Timestamp;

    public class fahrt {
        
	private int fid;
	private String startort;
	private String zielort;
	private int maxPlaetze;
	private double fahrtkosten;
	private String status;
	private String transportmittel;
	private String beschreibung;
        private String fahrtuhrzeit;
        private String fahrtdatum;
        private int freePlaces;
	
	
	public fahrt() {
		
	}
        public fahrt(String Fahrtdatumzeit,String startort,String zielort) {
		this.fahrtdatum=Fahrtdatumzeit;
                this.startort=startort;
                this.zielort=zielort;
	}
	
	public fahrt (int fid,int maxPlaetze,String Beschreibung,int Fahrtkosten,String Startort,String Zielort,String Fahrtdatum,String Fahrtuhrzeit,String status,String Transportmittel) {
		this.fid = fid;
		this.maxPlaetze = maxPlaetze;
		this.beschreibung = Beschreibung;
		this.fahrtkosten = Fahrtkosten;
		this.startort = Startort;
		this.zielort = Zielort;
                this.fahrtdatum=Fahrtdatum;
                this.fahrtuhrzeit=Fahrtuhrzeit;
                this.status = status;
		this.transportmittel = Transportmittel;		
	}
	
	
	public int getFid() {
            return fid;
	}
	
	public void setFid(int fid) {
		this.fid = fid;
	}
        
        public int getFreePlaces(){
            return freePlaces;
        }
        
        public void setFreePlaces(int free) {
		this.freePlaces = free;
	}
	
	public String getStartort() {
		return startort;
	}
	
	public void setStartort(String startort) {
		this.startort = startort;
	}
	
	public String getZielort() {
		return zielort;
	}
	
	public void setZielort(String zielort) {
		this.zielort = zielort;
	}
	
	public String getFahrtdatum() {
		return fahrtdatum;
	}
	
	public void setFahrtdatum(String fahrtdatumzeit) {
		this.fahrtdatum = fahrtdatumzeit;
	}
        
        public String getFahrtuhrzeit() {
		return fahrtuhrzeit;
	}
	
	public void setFahrtuhrzeit(String fahrtdatumzeit) {
		this.fahrtuhrzeit = fahrtdatumzeit;
	}
	
	public int getMaxPlaetze() {
		return maxPlaetze;
	}
	
	public void setMaxplaetze(int maxplaetze) {
		this.maxPlaetze = maxplaetze;
	}
	
	public double getFahrtkosten() {
		return fahrtkosten;
	}
	
	public void setFahrtkosten(double fahrtkosten) {
		this.fahrtkosten = fahrtkosten;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getTransportmittel() {
		return transportmittel;
	}
	
	public void setTransportmittel(String transportmittel) {
		this.transportmittel = transportmittel;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

}
