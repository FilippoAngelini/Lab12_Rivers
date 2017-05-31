package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class InfoRiver {
	
	private LocalDate prima;
	private LocalDate ultima;
	int cont;
	float fmed;
	
	public InfoRiver(LocalDate prima, LocalDate ultima, int cont, float fmed) {
		this.prima = prima;
		this.ultima = ultima;
		this.cont = cont;
		this.fmed = fmed;
	}
	public LocalDate getPrima() {
		return prima;
	}
	public void setPrima(LocalDate prima) {
		this.prima = prima;
	}
	public LocalDate getUltima() {
		return ultima;
	}
	public void setUltima(LocalDate ultima) {
		this.ultima = ultima;
	}
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	public float getFmed() {
		return fmed;
	}
	public void setFmed(float fmed) {
		this.fmed = fmed;
	}
	
	

}
