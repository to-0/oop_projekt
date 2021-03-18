package model;

import java.io.Serializable;

public class Adresa implements Serializable{
	private String ulica;
	private String obec;
	private String smerove_cislo;
	
	
	public Adresa(String ulica,String obec,String cislo) {
		this.ulica = ulica;
		this.obec = obec;
		this.smerove_cislo = cislo;
	}

	public String getUlica() {
		return ulica;
	}
	public String toString() {
		return this.ulica+" "+this.obec+" "+this.smerove_cislo;
	}

	public String getObec() {
		return obec;
	}

	
}
