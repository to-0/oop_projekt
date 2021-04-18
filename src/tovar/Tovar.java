package tovar;

import java.io.Serializable;

import pouzivatelia.Pouzivatel;

/**
 * Abstraktná trieda definujúca základné atribúty pre tovar.
 */
public abstract class Tovar implements Serializable{
	/**
	 * Cena za kus
	 */
	double cena_kus;
	/**
	 * Množstvo tovaru.
	 */
	protected int mnozstvo;
	String nazov;
	//Pouzivatel pracovnik_vyroby;
	/**
	 * Stav či je tovar vyrobený
	 */
	protected boolean vyrobeny;
	/**
	 * Typ daného tovaru (zošit typu 1,2,3 atd.
	 */
	int typ;
	public boolean getStav(){
		return this.vyrobeny;
	}
	public abstract double vypocitaj_spotrebu_pap(); //kolko listov papiera 
	public Tovar(int mnozstvo,int typ) {
		this.mnozstvo = mnozstvo;
		this.typ = typ;
		switch(this.typ){
			case  1:
				this.cena_kus = 1;
				break;
			case 2:
				this.cena_kus = 0.5;
				break;
			case 3:
				this.cena_kus = 0.7;
				break;
			default:
				this.cena_kus = 0.8;
		}
	}
	public void vybav() {
		this.vyrobeny = true;
	}
	public double vypocitaj_cenu(){
		return this.cena_kus*this.mnozstvo;
	}
	public int getMnozstvo(){
		return this.mnozstvo;
	}
}
