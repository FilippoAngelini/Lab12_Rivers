package it.polito.tdp.rivers.model;

public class Risultato {
	
	private int numGiorniInefficienza;
	private float cMedia;
	public Risultato(int numGiorniInefficienza, float cMedia) {
		super();
		this.numGiorniInefficienza = numGiorniInefficienza;
		this.cMedia = cMedia;
	}
	public int getNumGiorniInefficienza() {
		return numGiorniInefficienza;
	}
	public void setNumGiorniInefficienza(int numGiorniInefficienza) {
		this.numGiorniInefficienza = numGiorniInefficienza;
	}
	public float getcMedia() {
		return cMedia;
	}
	public void setcMedia(float cMedia) {
		this.cMedia = cMedia;
	}
	@Override
	public String toString() {
		return "Risultato: \nNumero di giorni di inefficienza= " + numGiorniInefficienza + "\nCapacità media nel periodo= " + cMedia;
	}
	
	

}
