package model;

import java.io.Serializable;

/**
 * Trieda Adresa sluzi iba na ulozenie adresy klienta.
 */
public class Adresa implements Serializable{
	/**
	 * Ulica
	 */
	private String ulica;
	/**
	 * Obec
	 */
	private String obec;
	/**
	 * číslo domu.
	 */
	private String cislo;
	
	
	public Adresa(String ulica,String obec,String cislo) {
		this.ulica = ulica;
		this.obec = obec;
		this.cislo = cislo;
	}

	public String getUlica() {
		return ulica;
	}

	/**
	 * Vytvorí string z adresy.
	 * @return
	 */
	public String toString() {
		return this.ulica+" "+this.cislo+" "+this.obec;
	}

	public String getObec() {
		return obec;
	}

	
}
