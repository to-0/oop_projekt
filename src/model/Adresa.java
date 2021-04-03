package model;

import java.io.Serializable;
//trieda Adresa sluzi iba na ulozenie adresy klienta
public class Adresa implements Serializable{
	private String ulica;
	private String obec;
	private String cislo;
	
	
	public Adresa(String ulica,String obec,String cislo) {
		this.ulica = ulica;
		this.obec = obec;
		this.cislo = cislo;
	}

	public String getUlica() {
		return ulica;
	}
	//toString na vypis adresy
	public String toString() {
		return this.ulica+" "+this.cislo+" "+this.obec;
	}

	public String getObec() {
		return obec;
	}

	
}
