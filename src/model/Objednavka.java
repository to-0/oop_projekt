package model;

import java.io.Serializable;
import java.util.ArrayList;

import pouzivatelia.Klient;
import pouzivatelia.Pouzivatel;
import tovar.Tovar;

/**
 * Trieda objednávka, má informácie o objednávke a poskytuje metódy na prácu s objednávkou.
 */
public class Objednavka implements Serializable{
	/**
	 * Id objednávky
	 */
	private int id;
	/**
	 * Zoznam tovarov v objednávke
	 */
	public ArrayList<Tovar> tovar = new ArrayList<Tovar>();
	/**
	 * Celková suma ojednávky.
	 */
	double suma;
	/**
	 * Vybavenost tovaru
	 */
	private boolean vybavena;
	/**
	 * Pripravenost objednávky na odoslanie
	 */
	private boolean pripravena; //je pripravena na odoslanie skladnikom? t.j. ci je vyrobeny vsetok tovar co ma
	public boolean get_stav(){
		return this.vybavena;
	}
	public void vybav(){
		this.vybavena = true;
	}

	public void priprav_odoslanie(){
		this.pripravena = true;
	}
	public boolean getPripravenost(){
		return this.pripravena;
	}
	Klient klient;
	ManazerObjednavok pozorovatel;
	public Objednavka(int id, ArrayList<Tovar> t, double suma,Klient k) {
		this.id = id;
		this.tovar = t;
		this.suma = suma;
		this.vybavena = false;
		this.pripravena = false;
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

	/**
	 * Upozorni manazera objednavok na zmenu objednavky.
	 */
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

	/**
	 * Vráti string, ktorý obsahuje základné informácie o objednávke.
	 * @return
	 */
	public String toString(){
		return "Id: "+ String.valueOf(this.id) + " suma: "+String.valueOf(this.suma) + " mnozstvo poloziek: "+String.valueOf(this.tovar.size());
	}
	public String detail(){
		return null;
	}
	public Klient getKlient(){
		return this.klient;
	}

	/**
	 * Vypočíta celkovú sumu objednávky.
	 * @return celková suma objednávky.
	 */
	private double vypocitaj_cel_sumu(){
		double sum= 0;
		for(Tovar t: this.tovar){
			sum += t.vypocitaj_cenu();
		}
		return sum;
	}
}
