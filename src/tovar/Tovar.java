package tovar;

import java.io.Serializable;

import pouzivatelia.Pouzivatel;

public abstract class Tovar implements Serializable{
	double cena_kus;
	int mnozstvo;
	String nazov;
	Pouzivatel pracovnik_vyroby;
	boolean vyrobeny;
	int typ;
	public abstract double vypocitaj_spotrebu_pap(); //kolko listov papiera 
	public Tovar(int mnozstvo,int typ) {
		this.mnozstvo = mnozstvo;
		this.typ = typ;
	}
	public void vybav() {
		this.vyrobeny = true;
	}
}
