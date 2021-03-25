package model;

import java.io.Serializable;
import java.util.ArrayList;

import pouzivatelia.Klient;
import pouzivatelia.Pouzivatel;
import tovar.Tovar;

public class Objednavka implements Serializable{
	private int id;
	public ArrayList<Tovar> tovar = new ArrayList<Tovar>();
	double suma;
	boolean vybavena;
	Klient klient;
	ManazerObjednavok pozorovatel;
	public Objednavka(int id, ArrayList<Tovar> t, double suma,Klient k) {
		this.id = id;
		this.tovar = t;
		this.suma = suma;
		this.vybavena = false;
		this.klient = k;
		this.pozorovatel = ManazerObjednavok.getInstance();
	}
	public Objednavka(int id, ArrayList<Tovar> t,Klient k){
		this.id = id;
		this.tovar = t;
		this.suma = vypocitaj_cel_sumu();
		this.vybavena = false;
		this.klient = k;
		this.pozorovatel = ManazerObjednavok.getInstance();
	}
	public int getId() {
		return id;
	}
	public void upozorni_pozorovatela(){
		this.pozorovatel.upozorni(this);
	}
	public int get_Klient_id() {
		return klient.getId();
	}
	public String toList(){
		String s="";
		for(Tovar t: this.tovar){
			s += t.toString();
		}
		return s;
	}
	public String toString(){
		return "Id: "+ String.valueOf(this.id) + " suma: "+String.valueOf(this.suma) + " mnozstvo poloziek: "+String.valueOf(this.tovar.size());
	}
	public String detail(){
		return null;
	}
	public Klient getKlient(){
		return this.klient;
	}
	private double vypocitaj_cel_sumu(){
		double sum= 0;
		for(Tovar t: this.tovar){
			sum += t.vypocitaj_cenu();
		}
		return sum;
	}
}
